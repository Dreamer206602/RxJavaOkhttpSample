package com.mx.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mx.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 使用schedulePeriodically做轮询请求
 */
public class PollingFragment extends Fragment {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GetDataDemo();
    }

    private void GetDataDemo() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Schedulers.newThread().createWorker().schedulePeriodically(new Action0() {
                    @Override
                    public void call() {

                    }
                },1000,1000, TimeUnit.MICROSECONDS);
            }
        })
        .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                //TODO 进行操作
            }

        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_polling, container, false);
    }

}
