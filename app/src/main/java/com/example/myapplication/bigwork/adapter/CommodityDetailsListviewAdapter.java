package com.example.myapplication.bigwork.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.myapplication.bigwork.R;
import com.example.myapplication.bigwork.table.CommodityDetailsTable;


// 订单详情的ListView选项（死的）

public class CommodityDetailsListviewAdapter extends BaseAdapter {

    private List<CommodityDetailsTable> lists = new ArrayList<CommodityDetailsTable>();

    private Context context;
    private LayoutInflater layoutInflater;

    public CommodityDetailsListviewAdapter(Context context,List list) {
        this.lists = list;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public CommodityDetailsTable getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.commodity_details_listview, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((CommodityDetailsTable)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(CommodityDetailsTable lists, ViewHolder holder) {
        //TODO implement
        holder.cdLvDetailsListFirshName.setText(lists.getFirshName());
        holder.cdLvDetailsListFinalName.setText(lists.getFinalName());
    }

    protected class ViewHolder {
        private TextView cdLvDetailsListFirshName;
        private TextView cdLvDetailsListFinalName;
        private ImageView cdLvDetailsListFinalNameImageView;

        public ViewHolder(View view) {
            cdLvDetailsListFirshName = (TextView) view.findViewById(R.id.cd_lv_detailsList_firshName);
            cdLvDetailsListFinalName = (TextView) view.findViewById(R.id.cd_lv_detailsList_finalName);
            cdLvDetailsListFinalNameImageView = (ImageView) view.findViewById(R.id.cd_lv_detailsList_finalName_imageView);
        }
    }
}
