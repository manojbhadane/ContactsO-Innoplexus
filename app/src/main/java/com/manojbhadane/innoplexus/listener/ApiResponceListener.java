package com.manojbhadane.innoplexus.listener;

/**
 * Created by lenovo on 27-Jan-18.
 */
public interface ApiResponceListener
{
    public void onResponce(String response);

    public void onError(String error);
}
