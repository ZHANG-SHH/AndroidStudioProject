package com.example.trail;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import jxl.*;
import jxl.read.biff.BiffException;


public class MainActivity extends AppCompatActivity {

    private Button btn1, btn2;
    private Spinner week;
    private TextView les11, les13, les15, les17, les191, les192, les193;
    private TextView les21, les23, les25, les27, les291, les292, les293;
    private TextView les31, les33, les35, les37, les391, les392, les393;
    private TextView les41, les43, les45, les47, les491, les492, les493;
    private TextView les51, les53, les55, les57, les591, les592, les593;
    private TextView les61, les63, les65, les67, les691, les692, les693;
    private TextView les71, les73, les75, les77, les791, les792, les793;
    private TextView room11, room13, room15, room17, room191, room192, room193;
    private TextView room21, room23, room25, room27, room291, room292, room293;
    private TextView room31, room33, room35, room37, room391, room392, room393;
    private TextView room41, room43, room45, room47, room491, room492, room493;
    private TextView room51, room53, room55, room57, room591, room592, room593;
    private TextView room61, room63, room65, room67, room691, room692, room693;
    private TextView room71, room73, room75, room77, room791, room792, room793;
    private TextView fclass, sclass, seclass, eclass, nclass, tclass, elclass,twclas,monthday,daymon,daytue,daywed,daythu,dayfri,daysat,daysun;
    public static  Week week1 = new Week(), week2 = new Week(), week3 = new Week(), week4 = new Week(), week5 = new Week(),
            week6 = new Week(), week7 = new Week(), week8 = new Week(), week9 = new Week(), week10 = new Week(),
            week11 = new Week(), week12 = new Week(), week13 = new Week(), week14 = new Week(), week15 = new Week(),
            week16 = new Week(), week17 = new Week(), week18 = new Week(), week19 = new Week(), week20 = new Week(),
            week21 = new Week(), week22 = new Week(), week23 = new Week(), week24 = new Week(), week25 = new Week();
    public static Week[] weeks = {week1, week2, week3, week4, week5, week6, week7, week8, week9, week10, week11, week12,
            week13, week14, week15, week16, week17, week18, week19, week20, week21, week22, week23, week24, week25};
    public static int[] classFromResult,classToResult,weekFromResult,weekToResult;

    public static int p=0,o=0;

    public String UserInputClassName,UserInputClassRoom,UserInputWeekday,UserDeleteClassName,Grade,ClassName;
    public int UserInputClassFrom,UserInputClassTo,UserInputWeekFrom,UserInputWeekTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        les11 = (TextView) findViewById(R.id.les_11) ;
        les13 = (TextView) findViewById(R.id.les_13) ;
        les15 = (TextView) findViewById(R.id.les_15) ;
        les17 = (TextView) findViewById(R.id.les_17) ;
        les191 = (TextView) findViewById(R.id.les_19_1) ;
        les192 = (TextView) findViewById(R.id.les_19_2) ;
        les193 = (TextView) findViewById(R.id.les_19_3)  ;
        les21 = (TextView) findViewById(R.id.les_21) ;
        les23 = (TextView) findViewById(R.id.les_23) ;
        les25 = (TextView) findViewById(R.id.les_25) ;
        les27 = (TextView) findViewById(R.id.les_27) ;
        les291 = (TextView) findViewById(R.id.les_29_1) ;
        les292 = (TextView) findViewById(R.id.les_29_2) ;
        les293 = (TextView) findViewById(R.id.les_29_3);
        les31 = (TextView) findViewById(R.id.les_31) ;
        les33 = (TextView) findViewById(R.id.les_33) ;
        les35 = (TextView) findViewById(R.id.les_35) ;
        les37 = (TextView) findViewById(R.id.les_37) ;
        les391 = (TextView) findViewById(R.id.les_39_1) ;
        les392 = (TextView) findViewById(R.id.les_39_2) ;
        les393 = (TextView) findViewById(R.id.les_39_3);
        les41 = (TextView) findViewById(R.id.les_41) ;
        les43 = (TextView) findViewById(R.id.les_43) ;
        les45 = (TextView) findViewById(R.id.les_45) ;
        les47 = (TextView) findViewById(R.id.les_47) ;
        les491 = (TextView) findViewById(R.id.les_49_1) ;
        les492 = (TextView) findViewById(R.id.les_49_2) ;
        les493 = (TextView) findViewById(R.id.les_49_3);
        les51 = (TextView) findViewById(R.id.les_51) ;
        les53 = (TextView) findViewById(R.id.les_53) ;
        les55 = (TextView) findViewById(R.id.les_55) ;
        les57 = (TextView) findViewById(R.id.les_57) ;
        les591 = (TextView) findViewById(R.id.les_59_1) ;
        les592 = (TextView) findViewById(R.id.les_59_2) ;
        les593 = (TextView) findViewById(R.id.les_59_3);
        les61 = (TextView) findViewById(R.id.les_61) ;
        les63 = (TextView) findViewById(R.id.les_63) ;
        les65 = (TextView) findViewById(R.id.les_65) ;
        les67 = (TextView) findViewById(R.id.les_67) ;
        les691 = (TextView) findViewById(R.id.les_69_1) ;
        les692 = (TextView) findViewById(R.id.les_69_2) ;
        les693 = (TextView) findViewById(R.id.les_69_3);
        les71 = (TextView) findViewById(R.id.les_71) ;
        les73 = (TextView) findViewById(R.id.les_73) ;
        les75 = (TextView) findViewById(R.id.les_75) ;
        les77 = (TextView) findViewById(R.id.les_77) ;
        les791 = (TextView) findViewById(R.id.les_79_1) ;
        les792 = (TextView) findViewById(R.id.les_79_2) ;
        les793 = (TextView) findViewById(R.id.les_79_3);
        room11 = (TextView) findViewById(R.id.room_11) ;
        room13 = (TextView) findViewById(R.id.room_13) ;
        room15 = (TextView) findViewById(R.id.room_15) ;
        room17 = (TextView) findViewById(R.id.room_17) ;
        room191 = (TextView) findViewById(R.id.room_19_1) ;
        room192 = (TextView) findViewById(R.id.room_19_2) ;
        room193 = (TextView) findViewById(R.id.room_19_3) ;
        room21 = (TextView) findViewById(R.id.room_21) ;
        room23 = (TextView) findViewById(R.id.room_23) ;
        room25 = (TextView) findViewById(R.id.room_25) ;
        room27 = (TextView) findViewById(R.id.room_27) ;
        room291 = (TextView) findViewById(R.id.room_29_1) ;
        room292 = (TextView) findViewById(R.id.room_29_2) ;
        room293 = (TextView) findViewById(R.id.room_29_3);
        room31 = (TextView) findViewById(R.id.room_31) ;
        room33 = (TextView) findViewById(R.id.room_33) ;
        room35 = (TextView) findViewById(R.id.room_35) ;
        room37 = (TextView) findViewById(R.id.room_37) ;
        room391 = (TextView) findViewById(R.id.room_39_1) ;
        room392 = (TextView) findViewById(R.id.room_39_2) ;
        room393 = (TextView) findViewById(R.id.room_39_3);
        room41 = (TextView) findViewById(R.id.room_41) ;
        room43 = (TextView) findViewById(R.id.room_43) ;
        room45 = (TextView) findViewById(R.id.room_45) ;
        room47 = (TextView) findViewById(R.id.room_47) ;
        room491 = (TextView) findViewById(R.id.room_49_1) ;
        room492 = (TextView) findViewById(R.id.room_49_2) ;
        room493 = (TextView) findViewById(R.id.room_49_3);
        room51 = (TextView) findViewById(R.id.room_51) ;
        room53 = (TextView) findViewById(R.id.room_53) ;
        room55 = (TextView) findViewById(R.id.room_55) ;
        room57 = (TextView) findViewById(R.id.room_57) ;
        room591 = (TextView) findViewById(R.id.room_59_1) ;
        room592 = (TextView) findViewById(R.id.room_59_2) ;
        room593 = (TextView) findViewById(R.id.room_59_3);
        room61 = (TextView) findViewById(R.id.room_61) ;
        room63 = (TextView) findViewById(R.id.room_63) ;
        room65 = (TextView) findViewById(R.id.room_65) ;
        room67 = (TextView) findViewById(R.id.room_67) ;
        room691 = (TextView) findViewById(R.id.room_69_1) ;
        room692 = (TextView) findViewById(R.id.room_69_2) ;
        room693 = (TextView) findViewById(R.id.room_69_3);
        room71 = (TextView) findViewById(R.id.room_71) ;
        room73 = (TextView) findViewById(R.id.room_73) ;
        room75 = (TextView) findViewById(R.id.room_75) ;
        room77 = (TextView) findViewById(R.id.room_77) ;
        room791 = (TextView) findViewById(R.id.room_79_1) ;
        room792 = (TextView) findViewById(R.id.room_79_2) ;
        room793 = (TextView) findViewById(R.id.room_79_3);
        fclass = (TextView) findViewById(R.id.fifthClass);
        sclass =(TextView) findViewById(R.id.sixthClass);
        seclass = (TextView) findViewById(R.id.seventhClass);
        eclass = (TextView) findViewById(R.id.eighthClass);
        nclass = (TextView) findViewById(R.id.ninthClass);
        tclass = (TextView) findViewById(R.id.tenthClass);
        elclass = (TextView) findViewById(R.id.eleventhClass);
        twclas = (TextView) findViewById(R.id.twelfthClass);
        monthday = (TextView) findViewById(R.id.month);
        daymon = (TextView) findViewById(R.id.dayMon);
        daytue = (TextView) findViewById(R.id.dayTue);
        daywed = (TextView) findViewById(R.id.dayWend);
        daythu = (TextView) findViewById(R.id.dayThur);
        dayfri = (TextView) findViewById(R.id.dayFri);
        daysat = (TextView) findViewById(R.id.daySat);
        daysun = (TextView) findViewById(R.id.daySun);



        TextView[][] names = {
                {les11, les13, les15, les17, les191, les192, les193},
                {les21, les23, les25, les27, les291, les292, les293},
                {les31, les33, les35, les37, les391, les392, les393},
                {les41, les43, les45, les47, les491, les492, les493},
                {les51, les53, les55, les57, les591, les592, les593},
                {les61, les63, les65, les67, les691, les692, les693},
                {les71, les73, les75, les77, les791, les792, les793},
                };
        TextView[][] rooms = {
                {room11, room13, room15, room17, room191, room192, room193},
                {room21, room23, room25, room27, room291, room292, room293},
                {room31, room33, room35, room37, room391, room392, room393},
                {room41, room43, room45, room47, room491, room492, room493},
                {room51, room53, room55, room57, room591, room592, room593},
                {room61, room63, room65, room67, room691, room692, room693},
                {room71, room73, room75, room77, room791, room792, room793},
        };



        //获取excel文件
        //转化成输入流
        //创建Workbook对象
        Workbook workbook = null;

        DatabaseHelper helper = new DatabaseHelper(this);
        helper.getWritableDatabase();
        Dao dao = new Dao(this);

        Intent intent = getIntent();
        String action = intent.getAction();
        if (intent.ACTION_VIEW.equals(action)) {
            try {
                Uri uri = intent.getData();
                String str = Uri.decode(uri.getEncodedPath());
                InputStream inputStream = openFileInput(str);
                workbook = Workbook.getWorkbook(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (BiffException e) {
                e.printStackTrace();
            }


            ImportExcel importExcel = new ImportExcel();
            String[][] result = importExcel.Import(workbook, Grade, ClassName);
            String[] nameResult = result[0], roomResult = result[1], weekdayResult = result[2];
            classFromResult = new int[result[3].length];
            classToResult = new int[result[3].length];
            weekFromResult = new int[result[3].length];
            weekToResult = new int[result[3].length];
            for (int i = 0; i < result[3].length; i++) {
                classFromResult[i] = Integer.parseInt(result[3][i]);
                classToResult[i] = Integer.parseInt(result[4][i]);
                weekFromResult[i] = Integer.parseInt(result[5][i]);
                weekToResult[i] = Integer.parseInt(result[6][i]);
            }
            dao.delete();
            for (int i = 0; i < classFromResult.length; i++) {
                dao.insert(nameResult[i], roomResult[i], weekdayResult[i], classFromResult[i], classToResult[i], weekFromResult[i], weekToResult[i]);
            }
            for (int i = 0; i < Constants.A.length; i++) {
                dao.delete(Constants.A[i]);
            }
        }
        dao.delete(UserDeleteClassName);
        dao.query();
        int count=(int)dao.allCaseNum();

            for (int i=0;i<count;i++){
                int classWhen;
                if (dao.classFroms[i] < 9)
                    classWhen = (dao.classFroms[i] + 1) / 2;
                else if (dao.classTos[i] == 12)
                    classWhen = 5;
                else if (dao.weekTos[i] == 11)
                    classWhen = 6;
                else classWhen = 7;

                int WeekDay = WhichDay(dao.thisWeekdays[i]);

                for (int j = dao.weekFroms[i] - 1; j < dao.weekTos[i]; j++) {
                    weeks[j].haveOrNot[WeekDay - 1][classWhen - 1] = true;
                    weeks[j].name[WeekDay - 1][classWhen - 1] = dao.thisNames[i];
                    weeks[j].room[WeekDay - 1][classWhen - 1] = dao.thisRooms[i];
                    weeks[i].to[WeekDay - 1][classWhen - 1]=dao.classTos[i];
                    weeks[i].from[WeekDay - 1][classWhen - 1]=dao.classFroms[i];
                }
            }
        //现在从excel文件夹中获取到了lastClassName,lastClassRoom,lastWeekday,classFrom,classTo,weekFrom,weekTo;
        // 设置后八节课时间以及每天日期
        final TextView[] daysets = {daymon, daytue, daywed, daythu, dayfri, daysat, daysun};
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String[] fd ={"2019","9","2"};
        weeks[0].setFirstDay(Integer.parseInt(fd[0]), Integer.parseInt(fd[1]), Integer.parseInt(fd[2]));
        int a = weeks[0].year, b = weeks[0].month, c = weeks[0].day[0];
        int look=-1;
        while(look<23) {
            look++;
            weeks[look].year = a;
            weeks[look].month = b;
            for (int j = 0; j < 7; j++) {
                weeks[look].day[j] = c;

                if (c < monthdays(a, b))
                    c++;
                else if (b < 12) {
                    b++;
                    c = 1;
                } else {
                    a++;
                    b = 1;
                    c = 1;
                }
            }

        }

        for (int i=0;i<25;i++) {
            int l=weeks[i].year,m=weeks[i].month;
            if (year==l && (month+1)==m)
                for (int j = 0; j < 7; j++) {
                    int n = weeks[i].day[j];
                    if (day==n) {
                        p=i;
                        o=j;
                        break;
                    }
                }
            else continue;
        }


        //课表添加与设置学期首日
        btn1 = (Button) findViewById(R.id.Add);
        btn2 = (Button) findViewById(R.id.SettingTable);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AddLesson.class);
                startActivityForResult(intent, 1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, setterm.class);
                startActivityForResult(intent, 0);
            }
        });


        //选择某一周来显示
        week=(Spinner)findViewById(R.id.sp_1);
        week.setSelection(p);
        week.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            TextView[][] names = {
                    {les11, les13, les15, les17, les191, les192, les193},
                    {les21, les23, les25, les27, les291, les292, les293},
                    {les31, les33, les35, les37, les391, les392, les393},
                    {les41, les43, les45, les47, les491, les492, les493},
                    {les51, les53, les55, les57, les591, les592, les593},
                    {les61, les63, les65, les67, les691, les692, les693},
                    {les71, les73, les75, les77, les791, les792, les793},
            };
            TextView[][] rooms = {
                    {room11, room13, room15, room17, room191, room192, room193},
                    {room21, room23, room25, room27, room291, room292, room293},
                    {room31, room33, room35, room37, room391, room392, room393},
                    {room41, room43, room45, room47, room491, room492, room493},
                    {room51, room53, room55, room57, room591, room592, room593},
                    {room61, room63, room65, room67, room691, room692, room693},
                    {room71, room73, room75, room77, room791, room792, room793},
            };

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str = (String) week.getSelectedItem();
                int subStr = Integer.parseInt(str.substring(1, str.indexOf("周")));
                DisShow(names);
                DisShow(rooms);
                Show(names, weeks[subStr - 1].name, weeks[subStr - 1].haveOrNot);
                Show(rooms, weeks[subStr - 1].room, weeks[subStr - 1].haveOrNot);
                Show(daysets, weeks[subStr - 1].day);

                monthday.setText(String.valueOf(weeks[subStr - 1].month) + "月");
                if (weeks[subStr - 1].month < 10 && weeks[subStr - 1].month > 4) {
                    fclass.setText("14:30");
                    sclass.setText("15:20");
                    seclass.setText("16:25");
                    eclass.setText("17:15");
                    nclass.setText("19:00");
                    tclass.setText("19:50");
                    elclass.setText("20:45");
                    twclas.setText("21:35");
                } else {
                    fclass.setText("14:00");
                    sclass.setText("14:50");
                    seclass.setText("15:55");
                    eclass.setText("16:45");
                    nclass.setText("18:30");
                    tclass.setText("19:20");
                    elclass.setText("20:15");
                    twclas.setText("21:05");
                }
            }

            @Override
            public void onNothingSelected (AdapterView < ? > adapterView){


            }
        });

    }

//设置好后传值给main函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            TextView month = (TextView) findViewById(R.id.month);
            TextView day = (TextView) findViewById(R.id.dayMon);
            month.setText(data.getExtras().getString("fdMonth") + "月");
            day.setText(data.getExtras().getString("fdDay"));

            String saveInfo=data.getExtras().getString("fdYear")+"-"+data.getExtras().getString("fdMonth")+"-"+data.getExtras().getString("fdDay");
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream=openFileOutput("firstDay.txt",MODE_APPEND);
                fileOutputStream.write(saveInfo.getBytes());
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this,"数据保存成功",Toast.LENGTH_SHORT).show();

        } else //设置课表
        {
            String name = data.getExtras().getString("className");
            String room = data.getExtras().getString("classRoom");
            String weekday = data.getExtras().getString("WeekDay");
            int weekFrom, weekTo, classFrom, classTo;
            weekFrom = Integer.parseInt(data.getExtras().getString("weekFrom"));
            weekTo = Integer.parseInt(data.getExtras().getString("weekTo"));
            classFrom = Integer.parseInt(data.getExtras().getString("from"));
            classTo = Integer.parseInt(data.getExtras().getString("to"));

            DatabaseHelper helper = new DatabaseHelper(this);
            helper.getWritableDatabase();
            Dao dao = new Dao(this);
            dao.insert(name,room,weekday,classFrom,classTo,weekFrom,weekTo);
            String saveInfo=name+","+room+","+weekday+","+classFrom+","+classTo+","+weekFrom+","+weekTo+",";
            FileOutputStream fos;
            try {
                fos=openFileOutput("class1.txt",MODE_APPEND);
                fos.write(saveInfo.getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this,"数据保存成功",Toast.LENGTH_SHORT).show();

            int classWhen;
            if (classFrom < 9)
                classWhen = (classFrom + 1) / 2;
            else if (classTo == 12)
                classWhen = 5;
            else if (classTo == 11)
                classWhen = 6;
            else classWhen = 7;

            int WeekDay = WhichDay(weekday);

            for (int i = weekFrom - 1; i < weekTo; i++) {
                weeks[i].haveOrNot[WeekDay - 1][classWhen - 1] = true;
                weeks[i].name[WeekDay - 1][classWhen - 1] = name;
                weeks[i].room[WeekDay - 1][classWhen - 1] = room;
                weeks[i].to[WeekDay - 1][classWhen - 1]=classTo;
                weeks[i].from[WeekDay - 1][classWhen - 1]=classFrom;
            }


        }

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

    public void Show(TextView[] textViews,int[] days)
    {
        for (int i=0;i<7;i++)
        textViews[i].setText(String.valueOf(days[i]));
    }
    public void Show(TextView[][] textViews,String[][] names,boolean[][] right)
    {
        for (int i=0;i<7;i++)
            for (int j=0;j<7;j++)
                if (right[i][j])
                {
                    textViews[i][j].setText(names[i][j]);
                }
    }
    public void DisShow(TextView[][] textViews)
    {
        for (int i=0;i<7;i++)
            for (int j=0;j<7;j++)
                textViews[i][j].setText("");
    }
    public int WhichDay(String string)
    {
        switch (string) {
            case "周一":
            case "星期一":
                return 1;
            case "周二":
            case "星期二":
                return 2;
            case "周三":
            case "星期三":
                return 3;
            case "周四":
            case "星期四":
                return 4;
            case "周五":
            case "星期五":
                return 5;
            case "周六":
            case "星期六":
                return 6;
            default:
                return 7;
        }
    }
    public int monthdays(int year,int month){
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                if (iy(year))
                    return 29;
                else
                    return 28;
        }
    }   
    public boolean iy(int year){
        if(year%4==0)
        {
            if (year%100!=0)
                return true;
            else if (year%400==0)
                return true;
            else return false;
        }
        else return false;
    }
}

class Week{
    String[][] name=new String[7][7];
    String[][] room=new String[7][7];
    int[][] to=new int[7][7];
    int[][] from=new int[7][7];
    boolean[][] haveOrNot=new boolean[7][7];
    int month,year;
    int[] day=new int[7];
    Week()
    {
        for(int i=0;i<7;i++)
            day[i]=0;
    }
    public void setFirstDay(int year,int month,int day)
    {
        this.year=year;
        this.month=month;
        this.day[0]=day;
    }
    public void setWeekClass(String name,String room,int day,int begin,int over)
    {
        if (begin<9)
        {
            this.name[day-1][begin-1]=name;
            this.room[day-1][begin-1]=room;
        }
        else if (over==12)
        {
            this.name[day-1][4]=name;
            this.room[day-1][4]=room;
        }
        else if (over==11)
        {
            this.name[day-1][5]=name;
            this.room[day-1][5]=room;
        }
        else
        {
            this.name[day-1][6]=name;
            this.room[day-1][6]=room;
        }
    }
}