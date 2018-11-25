package com.example.a32936;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        private ImageView imageView;
        List<Shop>mShopList = new ArrayList<>();
        private MyDatabaseHelper dbHelper; //声明全局变量
        private int flag_shop1 = 0,k = 0;
        private TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button buttonkd = findViewById(R.id.kaidian);
        dbHelper = new MyDatabaseHelper(this);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        //ContentValues values = new ContentValues();

        /*if (flag_shop1 == 0){
                 values.put("Shop_Name","海岚餐厅");
                 values.put("Shop_Address","海大二餐厅楼上");
                 values.put("Shop_Picture", R.drawable.a117);
                 db.insert("Shop",null,values);
                 values.clear();
                 db.close();
                 flag_shop1++;}*/
                 initData();

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ShopAdapter shopAdapter = new ShopAdapter(this,mShopList);
        recyclerView.setAdapter(shopAdapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.inflateHeaderView(R.layout.nav_header_main);
        navigationView.inflateMenu(R.menu.activity_main_drawer);
        View navHeaderView = navigationView.getHeaderView(0);
        tv1 = (TextView)navHeaderView.findViewById(R.id.userName);         //侧滑抽屉里的用户名
        tv2 = (TextView)navHeaderView.findViewById(R.id.userCount);
        tv1.setText(LoginActivity.yhm);
        tv2.setText(LoginActivity.account);
        imageView = (ImageView)navHeaderView.findViewById(R.id.imageView);
        //Button buttonsheet = navHeaderView.findViewById(R.id.nav_dingdan);
        imageView.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             switch (v.getId()){
                                                 case R.id.imageView:
                                                     Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                                                     finish();
                                                     startActivityForResult(intent,1);
                                             }
                                         }
                                     }
        );
        buttonkd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoginActivity.signer == null){
                    Toast.makeText(MainActivity.this,"请先登录",Toast.LENGTH_SHORT).show();
                }
                else {
                Intent intent = new Intent(MainActivity.this,Kaidian_Activity.class);
                finish();
                startActivityForResult(intent,1);
                     }
            }
        });
    }

    private void initData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Shop",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                    if(id > k){
                String owner = cursor.getString(cursor.getColumnIndex("owner"));
                String name = cursor.getString(cursor.getColumnIndex("Shop_Name"));
                String address = cursor.getString(cursor.getColumnIndex("Shop_Address"));
                int picture = cursor.getInt(cursor.getColumnIndex("Shop_Picture"));
                int account = cursor.getInt(cursor.getColumnIndex("shopAccount"));
                k = id;
                Shop shop = new Shop();
                shop.setOwner(owner);
                shop.setImageId(picture);
                shop.setName(name);
                shop.setShopAccount(account);
                shop.setShopAddress(address);
                mShopList.add(shop);
                    }
            }while (cursor.moveToNext());
        }
        cursor.close();

    }
   /* public void onClick(View v){              //明天继续设置头像点击事件！！！
        switch (v.getId()){
            case R.id.imageView:
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
        }
    }*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dingdan) {
                Intent intent = new Intent(MainActivity.this,Buy_sheet_Activity.class);
                finish();
                startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }



}
