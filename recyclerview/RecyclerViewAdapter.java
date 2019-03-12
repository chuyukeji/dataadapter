package com.xianneng.gamebook.dataadapter.recyclerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xianneng.gamebook.dataadapter.helper.IData;

import java.util.List;

/**
 * Created by xiaoxiaochu
 * 2018-03-05
 *
 * 以RecyclerView形式显示数据的适配器，将holder内的数据适配到调用层
 */
public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> implements IData<T> {
    protected Context mContext;
    protected List<T> mList;
    protected int[] layoutIds;
    protected LayoutInflater inflater;

    private SparseArray<View> mTransViews=new SparseArray<>();
    public RecyclerViewAdapter(Context context,List<T> list,int... layoutIds) {
        this.mContext=context;
        this.mList=list;
        this.layoutIds=layoutIds;
        this.inflater=LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType<0||viewType>layoutIds.length){
            throw new ArrayIndexOutOfBoundsException("layoutIndex");
        }
        if (layoutIds.length==0){
            throw new IllegalArgumentException("not layoutId");
        }
        int layoutId=layoutIds[viewType];
        View view=mTransViews.get(layoutId);
        if (view==null){
            view=inflater.inflate(layoutId,parent,false);
        }
        RecyclerViewHolder holder=(RecyclerViewHolder)view.getTag();
        if (holder==null||holder.getLayoutId()!=layoutId){
            holder=new RecyclerViewHolder(view,layoutId,mContext);
            return holder;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        T item = mList.get(position);
        onBindData(holder,position,item);
    }

    protected abstract void onBindData(RecyclerViewHolder holder, int position, T item);

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    /**
     * 指定item的布局样式在layoutIds的索引。默认为第一个
     * @param position
     * @param item
     * @return
     */
    public int getLayoutIndex(int position,T item){
        return 0;
    }


    /*
    以下为对数据接口的实现
    */

    @Override
    public boolean addAll(List<T> list) {
        boolean result = mList.addAll(list);
        notifyDataSetChanged();
        return result;
    }

    @Override
    public boolean addAll(int position, List<T> list) {
        boolean result = mList.addAll(position, list);
        notifyDataSetChanged();
        return result;
    }

    @Override
    public void add(T data) {
        mList.add(data);
        notifyDataSetChanged();
    }

    @Override
    public void add(int position, T data) {
        mList.add(position,data);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public boolean contains(T data) {
        return mList.contains(data);
    }

    @Override
    public T getData(int index) {
        T t = mList.get(index);
        return t;
    }

    @Override
    public void modify(T oldData, T newData) {
        modify(mList.indexOf(oldData),newData);
    }

    @Override
    public void modify(int index, T newData) {
        mList.set(index,newData);
        notifyDataSetChanged();
    }

    @Override
    public boolean remove(T data) {
        boolean result = mList.remove(data);
        notifyDataSetChanged();
        return result;
    }

    @Override
    public void remove(int index) {
        mList.remove(index);
        notifyDataSetChanged();
    }
}
