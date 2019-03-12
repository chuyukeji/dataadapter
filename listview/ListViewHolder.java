package com.xianneng.gamebook.dataadapter.listview;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xianneng.gamebook.dataadapter.helper.IListView;

/**
 * Created by xiaoxiaochu
 * 2018-03-04
 * 以ListView形式显示数据时做的缓存holder
 */
public class ListViewHolder implements IListView<ListViewHolder>{
    // 普通的view集合
    private SparseArray<View> mViews=new SparseArray<>();
    // 通过布局id的子项的view集合
    private SparseArray<View> mTransViews=new SparseArray<>();

    private View mTransView;
    protected int mPosition;
    protected int mLayoutId;
    protected Context mContext;

    protected ListViewHolder(Context mContext, int mPosition, ViewGroup parent,int mLayoutId) {
        this.mTransView=mTransViews.get(mLayoutId);
        this.mPosition = mPosition;
        this.mLayoutId = mLayoutId;
        this.mContext = mContext;
        if (mTransView==null){
            // 子项布局的view对象
            mTransView= LayoutInflater.from(mContext).inflate(mLayoutId,parent,false);
            mTransViews.put(mLayoutId,mTransView);
            mTransView.setTag(this);
        }
    }

    public ListViewHolder() {
    }

    // 获取(缓存有对象作用的)holder
    public <VH extends ListViewHolder> VH get(Context context,int position,View transView, ViewGroup parent, int layoutId){
        if (transView==null){
            return (VH) new ListViewHolder(context,position,parent,layoutId);
        }else {
           ListViewHolder holder=(ListViewHolder) transView.getTag();
           if (holder.mLayoutId!=layoutId){
               return (VH) new ListViewHolder(context,position,parent,layoutId);
           }
           holder.setPosition(position);
           return (VH)holder;
        }
    }

    private void setPosition(int position) {
        this.mPosition=position;
    }

    /**
     * 获取的是子项布局的view对象
     * @return
     */
    public View getmTransView(){
        return mTransViews.valueAt(0);
    }
    public View getmTransView(int mLayoutId){
        return mTransViews.get(mLayoutId);
    }

    /**
     * 获取的是普通的view对象
     */
    public <V extends View> V getView(int viewId){
        View view=mViews.get(viewId);
        if (view==null){
            view=mTransView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (V)view;
    }

    /**
     *
     * 以下这些是接口方法的实现
     */
    @Override
    public ListViewHolder setText(int viewId, String value) {
        TextView view=getView(viewId);
        view.setText(value);
        return this;
    }

    @Override
    public ListViewHolder setTextColor(int viewId, int color) {
        TextView view=getView(viewId);
        view.setTextColor(color);
        return this;
    }

    @Override
    public ListViewHolder setImageRes(int viewId, int imgResId) {
        ImageView view = getView(viewId);
        view.setImageResource(imgResId);
        return this;
    }

    @Override
    public ListViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view=getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    @Override
    public ListViewHolder setImageUrl(int viewId, String imgUrl) {
        ImageView view=getView(viewId);
        Glide.with(mContext).load(imgUrl).into(view);
        return this;
    }

    @Override
    public ListViewHolder setVisible(int viewId, boolean visible) {
        View view=getView(viewId);
        view.setVisibility(visible?View.VISIBLE:View.GONE);
        return this;
    }

    @Override
    public ListViewHolder setVisible(int viewId, int visible) {
        View view=getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    @Override
    public ListViewHolder setChecked(int viewId, boolean checked) {
        Checkable view=getView(viewId);
        view.setChecked(checked);
        return this;
    }

    @Override
    public ListViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view=getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}
