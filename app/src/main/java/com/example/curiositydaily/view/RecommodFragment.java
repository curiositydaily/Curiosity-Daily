package com.example.curiositydaily.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.curiositydaily.R;
import com.example.curiositydaily.adapter.MyAdapter;
import com.example.curiositydaily.model.RecommodData;
import com.example.curiositydaily.model.SQLiteDB;
import com.example.curiositydaily.model.UserArticle;
import com.example.curiositydaily.model.UserDesign;
import com.example.curiositydaily.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecommodFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecommodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommodFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView list_recommod;
    private MyAdapter ma;
    private List<RecommodData> datas = new ArrayList<RecommodData>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txt_title;
    private Context mContext;
//    private ArrayList<RecommodData> datas = null;
    private FragmentManager fManager = null;
    private long exitTime = 0;
    private ListView listView;
    private SQLiteDB db;
    private List<UserArticle> list;

    private OnFragmentInteractionListener mListener;

    public RecommodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecommodFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecommodFragment newInstance(String param1, String param2) {
        RecommodFragment fragment = new RecommodFragment();
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
        View view = inflater.inflate(R.layout.fragment_recommod, container, false);

        list_recommod = (ListView)view.findViewById(R.id.list_recommod);
        ma = new MyAdapter(datas, getActivity());
        list_recommod.setAdapter(ma);
//        //初始化
        initUser();
        initReconnodContent();

//        initReconnodContent();
        list =  SQLiteDB.getInstance(getActivity().getApplicationContext()).loadUserArticle();

        for(UserArticle i:list){
            System.out.println(i.toString());
            System.out.println(SQLiteDB.getInstance(getActivity().getApplicationContext()).queryUserInfo(i.getUserId()).toString());
            RecommodData data = new RecommodData(i.getTitle(),SQLiteDB.getInstance(getActivity().getApplicationContext()).queryUserInfo(i.getUserId()).getName(),i.getContent(),R.mipmap.ic_launcher);
            datas.add(data);
        }
//        for (int i = 1; i <= 20; i++) {
//            RecommodData data = new RecommodData("hello","第" + i+"个文章作者","第" + i+"个文章内容",R.mipmap.ic_launcher);
//            datas.add(data);
//        }
//        list_recommod.setAdapter(ma);

        System.out.println("-------------------------------------");
        list_recommod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getActivity(),"第"+position+"个item",Toast.LENGTH_SHORT).show();


                // 跳转显示详细设计内容页面

                Intent intent = new Intent(getActivity(), RecommodContentActivity.class);
                System.out.println("position="+position);
                intent.putExtra("article_id",String.valueOf(position));
                startActivity(intent);
            }

        });

        return view;
    }
//    public List<RecommodData> getData(){
//        final List<RecommodData> datas = new ArrayList<>();
////        ListView list_recommod = (ListView)getView().findViewById(R.id.list_recommod);
//        for (int i = 1; i <= 20; i++) {
//            RecommodData data = new RecommodData("第" + i+"个文章标题", "第" + i+"个文章作者", "第"+i+"个文章的作者");
//            datas.add(data);
//        }
//        return datas;
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
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
    public void initReconnodContent(){
        UserArticle userArticle1 = new UserArticle();
        userArticle1.setId(1);
        userArticle1.setUserId(1);
        userArticle1.setTitle("大公司头条");
        userArticle1.setContent("中国对外援助超过5亿元美元，外债是2000年的十倍；欧盟将对亚马逊展开...");
        userArticle1.setCommendation(100);

        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserArticle(userArticle1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        UserArticle userArticle2 = new UserArticle();
        userArticle2.setId(2);
        userArticle2.setUserId(2);
        userArticle2.setTitle("关于因果关系的新科学");
        userArticle2.setContent("为什么说你比你的数据更聪明");
        userArticle2.setCommendation(16);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserArticle(userArticle2)) System.out.println("插入文章成功!");
        else System.out.println("插入文章失败!");

        UserArticle userArticle3 = new UserArticle();
        userArticle3.setId(3);
        userArticle3.setUserId(2);
        userArticle3.setTitle("台湾小说家童伟格的短篇集");
        userArticle3.setContent("希望建立其另一种事实");
        userArticle3.setCommendation(20);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserArticle(userArticle3)) System.out.println("插入文章成功!");
        else System.out.println("插入文章失败!");
    }
    public void initUser(){
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setId(1);
        userInfo1.setName("@黄俊杰");
        userInfo1.setIntroduction("知名评论家");
        userInfo1.setImage("https://api.androidhive.info/json/movies/2.jpg");
        System.out.println("-------------1-----------"+userInfo1.toString());
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserInfo(userInfo1)) System.out.println("插入用户信息成功!");
        else System.out.println("插入用户信息失败!");

        userInfo1.setId(2);
        userInfo1.setName("@曾梦龙");
        userInfo1.setIntroduction("知名评论家");
        userInfo1.setImage("https://api.androidhive.info/json/movies/2.jpg");

        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserInfo(userInfo1)) System.out.println("插入用户信息成功!");
        else System.out.println("插入用户信息失败!");
    }

}
