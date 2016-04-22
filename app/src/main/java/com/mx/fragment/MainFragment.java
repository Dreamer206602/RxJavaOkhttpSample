package com.mx.fragment;


import android.os.Bundle;
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
public class MainFragment extends Fragment implements View.OnClickListener {


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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;


    }

    @Override
    public void onResume() {
        super.onResume();
        mBtnNet.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_net:
                open(new NetFragment());
                break;

            case R.id.btn_net2:
                open(new Net2Fragment());
                break;
            case R.id.btn_checkbox_state_update:
                open(new CheckBoxUpdateFragment());
                break;
            case R.id.btn_text_change:
                open(new DebounceFragment());
                break;

            case R.id.btn_loop:
                open(new LoopFragment());
                break;
            case R.id.btn_merge:
                open(new MergeFragment());
                break;
            case R.id.btn_not_more_click:
                open(new NotMoreFragment());
                break;
            case R.id.btn_publish:
                open(new PublishSubjectFragment());
                break;
            case R.id.btn_timer:
                open(new TimerFragment());
                break;
            case R.id.btn_zip:
                open(new ZipFragment());
                break;
        }
    }
}
