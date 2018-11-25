package com.example.a32936;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Buy_sheet_Activity extends AppCompatActivity {
    private List<Goods> mGoodsList = new ArrayList<>();
    private MyDatabaseHelper dbHelper;
    private  int k = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_sheet_);
        dbHelper = new MyDatabaseHelper(this);
        initData();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView_goods);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        GoodsAdapter goodsAdapter = new GoodsAdapter(this,mGoodsList);
        recyclerView.setAdapter(goodsAdapter);

    }

    private void initData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Buysheet",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                String customer = cursor.getString(cursor.getColumnIndex("customer"));
                if (customer.equals(LoginActivity.signer) ){
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    if(id > k){
                        String name = cursor.getString(cursor.getColumnIndex("goodsName"));
                        Double Price = cursor.getDouble(cursor.getColumnIndex("goodsPrice"));
                        int picture = cursor.getInt(cursor.getColumnIndex("goodsPicture"));
                        k = id;
                        Goods goods = new Goods();
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
