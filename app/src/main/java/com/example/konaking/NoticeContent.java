package com.example.konaking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NoticeContent extends AppCompatActivity {
    TextView content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_content);

        content=findViewById(R.id.content);
        content.setText(HomeActivity.NoticeData.getMap(""+HomeActivity.NoticeData.getPosition()));
    }

    public void BackBtn(View view){
        Intent intent = new Intent(getApplicationContext(), NoticeActivity.class); // 공지사항 화면 이동
        startActivity(intent);
    }

    // 핸드폰 뒤로가기 버튼 기능부여 메소드
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), NoticeActivity.class); // 공지사항 화면 이동
        startActivity(intent);
    }
}
