package com.manojbhadane.innoplexus.activity.contacts;

import com.manojbhadane.innoplexus.listener.BaseView;
import com.manojbhadane.innoplexus.model.ResponseModel;

import java.util.ArrayList;

/**
 * Created by lenovo on 27-Jan-18.
 */

public interface ContactsView extends BaseView
{
    public void showAnim();

    public void hideAnim();

    public void showRetry(boolean shouldShow);

    public void showList(ArrayList<ResponseModel> arrayList);
}
