package com.manojbhadane.innoplexus.activity.contacts;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.manojbhadane.innoplexus.api.JsonRequest;
import com.manojbhadane.innoplexus.app.App;
import com.manojbhadane.innoplexus.app.Constant;
import com.manojbhadane.innoplexus.listener.ApiResponceListener;
import com.manojbhadane.innoplexus.model.ResponseModel;
import com.manojbhadane.innoplexus.utils.ConnectionManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lenovo on 27-Jan-18.
 */

public class ContactsPresenterImpl implements ContactsPresenter
{
    private Gson mGson;
    private ContactsView mView;
    private boolean mAscdending;
    private ArrayList<ResponseModel> mArrayList;

    public ContactsPresenterImpl(ContactsView view)
    {
        mView = view;
        mGson = new Gson();
    }

    @Override public void getContacts()
    {
        if (ConnectionManager.getInstance(App.getInstance()).isConnectingToInternet())
        {
            mView.showRetry(false);
            mView.showAnim();
            JsonRequest.getInstance().makeJsonArrayRequest(Constant.URL, new ApiResponceListener()
            {
                @Override public void onResponce(String response)
                {
                    mArrayList = new ArrayList<>();
                    Type collectionType = new TypeToken<List<ResponseModel>>()
                    {
                    }.getType();
                    mArrayList = mGson.fromJson(response.toString(), collectionType);
                    Collections.sort(mArrayList);
                    mAscdending = true;
                    mView.hideAnim();
                    mView.showList(mArrayList);
                }

                @Override public void onError(String error)
                {
                    mView.showMsg(error);
                }
            });
        } else
        {
            mView.hideAnim();
            mView.showRetry(true);
        }
    }

    @Override public void sortList()
    {
        if (mAscdending)
        {
            mAscdending = false;
            Collections.reverse(mArrayList);
        } else
        {
            mAscdending = true;
            Collections.sort(mArrayList);
        }
        mView.showList(mArrayList);
    }
}
