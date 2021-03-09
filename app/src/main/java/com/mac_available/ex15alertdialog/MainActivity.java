package com.mac_available.ex15alertdialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] items = new  String[]{"Apple", "Banana", "Orange"};
    boolean[] checked = new boolean[]{true, true, false};
    int checkedItemIndex = 0;

    AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void clickBtn(View view) {
        //AlertDialog를 만들어주는 건축가(Builder)객체 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //건축가에게 원하는 AlertDialog의 모양을 의뢰
        //3단 구조가 있어. 무조건 따라야 하는건 아니지만 일단 고...title, message, button 임
        builder.setTitle("다이얼로그");
        builder.setIcon(android.R.drawable.ic_dialog_alert);

//        //1.단순메세지
//        builder.setMessage("wanna quit?");
//
//        //2.단순한 메세지 대신 항목리스트를 보여주기
//        builder.setItems(items, new DialogInterface.OnClickListener() { //얘는 positive, negative 필요없
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(MainActivity.this, items[which], Toast.LENGTH_SHORT).show();
//            }
//        });
//        //3. 항목 글씨 옆에 Radiobutton 보이게... radio는 single, checkbox는 multi로 검색하면 됨
//        builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {//checkeditem은 첨에 찍혀 있는 애 설정
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                checkedItemIndex = which;
//            }
//        });
//        //4.멀티 초이스
//        builder.setMultiChoiceItems(items, checked, new DialogInterface.OnMultiChoiceClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                checked[which] = isChecked;
//            }
//        });

        //5.Custom View로 메세지 영역을 바꾸기...xml로 뷰모양 설계하고 이를 읽어서 자바 View객체로 만들어 설정
        //xml문서를 읽어서 뷰 객체를 만들어주는(inflate)객체 Context로부터 소환
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.dialog, null);

        et = layout.findViewById(R.id.et);
        tv= layout.findViewById(R.id.tv);

        builder.setView(layout);

        //-------------
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, items[checkedItemIndex], Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }
        });

        //건축가에게 위 요청사항대로 AlertDialog 객체를 만들어 달라고 요청!!
        dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        //dialog.setCancelable(false); //이건 뒤로가기 안되게 하는거
        dialog.show();
    }

    //참조변수 at 멤버변수
    TextView tv;
    EditText et;

    //다이얼로그 안 Custom VIew의 버튼을 클릭했을 때
    public void clickDialogBtn(View view) {
        //MainActivity 가 보여주고 있는 화면 activity_main.xml에는 EditText, TextView가 없어서 찾을수(findviewById)가 없어
        //EditText et = findViewById(R.id.et);
        //TextView tv = findViewById(R.id.tv);
        //tv.setText(et.getText().toString());

        //고로, 이를 찾으려면 .. EditText, TextView를 실제로 가지고있는 view에게 찾아달라고 해야함.
        //저 위에 builder에게 setView()할때 설정했던 layout객체에게 찾아달라고 해야함
        //뷰를 만들때 참조해 놓아야함!!
        tv.setText(et.getText().toString());
        dialog.dismiss(); //dialog  사라지기.. 여기서도 dialog이름 먹히게 멤버변수로 객체 만들었음
    }

    //뒤로가가 버튼을 클릭했을때 자동으로 실행되는 메소드
    @Override
    public void onBackPressed(){
        //super.onBackPressed(); //이거 주석처리 하면 뒤에거 눌러도 안꺼짐
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("진짜 나갈껍니까?");
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); //액티비티를 종료시키는 명
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}