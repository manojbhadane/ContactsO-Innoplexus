package com.manojbhadane.innoplexus.api;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.manojbhadane.innoplexus.app.App;
import com.manojbhadane.innoplexus.listener.ApiResponceListener;
import com.manojbhadane.innoplexus.utils.Utils;

import org.json.JSONArray;

/**
 * Created by lenovo on 27-Jan-18.
 */
public class JsonRequest
{
    private static JsonRequest mInstance;

    private JsonRequest()
    {
    }

    public static synchronized JsonRequest getInstance()
    {
        if (mInstance == null)
            mInstance = new JsonRequest();
        return mInstance;
    }

    /**
     * GET JsonArray request
     *
     * @param url
     * @param listener
     */
    public void makeJsonArrayRequest(String url, final ApiResponceListener listener)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>()
        {
            @Override public void onResponse(JSONArray response)
            {
                listener.onResponce(response.toString());
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
                if (error.getMessage() != null)
                    Utils.printLog(error.getMessage().toString());
            }
        });

        App.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}
