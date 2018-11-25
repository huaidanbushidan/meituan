package com.example.a32936;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Addgoods_Activity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private int flag;
    private String addgoodsname,addgoodsprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgoods_);
        dbHelper = new MyDatabaseHelper(this);

        Button button2 = findViewById(R.id.makeSure);
        //String goodsname = findViewById()
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                /**Cursor cursor = db.query("Shop",new String[]{"owner"},"Shop_Name = ?",new String[],null,null,"id");
                if (cursor.moveToFirst() ){
                    do{
                        String owner = cursor.getString(cursor.getColumnIndex("owner"));
                        if()
                    }
                }*/
                flag = 0;
                TextView textView1 = findViewById(R.id.addgoodsName);
                TextView textView2 = findViewById(R.id.addgoodsPrice);
                addgoodsname = textView1.getText().toString();
                addgoodsprice = textView2.getText().toString();
                if(addgoodsname.equals("")){Toast.makeText(Addgoods_Activity.this,"商品名不能为空",Toast.LENGTH_SHORT).show();}
                else {
                    if(addgoodsprice.equals("")){Toast.makeText(Addgoods_Activity.this,"商品价格不能为空",Toast.LENGTH_SHORT).show();}
                    else {
                        String account = null;
                        Cursor cursor = db.query("Goods",new String[]{"goodsName"},null,null,null,null,"id");
                        if (cursor.moveToFirst()){
                            do {
                                account = cursor.getString(cursor.getColumnIndex("goodsName"));
                                if(account.equals(addgoodsname)){
                                    Toast.makeText(Addgoods_Activity.this,"非常抱歉，商品名已存在",Toast.LENGTH_SHORT).show();
                                    flag++;
                                    break;
                                }
                            }while (cursor.moveToNext());

                        }
                        cursor.close();
                        if (flag == 0){
                            ContentValues values = new ContentValues();
                            values.put("goodsName", addgoodsname);
                            values.put("goodsPrice",addgoodsprice);
                            values.put("belongshop",ShopAdapter.shopid);
                            //values.put("Password",mima);
                            Toast.makeText(Addgoods_Activity.this,ShopAdapter.shoping,Toast.LENGTH_SHORT).show();
                            db.insert("Goods", null, values);
                            values.clear();		//清掉现有的内容
                            db.close();

                            Toast.makeText(Addgoods_Activity.this,"添加成功",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Addgoods_Activity.this,ShopActivity.class);
                            finish();
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setClass(this,ShopActivity.class);
        startActivity(intent);
        finish();
    }
}
