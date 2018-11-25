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

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {

    private List<Goods>mgoodslist;
    public static Goods goods;                                     //使用public以便buyacivity使用
    private Context context;
    public GoodsAdapter(Context context, List<Goods> list) {
        this.context=context;
        this.mgoodslist = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       final View view = LayoutInflater.from(viewGroup.getContext())
               .inflate(R.layout.item_goods, viewGroup, false);
       final  ViewHolder holder = new ViewHolder(view);
       holder.goodsImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               int position = holder.getAdapterPosition();
               Goods goods = mgoodslist.get(position);
               Intent intent = new Intent();
               intent.setClass(context,Buy_Activity.class);
               context.startActivity(intent);
               //Toast.makeText(view.getContext(),goods.getGoodsName(),Toast.LENGTH_SHORT).show();
           }
       });
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        goods = mgoodslist.get(i);
        viewHolder.goodsImage.setImageResource(goods.getGoodsImageId());
        viewHolder.goodsName.setText(goods.getGoodsName());
        viewHolder.goodsPrice.setText(String.valueOf(goods.getGoodsPrice()));
    }

    @Override
    public int getItemCount() {
        return mgoodslist.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView goodsImage;
        TextView goodsName;
        TextView goodsPrice;
        View goodsView;

        public ViewHolder( View view) {
            super(view);
            goodsView = view;
            goodsImage = (ImageView) view.findViewById(R.id.iv_goodsimage);
            goodsName = (TextView) view.findViewById(R.id.tv_goodsname) ;
            goodsPrice = (TextView) view.findViewById(R.id.tv_goodsprice);
        }
    }
}
