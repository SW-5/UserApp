package com.example.konaking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentActivity extends AppCompatActivity {
    private static final String TAG="DB-------------";
    /////선언 부분/////
    String Product;
    int Check;
    Boolean KonaCheck=false;
    CheckBox Agree1;
    CheckBox Agree2;
    CheckBox Agree3;
    CheckBox Agree4;
    ImageButton MainKona;
    ImageButton DormitoryKona;
    ImageButton HallKona;

    String email;
    TextView Price;
    //파이어베이스 데이터베이스를 이용하기 위한 것
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    ////////
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Product=HomeActivity.productData.getProduct();//Product를 가져옴
        ///생성////
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            email = user.getEmail();
        }

        Agree1=findViewById(R.id.Agree1);
        Agree2=findViewById(R.id.Agree2);
        Agree3=findViewById(R.id.Agree3);
        Agree4=findViewById(R.id.Agree4);
        Price=findViewById(R.id.Price);
        MainKona=findViewById(R.id.imageButton2);
        DormitoryKona=findViewById(R.id.imageButton4);
        HallKona=findViewById(R.id.imageButton3);
        ////////
        Price.setText(Integer.toString(HomeActivity.productData.getPrice())+" 원");
    }

    public void onBackPressed() //뒤로가기 버튼을 눌렀을때
    {
        //ProductData의 Product와 Price를 초기화
        HomeActivity.productData.ClearProduct();
        HomeActivity.productData.ClearPrice();
        ///////////////////
        HomeActivity.productData.setProduct( HomeActivity.productData.getCartProduct());//ProductData의 Product를 CartProduct로 set
        super.onBackPressed();
    }

    public void MainKona(View view)//본관 코나킹을 선택했을때
    {
        Toast.makeText(getApplicationContext(), "본관코나킹 선택되었습니다", Toast.LENGTH_SHORT).show();
        Check=1;//Check를 변경
        KonaCheck=true;//버튼 선택 여부
    }

    public void DormitoryKona(View view)//기숙사 코나킹을 선택했을때
    {
        Toast.makeText(getApplicationContext(), "기숙사코나킹 선택되었습니다", Toast.LENGTH_SHORT).show();
        Check=2;//Check를 변경
        KonaCheck=true;//버튼 선택 여부
    }

    public void HallKona(View view)//학생회관 코나킹을 선택했을때
    {
        Toast.makeText(getApplicationContext(), "학생회관코나킹 선택되었습니다", Toast.LENGTH_SHORT).show();
        Check=3;//Check를 변경
        KonaCheck=true;//버튼 선택 여부
    }

    public void PaymentBtn(View view)//결제 부분
    {
        if(KonaCheck)//주문 장소를 선택했는지 확인
        {
            if (Agree2.isChecked() == true && Agree3.isChecked() == true && Agree4.isChecked() == true) // 3개의 약관동의 체크박스가 선택되었는지 확인
            {
                if (Check == 1)//본관 코나킹을 선택했을때
                {
                    String Location = "주문장소 : 본관 코나킹\n";

                    myRef.child("주문정보").push().setValue("주문처리중\n"+email+"\n"+Location + " " + Product);//데이터 베이스 주문정보 경로에 정보 추가
                    Toast.makeText(getApplicationContext(), "결제 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    KonaCheck=false;//선택값 초기화
                    //ProductData 안의 정보를 초기화
                    HomeActivity.productData.ClearPrice();
                    HomeActivity.productData.ClearCartProduct();
                    HomeActivity.productData.ClearProduct();
                    //////
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class); //홈 화면  이동
                    startActivity(intent);//홈화면 이동

                } else if (Check == 2) //기숙사 코나킹을 선택했을때
                {
                    String Location = "주문장소 : 기숙사코나킹\n";
                    myRef.child("주문정보").push().setValue("주문처리중\n"+email+"\n"+Location + " " + Product);//데이터 베이스 주문정보 경로에 정보 추가
                    Toast.makeText(getApplicationContext(), "결제 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    KonaCheck=false;
                    //ProductData 안의 정보를 초기화
                    HomeActivity.productData.ClearPrice();
                    HomeActivity.productData.ClearCartProduct();
                    HomeActivity.productData.ClearProduct();
                    //////////
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class); //홈 화면  이동
                    startActivity(intent);//홈화면 이동

                } else if (Check == 3) //학생회관코나킹
                {
                    String Location = "주문장소 : 학생회관코나킹\n";
                    myRef.child("주문정보").push().setValue("주문처리중\n"+email+"\n"+Location + " " + Product);//데이터 베이스 주문정보 경로에 정보 추가
                    Toast.makeText(getApplicationContext(), "결제 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    KonaCheck=false;
                    //ProductData 안의 정보를 초기화
                    HomeActivity.productData.ClearPrice();
                    HomeActivity.productData.ClearCartProduct();
                    HomeActivity.productData.ClearProduct();
                    ////////
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class); //홈 화면  이동
                    startActivity(intent);//홈화면 이동

                }
            } else {
                Toast.makeText(getApplicationContext(), "약관 동의를 해주세요", Toast.LENGTH_SHORT).show();//약관 동의를 안했을때
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "주문 장소를 선택하세요", Toast.LENGTH_SHORT).show();//주문장소 선택을 안했을떄
        }
    }

    public void Agree(View view)
    {
        if(Agree1.isChecked()){//전체 동의를 체크했을때 모든 약관체크
            Agree1.setChecked(true);
            Agree2.setChecked(true);
            Agree3.setChecked(true);
            Agree4.setChecked(true);
        }else{
            Agree1.setChecked(false);
            Agree2.setChecked(false);
            Agree3.setChecked(false);
            Agree4.setChecked(false);
        }
    }

    public void AgreeCheck1(View view) {
        if(Agree2.isChecked() == true && Agree3.isChecked() == true && Agree4.isChecked() == true) {
            Agree1.setChecked(true);
        }else if(Agree2.isChecked() == false || Agree3.isChecked() == false || Agree4.isChecked() == false){
            Agree1.setChecked(false);
        }
    }

}
