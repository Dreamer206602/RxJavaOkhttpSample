package com.mx.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MergeFragment extends Fragment {


    @Bind(R.id.tv_des)
    TextView mTvDes;
    @Bind(R.id.lv_list)
    ListView mLvList;
    @Bind(R.id.view_load)
    ProgressWheel mViewLoad;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mergeDemo();

    }

    private void mergeDemo() {

        Observable.merge(getDataFromLocal(),getDataFromNet())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Contacts>>() {
                    @Override
                    public void call(List<Contacts> contactses) {
                            initData(contactses);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });




    }

    private Observable<List<Contacts>> getDataFromNet() {
        return Observable.create(new Observable.OnSubscribe<List<Contacts>>() {
            @Override
            public void call(Subscriber<? super List<Contacts>> subscriber) {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                List<Contacts>contacts=new ArrayList<Contacts>();
                contacts.add(new Contacts("net:"+"android"));
                contacts.add(new Contacts("net:"+"ios"));
                contacts.add(new Contacts("net:"+"swift"));
                subscriber.onNext(contacts);
                subscriber.onCompleted();
            }
        });
    }

    private Observable<List<Contacts>> getDataFromLocal() {
        return Observable.create(new Observable.OnSubscribe<List<Contacts>>() {
            @Override
            public void call(Subscriber<? super List<Contacts>> subscriber) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                List<Contacts>contactses=new ArrayList<Contacts>();
                contactses.add(new Contacts("local:"+"tom"));
                contactses.add(new Contacts("local:"+"kemmy"));
                contactses.add(new Contacts("local:"+"joke"));
                subscriber.onNext(contactses);
                subscriber.onCompleted();

            }
        });
    }


    private void initData(List<Contacts> contactses) {

        mViewLoad.setVisibility(View.GONE);
        XgoLog.d(contactses.toString());
        mLvList.setAdapter(new ArrayAdapter<>(getActivity(),R.layout.item_list,R.id.tv_text,contactses));


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_merge, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
