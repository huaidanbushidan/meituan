package com.example.a32936;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private List<Goods> mGoodsList = new ArrayList<>();
    private MyDatabaseHelper dbHelper;
    private  int k = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Button buttonadd = findViewById(R.id.addGoods);
        Button buttondelete = findViewById(R.id.deleteshop);
        dbHelper = new MyDatabaseHelper(this);
        initData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView_goods);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        GoodsAdapter goodsAdapter = new GoodsAdapter(this,mGoodsList);
        recyclerView.setAdapter(goodsAdapter);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SQLiteDatabase db = dbHelper.getWritableDatabase();
                //Cursor cursor = db.query("Shop",new String[])

                if ( ShopAdapter.shoping.equals(LoginActivity.signer)){
                    Intent intent = new Intent(ShopActivity.this, Addgoods_Activity.class);
                    finish();
                    startActivityForResult(intent,1);
                }
                else{
                    Toast.makeText(ShopActivity.this,"对不起，您不是本店店主，无法修改商品",Toast.LENGTH_LONG).show();
                }
            }
        });


        buttondelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ShopAdapter.shoping.equals(LoginActivity.signer)) {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.delete("Shop", "Shop_Name = ?", new String[]{ShopAdapter.shopname});
                    db.delete("Goods","belongshop = ?",new String[]{ShopAdapter.shoping});
                    Toast.makeText(ShopActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ShopActivity.this,MainActivity.class);
                    finish();
                    startActivity(intent);
                }
                else{
                    Toast.makeText(ShopActivity.this,"对不起，您不是本店店主，无法删除店铺",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Goods",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                int belongshop1 = cursor.getInt(cursor.getColumnIndex("belongshop"));
                if (belongshop1 == ShopAdapter.shopid ){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                if(id > k){
                    int belongshop = cursor.getInt(cursor.getColumnIndex("belongshop"));
                    String name = cursor.getString(cursor.getColumnIndex("goodsName"));
                    Double Price = cursor.getDouble(cursor.getColumnIndex("goodsPrice"));
                    int picture = cursor.getInt(cursor.getColumnIndex("goodsPicture"));
                    k = id;
                    Goods goods = new Goods();
                    goods.setBelongshop(belongshop);
                    goods.setGoodsImageId(picture);
                    goods.setGoodsName(name);
                    goods.setGoodsPrice(Price);
                    mGoodsList.add(goods);
                            }
                        }
                    }while (cursor.moveToNext());
                  cursor.close();
            }
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}

