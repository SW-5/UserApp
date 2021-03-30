package com.example.konaking;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="SIGN";

     //파이어 베이스를 이용하기 위한 Auth, User 선언
     FirebaseAuth  mAuth = FirebaseAuth.getInstance();
     FirebaseUser user= mAuth.getCurrentUser();
     /////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //e-mail 인증값 확인용
        if (user != null)//사용자가 있는지 확인
        {
            signOut();//사용자 초기화
        }
        else {

        }
    }
    private void signOut()//사용자를 초기화 시키는 메소드
    {
        FirebaseAuth.getInstance().signOut();
    }

    // 회원가입 버튼 메소드
    public void SignBtn(View view)//회원가입 버튼을 눌렀을때 회원가입 화면 이동
    {
        Intent intent = new Intent(getApplicationContext(),SignActivity2.class); // 회원가입 화면 이동
        startActivity(intent);
    }

    // 로그인 버튼 메소드
    public void LoginBtn(View view) {
        Login();
    }

    public void Login(){
        try {
            String email = ((EditText) findViewById(R.id.ID)).getText().toString();//email값을 String으로 가져옴
            String password = ((EditText) findViewById(R.id.PASS)).getText().toString();//Password를 String으로 가져옴
            if(email.length()==0||password.length()==0)//이메일과 패스워드에 아무것도 입력이 안되어있을때
            {
                Toast.makeText(getApplicationContext(), "이메일 혹은 패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show();

            }
            else {
                //파이어베이스 오픈소스를 이용한 로그인과정
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");//로그인 성공시 성공로그 띄움
                            user = mAuth.getCurrentUser();//유저 내용 가져옴
                            if (user.isEmailVerified()) //이메일 인증을 했는지 확인
                            {
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class); //홈 화면  이동
                                startActivity(intent);//홈화면 이동
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "이메일을 인증해주시기 바랍니다.", Toast.LENGTH_SHORT).show();//이메일 인증이 안되었을 경우 이메일 인증해달라 요청
                            }
                        }
                        else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());//실패시 실패 로그
                            Toast.makeText(getApplicationContext(), "이메일 혹은 패스워드를 확인해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // 핸드폰 뒤로가기 버튼 기능부여 메소드
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}
