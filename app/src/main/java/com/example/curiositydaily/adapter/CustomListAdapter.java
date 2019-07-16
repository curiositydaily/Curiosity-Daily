package com.example.curiositydaily.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.curiositydaily.R;
import com.example.curiositydaily.app.AppController;
import com.example.curiositydaily.model.UserDesign;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<UserDesign> userDesignsItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<UserDesign> userDesignsItems) {
        this.activity = activity;
        this.userDesignsItems = userDesignsItems;
    }

    @Override
    public int getCount() {
        return userDesignsItems.size();
    }

    @Override
    public Object getItem(int location) {
        return userDesignsItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView introduction = (TextView) convertView.findViewById(R.id.introduction);
        TextView commendation = (TextView) convertView.findViewById(R.id.commendation);

        // 获取数据
        UserDesign m = userDesignsItems.get(position);

        // 获取图像
        thumbNail.setImageUrl(m.getImage(), imageLoader);

        // 获取标题
        name.setText(m.getName());

        // 获取类型
        if(m.getType()==0){ type.setText("头像");}
        else{type.setText("壁纸");}

        // 获取介绍
        introduction.setText(m.getIntroduction());

        // 获取点赞数
        commendation.setText(String.valueOf(m.getCommendation()));

        return convertView;
    }

}