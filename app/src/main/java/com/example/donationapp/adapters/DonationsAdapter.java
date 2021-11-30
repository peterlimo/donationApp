package com.example.donationapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.donationapp.R;
import com.example.donationapp.data.Cloth;
import com.example.donationapp.data.ClothData;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DonationsAdapter extends RecyclerView.Adapter<DonationsAdapter.ViewHolder>{
List<ClothData> list;
Context context;

    public DonationsAdapter(List<ClothData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cloth_donstions, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

holder.setData(list.get(position).getClothesDesc(),list.get(position).getClothesSizes(),list.get(position).getClothesType(),list.get(position).getClothesQuantity());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView descc,sizee,type,quant;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descc=itemView.findViewById(R.id.desc);
            sizee=itemView.findViewById(R.id.size);
            type=itemView.findViewById(R.id.type);
            quant=itemView.findViewById(R.id.quatity);
        }
        public void setData(String d,String s,String t,String q)
        {
          descc.setText(d);
          sizee.setText(s);
          type.setText(t);
          quant.setText(q);
        }
    }
}
