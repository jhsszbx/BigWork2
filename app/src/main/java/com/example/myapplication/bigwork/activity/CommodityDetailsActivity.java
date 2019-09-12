package com.example.myapplication.bigwork.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.SuperKotlin.pictureviewer.ImagePagerActivity;
import com.SuperKotlin.pictureviewer.PictureConfig;
import com.bumptech.glide.Glide;
import com.example.myapplication.bigwork.HomeRecycleView;
import com.example.myapplication.bigwork.HomeService;
import com.example.myapplication.bigwork.MyApplication;
import com.example.myapplication.bigwork.R;
import com.example.myapplication.bigwork.adapter.CommodityDetailsListviewAdapter;
import com.example.myapplication.bigwork.adapter.HomeTuijianRecyclerviewAdapter;
import com.example.myapplication.bigwork.table.CommodityDetailsTable;
import com.example.myapplication.bigwork.table.Goods;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.support.constraint.Constraints.TAG;

// 商品详情Activity

public class CommodityDetailsActivity extends AppCompatActivity implements OnBannerListener {

    private ScrollView scrollView;
    private int commodityId;
    private ImageView imageView;
    private String cdImage;
    private String cdNN;
    private TextView cdOPrice;
    private TextView cdName;
    private TextView cdPrice;

    private MyImageLoader cMyImageLoader;
    private Banner cBanner;
    private ArrayList<String> bannerImagePath ;
    private ArrayList<String> bannerImageTitle;

    private View view;
    private CommodityDetailsActivity commodityDetailsActivity;
    private List<CommodityDetailsTable> cdlist = new ArrayList<>();
    private CommodityDetailsListviewAdapter commodityDetailsListviewAdapter;

    private HomeRecycleView tuijianRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_details);
        //initFragment();
        commodityId = this.getIntent().getIntExtra("commodityId",0);
        Log.d("lllll",""+ commodityId);

        cBanner = findViewById(R.id.cd_banner);
//        imageView = findViewById(R.id.cd_banner);
        cdPrice = findViewById(R.id.cd_tv_discountPrice);
        cdOPrice = findViewById(R.id.cd_tv_originalPrice);
        cdName = findViewById(R.id.cd_tv_tradeName);

        onClick_retrofit_get0(commodityId);

        tuijianRecyclerView = findViewById(R.id.cd_rc);
        onClick_retrofit_get1(tuijianRecyclerView);

        // ListView 选项 的适配
        commodityDetailsListviewAdapter = new CommodityDetailsListviewAdapter(this, cdlist);
        ListView cdListView = findViewById(R.id.cd_lv_detailsList);
        cdListView.setAdapter(commodityDetailsListviewAdapter);
        initCDListData();
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);

    }

    // Banner 点击事件
    @Override
    public void OnBannerClick(int position) {
        PictureConfig config = new PictureConfig.Builder()
                .setListData((ArrayList<String>) bannerImagePath)	//图片数据List<String> list
                .setPosition(position)	//图片下标（从第position张图片开始浏览）
                .setDownloadPath("pictureviewer")	//图片下载文件夹地址
                .setIsShowNumber(true)//是否显示数字下标
                .needDownload(true)	//是否支持图片下载
                .setPlacrHolder(R.mipmap.ic_launcher)	//占位符图片（图片加载完成前显示的资源图片，来源drawable或者mipmap）
                .build();
        ImagePagerActivity.startActivity(CommodityDetailsActivity.this,config);
    }




    // Banner 图片加载
    class MyImageLoader extends com.youth.banner.loader.ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }


    // Banner 视图
    private void initBannerView() {
        cMyImageLoader = new MyImageLoader();
        //设置样式，里面有很多种样式可以自己都看看效果
        cBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        cBanner.setImageLoader(cMyImageLoader);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        cBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        //cBanner.setBannerTitles(bannerImageTitle);
        //设置轮播间隔时间
        cBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        cBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        cBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        cBanner.setImages(bannerImagePath)
                //轮播图的监听
                .setOnBannerListener(this)
                //开始调用的方法，启动轮播图。
                .start();


    }

    private void initBannerData(String imagePath) {
        bannerImagePath = new ArrayList<>();
        for(int i=0; i<5; i++){
            bannerImagePath.add(imagePath);
        }
    }


    public void onClick_retrofit_get0(int commodityId) {
        //step1:实例化Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HomeService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //step2:获取APIService实例
        HomeService apiService = retrofit.create(HomeService.class);

        //step3:通过apiService调用call  http://gank.io/api/data/Android/10/1
        final Call<Goods> gankCall= apiService.getGoodsId(commodityId);
        Log.d("CallGoods",gankCall + "");

        //step4:通过异步获取数据
        gankCall.enqueue(new Callback<Goods>() {
            @Override
            public void onResponse(Call<Goods> call, Response<Goods> response) {
                Log.e(TAG, "onResponse: 地址请求成功！！！" );
                //商品
                Log.d("111111", "onResponse: "+ response.body());

                Goods goods = response.body();
                Log.d("Goodsgoods",goods + "");
                Log.d("Name",""+goods.getTradeName());

                cdPrice.setText(goods.getDiscountPrice() + "");
                cdOPrice.setText(goods.getOriginalPrice() +"");
                cdNN = goods.getTradeName();
                cdName.setText(goods.getTradeName());


                // 轮播图获取数据
                initBannerData(goods.getProductPicUrl());
                initBannerView();

            }
            @Override
            public void onFailure(Call<Goods> call, Throwable t) {
                Log.e(TAG, "onFailure: 请求失败！！！！~~~~~" );
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });

    }

    // ListView 选项
    private void initCDListData() {
        CommodityDetailsTable cuXiao = new CommodityDetailsTable("促销","  购买可获得积分");
        CommodityDetailsTable fuWu = new CommodityDetailsTable("服务","  正品保证，退货运费险，七天退换");
        CommodityDetailsTable xueZe = new CommodityDetailsTable("选择","  请选择分类");
        CommodityDetailsTable canShu = new CommodityDetailsTable("参数","  品牌，生产地点...");
        cdlist.add(cuXiao);
        cdlist.add(cuXiao);
        cdlist.add(cuXiao);
        cdlist.add(cuXiao);
        cdlist.add(fuWu);
        cdlist.add(xueZe);
        cdlist.add(canShu);
    }

    public void onClick_retrofit_get1(final View view) {
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
                HomeTuijianRecyclerviewAdapter homeTuijianRecyclerviewAdapter = new HomeTuijianRecyclerviewAdapter(getBaseContext(),goods);
                tuijianRecyclerView.setAdapter(homeTuijianRecyclerviewAdapter);

//                Goods randGoods = goods.get(random.nextInt(goods.size()));
//                // 发生跳转传输数据设置
//                Intent intent = new Intent(getActivity(), MyBroadcastReceiver.class);
//                intent.putExtra("commodityId",randGoods.getCommodityId());
//                startActivity(intent);
//                Log.d("randGoods", "" +randGoods.getCommodityId());

            }
            @Override
            public void onFailure(Call<List<Goods>> call, Throwable t) {
                Log.e(TAG, "onFailure: 请求失败！！！！~~~~~" );
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });

    }

}
