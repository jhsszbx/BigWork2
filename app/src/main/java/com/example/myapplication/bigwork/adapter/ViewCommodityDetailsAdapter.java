package com.example.myapplication.bigwork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ScrollView;

import com.example.myapplication.bigwork.R;
import com.example.myapplication.bigwork.table.Goods;
import com.youth.banner.Banner;
import android.widget.TextView;
import android.widget.ListView;

public class ViewCommodityDetailsAdapter extends BaseAdapter {

    private List<Goods> goodsList = new ArrayList<Goods>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ViewCommodityDetailsAdapter(Context context, List list) {
        this.goodsList = list;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Goods getItem(int position) {
        return goodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.view_commodity_details, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Goods)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Goods goods, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private ScrollView scrollView2;
        private Banner cdBanner;
        private TextView cdTvOriginalPrice;
        private TextView cdTvDiscountPrice;
        private TextView cdTvTradeName;
        private ListView cdLvDetailsList;

        public ViewHolder(View view) {
            scrollView2 = (ScrollView) view.findViewById(R.id.scrollView2);
            cdBanner = (Banner) view.findViewById(R.id.cd_banner);
            cdTvOriginalPrice = (TextView) view.findViewById(R.id.cd_tv_originalPrice);
            cdTvDiscountPrice = (TextView) view.findViewById(R.id.cd_tv_discountPrice);
            cdTvTradeName = (TextView) view.findViewById(R.id.cd_tv_tradeName);
            cdLvDetailsList = (ListView) view.findViewById(R.id.cd_lv_detailsList);
        }
    }
}
