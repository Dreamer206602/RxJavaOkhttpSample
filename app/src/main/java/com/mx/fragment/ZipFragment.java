package com.mx.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mx.R;
import com.mx.model.Contacts;
import com.mx.utils.XgoLog;
import com.mx.view.ProgressWheel;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * RxJav中 zip方法的练习.
 */
public class ZipFragment extends RxFragment {


    @Bind(R.id.tv_des)
    TextView mTvDes;
    @Bind(R.id.lv_list)
    ListView mLvList;
    @Bind(R.id.view_load)
    ProgressWheel mViewLoad;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getContactData();
    }

    /**
     * 获的联系人信息
     */
    private void getContactData() {

        Observable.zip(queryContactsFromLocal(), queryContactsFromNet(), new Func2<List<Contacts>, List<Contacts>, List<Contacts>>() {
            @Override
            public List<Contacts> call(List<Contacts> contactses, List<Contacts> contactses2) {
                contactses.addAll(contactses2);
                return contactses;
            }
        })
        .subscribeOn(Schedulers.io())
                //.compose(this.<List<Contacts>>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Contacts>>() {
                    @Override
                    public void call(List<Contacts> contactses) {
                        initPage(contactses);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }

    private Observable<? extends List<Contacts>> queryContactsFromNet() {
        return Observable.create(new Observable.OnSubscribe<List<Contacts>>() {
            @Override
            public void call(Subscriber<? super List<Contacts>> subscriber) {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ArrayList<Contacts>contactses=new ArrayList<Contacts>();
                contactses.add(new Contacts("net:Zeus"));
                contactses.add(new Contacts("net:boobooL"));
                contactses.add(new Contacts("net:Dreamer"));
                subscriber.onNext(contactses);
                subscriber.onCompleted();
            }
        });
    }

    private Observable<? extends List<Contacts>> queryContactsFromLocal() {

        return  Observable.create(new Observable.OnSubscribe<List<Contacts>>() {
            @Override
            public void call(Subscriber<? super List<Contacts>> subscriber) {
                ArrayList<Contacts>contactses=new ArrayList<Contacts>();
                contactses.add(new Contacts("local:张三"));
                contactses.add(new Contacts("local:李四"));
                contactses.add(new Contacts("local:王五"));
                subscriber.onNext(contactses);
                subscriber.onCompleted();
            }
        });
    }

    private void initPage(List<Contacts> contactses) {
        mViewLoad.setVisibility(View.GONE);
        XgoLog.d(contactses.toString());
        mLvList.setAdapter(new ArrayAdapter<Contacts>(getActivity(),R.layout.item_list,R.id.tv_text,contactses));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zip, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
