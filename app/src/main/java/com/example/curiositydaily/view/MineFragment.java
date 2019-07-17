package com.example.curiositydaily.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.curiositydaily.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MineFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment {

    //private FragmentManager fManager;
    //private Bitmap head;// 头像Bitmap
    //@SuppressLint("SdCardPath")
    //private static String path = "/sdcard/myHead/";// sd路径
    //private ImageButton touxiang;
    //private OnButtonClick onButtonClick;//2、定义接口成员变量

    EditText name;
    EditText sign;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MineFragment newInstance(String param1, String param2) {
        MineFragment fragment = new MineFragment();
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
        View view =  inflater.inflate(R.layout.fragment_mine, container,false);
        //TextView txt_mine = (TextView) view.findViewById(R.id.txt_mine);
        //txt_mine.setText("我的");

        //touxiang = (ImageButton)view.findViewById(R.id.ib_head);

        //按钮事件
        //final Button btn1 = (Button)view.findViewById(R.id.rb_mine_article);
        Button btn1 = (Button)view.findViewById(R.id.rb_mine_article);
        Button btn2 = (Button)view.findViewById(R.id.rb_mine_commendation);
        Button btn3 = (Button)view.findViewById(R.id.rb_mine_attention);
        Button btn4 = (Button)view.findViewById(R.id.rb_mine_feedback);
        Button btn5 = (Button)view.findViewById(R.id.rb_mine_protocol);
        ImageButton ibtn = (ImageButton)view.findViewById(R.id.ib_head);

        final EditText name = (EditText)view.findViewById(R.id.et_mine_info_name);
        final EditText sign = (EditText)view.findViewById(R.id.et_mine_info_sign);

        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click imagebutton");
                Toast.makeText(getActivity(),"个人资料",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), HeadActivity.class);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click button 1");
                Toast.makeText(getActivity(),"1",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), MineArticle.class);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click button 2");
                Toast.makeText(getActivity(),"发现",Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(getActivity(),MineAttention.class);
                //startActivity(intent);
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.ly_content, new DesignFragment()).commit();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click button 3");
                Toast.makeText(getActivity(),"关注",Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(getActivity(),MineAttention.class);
                //startActivity(intent);
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
                        .replace(R.id.ly_content, new AttentionFragment()).commit();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click button 4");
                Toast.makeText(getActivity(),"意见反馈",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click button 5");
                Toast.makeText(getActivity(),"用户协议",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), MineProtocolActivity.class);
                startActivity(intent);
            }
        });
        //initView();
        return view;
    }

    /*
    private void initView() {
        //LL01=(LinearLayout)getActivity().findViewById(R.id.LL01);
        //LL02=(LinearLayout)getActivity().findViewById(R.id.LL02);
        //LL03=(LinearLayout)getActivity().findViewById(R.id.LL03);

        touxiang = (ImageButton) Objects.requireNonNull(getActivity()).findViewById(R.id.ib_head);
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");// 从SD卡中找头像，转换成Bitmap
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);// 转换成drawable
            touxiang.setImageDrawable(drawable);
        } else {

        }
       }
    private void showTypeDialog() {
        //显示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getActivity(), R.layout.dialog_select_photo, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
        @Override
    public void onClick(View v) {
        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
        //打开文件
        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent1, 1);
        dialog.dismiss();
        }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
    @Override
    public void onClick(View v) {
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent2.putExtra(MediaStore.EXTRA_OUTPUT,
        Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
        startActivityForResult(intent2, 2);// 采用ForResult打开
        dialog.dismiss();
        }
        });
        dialog.setView(view);
        dialog.show();
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case 1:
        if (resultCode == RESULT_OK) {
        cropPhoto(data.getData());// 裁剪图片
        }

        break;
        case 2:
        if (resultCode == RESULT_OK) {
        File temp = new File(Environment.getExternalStorageDirectory() + "/head.jpg");
        cropPhoto(Uri.fromFile(temp));// 裁剪图片
        }

        break;
        case 3:
        if (data != null) {
        Bundle extras = data.getExtras();
        //
        assert extras != null;
        head = extras.getParcelable("data");
        if (head != null) {

        setPicToView(head);// 保存在SD卡中
        touxiang.setImageBitmap(head);// 用ImageButton显示出来
        }
        }
        break;
default:
        break;

        }
        super.onActivityResult(requestCode, resultCode, data);
        }

//调用系统的裁剪功能
//public->private
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 90);
        intent.putExtra("outputY", 90);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
        }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
        return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";// 图片名字
        try {
        b = new FileOutputStream(fileName);
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } finally {
        try {
        // 关闭流
        //
        assert b != null;
        b.flush();
        b.close();
        } catch (IOException e) {
        e.printStackTrace();
        }
        }
        }
    */



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
