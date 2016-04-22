package com.mx.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mx.R;
import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class BufferFragment extends RxFragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buffer, container, false);
    }

}
