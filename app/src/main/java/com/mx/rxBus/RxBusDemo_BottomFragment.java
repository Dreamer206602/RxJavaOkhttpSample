package com.mx.rxBus;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mx.MainActivity;
import com.mx.R;
import com.trello.rxlifecycle.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.ConnectableObservable;

/**
 * A simple {@link Fragment} subclass.
 */
public class RxBusDemo_BottomFragment extends RxFragment {


    @Bind(R.id.demo_rxbus_tap_count)
    TextView mDemoRxbusTapCount;
    @Bind(R.id.demo_rxbus_tap_txt)
    TextView mDemoRxbusTapTxt;
    private RxBus mRxBus;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRxBus = ((MainActivity) getActivity()).getRxBusSingleton();

    }

    @Override
    public void onStart() {
        super.onStart();
        ConnectableObservable<Object> tapEventEmitter = mRxBus.toObservable().publish();

        tapEventEmitter
                .compose(this.bindToLifecycle())
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (o instanceof RxBusDemoFragment.TapEvent) {
                            showTapText();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

        tapEventEmitter
                .compose(this.bindUntilEvent(FragmentEvent.DESTROY))
                //一个出发后缓存一秒内的点击数并显示的监听者
                .publish(new Func1<Observable<Object>, Observable<List<Object>>>() {
                    @Override
                    public Observable<List<Object>> call(Observable<Object> objectObservable) {
                        return objectObservable.buffer(objectObservable.debounce(1, TimeUnit.SECONDS));
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Object>>() {
                    @Override
                    public void call(List<Object> objects) {
                        showTapCount(objects.size());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });

        tapEventEmitter.connect();//可连接的Observable并在在订阅时触发，而需要调用connect（）方法

    }

    private void showTapCount(int size) {
        mDemoRxbusTapCount.setVisibility(View.VISIBLE);
        mDemoRxbusTapCount.setText(String.valueOf(size));
        mDemoRxbusTapCount.setScaleX(1f);
        mDemoRxbusTapCount.setScaleY(1f);
        ViewCompat.animate(mDemoRxbusTapCount)
                .scaleXBy(-1f)
                .scaleYBy(-1f)
                .setDuration(800)
                .setStartDelay(100);
    }

    private void showTapText() {
        mDemoRxbusTapTxt.setVisibility(View.VISIBLE);
        mDemoRxbusTapTxt.setAlpha(1f);
        ViewCompat.animate(mDemoRxbusTapTxt).alphaBy(-1f).setDuration(400);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rx_bus_demo__bottom, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
