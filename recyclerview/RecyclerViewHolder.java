package com.xianneng.gamebook.dataadapter.recyclerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xianneng.gamebook.dataadapter.helper.IRecyclerView;

/**
 * Created by xiaoxiaochu
 * 2018-03-04
 * 以RecyclerView形式显示数据时做的缓存holder
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements IRecyclerView<RecyclerViewHolder> {
    private SparseArray<View> mViews = new SparseArray<>();

    private View mTransView;
    private int mLayoutId;
    protected Context mContext;

    public RecyclerViewHolder(View itemView, int mLayoutId, Context mContext) {
        super(itemView);
        this.mLayoutId = mLayoutId;
        this.mContext = mContext;
        mTransView = itemView;
        mTransView.setTag(this);
    }

    // 该方法返回的V泛型，指返回的view可以是TextView,ImageView,Button等view控件
    public <V extends View> V getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mTransView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (V) view;
    }

    // 获取布局id
    public int getLayoutId() {
        return mLayoutId;
    }

    // 获取item的view对象
    public View getItemView() {
        return mTransView;
    }

    /**
     * 以下是对接口内方法的实现
     *
     */
    @Override
    public RecyclerViewHolder setText(int viewId, String value) {
        TextView view=getView(viewId);
        view.setText(value);
        return this;
    }

    @Override
    public RecyclerViewHolder setTextColor(int viewId, int color) {
        TextView view=getView(viewId);
        view.setTextColor(color);
        return this;
    }

    @Override
    public RecyclerViewHolder setImageRes(int viewId, int imgResId) {
        ImageView view=getView(viewId);
        view.setImageResource(imgResId);
        return this;
    }

    @Override
    public RecyclerViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view=getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    @Override
    public RecyclerViewHolder setImageUrl(int viewId, String imgUrl) {
        ImageView view=getView(viewId);
        Glide.with(mContext).load(imgUrl).into(view);
        return this;
    }

    @Override
    public RecyclerViewHolder setVisible(int viewId, boolean visible) {
        View view=getView(viewId);
        view.setVisibility(visible?View.VISIBLE:View.GONE);
        return this;
    }

    @Override
    public RecyclerViewHolder setVisible(int viewId, int visible) {
        View view=getView(viewId);
        view.setVisibility(viewId);
        return this;
    }

    @Override
    public RecyclerViewHolder setChecked(int viewId, boolean checked) {
        Checkable view =getView(viewId);
        view.setChecked(checked);
        return null;
    }

    @Override
    public RecyclerViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        mTransView.setOnClickListener(listener);
        return this;
    }
}
