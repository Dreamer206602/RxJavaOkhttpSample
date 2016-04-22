package com.mx.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mx.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublishSubjectBottomFragment extends Fragment {


    @Bind(R.id.tv_result)
    TextView mTvResult;
    private PublishSubject<String> mPublishSubject;

    public PublishSubjectBottomFragment() {
        // Required empty public constructor
    }

    public PublishSubjectBottomFragment(PublishSubject<String> publishSubject) {
        mPublishSubject = publishSubject;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publish_subject_bottom, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        mPublishSubject.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                mTvResult.setText(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mTvResult.setText(throwable.getMessage().toString());
            }
        });

    }
}
