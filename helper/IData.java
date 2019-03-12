package com.xianneng.gamebook.dataadapter.helper;

import java.util.List;

/**
 * Created by xiaoxiaochu
 * 2018-03-05
 */
public interface IData<T> {

    boolean addAll(List<T> list);

    boolean addAll(int position,List<T> list);

    void add(T data);

    void add(int position,T data);

    void clear();

    boolean contains(T data);

    T getData(int index);

    // 修改
    void modify(T oldData,T newData);

    void modify(int index,T newData);

    boolean remove(T data);

    void remove(int index);
}
