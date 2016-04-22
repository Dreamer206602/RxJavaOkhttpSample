package com.mx.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.mx.R;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 */
public class BufferFragment extends RxFragment {


    @Bind(R.id.btn_buffer_count)
    Button mBtnBufferCount;
    @Bind(R.id.et_input)
    EditText mEtInput;
    @Bind(R.id.btn_buffer_count_skip)
    Button mBtnBufferCountSkip;
    @Bind(R.id.tv_output)
    TextView mTvOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_buffer, container, false);
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

        demo_buffer_count();
    }

    private void demo_buffer_count() {
        RxView.clicks(mBtnBufferCount)
                .buffer(3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Void>>() {
                    @Override
                    public void call(List<Void> voids) {
                        Toast.makeText(BufferFragment.this.getActivity(), R.string.des_demo_buffer_count, Toast.LENGTH_SHORT).show();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

    }

    @OnClick(R.id.btn_buffer_count_skip)
    public void  demo_bufer_count_skip(){
        mTvOutput.setText("");
        char[] cs=mEtInput.getText().toString().trim().toCharArray();
        Character[] chs=new Character[cs.length];
        for (int i = 0; i <chs.length ; i++) {
            chs[i]=cs[i];

        }
        Observable.from(chs)
                .buffer(2,3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Character>>() {
                    @Override
                    public void call(List<Character> characters) {
                     mTvOutput.setText(mTvOutput.getText()+characters.toString());
                    }
                });
    }
}
