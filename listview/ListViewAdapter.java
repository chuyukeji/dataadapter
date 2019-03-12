package com.xianneng.gamebook.dataadapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xianneng.gamebook.dataadapter.helper.IData;

import java.util.List;

/**
 * Created by xiaoxiaochu
 * 2018-03-05
 * 以ListView形式显示数据的适配器，将holder内的数据适配到调用层
 */
public abstract class ListViewAdapter<T> extends BaseAdapter implements IData<T> {

    protected Context mContext;
    protected List<T> mList;
    protected int[] layoutIds;
    protected LayoutInflater inflater;

    protected ListViewHolder holder=new ListViewHolder();

    public ListViewAdapter(Context context, List<T> mList, int[] layoutIds) {
        this.mContext = context;
        this.mList = mList;
        this.layoutIds = layoutIds;
        this.inflater = LayoutInflater.from(mContext);

    }

    public ListViewAdapter(Context context, List<T> mList) {
        this.mContext = context;
        this.mList = mList;
        this.inflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList==null?null:mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int layoutId=getViewCheckLayoutId(position);
        holder=holder.get(mContext,position,convertView,parent,layoutId);
        convert(holder,position,mList.get(position));
        return holder.getmTransView(layoutId);
    }

    public abstract void convert(ListViewHolder holder, int position, T t);

    private int getViewCheckLayoutId(int position) {
        int layoutId;
        if (layoutIds==null||layoutIds.length==0){
            // 若layoutIds为null，则指定layoutId为0
            layoutId=getLayoutId(position,mList.get(position));
        }else {
            layoutId=layoutIds[getLayoutIndex(position,mList.get(position))];
        }
        return layoutId;
    }
    // 若构造函数没有指定layoutIds,即调用的是第二个人没有layoutIds参数的构造方法，则要重写该方法
    public int getLayoutId(int position,T item){
        return 0;
    }
    // 若layoutIds不为null，即调用第一个构造方法的，指定item布局样式在layoutIds的索引，默认为第一个
    public int getLayoutIndex(int position,T item){
        return 0;
    }


    /*以下是对数据接口的实现*/

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
        boolean result = mList.contains(data);
        notifyDataSetChanged();
        return result;
    }

    @Override
    public T getData(int index) {
        T result = mList.get(index);
        return result;
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
