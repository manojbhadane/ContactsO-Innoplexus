package com.manojbhadane.innoplexus.activity.contacts;

import com.manojbhadane.innoplexus.listener.BasePresenter;

/**
 * Created by lenovo on 27-Jan-18.
 */

public interface ContactsPresenter extends BasePresenter
{
    public void getContacts();

    public void sortList();
}
