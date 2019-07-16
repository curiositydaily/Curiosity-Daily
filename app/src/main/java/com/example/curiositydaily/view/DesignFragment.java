package com.example.curiositydaily.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.ContentFrameLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.curiositydaily.R;
import com.example.curiositydaily.model.SQLiteDB;
import com.example.curiositydaily.model.UserDesign;

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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DesignFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DesignFragment.
     */
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


        // 初始化设计专题内容
//        initUserDesign();

        // 显示设计专题内容
        List<UserDesign> test_list = new ArrayList<UserDesign>();
        test_list = SQLiteDB.getInstance((getActivity().getApplicationContext())).loadUserDesign();
        for( UserDesign ud : test_list){
            System.out.println(ud.toString());
        }

        return view;
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
        userDesign1.setType(1);
        userDesign1.setIntroduction("多种样式供你选择");
        userDesign1.setCommendation(20);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        userDesign1.setId(2);
        userDesign1.setName("好看的头像万里挑一");
        userDesign1.setType(0);
        userDesign1.setIntroduction("满足你的所有需求");
        userDesign1.setCommendation(50);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

        userDesign1.setId(3);
        userDesign1.setName("夏日壁纸");
        userDesign1.setType(0);
        userDesign1.setIntroduction("给夏天降降温");
        userDesign1.setCommendation(100);
        if(SQLiteDB.getInstance(getActivity().getApplicationContext()).saveUserDesign(userDesign1)) System.out.println("插入设计专题成功!");
        else System.out.println("插入设计专题失败!");

    }
}
