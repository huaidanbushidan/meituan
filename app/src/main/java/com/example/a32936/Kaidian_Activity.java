package com.example.a32936;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Kaidian_Activity extends AppCompatActivity {

    private ImageView imageView;
    private String dpname,dpaddress,mid;
    private int flag;
    private int dpaccount;
    private MyDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaidian_);
        databaseHelper = new MyDatabaseHelper(this);
        Button button1 = findViewById(R.id.iv_shopPicture);
        Button button2 = findViewById(R.id.Newshop);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = databaseHelper.getWritableDatabase();
                flag = 0;
                EditText editTextaccount = findViewById(R.id.shopAccount);
                TextView textView1 = findViewById(R.id.Newshop_Name);
                TextView textView2 = findViewById(R.id.Newshop_Address);
                 dpname = textView1.getText().toString();
                 dpaddress = textView2.getText().toString();
                 mid = editTextaccount.getText().toString();
                 if (mid.equals("")){Toast.makeText(Kaidian_Activity.this,"店铺账号不能为空",Toast.LENGTH_SHORT).show();
                 }else{
                 dpaccount = Integer.valueOf(mid);
                if(dpname.equals("")){Toast.makeText(Kaidian_Activity.this,"店铺名不能为空",Toast.LENGTH_SHORT).show();}
                else {
                    if(dpaddress.equals("")){Toast.makeText(Kaidian_Activity.this,"店铺地址不能为空",Toast.LENGTH_SHORT).show();}
                    else {
                            String name = null;
                            int account = 0;
                            Cursor cursor = db.query("Shop",new String[]{"Shop_Name","Shop_Address","shopAccount"},null,null,null,null,"id");
                            if (cursor.moveToFirst()){
                                do {
                                    account = cursor.getInt(cursor.getColumnIndex("shopAccount"));
                                    //account = cursor.getInt(cursor.getColumnIndex("shopAccount"));
                                    name = cursor.getString(cursor.getColumnIndex("Shop_Name"));
                                    if(dpname.equals(name)){
                                        Toast.makeText(Kaidian_Activity.this,"非常抱歉，店铺名已存在",Toast.LENGTH_SHORT).show();
                                        flag++;
                                        break;
                                    }
                                    if (account == 0){
                                        Toast.makeText(Kaidian_Activity.this,"非常抱歉，店铺账号不能为0",Toast.LENGTH_SHORT).show();
                                        flag++;
                                        break;
                                    }
                                    if(dpaccount == account){
                                        Toast.makeText(Kaidian_Activity.this,"非常抱歉，店铺账号已存在",Toast.LENGTH_SHORT).show();
                                        flag++;
                                        break;
                                    }
                                }while (cursor.moveToNext());

                            }
                            cursor.close();
                            if (flag == 0){
                                ContentValues values = new ContentValues();
                                values.put("Shop_Name", dpname);
                                values.put("Shop_Address",dpaddress);
                                values.put("owner",LoginActivity.signer);
                                values.put("shopAccount",dpaccount);
                                Toast.makeText(Kaidian_Activity.this,String.valueOf(dpaccount),Toast.LENGTH_SHORT).show();
                                //values.put("Password",mima);
                                db.insert("Shop", null, values);
                                values.clear();		//清掉现有的内容
                                db.close();

                                Toast.makeText(Kaidian_Activity.this,String.valueOf(dpaccount),Toast.LENGTH_SHORT).show();
                                //Toast.makeText(Kaidian_Activity.this,"开店成功",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Kaidian_Activity.this,MainActivity.class);
                                finish();
                                startActivityForResult(intent,1);
                            }
                        }
                    }
                 }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
