package com.example.myapplication.bigwork;

import android.app.Application;
import android.content.Context;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MyApplication extends Application {

    private static DisplayImageOptions mLoaderOptions;
    private static RequestQueue mQueue;


    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
        mQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.
                Builder(context).
                denyCacheImageMultipleSizesInMemory().
                threadPriority(Thread.NORM_PRIORITY - 2).
                diskCacheFileNameGenerator(new Md5FileNameGenerator()).
                tasksProcessingOrder(QueueProcessingType.FIFO).
                build();
        ImageLoader.getInstance().init(config);
        mLoaderOptions = new DisplayImageOptions.Builder().
                showImageOnLoading(R.drawable.sou_suo).
                showImageOnFail(R.drawable.sou_suo).
                showImageForEmptyUri(R.drawable.sou_suo).
                imageScaleType(ImageScaleType.EXACTLY_STRETCHED).
                cacheInMemory(true).
                cacheOnDisk(true).
                considerExifParams(true).
                build();
    }



    public static RequestQueue getHttpQueue() {
        return mQueue;
    }

    public static DisplayImageOptions getLoaderOptions() {
        return mLoaderOptions;
    }

    public static void addRequest(Request request, Object tag) {
        request.setTag(tag);
        request.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(request);
    }

    public static void removeRequest(Object tag) {
        mQueue.cancelAll(tag);
    }

}
