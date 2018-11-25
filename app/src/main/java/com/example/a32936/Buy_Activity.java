package com.example.a32936;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static com.example.a32936.GoodsAdapter.goods;

public class Buy_Activity extends AppCompatActivity {
    List<Shop>mShopList;
    private Context context;
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_);
        dbHelper = new MyDatabaseHelper(this);
        ImageView imageView = findViewById(R.id.buyGoodsPicture);
        TextView editTextname = findViewById(R.id.buyGoodsName);
        TextView editTextprice = findViewById(R.id.buyGoodsPrice);
        Button buymakesure = findViewById(R.id.buyMakeSure);
        Button deletegoods = findViewById(R.id.deletegoods);
        Button gaigoods = findViewById(R.id.gaigoods);
        //imageView.setImageResource();
        editTextname.setText(goods.getGoodsName());
        editTextprice.setText(String.valueOf(goods.getGoodsPrice()));

        buymakesure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.signer == null){
                    Toast.makeText(Buy_Activity.this,"请先登录",Toast.LENGTH_SHORT).show();
                }
                else {
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("customer",LoginActivity.signer);
                    values.put("goodsName",GoodsAdapter.goods.getGoodsName());
                    values.put("goodsPrice",GoodsAdapter.goods.getGoodsPrice());
                    values.put("goodsPicture",GoodsAdapter.goods.getGoodsImageId());
                    db.insert("Buysheet",null,values);
                    Toast.makeText(Buy_Activity.this, "购买成功", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(Buy_Activity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        deletegoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ShopAdapter.shoping.equals(LoginActivity.signer)){
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.delete("Goods","goodsName = ?",new String[]{goods.getGoodsName()});
                    Toast.makeText(Buy_Activity.this,"删除成功",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Buy_Activity.this,"对不起，您不是本店店主，无法删除商品",Toast.LENGTH_LONG).show();
                }

            }
        });

        gaigoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ShopAdapter.shoping.equals(LoginActivity.signer)){
                    Intent intent = new Intent(Buy_Activity.this,Edit_Goods_Activity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Buy_Activity.this,"对不起，您不是本店店主，无法修改商品信息",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    /*private void LoadDate() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Goods", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                if (id > k) {
                    String owner = cursor.getString(cursor.getColumnIndex("owner"));
                    String name = cursor.getString(cursor.getColumnIndex("Shop_Name"));
                    String address = cursor.getString(cursor.getColumnIndex("Shop_Address"));
                    int picture = cursor.getInt(cursor.getColumnIndex("Shop_Picture"));
                    k = id;
                    Shop shop = new Shop();
                    shop.setOwner(owner);
                    shop.setImageId(picture);
                    shop.setName(name);
                    shop.setShopAddress(address);
                    mShopList.add(shop);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
    }*/
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}