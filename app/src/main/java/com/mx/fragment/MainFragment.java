package com.mx.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mx.R;
import com.mx.rxBus.RxBusDemoFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {


    @Bind(R.id.btn_net)
    Button mBtnNet;
    @Bind(R.id.btn_net2)
    Button mBtnNet2;
    @Bind(R.id.btn_not_more_click)
    Button mBtnNotMoreClick;
    @Bind(R.id.btn_checkbox_state_update)
    Button mBtnCheckboxStateUpdate;
    @Bind(R.id.btn_text_change)
    Button mBtnTextChange;
    @Bind(R.id.btn_buffer)
    Button mBtnBuffer;
    @Bind(R.id.btn_zip)
    Button mBtnZip;
    @Bind(R.id.btn_merge)
    Button mBtnMerge;
    @Bind(R.id.btn_loop)
    Button mBtnLoop;
    @Bind(R.id.btn_timer)
    Button mBtnTimer;
    @Bind(R.id.btn_publish)
    Button mBtnPublish;
    @Bind(R.id.btn_rxbus)
    Button mBtnRxbus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void open(Fragment fragment) {
        final String tag = fragment.getClass().toString();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.main_parent, fragment, tag)
                .commit();
    }

    @OnClick(R.id.btn_net)
    public void test_net() {
        open(new NetFragment());
    }

    @OnClick(R.id.btn_net2)
    public void test_net2() {
        open(new Net2Fragment());
    }

    @OnClick(R.id.btn_checkbox_state_update)
    public void test_checkbox_state_Update() {
        open(new CheckBoxUpdateFragment());
    }

    @OnClick(R.id.btn_text_change)
    public void test_TextChange() {
        open(new DebounceFragment());
    }

    @OnClick(R.id.btn_loop)
    public void test_loop() {
        open(new LoopFragment());
    }

    @OnClick(R.id.btn_merge)
    public void test_merge() {
        open(new MergeFragment());
    }

    @OnClick(R.id.btn_not_more_click)
    public void test_not_more() {
        open(new NotMoreFragment());
    }

    @OnClick(R.id.btn_publish)
    public void test_publish() {
        open(new PublishSubjectFragment());
    }

    @OnClick(R.id.btn_timer)
    public void test_timer() {
        open(new TimerFragment());
    }

    @OnClick(R.id.btn_zip)
    public void test_zip() {
        open(new ZipFragment());
    }


    @OnClick(R.id.btn_rxbus)
    public void test_RxBus() {
        open(new RxBusDemoFragment());
    }

    @OnClick(R.id.btn_buffer)
    public void test_buffer() {
        open(new BufferFragment());
    }


}
