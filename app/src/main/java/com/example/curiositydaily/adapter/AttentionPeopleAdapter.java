package com.example.curiositydaily.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.example.curiositydaily.R;
import com.example.curiositydaily.model.PeopleData;
import com.example.curiositydaily.view.AttentionContentActivity;
import com.example.curiositydaily.view.RecommodContentActivity;
import com.example.curiositydaily.model.RecommodData;

public class AttentionPeopleAdapter extends BaseAdapter {
    private List<PeopleData> mData;
    private Context mContext;
    public AttentionPeopleAdapter(List<PeopleData> mData, Context mContext){
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
            view = LayoutInflater.from(mContext).inflate(R.layout.list_item_attention,null);
            viewHolder.name_people = (TextView) view.findViewById(R.id.name_people);
            viewHolder.info_people = (TextView)view.findViewById(R.id.info_people);
            viewHolder.image_people = (ImageView) view.findViewById(R.id.image_people);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(mContext, AttentionContentActivity.class);
                mContext.startActivity(intent);

            }
        });
        viewHolder.image_people.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(mContext,"点击头像",Toast.LENGTH_SHORT).show();
            }
        });
        PeopleData data = mData.get(i);
        viewHolder.name_people.setText(data.getName());
        viewHolder.image_people.setImageResource(data.getImg());
        viewHolder.info_people.setText(data.getInfo());


//        viewHolder.txt_item_title.setText(mData.get(i).getTitle());
        return view;
    }
    private class ViewHolder{
        public ImageView image_people;
        public TextView name_people;
        public TextView info_people;
    }

}
