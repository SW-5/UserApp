package com.example.konaking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    static ProductData productData=new ProductData();
    static NoticeData NoticeData=new NoticeData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    // 로그아웃 버튼 메소드
    private void signOut() {
        FirebaseAuth.getInstance().signOut();
    }

    // 로그인 버튼 메소드
    public void LogoutBtn(View view) {
        signOut();
        Intent intent = new Intent(getApplicationContext() , MainActivity.class); // 로그인 화면 이동
        startActivity(intent);
    }

    // 메뉴 버튼 메소드
    public void MenuBtn(View view) {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class); // 메뉴1 화면 이동
        startActivity(intent);
    }

    // 공지사항 버튼 메소드
    public void NoticeBtn(View view) {
        NoticeData.ClearMap();
        Intent intent = new Intent(getApplicationContext(), NoticeActivity.class); // 공지사항 화면 이동
        startActivity(intent);
    }

    // 장바구니(아이콘) 버튼 메소드
    public void CartBtn(View view) {
        Intent intent = new Intent(getApplicationContext() , CartActivity.class); // 장바구니 화면 이동
        startActivity(intent);
    }

    // 핸드폰 뒤로가기 버튼 기능부여 메소드
    @Override
    public void onBackPressed() { // 이 상태로 두면 아무런 기능도 하지않음
        //super.onBackPressed();
    }
}

