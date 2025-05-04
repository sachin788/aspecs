package com.example.aspecs.Adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aspecs.Actitvitys.Productdetails_activity;
import com.example.aspecs.R;
import com.example.aspecs.models.Readingglassesmodel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ReadingAdapter extends FirebaseRecyclerAdapter<Readingglassesmodel, ReadingAdapter.myviewholder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ReadingAdapter(@NonNull FirebaseRecyclerOptions<Readingglassesmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Readingglassesmodel model) {
        holder.brandname.setText(model.getBrandname());
        holder.size.setText(model.getSize());
        holder.discount.setText(model.getDiscount());
        holder.price.setText(model.getPrice());
        holder.rating.setText(model.getRating());
        holder.favorite.setOnClickListener(view -> {
            if (holder.favorite.isChecked()){
                Toast.makeText(view.getContext(), "True", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(view.getContext(), "False", Toast.LENGTH_SHORT).show();

            }
        });
        Glide.with(holder.frameimage.getContext()).load(model.getImageurl()).
                error(R.drawable.dial_icon).into(holder.frameimage);
        Log.d("errorrr",""+model.getBrandname()+"  "+model.getImageurl());
        holder.readingdata.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), Productdetails_activity.class);
            i.putExtra("brandname",model.getBrandname());
            i.putExtra("image",model.getImageurl());
            i.putExtra("price",model.getPrice());
            i.putExtra("rating",model.getRating());
            i.putExtra("discount",model.getDiscount());
            i.putExtra("size",model.getSize());
            i.putExtra("subimage1",model.getSubimage1());
            i.putExtra("subimage2",model.getSubimage2());
            i.putExtra("subimage3",model.getSubimage3());
            i.putExtra("uid",model.getUid());

            view.getContext().startActivity(i);
            Toast.makeText(view.getContext(), ""+model.getBrandname()+""+model.getPrice(), Toast.LENGTH_SHORT).show();
        });
    }
    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReadingAdapter.myviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.readingglasses,parent,false));
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        CardView readingdata;
        private ToggleButton favorite;
        private AppCompatImageView frameimage;
        private AppCompatTextView brandname;
        private AppCompatTextView size;
        private AppCompatTextView rating;
        private AppCompatTextView discount;
        private AppCompatTextView price;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            frameimage = itemView.findViewById(R.id.ivframeimage);
            brandname = itemView.findViewById(R.id.tvbrandname);
            size = itemView.findViewById(R.id.tvsize);
            rating = itemView.findViewById(R.id.tvrating);
            discount = itemView.findViewById(R.id.tvdiscount);
            price = itemView.findViewById(R.id.tvprice);
            favorite = itemView.findViewById(R.id.ivfavorite);
            readingdata = itemView.findViewById(R.id.cvreadingglasses);
        }
    }
}
