package com.mx.rxBus;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mx.MainActivity;
import com.mx.R;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RxBusDemo_TopFragment extends RxFragment {


    @Bind(R.id.btn_demo_rxbus_tap)
    Button mBtnDemoRxbusTap;
    private RxBus mRxBus;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRxBus = ((MainActivity) getActivity()).getRxBusSingleton();

    }

    @OnClick(R.id.btn_demo_rxbus_tap)
    public void onTopButtonClick() {
        if (mRxBus.hasObservables()) {
            //判断是否有观察者，有，则发送，否则，不发送
            mRxBus.send(new RxBusDemoFragment.TapEvent());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rx_bus_demo__top, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
