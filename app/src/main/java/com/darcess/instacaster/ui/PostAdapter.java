package com.darcess.instacaster.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.darcess.instacaster.R;
import com.darcess.instacaster.api.post.Datum;
import com.darcess.instacaster.mvp.Model.dbPost;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alexander Dmitryukov on 7/16/2017.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.Holder> {

    private LayoutInflater mInflater;
    private List<dbPost> mList = new ArrayList<>();

    public PostAdapter(LayoutInflater layoutInflater) {
        mInflater = layoutInflater;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.post_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mList.get(position));
    }

    public class Holder extends RecyclerView.ViewHolder{

        @BindView(R.id.post_text)
        TextView tvPostText;
        @BindView(R.id.post_img)
        ImageView ivPostImg;
        @BindView(R.id.post_title)
        TextView tvPostTitle;
        @BindView(R.id.user_img)
        ImageView ivUserImg;

        private Context mContext;

        public Holder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }

        public void bind(dbPost datum) {
            Glide.with(mContext).load(datum.getUserImgUrl())
                    .into(ivUserImg);
            Glide.with(mContext).load(datum.getPostImgUrl())
                    .into(ivPostImg);
            tvPostTitle.setText(datum.getUsername());
            tvPostText.setText(datum.getText());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size()>10?10:mList.size();
    }

    public void updateList(List<dbPost> list){
        mList = list;
        notifyDataSetChanged();
    }
}
