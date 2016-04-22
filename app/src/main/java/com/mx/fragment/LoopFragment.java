package com.mx.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Scroller;

import com.mx.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Loop轮询器Demo
 */
public class LoopFragment extends Fragment {


    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.btn_start_loop)
    Button mBtnStartLoop;
    @Bind(R.id.btn_stop_loop)
    Button mBtnStopLoop;
    private Subscription mSubscription;

    private List<ImageView>mImageViews=new ArrayList<>();

    private int[] datas=new int[]{R.mipmap.pic_1,R.mipmap.pic_2,R.mipmap.pic_3};

    private PicLoopAdapter mAdapter;
    public class PicLoopAdapter extends PagerAdapter{

        private int[] mDatas;

        public PicLoopAdapter(int[] datas) {
            mDatas = datas;
        }


        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int index=position%mDatas.length;
            ImageView iv;
            if(mImageViews.size()>0){
                iv=mImageViews.remove(0);

            }else{
                iv=new ImageView(getActivity());
                iv.setLayoutParams(new ViewPager.LayoutParams());
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
            iv.setImageResource(mDatas[index]);
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
            mImageViews.add((ImageView) object);
        }
    }

    /**
     * 自定义Scroller，用于调节ViewPager的滑动速度
     */
    public class ViewPagerScroller extends Scroller{

        private int mScrollerDuration=1200;//滑动速度


        public ViewPagerScroller(Context context) {
            super(context);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        public ViewPagerScroller(Context context, Interpolator interpolator, boolean flywheel) {
            super(context, interpolator, flywheel);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mScrollerDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy,mScrollerDuration);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loop, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewPager();
        startLoop();
    }

    @OnClick(R.id.btn_start_loop)
    public void startLoop(){
        autoLoop();
    }

    private void autoLoop() {
        if(mSubscription==null||mSubscription.isUnsubscribed()){
            mSubscription= rx.Observable.interval(3000,3000,//延时3000，每间隔3000
                    TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<Long>() {
                        @Override
                        public void call(Long aLong) {
                            int currentIndex = mViewpager.getCurrentItem();
                            if (++currentIndex == mAdapter.getCount()) {
                                mViewpager.setCurrentItem(0);
                            } else {
                                mViewpager.setCurrentItem(currentIndex, true);
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {

                        }
                    });
        }
    }

    @OnClick(R.id.btn_stop_loop)
    public void stopLoop(){
        if(mSubscription!=null&&!mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }
    private void initViewPager()  {
        mAdapter=new PicLoopAdapter(datas);
        mViewpager.setAdapter(mAdapter);


        try {
            Field mScrollerField = ViewPager.class.getDeclaredField("mScroller");
            mScrollerField.setAccessible(true);
            mScrollerField.set(mViewpager,new ViewPagerScroller(mViewpager.getContext()));

        }catch (NoSuchFieldException es){
            es.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch ( Exception e){
            e.printStackTrace();
        }


    }
}
