package com.mx.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mx.R;
import com.mx.protocol.TestProtocol;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.TreeMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class NetFragment extends RxFragment implements View.OnClickListener {


    @Bind(R.id.tv_msg)
    TextView mTvMsg;
    @Bind(R.id.btn_get)
    Button mBtnGet;
    @Bind(R.id.btn_post)
    Button mBtnPost;
    @Bind(R.id.btn_put)
    Button mBtnPut;
    @Bind(R.id.btn_delete)
    Button mBtnDelete;
    @Bind(R.id.tv_result)
    TextView mTvResult;

    private TestProtocol mTestProtocol;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mTestProtocol = new TestProtocol();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_net, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mBtnGet.setOnClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get:
                mTestProtocol.test_Get()
                        .compose(this.<String>bindToLifecycle())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                mTvResult.setText("Get Result:\r\n" + s);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mTvResult.setText("Get Error:\r\n" + throwable.getMessage());
                            }
                        });
                break;

            case R.id.btn_post:
                TreeMap<String,Object>params=new TreeMap<>();
                params.put("name","Zeus");
                mTestProtocol.test_post(params)
                        .compose(this.<String>bindToLifecycle())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                mTvResult.setText("Get Result:\r\n" + s);

                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mTvResult.setText("Get Error:\r\n" + throwable);
                            }
                        });
                break;

            case R.id.btn_put:
                TreeMap<String,Object>params_put=new TreeMap<>();
                params_put.put("name","Zeus");
                mTestProtocol.text_Put(params_put)
                        .compose(this.<String>bindToLifecycle())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                mTvResult.setText("Get Result:\r\n" + s);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mTvResult.setText("Get Error:\r\n" + throwable);
                            }
                        });
                break;
            case R.id.btn_delete:
                mTestProtocol.test_delete()
                        .compose(this.<String>bindToLifecycle())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Action1<String>() {
                            @Override
                            public void call(String s) {
                                mTvResult.setText("Get Result:\r\n" + s);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                mTvResult.setText("Get Error:\r\n" + throwable);
                            }
                        });
                break;
        }
    }
}
