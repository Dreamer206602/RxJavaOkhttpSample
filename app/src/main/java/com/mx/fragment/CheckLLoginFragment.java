package com.mx.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.mx.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.functions.Func3;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckLLoginFragment extends Fragment {

    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.et_email)
    EditText mEtEmail;
    @Bind(R.id.btn_login)
    Button mBtnLogin;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_llogin, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        CheckLoginDemo();
    }

    private void CheckLoginDemo() {

        Observable<CharSequence>nameChangeObservable= RxTextView.textChanges(mEtName).skip(1);
        Observable<CharSequence>passwordChangeObservable= RxTextView.textChanges(mEtPassword).skip(1);
        Observable<CharSequence>emailChangeObservable= RxTextView.textChanges(mEtEmail).skip(1);
        Observable.combineLatest(nameChangeObservable, passwordChangeObservable, emailChangeObservable, new Func3<CharSequence, CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean call(CharSequence name, CharSequence password, CharSequence email) {
                boolean nameValid= !TextUtils.isEmpty(name);
                if(!nameValid){
                    mEtName.setError("Invalid Name");
                }

                boolean passValid=!TextUtils.isEmpty(password)&&password.length()>8;
                if(!passValid){
                    mEtPassword.setError("Invalid Password");
                }

                boolean emailValid=!TextUtils.isEmpty(email);
                if(!emailValid){
                    mEtEmail.setError("Invalid Email");
                }

                return nameValid&&passValid&&emailValid;
            }
        })
        .subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {

                mBtnLogin.setEnabled(aBoolean);

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
