package com.example.coursebuy.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursebuy.Interface.ItemClickListener;
import com.example.coursebuy.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView txtproductname,txtproduxtdesc,txtproductprice;
    public ImageView image;
    public ItemClickListener itemClickListener;
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        txtproductname=(TextView)itemView.findViewById(R.id.product_names);
        txtproduxtdesc=(TextView)itemView.findViewById(R.id.product_desc);
        image=(ImageView)itemView.findViewById(R.id.product_image);
        txtproductprice=(TextView)itemView.findViewById(R.id.product_prices);
    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
