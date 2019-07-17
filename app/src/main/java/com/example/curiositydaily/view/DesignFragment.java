package com.example.curiositydaily.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.TestLooperManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.curiositydaily.R;
import com.example.curiositydaily.adapter.CustomListAdapter;
import com.example.curiositydaily.app.AppController;
import com.example.curiositydaily.model.DesignContent;
import com.example.curiositydaily.model.SQLiteDB;
import com.example.curiositydaily.model.UserDesign;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DesignFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DesignFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DesignFragment extends Fragment{
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String url = "https://api.androidhive.info/json/movies.json";
    private ProgressDialog pDialog;
    private List<UserDesign> userDesignsList = new ArrayList<UserDesign>();
    private ListView listView;
    private CustomListAdapter adapter;

    private String name;
    private String image;
    private int type;
    private int commendation;
    private String introduction;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DesignFragment() { }

    // TODO: Rename and change types and number of parameters
    public static DesignFragment newInstance(String param1, String param2) {
        DesignFragment fragment = new DesignFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_design, container, false);

//        TextView textView = (TextView)view.findViewById(R.id.design_tab_menu_search);

        // 初始化设计专题内容
//        initUserDesign();



//        this.getActivity().setContentView(R.layout.activity_design);
//        this.getActivity().getWindow().setStatusBarColor(0xffffcc66);
//        listView = (ListView) this.getActivity().findViewById(R.id.list);

        listView = (ListView)view.findViewById(R.id.list);
        adapter = new CustomListAdapter(getActivity(), userDesignsList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("正在努力加载中...");
        pDialog.show();
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFCC66")));

        // 创建Volley响应对象
        JsonArrayRequest movieReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                hidePDialog();

                InputStreamReader inputStreamReader;
                try {
                    inputStreamReader = new InputStreamReader(getActivity().getAssets().open("test.json"),"UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String line;
                    StringBuilder stringBuilder = new StringBuilder();
                    int i=0,index=0;
                    while ((line = bufferedReader.readLine()) != null){
                        if(i==1){ name=line.substring(17,line.length()-2); }
                        if(i==2){ image=line.substring(18,line.length()-2); }
                        if(i==3){
                            String demo=line.substring(16,line.length()-1);
                            type=Integer.valueOf(demo.toString());
                        }
                        if(i==4){
                            String demo=line.substring(24,line.length()-1);
                            commendation=Integer.valueOf(demo.toString());
                        }
                        if(i==5){
                            introduction = line.substring(25,line.length()-1);
                        }
                        if(i==6){
                            UserDesign userDesign = new UserDesign();
                            userDesign.setId(index);
                            userDesign.setName(name);
                            userDesign.setImage(image);
                            userDesign.setType(type);
                            userDesign.setCommendation(commendation);
                            userDesign.setIntroduction(introduction);
//                            System.out.println(userDesign.toString());
                            userDesignsList.add(userDesign);
                        }
                        if(i==7){ i=0;index++; }
                        stringBuilder.append(line);
                        i++;
                    }
                    inputStreamReader.close();
                    bufferedReader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // 通知有关数据更改以便能显示更新后的数据
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // 加入队列
        AppController.getInstance().addToRequestQueue(movieReq);


        // 给listView添加点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"第"+position+"个item",Toast.LENGTH_SHORT).show();
                // 显示设计专题内容
//                List<UserDesign> test_list = new ArrayList<UserDesign>();
//                test_list = SQLiteDB.getInstance((getActivity().getApplicationContext())).loadUserDesign(position+1);
//                for( UserDesign ud : test_list){
//                    System.out.println(ud.toString());
//                }

                // 跳转显示详细设计内容页面
                Intent intent = new Intent(getActivity(), DesignContentActivity.class);
                intent.putExtra("design_id",String.valueOf(position));
                startActivity(intent);
            }

        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }

    }

    @Override
    public void onAttach(Context context) throws RuntimeException {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    // 初始化设计专题内容
    public void initUserDesign(){
        UserDesign userDesign1 = new UserDesign();
        userDesign1.setId(1);
        userDesign1.setName("打造你的专属壁纸");
        userDesign1.setImage("https://api.androidhive.info/json/movies/15.jpg");
        userDesign1.setType(1);
        userDesign1.setIntroduction("多种样式供你选择");
        userDesign1.setCommendation(20);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        userDesign1.setId(2);
        userDesign1.setName("好看的头像万里挑一");
        userDesign1.setImage("https://api.androidhive.info/json/movies/8.jpg");
        userDesign1.setType(0);
        userDesign1.setIntroduction("满足你的所有需求");
        userDesign1.setCommendation(50);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        userDesign1.setId(3);
        userDesign1.setName("夏日壁纸");
        userDesign1.setImage("https://api.androidhive.info/json/movies/13.jpg");
        userDesign1.setType(0);
        userDesign1.setIntroduction("给夏天降降温");
        userDesign1.setCommendation(100);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");
    }
}
