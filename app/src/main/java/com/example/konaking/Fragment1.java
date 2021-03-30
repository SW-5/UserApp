package com.example.konaking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // 첫번째 메뉴화면 프래그먼트
    public Fragment1() {
        // Required empty public constructor
    }

    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
/////////////////버튼 생성////////////
        ImageButton btn1 = (ImageButton)view.findViewById(R.id.image_1);
        ImageButton btn2 = (ImageButton)view.findViewById(R.id.image_2);
        ImageButton btn3 = (ImageButton)view.findViewById(R.id.image_3);
        ImageButton btn4 = (ImageButton)view.findViewById(R.id.image_4);
        ImageButton btn5 = (ImageButton)view.findViewById(R.id.image_5);
        ImageButton btn6 = (ImageButton)view.findViewById(R.id.image_6);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
///////////////////////////
        return view;
    }

    @Override
    public void onClick(View view) {
        // 어떤 버튼이 클릭했는지 switch문으로 판단
        switch (view.getId()){
            case R.id.image_1:
                if(HomeActivity.productData.getProduct().contains("카푸치노")){
                    Toast.makeText(getActivity(), "이미 장바구니에 카푸치노 메뉴가 있습니다.", Toast.LENGTH_SHORT).show();//장바구니에 물품이 있는경우
                }
                else {
                    HomeActivity.productData.setProduct("카푸치노\n");//Product data에 set
                    Intent intent = new Intent(getActivity(), CartActivity.class);//이동
                    startActivity(intent);
                }
                break;

            case R.id.image_2:

                if(HomeActivity.productData.getProduct().contains("에스프레소")){
                    Toast.makeText(getActivity(), "이미 장바구니에 에스프레소 메뉴가 있습니다.", Toast.LENGTH_SHORT).show();//장바구니에 물품이 있는경우
                }
                else {
                    HomeActivity.productData.setProduct("에스프레소\n");//Product data에 set
                    Intent intent = new Intent(getActivity(), CartActivity.class);//이동
                    startActivity(intent);
                }
                break;

            case R.id.image_3:
                if(HomeActivity.productData.getProduct().contains("아이스카페모카")){
                    Toast.makeText(getActivity(), "이미 장바구니에 아이스카페모카 메뉴가 있습니다.", Toast.LENGTH_SHORT).show();//장바구니에 물품이 있는경우
                }
                else {
                    HomeActivity.productData.setProduct("아이스카페모카\n");//Product data에 set
                    Intent intent = new Intent(getActivity(), CartActivity.class);//이동
                    startActivity(intent);
                }
                break;
            case R.id.image_4:

                if(HomeActivity.productData.getProduct().contains("아이스아메리카노")){
                    Toast.makeText(getActivity(), "이미 장바구니에 아이스아메리카노 메뉴가 있습니다.", Toast.LENGTH_SHORT).show();//장바구니에 물품이 있는경우
                }
                else {
                    HomeActivity.productData.setProduct("아이스아메리카노\n");//Product data에 set
                    Intent intent = new Intent(getActivity(), CartActivity.class);//이동
                    startActivity(intent);
                }
                break;

            case R.id.image_5:
                if(HomeActivity.productData.getProduct().contains("아이스티")){
                    Toast.makeText(getActivity(), "이미 장바구니에 아이스티 메뉴가 있습니다.", Toast.LENGTH_SHORT).show();//장바구니에 물품이 있는경우
                }
                else {
                    HomeActivity.productData.setProduct("아이스티\n");//Product data에 set
                    Intent intent = new Intent(getActivity(), CartActivity.class);//이동
                    startActivity(intent);
                }
                break;
            case R.id.image_6:
                if(HomeActivity.productData.getProduct().contains("카라멜마끼아또")){
                    Toast.makeText(getActivity(), "이미 장바구니에 카라멜마끼아또 메뉴가 있습니다.", Toast.LENGTH_SHORT).show();//장바구니에 물품이 있는경우
                }
                else {
                    HomeActivity.productData.setProduct("카라멜마끼아또\n");//Product data에 set
                    Intent intent = new Intent(getActivity(), CartActivity.class);//이동
                    startActivity(intent);
                }
            break;
        }
    }
}
