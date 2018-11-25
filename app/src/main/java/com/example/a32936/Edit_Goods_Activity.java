package com.example.a32936;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit_Goods_Activity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__goods_);
        final EditText editTextprice = findViewById(R.id.Edit_goodsprice);
        Button buttonsure = findViewById(R.id.Editgoods_makesure);
        dbHelper = new MyDatabaseHelper(this);
        editTextprice.setText(String.valueOf(GoodsAdapter.goods.getGoodsPrice()));

        buttonsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price = editTextprice.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("goodsPrice",price);
                db.update("Goods",values,"goodsName = ?",new String[]{GoodsAdapter.goods.getGoodsName()});
                Toast.makeText(Edit_Goods_Activity.this,"修改成功",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                finish();
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(Edit_Goods_Activity.this,ShopActivity.class);
                startActivity(intent);
            }
        });
    }
}
