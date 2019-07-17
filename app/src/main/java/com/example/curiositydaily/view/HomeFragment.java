package com.example.curiositydaily.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.curiositydaily.R;
import com.example.curiositydaily.model.SQLiteDB;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ViewPager viewPager;
    private TextView textView1,textView2;
    private List<Fragment> fragmentList;
    private VpAdapter mAdapter;
    private SQLiteDB db;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//    private String content

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
//        this.content = con;
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
//        TextView txt_home = (TextView)view.findViewById(R.id.txt_home);
//        txt_home.setText("首页");
        ImageButton home_search_imagebtn = (ImageButton) view.findViewById(R.id.home_search_imagebtn);
        ImageButton home_publish_imagebtn = (ImageButton) view.findViewById(R.id.home_publish_imagebtn);
        home_search_imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),HomeSearchActivity.class);
                startActivity(intent);
            }
        });
        home_publish_imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PublishActivity.class);
                startActivity(intent);
            }
        });
        initView(view);

        return view;
    }
    private void initView(View view){
        viewPager = view.findViewById(R.id.home_vp_fragment);
        textView1 = view.findViewById(R.id.home_tab_menu_recommod);
        textView2 = view.findViewById(R.id.home_tab_menu_attention);
        viewPager.addOnPageChangeListener(this);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        fragmentList = new ArrayList<>();
        fragmentList.add(new RecommodFragment());
        fragmentList.add(new AttentionFragment());
        mAdapter = new VpAdapter(getChildFragmentManager());
        viewPager.setAdapter(mAdapter);
        resetTextViewColor();
        textView1.setTextColor(Color.parseColor("#FFC800"));
    }

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

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.home_tab_menu_recommod:
                viewPager.setCurrentItem(0);
                break;
            case R.id.home_tab_menu_attention:
                viewPager.setCurrentItem(1);
                break;
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        resetTextViewColor();
        switch(position){
            case 0:
                textView1.setTextColor(Color.parseColor("#FFC800"));
                break;
            case 1:
                textView2.setTextColor(Color.parseColor("#FFC800"));
                break;


        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

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

        void onCheckedChanged(RadioGroup radioGroup, int i);
    }
    private void resetTextViewColor(){
        textView1.setTextColor(Color.parseColor("#4a4a4a"));
        textView2.setTextColor(Color.parseColor("#4a4a4a"));
    }
    class VpAdapter extends FragmentPagerAdapter{

        public VpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
