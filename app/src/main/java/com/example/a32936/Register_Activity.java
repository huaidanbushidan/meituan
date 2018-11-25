package com.example.a32936;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity {
    //声明全局变量
    private MyDatabaseHelper dbHelper;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);
        dbHelper = new MyDatabaseHelper(this);
        Button zhuce = (Button)findViewById(R.id.email_register_button);

        zhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1 =(EditText) findViewById (R.id.zhuce_Yonghuming);
                EditText editText2 =(EditText) findViewById (R.id.register_email);
                EditText editText3 =(EditText) findViewById (R.id.register_password);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                flag = 0;
                 String yonghuming = editText1.getText().toString();
                 String zhanghao = editText2.getText().toString();
                 String mima = editText3.getText().toString();
                if(yonghuming.equals("")){Toast.makeText(Register_Activity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();}
                else {
                    if(zhanghao.equals("")){Toast.makeText(Register_Activity.this,"账号不能为空",Toast.LENGTH_SHORT).show();}
                    else {
                        if (mima.equals("")){Toast.makeText(Register_Activity.this,"密码不能为空",Toast.LENGTH_SHORT).show();}
                        else {
                                String account = null;
                            Cursor cursor = db.query("User",new String[]{"UserCount","Password"},null,null,null,null,"id");
                            if (cursor.moveToFirst()){
                                do {
                                     account = cursor.getString(cursor.getColumnIndex("UserCount"));
                                    if(account.equals(zhanghao)){
                                        Toast.makeText(Register_Activity.this,"非常抱歉，账号已存在",Toast.LENGTH_SHORT).show();
                                        flag++;
                                        break;
                                    }
                                }while (cursor.moveToNext());

                            }
                            cursor.close();
                            if (flag == 0){
                ContentValues values = new ContentValues();
                            values.put("UserName", yonghuming);
                            values.put("UserCount",zhanghao);
                            values.put("Password",mima);
                        db.insert("User", null, values);
                        values.clear();		//清掉现有的内容
                        db.close();

                Toast.makeText(Register_Activity.this,"注册成功",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Register_Activity.this,LoginActivity.class);
                            finish();
                            startActivity(intent);}
                    }}}}
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.setClass(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
