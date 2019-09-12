package com.example.myapplication.bigwork.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.bigwork.HomeRecycleView;
import com.example.myapplication.bigwork.HomeService;
import com.example.myapplication.bigwork.MyBroadcastReceiver;
import com.example.myapplication.bigwork.R;
import com.example.myapplication.bigwork.activity.CommodityDetailsActivity;
import com.example.myapplication.bigwork.activity.MainActivity;
import com.example.myapplication.bigwork.adapter.HomeTuijianRecyclerviewAdapter;
import com.example.myapplication.bigwork.table.Commodit;
import com.example.myapplication.bigwork.table.Goods;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements OnBannerListener {

    private View view;
    private RefreshLayout hRefreshLayout;

    // 随机数
    private Random random = new Random();
    private int randId;

    private MyImageLoader hMyImageLoader;
    private Banner hBanner;
    private ArrayList<Integer> bannerImagePath;
    private ArrayList<String> bannerImageTitle;

    private HomeRecycleView tuijianRecyclerView;
    //定义以goodsentity实体类为对象的数据集合
    private ArrayList<Commodit> commoditList = new ArrayList<>();

    private ArrayList<Goods> goodsList = new ArrayList<>();
    //自定义recyclerveiw的适配器
    private HomeTuijianRecyclerviewAdapter homeTuijianRecyclerviewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_home, container, false);
        //获取fragment的layout
        view = inflater.inflate(R.layout.fragment_home, container, false);

//        对recycleview进行配置
//        initRecyclerView();
//        模拟数据

        // 上拉刷新
        shangLaShuaXin();

        return view;
    }

    private void shangLaShuaXin() {
        hRefreshLayout = view.findViewById(R.id.refreshLayout);
        hRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                // 需要刷新的方法
                onClick_retrofit_get0(tuijianRecyclerView);
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        hRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tuijianRecyclerView = view.findViewById(R.id.hf_rv_tuijian_goods);
        onClick_retrofit_get0(tuijianRecyclerView);

        initBannerData();
        initBannerView();

        //tuijianRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
    }

    // Banner 点击事件
    @Override
    public void OnBannerClick(int position) {

    }

    // Banner 图片加载
    class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

    // Banner 视图
    private void initBannerView() {
        hMyImageLoader = new MyImageLoader();
        hBanner = getView().findViewById(R.id.hf_banner);
        //设置样式，里面有很多种样式可以自己都看看效果
        hBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        hBanner.setImageLoader(hMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        hBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        hBanner.setBannerTitles(bannerImageTitle);
        //设置轮播间隔时间
        hBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        hBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        hBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        hBanner.setImages(bannerImagePath)
                //轮播图的监听
                .setOnBannerListener(this)
                //开始调用的方法，启动轮播图。
                .start();
    }

    private void initBannerData() {
        bannerImagePath = new ArrayList<>();
        bannerImageTitle = new ArrayList<>();
        bannerImagePath.add(R.drawable.jia);
        bannerImageTitle.add("");
        bannerImagePath.add(R.drawable.luoji);
        bannerImageTitle.add("");
        bannerImagePath.add(R.drawable.jiadian);
        bannerImageTitle.add("");
        bannerImagePath.add(R.drawable.yifu);
        bannerImageTitle.add("");
    }

    // 推荐 RecyclerView 的地盘

    public void onClick_retrofit_get0(final View view) {
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
                Log.d("List<Goods>goods", goods+ "");

                tuijianRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                HomeTuijianRecyclerviewAdapter homeTuijianRecyclerviewAdapter = new HomeTuijianRecyclerviewAdapter(getContext(),goods);
                tuijianRecyclerView.setAdapter(homeTuijianRecyclerviewAdapter);



//                Goods randGoods = goods.get(random.nextInt(goods.size()));
////                // 发生跳转传输数据设置
////                Intent intent = new Intent(getActivity(), MyBroadcastReceiver.class);
////                intent.putExtra("commodityId",randGoods.getCommodityId());
////                startActivity(intent);
////                Log.d("randGoods", "" +randGoods.getCommodityId());

            }
            @Override
            public void onFailure(Call<List<Goods>> call, Throwable t) {
                Log.e(TAG, "onFailure: 请求失败！！！！~~~~~" );
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });

    }

    private void initRecyclerViewData() {
        for (int i=0;i<10;i++){
            Goods goods =new Goods();
            goods.setTradeName("模拟数据"+i);
            goods.setDiscountPrice(1000 + i);
            goodsList.add(goods);
        }
    }

    private void initRecyclerView() {
        //获取RecyclerView
        tuijianRecyclerView= view.findViewById(R.id.hf_rv_tuijian_goods);
        //创建adapter
        homeTuijianRecyclerviewAdapter = new HomeTuijianRecyclerviewAdapter(getActivity(), goodsList);
        //给RecyclerView设置adapter
        tuijianRecyclerView.setAdapter(homeTuijianRecyclerviewAdapter);
        //设置layoutManager,可以设置显示效果，是线性布局、grid布局，还是瀑布流布局
        //参数是：上下文、列表方向（横向还是纵向）、是否倒叙
        tuijianRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //设置item的分割线
        tuijianRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        //RecyclerView中没有item的监听事件，需要自己在适配器中写一个监听事件的接口。参数根据自定义
    }



}
