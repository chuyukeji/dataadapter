package com.xianneng.gamebook.dataadapter.helper;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.xianneng.gamebook.dataadapter.recyclerview.RecyclerViewHolder;

/**
 * Created by xiaoxiaochu
 * 2018-03-04
 * 当调用层要显示时用该接口设置数据：文本，颜色，图片，控件，标签，字体，view的监听等
 */
 // 接口泛型为对应的holder类型，即将设置的数据先存到holder中缓存，以提高效率
public interface IRecyclerView<VH extends RecyclerViewHolder> {
    /**
     * 设置文本内容
     * @param viewId
     * @param value
     * @return
     */
    VH setText(int viewId,String value);

    /**
     * 设置文本颜色
     * @param viewId
     * @param color
     * @return
     */
    VH setTextColor(int viewId,int color);

    /**
     * 根据图片id设置图片
     * @param viewId
     * @param imgResId
     * @return
     */
    VH setImageRes(int viewId,int imgResId);

    /**
     * 根据drawable来设置图片
     * @param viewId
     * @param drawable
     * @return
     */
    VH setImageDrawable(int viewId, Drawable drawable);

    /**
     * 根据图片路径设置图片
     * @param viewId
     * @param imgUrl
     * @return
     */
    VH setImageUrl(int viewId,String imgUrl);

    /**
     * 设置控件是否显示
     * @param viewId
     * @param visible
     * @return
     */
    VH setVisible(int viewId,boolean visible);

    /**
     * 设置控件是否显示
     * @param viewId
     * @param visible
     * @return
     */
    VH setVisible(int viewId,int visible);

    /**
     * 设置选择框是否选中
     * @param viewId
     * @param checked
     * @return
     */
    VH setChecked(int viewId,boolean checked);

    /**
     * 设置view的监听
     * @param viewId
     * @return
     */
    VH setOnClickListener(int viewId, View.OnClickListener listener);


}
