package com.mx.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mx.R;

import rx.subjects.PublishSubject;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublishSubjectFragment extends Fragment {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        PublishSubject<String> publishSubject = PublishSubject.create();
        PublishSubjectTopFragment topFragment = new PublishSubjectTopFragment(publishSubject);
        PublishSubjectBottomFragment bottomFragment = new PublishSubjectBottomFragment(publishSubject);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_top, topFragment, "top")
                .replace(R.id.fl_bottom, bottomFragment, "bottom").commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publish_subject, container, false);
    }

}
