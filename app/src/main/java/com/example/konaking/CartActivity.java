package com.example.konaking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class CartActivity extends AppCompatActivity  {
    ArrayList<String> arrayList=new ArrayList<String>();//Product에저장된 데이터를 넣을 곳
    ArrayList<String> arrayList2=new ArrayList<String>();//상품명 수량의 데이터가 저장될 곳
    ArrayList<CheckBox> CheckBox=new ArrayList<CheckBox>();//체크 박스를 넣을 곳
    ArrayList<EditText> Number=new ArrayList<EditText>();//수량을 넣을 곳
    int CheckListsu=0;
    int ProductKinds;
    String Product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        HomeActivity.productData.setCartProduct(HomeActivity.productData.getProduct());//Product 데이터를 CartProduct에 set
        Product = HomeActivity.productData.getProduct(); //Product데이터를 Product에 가져옴
        StringTokenizer st = new StringTokenizer(Product, "\n"); //가져온 데이터를 자름

        ///////////////////리스트 에 넣는 구간
        CheckBox.add(0,(CheckBox) findViewById(R.id.checkBox0));
        CheckBox.add(1,(CheckBox) findViewById(R.id.checkBox1));
        CheckBox.add(2,(CheckBox) findViewById(R.id.checkBox2));
        CheckBox.add(3,(CheckBox) findViewById(R.id.checkBox3));
        CheckBox.add(4,(CheckBox) findViewById(R.id.checkBox4));
        CheckBox.add(5,(CheckBox) findViewById(R.id.checkBox5));
        CheckBox.add(6,(CheckBox) findViewById(R.id.checkBox6));
        CheckBox.add(7,(CheckBox) findViewById(R.id.checkBox7));

        Number.add(0,(EditText) findViewById(R.id.Quantity0));
        Number.add(1,(EditText) findViewById(R.id.Quantity1));
        Number.add(2,(EditText) findViewById(R.id.Quantity2));
        Number.add(3,(EditText) findViewById(R.id.Quantity3));
        Number.add(4,(EditText) findViewById(R.id.Quantity4));
        Number.add(5,(EditText) findViewById(R.id.Quantity5));
        Number.add(6,(EditText) findViewById(R.id.Quantity6));
        Number.add(7,(EditText) findViewById(R.id.Quantity7));
        //////////////////////////////////////////////////////////////////

        while (st.hasMoreTokens()) {
            arrayList.add(st.nextToken());//자른 데이터를 리스트에 넣음
        }
        /////////체크구간
        ProductKinds = arrayList.size() ;//arrayList의 사이즈를 ProductKinds에 넣음

        for (int i = 0; i < ProductKinds; i++) //ProductKinds는 물품의 종류니까 종류의 수만큼 체크박스와 수량UI를 보이게 함
        {
            CheckBox.get(i).setVisibility(View.VISIBLE);
            CheckBox.get(i).setText(arrayList.get(i));
            Number.get(i).setVisibility(View.VISIBLE);
            CheckListsu++;//몇개 인지 체크
        }
    }

    // 메뉴추가 버튼 메소드
    public void AddBtn(View view) {
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class); // 메뉴화면 이동
        startActivity(intent);
    }

    // 취소 버튼 메소드
    public void CancleBtn(View view) {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class); // 홈으로 이동
        startActivity(intent);
    }

    // 결제 버튼 메소드
    public void PaymentBtn(View view) {

        if(arrayList.size() == 0) {
            Toast.makeText(getApplicationContext(), "메뉴를 선택해주세요", Toast.LENGTH_SHORT).show();
        }

        else {
            for (int i = 0; i < CheckListsu; i++)//체크박스와 수량UI가 켜져있는 수 만큼 arrayList2에 데이터에 저장
            {
                arrayList2.add((String) CheckBox.get(i).getText() + " " + Number.get(i).getText());//arrayList2에 체크박스에 들어있는 이름과 수량을 저장
                PriceCheck((String) CheckBox.get(i).getText(), Integer.parseInt(Number.get(i).getText().toString()));//만든 PriceCheck를 이용해서 물품에 맞는 가격을 PriceData에있는 Price에 추가
            }

            Product = "";//Product 초기화
            for (int i = 0; i < arrayList2.size(); i++)//arrayList2에 있는 물품과 수량의 정보를 Product에 넣음
            {
                Product += arrayList2.get(i) + "\n";
            }

            Product += Integer.toString(HomeActivity.productData.getPrice());//ProductData에 있는 Price 정보를 가져와서 Product에 추가

            HomeActivity.productData.ClearProduct();//현재 ProductData에 있는 Product의 정보를 초기화 시킴
            arrayList2.clear();//arrayList2의 정보를 초기화

            HomeActivity.productData.setProduct(Product);//위에서 만든 Product 문자열을 ProductData에있는 Product로 추가
            Intent intent = new Intent(getApplicationContext(), PaymentActivity.class); // 결제화면 으로 이동
            startActivity(intent);//이동
        }
    }

    public void PriceCheck(String product,int q)//들어온 문자열과 수량에 따라 ProductData에있는 Price 값을 변경 시키는 메소드
    {
        if(product.equals("카푸치노")){
            HomeActivity.productData.setPrice(2500*q);
        }
        else if(product.equals("에스프레소")){
            HomeActivity.productData.setPrice(1500*q);
        }
        else if(product.equals("아이스카페모카")){
            HomeActivity.productData.setPrice(3500*q);
        }
        else if(product.equals("아이스아메리카노")){
            HomeActivity.productData.setPrice(2300*q);
        }
        else if(product.equals("아이스티")){
            HomeActivity.productData.setPrice(2000*q);
        }
        else if(product.equals("카라멜마끼아또")){
            HomeActivity.productData.setPrice(3500*q);
        }
        else if(product.equals("헤이즐넛라떼")){
            HomeActivity.productData.setPrice(3000*q);
        }
        else if(product.equals("초코라떼")){
            HomeActivity.productData.setPrice(2000*q);
        }
        else if(product.equals("그린티")){
            HomeActivity.productData.setPrice(3000*q);
        }
        else if(product.equals("유자차")){
            HomeActivity.productData.setPrice(2500*q);
        }
        else if(product.equals("카페모카")){
            HomeActivity.productData.setPrice(3500*q);
        }
        else if(product.equals("바닐라라떼")){
            HomeActivity.productData.setPrice(3000*q);
        }
    }

    public void DeleteBtn(View view) //결제 취소 버튼
    {
        for(int i=0;i<arrayList.size();i++)//체크박스가 체크되어있는경우를 골라서 취소 시킴
        {
            if(CheckBox.get(i).isChecked()==true){
                arrayList.remove(i);
                CheckBox.remove(i);
                    i-=1;
            }
        }

        if(arrayList.size() == 0){ // 메뉴 선택이 아무것도 되어있지 않을 때
            Toast.makeText(getApplicationContext(), "메뉴를 선택해주세요", Toast.LENGTH_SHORT).show();
        }

        HomeActivity.productData.ClearProduct();//ProductData의 Product를 초기화
        for(int i=0;i<arrayList.size();i++){
            HomeActivity.productData.setProduct(arrayList.get(i)+"\n");//다시 arrayList에 있는 물품을  ProductData의 Product에 넣음
        }

        /////////새로고침/////////
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        /////////////////////////
    }

    // 툴바 뒤로가기 버튼
    public void BackBtn(View view){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class); // 홈으로 이동
        startActivity(intent);
    }

    // 툴바 플러스(메뉴보기) 버튼
    public void PlusBtn(View view){
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class); // 메뉴화면 이동
        startActivity(intent);
    }

    // 핸드폰 뒤로가기 버튼 기능부여 메소드
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class); // 홈 화면 이동
        startActivity(intent);
    }

////////////체크 버튼 메소드//////////////
    public void ProductList0(View view) {
    }
    public void ProductList1(View view) {
    }
    public void ProductList2(View view) {
    }
    public void ProductList3(View view) {
    }
    public void ProductList4(View view) {
    }
    public void ProductList5(View view) {
    }
    public void ProductList6(View view) {
    }
    public void ProductList7(View view) {
    }
//////////////////////////////////////////
}