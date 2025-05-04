package com.example.aspecs.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aspecs.R;

import java.util.List;

public class Treadingbrandsadapter extends RecyclerView.Adapter<Treadingbrandsadapter.viewholder> {

    List<String> brandlist;
    Context context;

    public Treadingbrandsadapter(List<String> brandnames, Context context) {
        this.brandlist = brandnames;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(LayoutInflater.from(context).inflate(R.layout.rvtrendingbrandlayout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
      holder.brandname.setText(brandlist.get(position));
    }

    @Override
    public int getItemCount() {
        return brandlist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        AppCompatTextView brandname;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            brandname = itemView.findViewById(R.id.tvtrendingbrandname);
        }
    }
}
