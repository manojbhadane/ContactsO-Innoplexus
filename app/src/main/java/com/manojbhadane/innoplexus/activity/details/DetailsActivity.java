package com.manojbhadane.innoplexus.activity.details;

import android.databinding.ViewDataBinding;
import android.text.Html;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.TextView;

import com.manojbhadane.innoplexus.R;
import com.manojbhadane.innoplexus.activity.base.BaseActivity;
import com.manojbhadane.innoplexus.app.Constant;
import com.manojbhadane.innoplexus.databinding.ActivityContactDetailsBinding;
import com.manojbhadane.innoplexus.model.ResponseModel;
import com.manojbhadane.innoplexus.utils.SimpleSpanBuilder;

/**
 * Created by lenovo on 28-Jan-18.
 */

public class DetailsActivity extends BaseActivity implements DetailsView
{
    private ResponseModel mDataModel;
    private DetailsPresenter mPresenter;
    private ActivityContactDetailsBinding mDataBinding;

    @Override public int getLayoutResId()
    {
        return R.layout.activity_contact_details;
    }

    @Override public void init(ViewDataBinding dataBinding)
    {
        mPresenter = new DetailsPresenterImpl(this);
        mDataBinding = (ActivityContactDetailsBinding) dataBinding;
        mDataModel = getIntent().getParcelableExtra(Constant.INTENT_DETAILS);

        showToolbarBackBtn(true);
        setToolbarTitle(getString(R.string.title_details));

        mDataBinding.txtName.setText(mDataModel.getName());
        mDataBinding.txtUserName.setText(mDataModel.getUsername());
        mDataBinding.txtBs.setText(mDataModel.getCompany().getBs());
        mDataBinding.txtEmail.setText(Html.fromHtml(mDataModel.getEmail()));
        mDataBinding.txtCompanyname.setText(mDataModel.getCompany().getName());
        mDataBinding.txtWebsite.setText(Html.fromHtml(mDataModel.getWebsite()));
        mDataBinding.txtCatchPhrase.setText(mDataModel.getCompany().getCatchPhrase());

        setSpanText(mDataBinding.txtPhone, getString(R.string.DS_phone), mDataModel.getPhone());
        setSpanText(mDataBinding.txtEmail, getString(R.string.DS_email), mDataModel.getEmail());
        setSpanText(mDataBinding.txtAddress, getString(R.string.DS_address), mDataModel.getAddress().getSuite() + ", " + mDataModel.getAddress().getStreet() + ", " + mDataModel.getAddress().getCity() + " - " + mDataModel.getAddress().getZipcode());
    }

    @Override public void showMsg(String msg)
    {

    }

    private void setSpanText(TextView textView, String str1, String str2)
    {
        SimpleSpanBuilder ssb = new SimpleSpanBuilder();
        ssb.append(str1 + " : ", new ForegroundColorSpan(getResources().getColor(R.color.pocket_color_3)), new RelativeSizeSpan(1));
        ssb.append(str2, new ForegroundColorSpan(getResources().getColor(R.color.black)), new RelativeSizeSpan(1));
        textView.setText(ssb.build());
    }
}
