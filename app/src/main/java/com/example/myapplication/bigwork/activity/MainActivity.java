package com.example.myapplication.bigwork.activity;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.bigwork.HomeService;
import com.example.myapplication.bigwork.MyBroadcastReceiver;
import com.example.myapplication.bigwork.MyService;
import com.example.myapplication.bigwork.R;
import com.example.myapplication.bigwork.adapter.HomeTuijianRecyclerviewAdapter;
import com.example.myapplication.bigwork.fragment.CarFragment;
import com.example.myapplication.bigwork.fragment.HomeFragment;
import com.example.myapplication.bigwork.fragment.MyselfFragment;
import com.example.myapplication.bigwork.table.Goods;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

// 主页面Activity

public class MainActivity extends AppCompatActivity {

    private Random random = new Random();
    private Goods randGoods = new Goods();

    private BottomNavigationView mBottomNavigationView;
    private FragmentTransaction fragmentTransaction;
    private int lastIndex;
    private List<Fragment> fragments;

    private MyBroadcastReceiver myBroadcastReceiver;
    private MyService myService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClick_retrofit_get0();

        // 动态注册广播
        myBroadcastReceiver = new MyBroadcastReceiver();
        //定义跳转到service的方法
        turnService();

        setListener();
        initFragment();
    }

    // Main 中使用定时广播
    private void turnService(){
        Intent intent=new Intent(this,MyService.class);
        startService(intent);
    }

//    @Override
    protected void onResume(int commodityId, String tradeName){

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("MyBroadcastReceiver");

        registerReceiver(myBroadcastReceiver, intentFilter);
        Intent intent = new Intent(MainActivity.this, MyBroadcastReceiver.class);
        intent.putExtra("commodityId", commodityId);
        intent.putExtra("tradeName",tradeName);
        Log.e("onResume", "" +commodityId);
        Log.e("onResume", "" +tradeName);
        sendBroadcast(intent);

        Log.d("randGoods", "" +randGoods.getCommodityId());
        super.onResume();

    }



    /**
     * ⑧开始接收广播时动态获取权限
     */
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter=new IntentFilter();
        filter.addAction("fly.to.MainAcvitity");
        registerReceiver(myBroadcastReceiver,filter);
    }

    /**
     * ⑨结束广播时释放资源，解除绑定
     */
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }

    //

    public void onClick_retrofit_get0() {
        //step1:实例化Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //step2:获取APIService实例
        HomeService apiService = retrofit.create(HomeService.class);

        //step3:通过apiService调用call  http://gank.io/api/data/Android/10/1
        Call<List<Goods>> gankCall= apiService.getAllGoods();

        //step4:通过异步获取数据
        gankCall.enqueue(new Callback<List<Goods>>() {
            @Override
            public void onResponse(Call<List<Goods>> call, Response<List<Goods>> response) {
                Log.e(TAG, "onResponse: 地址请求成功！！！" );
                //商品
                Log.d("111111", "onResponse: "+ response.body());

                List<Goods> goods = response.body();

                Log.d("goods.get", "" +goods.get(random.nextInt(goods.size())));
                randGoods = goods.get(random.nextInt(goods.size()));

                Log.d("randGoods", " " + randGoods.getCommodityId());
//                Log.d("randGoods", "" +goods.get(random.nextInt(goods.size())));
                // 发生跳转传输数据设置
                onResume(randGoods.getCommodityId(), randGoods.getTradeName());
//                Log.d("randGoods", "" +randGoods.getCommodityId());

            }
            @Override
            public void onFailure(Call<List<Goods>> call, Throwable t) {
                Log.e(TAG, "onFailure: 请求失败！！！！~~~~~" );
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });

    }

    public void setListener() {
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        // 解决当item大于三个时，非平均布局问题
        // BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                setFragmentPosition(0);
                                break;
                            case R.id.navigation_dashboard:
                                setFragmentPosition(1);
                                break;
                            case R.id.navigation_car:
                                setFragmentPosition(2);
                                break;
                            default:
                                break;
                        }
                        // 这里注意返回true,否则点击失效
                        return true;
                    }
                });
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new MyselfFragment());
        fragments.add(new CarFragment());
        // 初始化展示MessageFragment
        setFragmentPosition(0);
    }

    // 回收 Fragment
    private void setFragmentPosition(int position){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = fragments.get(position);
        Fragment lastFragment = fragments.get(lastIndex);
        lastIndex = position;
        fragmentTransaction.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            fragmentTransaction.add(R.id.fl_content, currentFragment);
        }
        fragmentTransaction.show(currentFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
