package com.example.myapplication.bigwork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.bigwork.MyApplication;
import com.example.myapplication.bigwork.MyBroadcastReceiver;
import com.example.myapplication.bigwork.R;
import com.example.myapplication.bigwork.activity.CommodityDetailsActivity;
import com.example.myapplication.bigwork.table.Commodit;
import com.example.myapplication.bigwork.table.Goods;
import com.nostra13.universalimageloader.core.ImageLoader;

import retrofit2.Call;


// 首页的推荐商品RecycleView（活的）

public class HomeTuijianRecyclerviewAdapter extends RecyclerView.Adapter<HomeTuijianRecyclerviewAdapter.ViewHolder> {

    private List<Goods> goodsList;
    private View goodsView;

    private Context context;
    private LayoutInflater layoutInflater;

    public HomeTuijianRecyclerviewAdapter(Context context, List<Goods> goodsList) {
        this.goodsList = goodsList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 创建viewhodler，相当于listview中getview中的创建view和viewhodler
     *
     * @param parent
     * @param i
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        //创建自定义布局
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_tuijian_recyclerview, parent, false);
        View view = View.inflate(context, R.layout.home_tuijian_recyclerview, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.goodsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Log.e("FFFFFF",""+ position);
                //List<Goods> goods = response.body();
                Goods goods = goodsList.get(position);
//                Toast.makeText(v.getContext(), "fsadlkfjasdljfla" + goods.getTradeName(), Toast.LENGTH_LONG).show();

                // 发生跳转传输数据设置
                Intent intent = new Intent(HomeTuijianRecyclerviewAdapter.this.context, CommodityDetailsActivity.class);
                intent.putExtra("commodityId",goods.getCommodityId());
                context.startActivity(intent);

            }
        });
        return viewHolder;
    }

//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        return null;
//    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //根据点击位置绑定数据
        Goods data = goodsList.get(position);
        viewHolder.tuijinaTitle.setText(data.getTradeName());
        viewHolder.tuijianPrice.setText(data.getDiscountPrice() + "");
        ImageLoader.getInstance().displayImage( data.getProductPicUrl(), viewHolder.tuijinaImage, MyApplication.getLoaderOptions());

    }

//    private void initializeViews(Commodit commodit, ViewHolder holder) {
//
//    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 得到总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return goodsList.size();
    }



    protected class ViewHolder extends RecyclerView.ViewHolder{
        private View goodsView;
        private ImageView tuijinaImage;
        private TextView tuijinaTitle;
        private TextView tuijianPrice;
        private ImageView tuijinaMore;

        public ViewHolder(View view) {
            super(view);
            goodsView = view;
            tuijinaImage = (ImageView) view.findViewById(R.id.tuijina_image);
            tuijinaTitle = (TextView) view.findViewById(R.id.tuijina_title);
            tuijianPrice = (TextView) view.findViewById(R.id.tuijian_price);
            tuijinaMore = (ImageView) view.findViewById(R.id.tuijina_more);
        }
    }



    /**
     * 设置item的监听事件的接口
     */
//    public interface OnItemClickListener {
//        /**
//         * 接口中的点击每一项的实现方法，参数自己定义
//         *
//         * @param view 点击的item的视图
//         * @param call 点击的item的数据
//         */
//        public void OnItemClick(View view);
//    }

    /**
     * 自定义接口
     */
//    public interface OnRecyclerViewItemClickListener {
//        public void onItemClick(View view, int postion);
//    }
//
//    //需要外部访问，所以需要设置set方法，方便调用
//    private OnRecyclerViewItemClickListener onItemClickListener;
//
//    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }


}
