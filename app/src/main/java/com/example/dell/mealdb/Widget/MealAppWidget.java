package com.example.dell.mealdb.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import com.example.dell.mealdb.MainActivity;
import com.example.dell.mealdb.R;
import com.squareup.picasso.Picasso;

/**
 * Implementation of App Widget functionality.
 */
public class MealAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mypref",0);
        String image = sharedPreferences.getString("thumb","DATA NOT FOUND");
        String meal_name = sharedPreferences.getString("title","RECIPE_NAME NOT AVAILABLE");

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        RemoteViews views = new RemoteViews(context.getPackageName(),
                R.layout.meal_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        views.setTextViewText(R.id.appwidget_text,meal_name);
        Picasso.with(context).load(image)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(views, R.id.appwidget_image, new int[]{appWidgetId});
        Intent intent = new Intent(context,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
        views.setOnClickPendingIntent(R.id.widget_host,pendingIntent);
        // Construct the RemoteViews object


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
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

