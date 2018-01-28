package com.manojbhadane.innoplexus.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.manojbhadane.innoplexus.R;
import com.manojbhadane.innoplexus.app.App;
import com.manojbhadane.innoplexus.listener.OnItemClickListener;
import com.manojbhadane.innoplexus.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 28-Jan-18.
 */
public class ContactListAdapter extends RecyclerView.Adapter implements Filterable
{
    private List<ResponseModel> mUsersList;
    private OnItemClickListener mListener;
    private List<ResponseModel> mUsersListFilter;
    private ItemFilter mFilter = new ItemFilter();

    public ContactListAdapter(List<ResponseModel> usersList, OnItemClickListener listener)
    {
        mListener = listener;
        mUsersList = usersList;
        mUsersListFilter = usersList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_users_list, parent, false);
        vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        ResponseModel model = (ResponseModel) mUsersListFilter.get(position);

        ((ViewHolder) holder).mTxtName.setText(model.getName());
        ((ViewHolder) holder).mTxtDesignation.setText(model.getCompany().getName());
        ((ViewHolder) holder).mTxtSubText.setText(model.getAddress().getStreet() + " " + App.getInstance().getString(R.string.dot) + " " + model.getAddress().getCity());

        ((ViewHolder) holder).mTxtName.setTypeface(App.getUbuntuTypeFace());
        ((ViewHolder) holder).mTxtSubText.setTypeface(App.getUbuntuTypeFace());
        ((ViewHolder) holder).mTxtDesignation.setTypeface(App.getUbuntuTypeFace());
        ((ViewHolder) holder).mCard.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                mListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mUsersListFilter.size();
    }

    @Override
    public long getItemId(int position)
    {
        return mUsersListFilter.indexOf(mUsersListFilter.get(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        protected CardView mCard;
        protected TextView mTxtName, mTxtSubText, mTxtDesignation;

        public ViewHolder(View v)
        {
            super(v);
            mCard = (CardView) v.findViewById(R.id.card);
            mTxtName = (TextView) v.findViewById(R.id.txt_user_name);
            mTxtSubText = (TextView) v.findViewById(R.id.txt_sub_text);
            mTxtDesignation = (TextView) v.findViewById(R.id.txt_sub_text1);
        }
    }

    @Override
    public Filter getFilter()
    {
        return mFilter;
    }

    private class ItemFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            FilterResults results = new FilterResults();
            final List<ResponseModel> list = mUsersList;
            int count = list.size();
            final List<ResponseModel> nlist = new ArrayList<ResponseModel>(count);
            for (int i = 0; i < count; i++)
            {
                ResponseModel model = list.get(i);
                if (model != null)
                    if ((list.get(i).getName() + list.get(i).getUsername())
                            .toLowerCase().contains(constraint.toString().toLowerCase()))
                        nlist.add(list.get(i));
            }
            results.values = nlist;
            results.count = nlist.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            mUsersListFilter = (ArrayList<ResponseModel>) results.values;
            notifyDataSetChanged();
        }
    }
}
