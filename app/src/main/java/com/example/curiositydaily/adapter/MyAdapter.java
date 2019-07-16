package com.example.curiositydaily.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.List;

import com.example.curiositydaily.R;
import com.example.curiositydaily.view.MainActivity;
import com.example.curiositydaily.view.RecommodContentActivity;
import com.example.curiositydaily.view.RecommodContentFragment;
import com.example.curiositydaily.view.RecommodData;
import com.example.curiositydaily.view.RecommodFragment;

public class MyAdapter extends BaseAdapter {
    private List<RecommodData> mData;
    private Context mContext;
    public MyAdapter(List<RecommodData> mData,Context mContext){
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item,null);
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.info = (TextView)view.findViewById(R.id.info);
            viewHolder.writer = (TextView) view.findViewById(R.id.writer);
            viewHolder.image = (ImageView) view.findViewById(R.id.image);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext, RecommodContentActivity.class);
                mContext.startActivity(intent);

            }
        });
        viewHolder.image.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(mContext,"点击图片",Toast.LENGTH_SHORT).show();
            }
        });
        RecommodData data = mData.get(i);
        viewHolder.title.setText(data.getTitle());
        viewHolder.image.setImageResource(data.getImg());
        viewHolder.info.setText(data.getContent());
        viewHolder.writer.setText(data.getWriter());

//        viewHolder.txt_item_title.setText(mData.get(i).getTitle());
        return view;
    }
    private class ViewHolder{
//        TextView txt_item_title;
        public ImageView image;
        public TextView title;
        public Button view;
        public TextView info;
        public TextView writer;
    }

}
