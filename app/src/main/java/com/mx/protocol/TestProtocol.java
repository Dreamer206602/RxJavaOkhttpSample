package com.mx.protocol;


import com.mx.net.interceptor.XgoHttpClient;

import java.util.TreeMap;

import rx.Observable;

/**
 * Created by boobooL on 2016/4/21 0021
 * Created 邮箱 ：boobooMX@163.com
 */
public class TestProtocol extends BaseProtocol {

    public static final String BASE_URL="http://service.test.xgo.com.cn:8080/app/v1/demo/";


    /**
     * 测试“GET”请求
     * @return
     */
    public Observable<String> test_Get(){
        String path="1";
        return createObservable(BASE_URL+path, XgoHttpClient.METHOD_GET,null);
    }

    /**
     * 测试POST的请求
     * @param params
     * @return
     */
    public Observable<String>test_post(TreeMap<String,Object>params){
        return createObservable(BASE_URL,XgoHttpClient.METHOD_POST,params);
    }

    /**
     * 测试Put的请求
     * @param params
     * @return
     */
    public Observable<String>text_Put(TreeMap<String,Object>params){
        return createObservable(BASE_URL,XgoHttpClient.METHOD_PUT,params);
    }


    /**
     * 测试Delete方法
     * @return
     */
    public Observable<String>test_delete(){
        return createObservable(BASE_URL+"1",XgoHttpClient.METHOD_DELETE,null);
    }




}
