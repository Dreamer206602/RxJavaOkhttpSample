package com.mx.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mx.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subjects.PublishSubject;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublishSubjectTopFragment extends Fragment {

    @Bind(R.id.et_input)
    EditText mEtInput;
    @Bind(R.id.btn_send)
    Button mBtnSend;
    private PublishSubject<String> mPublishSubject;

    public PublishSubjectTopFragment() {
    }

    public PublishSubjectTopFragment(PublishSubject<String> publishSubject) {
        mPublishSubject = publishSubject;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publish_subject_top, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @OnClick(R.id.btn_send)
    public void sendToBottom(){
        String result=mEtInput.getText().toString().trim();
        mPublishSubject.onNext(result);
    }
}
