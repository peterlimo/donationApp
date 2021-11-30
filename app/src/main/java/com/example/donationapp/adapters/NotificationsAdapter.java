package com.example.donationapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.donationapp.R;
import com.example.donationapp.data.ClothData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    private List<ClothData> list;
    private Context context;
    OnItemClickListener listener;
    public NotificationsAdapter(List<ClothData>UserList,Context context,OnItemClickListener listener){

        this.list=UserList;
        this.context=context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.list_item_cloth_donstions,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setData(list.get(position).getClothesDesc(),list.get(position).getClothesSizes(),list.get(position).getClothesType(),list.get(position).getClothesQuantity());
        holder.setImageView(list.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView descc,sizee,type,quant;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descc=itemView.findViewById(R.id.desc);
            sizee=itemView.findViewById(R.id.size);
            type=itemView.findViewById(R.id.type);
            quant=itemView.findViewById(R.id.quatity);
            imageView=itemView.findViewById(R.id.cloth_image);
        }

        public void setData(String d,String s,String t,String q)
        {
            descc.setText(d);
            sizee.setText(s);
            type.setText(t);
            quant.setText(q);
        }

        public void setImageView(String url) {
            Picasso.get().load(url).into(imageView);
        }
    }

    public interface OnItemClickListener{
        void onItemDetailsButtonClick(int position, View v);
    }
}
