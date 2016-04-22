package com.mx.rxBus;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mx.R;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RxBusDemoFragment extends RxFragment{


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.demo_rxbus_frag_1,new RxBusDemo_TopFragment())
                .replace(R.id.demo_rxbus_frag_2,new RxBusDemo_BottomFragment()).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rx_bus_demo, container, false);
    }

    public static class TapEvent{

    }

}
