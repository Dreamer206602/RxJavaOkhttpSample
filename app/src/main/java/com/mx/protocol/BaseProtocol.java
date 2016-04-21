package com.mx.protocol;


import android.text.TextUtils;

import com.mx.net.interceptor.XgoHttpClient;
import com.squareup.okhttp.Request;

import java.util.TreeMap;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by boobooL on 2016/4/21 0021
 * Created 邮箱 ：boobooMX@163.com
 */
public abstract class BaseProtocol {

    protected Observable<String> createObservable(final String url, final String method, final TreeMap<String,Object>params){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

                Request request= XgoHttpClient.getClient().getRequest(url,method,params);
                String json=XgoHttpClient.getClient().execute2String(request);
                setData(subscriber,json);
            }
        }).subscribeOn(Schedulers.io());//
    }

    /**
     * 为观察者（订阅者）设置返回的数据
     * @param subscriber
     * @param json
     */
    private void setData(Subscriber<? super String> subscriber, String json) {
        if(TextUtils.isEmpty(json)){
            subscriber.onError(new Throwable("not data"));
            subscriber.onCompleted();
            return;
        }
        subscriber.onNext(json);
        subscriber.onCompleted();

    }


}



