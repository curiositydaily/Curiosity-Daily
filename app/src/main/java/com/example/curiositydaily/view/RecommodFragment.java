package com.example.curiositydaily.view;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.curiositydaily.R;
import com.example.curiositydaily.adapter.MyAdapter;

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView txt_title;
    private Context mContext;
    private ArrayList<RecommodData> datas = null;
    private FragmentManager fManager = null;
    private long exitTime = 0;
    private ListView listView;

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
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        final List<RecommodData> datas = new ArrayList<>();
        ListView list_recommod = (ListView)getView().findViewById(R.id.list_recommod);
        MyAdapter ma = new MyAdapter(datas, getActivity());
        list_recommod.setAdapter(ma);
        for (int i = 1; i <= 20; i++) {
            RecommodData data = new RecommodData("第" + i+"个文章标题", "第" + i+"个文章作者","第" + i+"个文章内容",R.mipmap.ic_launcher);
            datas.add(data);
        }
//        MyAdapter myAdapter = new MyAdapter(datas, getActivity());
//        list_recommod.setAdapter(myAdapter);
        list_recommod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RecommodData data1 = datas.get(i);
                Toast.makeText(getActivity(),data1.getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent  =new Intent(getActivity(),MainActivity.class);
                intent.putExtra("obj", (CharSequence) data1);
                Bundle bundle1 = new Bundle();
                bundle1.putString("arg1","新闻详情");
                bundle1.putString("title",data1.getTitle());
                intent.putExtra("bundle",bundle1);
                startActivity(intent);
            }
        });

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
}
