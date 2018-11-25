package com.example.a32936;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    List<Shop>mShopList;
    private Context context;
    public static String shoping,shopname;
    public static int shopid;
    public Shop shop;

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView shopImage;
        TextView shopName;
        TextView shopAddress;
        View shopView;

        public ViewHolder(View view) {
            super(view);
            shopView = view;
            shopAddress = (TextView ) view.findViewById(R.id.tv_shopaddress);
            shopImage = (ImageView) view.findViewById(R.id.iv_shop);
            shopName = (TextView) view.findViewById(R.id.tv_shopname);
        }
    }
    public ShopAdapter(Context context,List<Shop>shopList){
        this.context=context;
        mShopList = shopList;
    }
    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_shop, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ShopAdapter.ViewHolder holder, int i){
        Shop shop = mShopList.get(i);
        //Toast.makeText(context,String.valueOf(mShopList.get(i).getShopAccount()),Toast.LENGTH_LONG);
        holder.shopAddress.setText(shop.getShopAddress());
        holder.shopImage.setImageResource(shop.getImageId());
        holder.shopName.setText(shop.getName());
        holder.shopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        shoping = null;
                        int position = holder.getAdapterPosition();
                        Shop shop = mShopList.get(position);
                        Intent intent = new Intent() ;
                        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        shoping = shop.getOwner();
                        shopname = shop.getName();
                        shopid = shop.getShopAccount();
                        Toast.makeText(context,String.valueOf(shopid),Toast.LENGTH_SHORT).show();
                        intent.setClass(context,ShopActivity.class);
                        context.startActivity(intent);

                        //Toast.makeText(view.getContext(),shop.getName(),Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mShopList.size();
    }


}
