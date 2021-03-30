package com.example.konaking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

public class NoticeActivity extends AppCompatActivity {

    private ListView list; // 공지사항을 담기위한 리스트객체
    private TextView dateNow; // 현재시간을 담는 텍스트뷰
    ArrayAdapter<String> adapter;

    ///////////////////// 현재시간을 계산하기 위한 변수 ///////////////////

    long now = System.currentTimeMillis();

    Date date = new Date(now);
    private SimpleDateFormat mFormat = new SimpleDateFormat("   yyyy년 M월 d일"); // 날짜 초기화
    String formatDate = mFormat.format(date);
    ////////////////////////////////////////////////////////////////////

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private ChildEventListener mChild;

    List<String> data = new ArrayList<>();
    String Title="";
    StringTokenizer st;
    String TitleShow;
    String Sub="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        list = (ListView) findViewById(R.id.list);
        initDatabase();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);


        HomeActivity.NoticeData.PositionClear();
        mReference = mDatabase.getReference("공지사항"); // 변경값을 확인할 child 이름
        mReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapter.clear();

                for (DataSnapshot messageData : dataSnapshot.getChildren()) {

                    String Pull=messageData.getValue().toString();
                    st=new StringTokenizer(Pull,"\n");
                    TitleShow=st.nextToken();
                    while(st.hasMoreTokens()){
                        Sub+=("\n"+st.nextToken());
                    }
                    NoticeAdd(TitleShow);
                    //  NoticeAdd(messageData.getKey());//키 값 가져오기
                    HomeActivity.NoticeData.setMap(TitleShow,Sub);
                    Sub="";
                }

                adapter.notifyDataSetChanged();
                list.setSelection(adapter.getCount()-1);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        list.setOnItemClickListener(listener);

        // 현재시간을 dateText에 출력
        dateNow = (TextView) findViewById(R.id.dateText);
        dateNow.setText(formatDate);
    }

    AdapterView.OnItemClickListener listener= new AdapterView.OnItemClickListener() {

        @Override

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            HomeActivity.NoticeData.setPosition(data.get(position));
            Intent intent =new Intent(getApplicationContext(),NoticeContent.class);
            startActivity(intent);
        }
    };

    public void NoticeAdd(String New) {
        if(data.isEmpty()){
            data.add(New);
            Title=New;
        }
        else{
            int a=data.indexOf(Title);
            data.add(a,New);
            Title=New;
        }
    }

    private void initDatabase() {

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference("공지사항");

        mChild = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChild);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReference.removeEventListener(mChild);
    }

    // 핸드폰 뒤로가기 버튼 기능부여 메소드
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class); // 홈 화면 이동
        startActivity(intent);
    }
}
