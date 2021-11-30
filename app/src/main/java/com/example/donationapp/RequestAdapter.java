package com.example.donationapp;

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
import com.example.donationapp.data.requestdata;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ViewHolder> {
    private final List<requestdata> list;
    private final Context context;
    OnItemClickListener listener;
    public RequestAdapter(List<requestdata>UserList,Context context,OnItemClickListener listener){

        this.list=UserList;
        this.context=context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.list_of_donstion_requested,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.setData(list.get(position).getFname(),list.get(position).getLname(),list.get(position).getEmails(),
                list.get(position).getAddress(),
                list.get(position).getPhone(),list.get(position).getMem(),list.get(position).getOther());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView firstname,lastname,email,address,phone,members,other;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname=itemView.findViewById(R.id.firstname);
            lastname=itemView.findViewById(R.id.lastname);
            email=itemView.findViewById(R.id.email);
            address=itemView.findViewById(R.id.address);
            phone=itemView.findViewById(R.id.phone);
            members=itemView.findViewById(R.id.members);
            other=itemView.findViewById(R.id.other);

        }

        public void setData(String f,String l,String e,String a,String p,String m,String o)
        {
            firstname.setText(f);
            lastname.setText(l);
            email.setText(e);
            address.setText(a);
            phone.setText(p);
            members.setText(m);
            other.setText(o);
        }
    }

    public interface OnItemClickListener{
        void onItemDetailsButtonClick(int position, View v);
    }
}


