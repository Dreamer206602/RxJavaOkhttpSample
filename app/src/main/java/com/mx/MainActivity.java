package com.mx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mx.fragment.MainFragment;
import com.mx.rxBus.RxBus;

public class MainActivity extends AppCompatActivity {

    private RxBus mRxBus;

    public RxBus getRxBusSingleton() {
        if (mRxBus == null) {
            mRxBus = new RxBus();
        }
        return mRxBus;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_parent,
                new MainFragment(), MainFragment.class.getName()).commit();
    }
}
