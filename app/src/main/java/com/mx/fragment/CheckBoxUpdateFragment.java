package com.mx.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import com.f2prateek.rx.preferences.Preference;
import com.f2prateek.rx.preferences.RxSharedPreferences;
import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.mx.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 */
public class CheckBoxUpdateFragment extends Fragment {

    @Bind(R.id.cb_1)
    CheckBox mCb1;
    @Bind(R.id.btn_login)
    Button mBtnLogin;
    @Bind(R.id.cb_2)
    CheckBox mCb2;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        check_update1();
        check_update2();
    }

    private void check_update2() {

        RxCompoundButton.checkedChanges(mCb2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        mBtnLogin.setClickable(aBoolean);
                        mBtnLogin.setBackgroundResource(aBoolean ? R.color.can_login : R.color.not_login);
                    }
                });


    }

    private void check_update1() {
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getActivity());
        RxSharedPreferences rxSharedPreferences=RxSharedPreferences.create(preferences);
        Preference<Boolean>xxFunction=rxSharedPreferences.getBoolean("xxFunction",false);
        mCb1.setChecked(xxFunction.get());
        RxCompoundButton.checkedChanges(mCb1)
                .subscribe(xxFunction.asAction());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_check_box_update, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
