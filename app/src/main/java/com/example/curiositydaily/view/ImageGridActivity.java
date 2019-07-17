package com.example.curiositydaily.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import com.example.curiositydaily.R;
import com.example.curiositydaily.model.AlbumHelper;
import com.example.curiositydaily.model.Bimp;
import com.example.curiositydaily.adapter.ImageGridAdapter;
import com.example.curiositydaily.adapter.ImageGridAdapter.TextCallback;
import com.example.curiositydaily.model.ImageItem;

public class ImageGridActivity extends AppCompatActivity {
    public static final String EXTRA_IMAGE_LIST = "imagelist";

    // ArrayList<Entity> dataList;//鐢ㄦ潵瑁呰浇鏁版嵁婧愮殑鍒楄〃
    List<ImageItem> dataList;
    GridView gridView;
    ImageGridAdapter adapter;// 鑷畾涔夌殑閫傞厤鍣�
    AlbumHelper helper;
    Button bt;
    int LENGTH_LONG = 400;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(ImageGridActivity.this, "最多选择9张图片", Toast.LENGTH_LONG).show();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_grid);

        helper = AlbumHelper.getHelper();
        helper.init(getApplicationContext());

        System.out.println("dataList------test2");
        dataList = (List<ImageItem>) getIntent().getSerializableExtra(
                EXTRA_IMAGE_LIST);
        System.out.println("dataList------test3");
        initView();
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<String>();
                Collection<String> c = adapter.map.values();
                Iterator<String> it = c.iterator();
                for (; it.hasNext();) {
                    list.add(it.next());
                }

                if (Bimp.act_bool) {
                    Intent intent = new Intent(ImageGridActivity.this,
                            PublishActivity.class);
                    startActivity(intent);
                    Bimp.act_bool = false;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (Bimp.drr.size() < 9) {
                        Bimp.drr.add(list.get(i));
                    }
                }
                finish();
            }

        });
    }

    /**
     * 鍒濆鍖杤iew瑙嗗浘
     */
    private void initView() {
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new ImageGridAdapter(ImageGridActivity.this, dataList,
                mHandler);
        gridView.setAdapter(adapter);
        adapter.setTextCallback(new TextCallback() {
            public void onListen(int count) {
                bt.setText("完成" + "(" + count + ")");
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                /**
                 * 鏍规嵁position鍙傛暟锛屽彲浠ヨ幏寰楄窡GridView鐨勫瓙View鐩哥粦瀹氱殑瀹炰綋绫伙紝鐒跺悗鏍规嵁瀹冪殑isSelected鐘舵
                 * �锛� 鏉ュ垽鏂槸鍚︽樉绀洪�涓晥鏋溿� 鑷充簬閫変腑鏁堟灉鐨勮鍒欙紝涓嬮潰閫傞厤鍣ㄧ殑浠ｇ爜涓細鏈夎鏄�
                 */
                // if(dataList.get(position).isSelected()){
                // dataList.get(position).setSelected(false);
                // }else{
                // dataList.get(position).setSelected(true);
                // }

                adapter.notifyDataSetChanged();
            }

        });

    }
}
