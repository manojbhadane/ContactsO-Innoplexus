package com.manojbhadane.innoplexus.activity.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.manojbhadane.innoplexus.R;
import com.manojbhadane.innoplexus.app.App;
import com.manojbhadane.innoplexus.utils.Utils;

/**
 * Created by lenovo on 27-Jan-18.
 */

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity
{
    private B mDataBinding;
    public Toolbar mToolbar;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, getLayoutResId());
        setToolbar();
        init(mDataBinding);
        Utils.setFont((ViewGroup) findViewById(R.id.parent), App.getUbuntuTypeFace());
    }

    public abstract int getLayoutResId();

    public abstract void init(B dataBinding);

    public void setToolbar()
    {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    public Toolbar getToolbar()
    {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    public void showToolbarBackBtn(boolean isShown)
    {
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isShown);
            getSupportActionBar().setDisplayShowHomeEnabled(isShown);
        }
    }

    public void setToolbarTitle(String title)
    {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
