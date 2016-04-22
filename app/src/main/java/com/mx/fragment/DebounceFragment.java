package com.mx.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.mx.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * RxJava实现关键字搜索功能
 */
public class DebounceFragment extends Fragment {


    @Bind(R.id.et_search)
    EditText mEtSearch;
    @Bind(R.id.iv_x)
    ImageView mIvX;
    @Bind(R.id.lv_list)
    ListView mLvList;
    private ArrayAdapter<String>mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchKeyWordDemo();
    }

    private void searchKeyWordDemo() {
        RxTextView.textChangeEvents(mEtSearch)
                .debounce(300, TimeUnit.MILLISECONDS)
                //.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {

                        String key = textViewTextChangeEvent.text().toString().trim();
                        if (TextUtils.isEmpty(key)) {
                            mIvX.setVisibility(View.GONE);
                            if (mAdapter != null) {
                                mAdapter.clear();
                                mAdapter.notifyDataSetChanged();
                            }
                        } else {
                            mIvX.setVisibility(View.VISIBLE);
                            getKeyWordFormNet(key)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Action1<List<String>>() {
                                        @Override
                                        public void call(List<String> strings) {

                                            initPage(strings);
                                        }
                                    });

                        }

                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });



    }

    private void initPage(List<String> strings) {

        if(mAdapter==null){
            mAdapter=new ArrayAdapter<String>(getActivity(),R.layout.item_list,
                    R.id.tv_text,strings);
            mLvList.setAdapter(mAdapter);
            mLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getActivity(),"搜素:"+mAdapter.getItem(position),Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            mAdapter.clear();
            mAdapter.addAll(strings);
            mAdapter.notifyDataSetChanged();
        }
    }

    private Observable<List<String>> getKeyWordFormNet(final String key) {
        return Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {

                //这里是网络请求的操作
                List<String>datas=new ArrayList<String>();
                for (int i = 0; i <20 ; i++) {
                    datas.add("KeyWord:"+key+i);
                }
                subscriber.onNext(datas);
                subscriber.onCompleted();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_debounce, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.iv_x)
    public void clear(){
        mEtSearch.setText("");
    }
}
