package com.example.trail;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Implementation of App Widget functionality.
 */
public class DeskWidget extends AppWidgetProvider {


    private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.desk_widget);
        int count=0;
        String[] names={"DeskName1","DeskName2","DeskName3","DeskName4","DeskName5"};
        for (int i=0;i<7;i++) {
            if (MainActivity.weeks[MainActivity.p].haveOrNot[MainActivity.o][i]==true) {
                switch (count){
                    case 0: {
                        views.setTextViewText(R.id.DeskName1, MainActivity.weeks[MainActivity.p].name[MainActivity.o][i]);
                        views.setTextViewText(R.id.DeskInfo1, MainActivity.weeks[MainActivity.p].from[MainActivity.o][i] + "~" + MainActivity.weeks[MainActivity.p].to[MainActivity.o][i] + " " + MainActivity.weeks[MainActivity.p].room[MainActivity.o][i]);
                        count++;
                        break;
                    }
                    case 1:{
                        views.setTextViewText(R.id.DeskName2, MainActivity.weeks[MainActivity.p].name[MainActivity.o][i]);
                        views.setTextViewText(R.id.DeskInfo2, MainActivity.weeks[MainActivity.p].from[MainActivity.o][i] + "~" + MainActivity.weeks[MainActivity.p].to[MainActivity.o][i] + " " + MainActivity.weeks[MainActivity.p].room[MainActivity.o][i]);
                        count++;
                        break;
                    }
                    case 2:{
                        views.setTextViewText(R.id.DeskName3, MainActivity.weeks[MainActivity.p].name[MainActivity.o][i]);
                        views.setTextViewText(R.id.DeskInfo3, MainActivity.weeks[MainActivity.p].from[MainActivity.o][i] + "~" + MainActivity.weeks[MainActivity.p].to[MainActivity.o][i] + " " + MainActivity.weeks[MainActivity.p].room[MainActivity.o][i]);
                        count++;
                        break;
                    }
                    case 3:{
                        views.setTextViewText(R.id.DeskName4, MainActivity.weeks[MainActivity.p].name[MainActivity.o][i]);
                        views.setTextViewText(R.id.DeskInfo4, MainActivity.weeks[MainActivity.p].from[MainActivity.o][i] + "~" + MainActivity.weeks[MainActivity.p].to[MainActivity.o][i] + " " + MainActivity.weeks[MainActivity.p].room[MainActivity.o][i]);
                        count++;
                        break;
                    }
                    default:{
                        views.setTextViewText(R.id.DeskName5, MainActivity.weeks[MainActivity.p].name[MainActivity.o][i]);
                        views.setTextViewText(R.id.DeskInfo5, MainActivity.weeks[MainActivity.p].from[MainActivity.o][i] + "~" + MainActivity.weeks[MainActivity.p].to[MainActivity.o][i] + " " + MainActivity.weeks[MainActivity.p].room[MainActivity.o][i]);
                        break;
                    }

                }
                }
        }

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        textView1 = textView1.findViewById(R.id.DeskName1);
        textView2 = textView2.findViewById(R.id.DeskName2);
        textView3 = textView3.findViewById(R.id.DeskName3);
        textView4 = textView4.findViewById(R.id.DeskName4);
        textView5 = textView5.findViewById(R.id.DeskName5);
        textView6 = textView6.findViewById(R.id.DeskInfo1);
        textView7 = textView7.findViewById(R.id.DeskInfo2);
        textView8 = textView8.findViewById(R.id.DeskInfo3);
        textView9 = textView9.findViewById(R.id.DeskInfo4);
        textView10 = textView10.findViewById(R.id.DeskInfo5);
//        Typeface type = Typeface.createFromAsset(textView.getContext().getAssets(), "STKAITI.TTF");
//        textView.setTypeface(type);

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

