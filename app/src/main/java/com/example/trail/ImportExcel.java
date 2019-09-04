package com.example.trail;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ImportExcel {



    public String[][] Import(Workbook workbook,String grade,String className){
        int gradeInt = 0;
        //获取年级课表
        if (workbook.getNumberOfSheets()==8) {
            for (int i = 0; i < 8; i++) {
                Sheet readsheet = workbook.getSheet(i);
                if (readsheet.getName().equals(grade)) {
                    gradeInt = i;
                    break;
                }
            }
        }
        else{
            grade=grade.substring(2);
            for (int i = 0; i < 4; i++) {
                Sheet readsheet = workbook.getSheet(i);
                if (readsheet.getName().equals(grade)) {
                    gradeInt = i;
                    break;
                }
            }
        }
        Sheet readSheet = workbook.getSheet(gradeInt);
        Range[] range = readSheet.getMergedCells();
        //获取sheet表中的总行数
        int rsRows = readSheet.getRows();
        //获取sheet表中的总列数
        int rsColumns = readSheet.getColumns();

        //获取班级编号所在行
        int whichLine = 0;
        //获取班级编号所在列
        int whichColumn = 0;
        boolean look=false;
        for (int i=0;i<rsRows;i++) {
            for (int j = 0; j < rsColumns; j++) {
                Cell cell = readSheet.getCell(j, i);
                String p = cell.getContents();
                if (p.contains(className)) {
                    whichColumn = j;
                    whichLine = i;
                    look = true;
                    break;
                }
            }
            if (look)
                break;
        }


//        for (int i = 0; i < rsRows; i++) {
//            Cell cell = readSheet.getCell(0, i);
//            if (cell.getContents().contains("专业班")) {
//                whichLine = i;
//                break;
//            }
//        }
//
//        for (int i = 0; i < rsColumns; i++) {
//            Cell cell = readSheet.getCell(i, whichLine);
//            String a=cell.getContents();
//            if (cell.getContents().contains(className)) {
//                whichColumn = i;
//                break;
//            }
//        }
        int endLine = 0;
        for (int i = whichLine; i < rsRows; i++) {
            Cell cell = readSheet.getCell(0, i);
            if (cell.getContents().contains("备注")) {
                endLine = i;
                break;
            }
        }

        //将星期几和节数导入
        int[] beginWeekday=new int[8];
        String[] weekdayNames={"星期一","星期二","星期三","星期四","星期五","星期六","星期天"};

        beginWeekday[7]=endLine;
        for (int i=whichLine;i<endLine;i++)
        {
            Cell cell=readSheet.getCell(0,i);
            for (int j=0;j<7;j++){
                if (cell.getContents().equals(weekdayNames[j])){
                    beginWeekday[j]=i;
                    break;
                }
            }
        }
        for (int i=0;i<7;i++){
            if (beginWeekday[i]==0)
            {
                beginWeekday[i]=endLine;
                break;
            }
        }


        String[] classTimeInfo=new String[40];
        int[] classTime=new int[40];
        int classTimeCounter=0;
        for (int i=whichLine;i<endLine;i++){
            Cell cell=readSheet.getCell(1,i);
            if(!cell.getContents().equals("")){
                classTime[classTimeCounter]=i;
                classTimeInfo[classTimeCounter]=cell.getContents();
                classTimeCounter++;
            }
        }
        classTime[classTimeCounter]=endLine;

        //将表格信息导入至tableInfo数组
        String[] tableInfo = new String[endLine - whichLine];
        String[] weekDays=new String[endLine-whichLine];
        String[] classTimes=new String[endLine-whichLine];
        int tableIndex = 0;
        for (int i = whichLine + 1; i < endLine; i++) {
            Cell cell = readSheet.getCell(whichColumn, i);

            if (cell.getContents().equals("")) {
                for (int j = 0; j < range.length; j++) {
                    Cell c1 = range[j].getTopLeft();
                    Cell c2 = range[j].getBottomRight();

                    int firstColumn = c1.getColumn(), firstRow = c1.getRow(), lastColumn = c2.getColumn(), lastRow = c2.getRow();

                    if (i >= firstRow && i <= lastRow && whichColumn >= firstColumn && whichColumn <= lastColumn) {
                        tableInfo[tableIndex] = c1.getContents();

                        for (int k=0;k<7;k++){
                            if (i>=beginWeekday[k] && i<beginWeekday[k+1]){
                                weekDays[tableIndex]=weekdayNames[k];
                                break;
                            }
                        }
                        for (int k=0;k<classTimeCounter;k++){
                            if(i>=classTime[k] && i<classTime[k+1]){
                                classTimes[tableIndex]=classTimeInfo[k];
                                break;
                            }
                        }

                        tableIndex++;
                        break;
                    }
                }
            } else {
                tableInfo[tableIndex] = cell.getContents();
                for (int k=0;k<7;k++){
                    if (i>=beginWeekday[k] && i<beginWeekday[k+1]){
                        weekDays[tableIndex]=weekdayNames[k];
                        break;
                    }
                }
                for (int k=0;k<classTimeCounter;k++){
                    if(i>=classTime[k] && i<classTime[k+1]){
                        classTimes[tableIndex]=classTimeInfo[k];
                        break;
                    }
                }
                tableIndex++;
            }
        }
//？？？？？？
        String[] eachClass=new String[2*tableIndex],eachClassWeekday=new String[2*tableIndex],eachClassClassTime=new String[2*tableIndex];
        int classIndex=0;
        for (int i=0;i<tableIndex;i++){
            int p=1;
            if (CharCount(tableInfo[i],"周")>1 && CharCount(tableInfo[i],"楼")>1)
            {
                String[] apartString=tableInfo[i].split("\n");
                for (int j=0;j<apartString.length;j++){
                    eachClass[classIndex+j]=apartString[j];
                    eachClassWeekday[classIndex+j]=weekDays[i];
                    eachClassClassTime[classIndex+j]=classTimes[i];
                }
                p=Math.min(CharCount(tableInfo[i],"楼"),CharCount(tableInfo[i],"周"));
            }
//            else if (CharCount(tableInfo[i],"周")>1){
//                String[] apartString=tableInfo[i].split("\n");
//                for (int j=0;j<apartString.length;j++){
//                    eachClass[classIndex+j]=apartString[j];
//                    eachClassWeekday[classIndex+j]=weekDays[i];
//                    eachClassClassTime[classIndex+j]=classTimes[i];
//                }
//                p=CharCount(tableInfo[i],"周");
//            }
            else{
                eachClass[classIndex]=tableInfo[i];
                eachClassWeekday[classIndex]=weekDays[i];
                eachClassClassTime[classIndex]=classTimes[i];
            }
            classIndex+=p/*CharCount(tableInfo[i],"周")*/;
        }

        String[] middle=new String[classIndex],classRooms=new String[classIndex],classNames=new String[classIndex],Eweeks=new String[classIndex];
        for (int i=0;i<classIndex;i++) {
            eachClass[i] = eachClass[i].replaceAll("\\s*", "");
            if (eachClass[i].equals("")){
                for (int j=i;j<classIndex-1;j++){
                    eachClass[j]=eachClass[j+1];
                }
                classIndex--;
            }
            middle[i] = eachClass[i].substring(0, eachClass[i].indexOf("周"));
            classRooms[i] = eachClass[i].substring(eachClass[i].indexOf("周") + 1);

            if (CharCount(classRooms[i],"班")>1){
                if (className.substring(2, 3).equals("0")) {
                    String a = className.substring(3) + "班";
                    String b = (Integer.parseInt(className.substring(3)) + 1) + "班";
                    int c = classRooms[i].indexOf(a) + a.length(), d = classRooms[i].indexOf(b) - 1;
                    classRooms[i] = classRooms[i].substring(c, d);
                } else {
                    String a = className.substring(2) + "班";
                    classRooms[i] = classRooms[i].substring(classRooms[i].indexOf(a) + a.length(), classRooms[i].indexOf("；"));
                }
            }

//            if (classRooms[i].contains("8班")) {

//            }

            for (int j = 0; j <= middle[i].length() - 1; j++) {
                String a = middle[i].substring(j, j + 1);
                if (a.equals("1")) {
                    classNames[i] = middle[i].substring(0, j);
                    Eweeks[i] = middle[i].substring(j);
                    break;
                } else if (a.equals("2")) {
                    classNames[i] = middle[i].substring(0, j);
                    Eweeks[i] = middle[i].substring(j);
                    break;
                } else if (a.equals("3")) {
                    classNames[i] = middle[i].substring(0, j);
                    Eweeks[i] = middle[i].substring(j);
                    break;
                } else if (a.equals("4")) {
                    classNames[i] = middle[i].substring(0, j);
                    Eweeks[i] = middle[i].substring(j);
                    break;
                } else if (a.equals("5")) {
                    classNames[i] = middle[i].substring(0, j);
                    Eweeks[i] = middle[i].substring(j);
                    break;
                } else if (a.equals("6")) {
                    classNames[i] = middle[i].substring(0, j);
                    Eweeks[i] = middle[i].substring(j);
                    break;
                } else if (a.equals("7")) {
                    classNames[i] = middle[i].substring(0, j);
                    Eweeks[i] = middle[i].substring(j);
                    break;
                } else if (a.equals("8")) {
                    classNames[i] = middle[i].substring(0, j);
                    Eweeks[i] = middle[i].substring(j);
                    break;
                } else if (a.equals("9")) {
                    classNames[i] = middle[i].substring(0, j);
                    Eweeks[i] = middle[i].substring(j);
                    break;
                } else continue;
            }
            Log.d("i为", i + "");
        }

        int weekIndex=0;
        String[] lastClassName=new String[2*classIndex],lastClassRoom=new String[2*classIndex],lastWeekday=new String[2*classIndex],middleWeeks=new String[2*classIndex],lastClassTime=new String[2*classIndex];
        for (int i=0;i<classIndex;i++){
            Eweeks[i]=Eweeks[i].replaceAll("，", ",");
            if (Eweeks[i].contains(",")){
                String[] apart=Eweeks[i].split(",");
                for (int j=0;j<=CharCount(Eweeks[i],",");j++){
                    middleWeeks[weekIndex+j]=apart[j];
                    lastClassName[weekIndex+j]=classNames[i];
                    lastClassRoom[weekIndex+j]=classRooms[i];
                    lastWeekday[weekIndex+j]=eachClassWeekday[i];
                    lastClassTime[weekIndex+j]=eachClassClassTime[i];
                }
            }
            else{
                middleWeeks[weekIndex]=Eweeks[i];
                lastClassName[weekIndex]=classNames[i];
                lastClassRoom[weekIndex]=classRooms[i];
                lastWeekday[weekIndex]=eachClassWeekday[i];
                lastClassTime[weekIndex]=eachClassClassTime[i];
            }
            weekIndex+=(CharCount(Eweeks[i],",")+1);
        }
        int[] classFrom=new int[weekIndex],classTo=new int[weekIndex],weekFrom=new int[weekIndex],weekTo=new int[weekIndex];
        for (int i=0;i<weekIndex;i++){
            classFrom[i]=Integer.parseInt(lastClassTime[i].substring(0,lastClassTime[i].indexOf("—")));
            classTo[i]=Integer.parseInt(lastClassTime[i].substring(lastClassTime[i].indexOf("—")+1));
            if (middleWeeks[i].contains("-")) {
                weekFrom[i] = Integer.parseInt(middleWeeks[i].substring(0, middleWeeks[i].indexOf("-")));
                weekTo[i] = Integer.parseInt(middleWeeks[i].substring(middleWeeks[i].indexOf("-") + 1));
            }else{
                weekFrom[i]=Integer.parseInt(middleWeeks[i]);
                weekTo[i]=Integer.parseInt(middleWeeks[i]);
            }
        }

        int lastIndex=0;
        String[] dLastClassName=new String[2*weekIndex],dLastClassRoom=new String[2*weekIndex],dLastWeekday=new String[2*weekIndex];
        int[] dLastClassFrom=new int[2*weekIndex],dLastClassTo=new int[2*weekIndex],dLastWeekFrom=new int[2*weekIndex],dLastWeekTo=new int[2*weekIndex];
        for (int i=0;i<weekIndex;i++){
            int p=1;
            if(lastClassRoom[i].contains("单周")){
                if (weekFrom[i]%2==0){
                    weekFrom[i]++;
                }
                if (weekTo[i]%2==0){
                    weekTo[i]--;
                }
                p+=(weekTo[i]-weekFrom[i])/2;
                for (int j=0;j<p;j++){
                    dLastClassName[lastIndex+j]=lastClassName[i];
                    dLastClassRoom[lastIndex+j]=lastClassRoom[i];
                    dLastWeekday[lastIndex+j]=lastWeekday[i];
                    dLastClassFrom[lastIndex+j]=classFrom[i];
                    dLastClassTo[lastIndex+j]=classTo[i];

                    dLastWeekFrom[lastIndex+j]=weekFrom[i]+2*j;
                    dLastWeekTo[lastIndex+j]=weekFrom[i]+2*j;
                }
            }
            else if (lastClassRoom[i].contains("双周")){
                if (weekFrom[i]%2!=0){
                    weekFrom[i]++;
                }
                if (weekTo[i]%2!=0){
                    weekTo[i]--;
                }
                p+=(weekTo[i]-weekFrom[i])/2;
                for (int j=0;j<p;j++){
                    dLastClassName[lastIndex+j]=lastClassName[i];
                    dLastClassRoom[lastIndex+j]=lastClassRoom[i];
                    dLastWeekday[lastIndex+j]=lastWeekday[i];
                    dLastClassFrom[lastIndex+j]=classFrom[i];
                    dLastClassTo[lastIndex+j]=classTo[i];

                    dLastWeekFrom[lastIndex+j]=weekFrom[i]+2*j;
                    dLastWeekTo[lastIndex+j]=weekFrom[i]+2*j;
                }
            }
            else{
                dLastClassName[lastIndex]=lastClassName[i];
                dLastClassRoom[lastIndex]=lastClassRoom[i];
                dLastWeekday[lastIndex]=lastWeekday[i];
                dLastClassFrom[lastIndex]=classFrom[i];
                dLastClassTo[lastIndex]=classTo[i];
                dLastWeekFrom[lastIndex]=weekFrom[i];
                dLastWeekTo[lastIndex]=weekTo[i];
            }
            lastIndex+=p;
        }
        int l=0;
        for (int i=0;i<lastIndex;i++){
            if(dLastClassRoom[i].contains("(")){
                for (int j=0;j<dLastClassRoom[i].length();j++)
                    if (dLastClassRoom[i].substring(j,j+1).equals("(")){
                        dLastClassRoom[i]=dLastClassRoom[i].substring(0,j);
                        continue;
                    }
            } else continue;
        }
        for (int i=0;i<lastIndex;i++){
            if(dLastClassRoom[i].contains("（")){
                for (int j=0;j<dLastClassRoom[i].length();j++)
                    if (dLastClassRoom[i].substring(j,j+1).equals("（")){
                        dLastClassRoom[i]=dLastClassRoom[i].substring(0,j);
                        continue;
                    }
            }else continue;
        }
        String[] ReturnClassFrom=new String[lastIndex],ReturnClassTo=new String[lastIndex],ReturnWeekFrom=new String[lastIndex],ReturnWeekTo=new String[lastIndex];
        for (int i=0;i<lastIndex;i++){
            ReturnClassFrom[i]=String.valueOf(dLastClassFrom[i]);
            ReturnClassTo[i]=String.valueOf(dLastClassTo[i]);
            ReturnWeekFrom[i]=String.valueOf(dLastWeekFrom[i]);
            ReturnWeekTo[i]=String.valueOf(dLastWeekTo[i]);
        }
        String[][] result={dLastClassName,dLastClassRoom,dLastWeekday,ReturnClassFrom,ReturnClassTo,ReturnWeekFrom,ReturnWeekTo};
        return result;
    }

    public int CharCount(String string1, String string2) {
        int count = 0;
        if (string1.indexOf(string2) != -1) {
            while(string1.indexOf(string2) != -1){
                string1=string1.substring(string1.indexOf(string2)+string2.length());
                count++;
            }
            return count;
        }
        else return 0;
    }
}
