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
import com.example.curiositydaily.adapter.AttentionPeopleAdapter;
import com.example.curiositydaily.adapter.MyAdapter;
import com.example.curiositydaily.model.PeopleData;
import com.example.curiositydaily.model.RecommodData;

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
public class AttentionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context mContext;
    private ArrayList<PeopleData> datas = null;
    private FragmentManager fManager = null;

    private OnFragmentInteractionListener mListener;

    public AttentionFragment() {
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
    public static AttentionFragment newInstance(String param1, String param2) {
        AttentionFragment fragment = new AttentionFragment();
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
        View view = inflater.inflate(R.layout.fragment_attention, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        final List<PeopleData> datas = new ArrayList<>();
        ListView list_attention = (ListView)getView().findViewById(R.id.list_attention);
        AttentionPeopleAdapter ma = new AttentionPeopleAdapter(datas, getActivity());
        list_attention.setAdapter(ma);
        for (int i = 1; i <= 20; i++) {
            PeopleData data = new PeopleData("第" + i+"个关注的人的名字", "第" + i+"个关注的人的简介",R.mipmap.ic_launcher);
            datas.add(data);
        }
        list_attention.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PeopleData data1 = datas.get(i);
                Toast.makeText(getActivity(),data1.getName(),Toast.LENGTH_SHORT).show();
                Intent intent  =new Intent(getActivity(),MainActivity.class);
                intent.putExtra("obj", (CharSequence) data1);
                Bundle bundle1 = new Bundle();
                bundle1.putString("arg1","关注的人详情");
                bundle1.putString("name",data1.getName());
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
