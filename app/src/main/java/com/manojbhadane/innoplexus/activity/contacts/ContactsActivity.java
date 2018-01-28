package com.manojbhadane.innoplexus.activity.contacts;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.manojbhadane.innoplexus.R;
import com.manojbhadane.innoplexus.activity.base.BaseActivity;
import com.manojbhadane.innoplexus.activity.details.DetailsActivity;
import com.manojbhadane.innoplexus.adapter.ContactListAdapter;
import com.manojbhadane.innoplexus.app.Constant;
import com.manojbhadane.innoplexus.databinding.ActivityContactsBinding;
import com.manojbhadane.innoplexus.listener.OnItemClickListener;
import com.manojbhadane.innoplexus.model.ResponseModel;

import java.util.ArrayList;

/**
 * Created by lenovo on 27-Jan-18.
 */

public class ContactsActivity extends BaseActivity implements ContactsView, OnItemClickListener
{
    private ContactListAdapter mAdapter;
    private ContactsPresenter mPresenter;
    private ArrayList<ResponseModel> mArrayList;
    private ActivityContactsBinding mDataBinding;

    @Override public int getLayoutResId()
    {
        return R.layout.activity_contacts;
    }

    @Override public void init(ViewDataBinding dataBinding)
    {
        mPresenter = new ContactsPresenterImpl(this);
        mDataBinding = (ActivityContactsBinding) dataBinding;

        setToolbarTitle(getString(R.string.title_contacts));

        mArrayList = new ArrayList<>();
        mAdapter = new ContactListAdapter(mArrayList, ContactsActivity.this);
        mDataBinding.rcvContactList.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.rcvContactList.setAdapter(mAdapter);

        mPresenter.getContacts();

        ((Button) findViewById(R.id.btn_retry)).setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                mPresenter.getContacts();
            }
        });
    }

    @Override public void showMsg(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override public void showList(ArrayList<ResponseModel> arrayList)
    {
        mArrayList.clear();
        mArrayList.addAll(arrayList);
        mAdapter.notifyDataSetChanged();
    }

    @Override public void onItemClick(int position)
    {
        Intent intent = new Intent(ContactsActivity.this, DetailsActivity.class);
        intent.putExtra(Constant.INTENT_DETAILS, mArrayList.get(position));
        startActivity(intent);
    }

    @Override public void showRetry(boolean shouldShow)
    {
        ((RelativeLayout) findViewById(R.id.lay_no_internet)).setVisibility(shouldShow ? View.VISIBLE : View.GONE);
    }

    @Override public void showAnim()
    {
        mDataBinding.avi.show();
    }

    @Override public void hideAnim()
    {
        mDataBinding.avi.hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_users_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;

            case R.id.menu_sort:
                mPresenter.sortList();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
