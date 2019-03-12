package com.xianneng.gamebook.dataadapter.helper;


import android.graphics.drawable.Drawable;
import android.view.View;

import com.xianneng.gamebook.dataadapter.listview.ListViewHolder;

/**
 * Created by xiaoxiaochu
 * 2018-03-04
 */
public interface IListView<VH extends ListViewHolder> {
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
