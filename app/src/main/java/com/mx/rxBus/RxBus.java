package com.mx.rxBus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by boobooL on 2016/4/21 0021
 * Created 邮箱 ：boobooMX@163.com
 */
public class RxBus {

    //private final PublishSubject<Object>mPublishSubject=PublishSubject.create();//线程不安全
    private final Subject<Object,Object>mSubject=new SerializedSubject<>(PublishSubject.create());//线程安全

    public void send(Object o){
        mSubject.onNext(o);
    }

    /**
     * 获取实际的Bus对象
     * @return
     */
    public Observable<Object> toObservable(){
        return mSubject;
    }

    /**
     * 判断是否含有观察者
     * @return
     */
    public boolean hasObservables(){
        return mSubject.hasObservers();
    }




}
