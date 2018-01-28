package com.manojbhadane.innoplexus.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.manojbhadane.innoplexus.BuildConfig;

/**
 * Created by lenovo on 27-Jan-18.
 */
public class Utils
{
    public static void printLog(String msg)
    {
        if (BuildConfig.DEBUG)
            Log.e("--LOG--", msg);
    }

    public static void printLog(Context context, String msg)
    {
        if (BuildConfig.DEBUG)
            Log.e(context.getClass().getName().toString(), msg);
    }

    public static void setFont(ViewGroup group, Typeface lTypeface)
    {
        int count = group.getChildCount();
        View v;
        for (int i = 0; i < count; i++)
        {
            v = group.getChildAt(i);
            if (v instanceof TextView)
            {
                ((TextView) v).setTypeface(lTypeface);
            } else if (v instanceof EditText)
            {
                ((EditText) v).setTypeface(lTypeface);
            } else if (v instanceof Button)
            {
                ((Button) v).setTypeface(lTypeface);
            } else if (v instanceof ViewGroup)
                setFont((ViewGroup) v, lTypeface);
        }
    }
}
