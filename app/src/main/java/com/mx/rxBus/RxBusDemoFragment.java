package com.mx.rxBus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mx.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RxBusDemoFragment extends Fragment {


    public RxBusDemoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rx_bus_demo, container, false);
    }

}