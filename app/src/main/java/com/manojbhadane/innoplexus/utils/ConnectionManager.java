package com.manojbhadane.innoplexus.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.manojbhadane.innoplexus.R;


/**
 * Created by lenovo on 27-Jan-18.
 */
public class ConnectionManager
{
    private Context mContext;
    private static ConnectionManager mInstance;

    private ConnectionManager(Context context)
    {
        this.mContext = context;
    }

    public static synchronized ConnectionManager getInstance(Context context)
    {
        if (mInstance == null)
            mInstance = new ConnectionManager(context);
        return mInstance;
    }

    public boolean isConnectingToInternet()
    {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = cm.getAllNetworkInfo();
        if (info != null)
        {
            for (int i = 0; i < info.length; i++)
            {
                if (info[i].getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isConnectingToInternet(boolean showErrorMsg)
    {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = cm.getAllNetworkInfo();
        if (info != null)
        {
            for (int i = 0; i < info.length; i++)
            {
                if (info[i].getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        Toast.makeText(mContext, mContext.getResources().getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
        return false;
    }
}