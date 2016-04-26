package com.mx.fragment;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mx.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SchedulerFragment extends Fragment {


    @Bind(R.id.btn)
    Button mBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scheduler, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CountDownTimer(10000,1000){
                    @Override
                    public void onTick(long millisUntilFinished) {
                        //第一个参数是总的倒计时时间
                        //第二个参数是每隔多长时间(ms)调用一次onTick()方法
                        mBtn.setText(millisUntilFinished/1000+"s后重新发送");
                        mBtn.setEnabled(false);

                    }

                    @Override
                    public void onFinish() {
                        mBtn.setText("重新获取验证码");
                        mBtn.setEnabled(true);
                    }
                }.start();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
