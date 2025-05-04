package com.example.aspecs.Adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aspecs.Actitvitys.Kidsreadingglasses;
import com.example.aspecs.Actitvitys.Menreadingglasses;
import com.example.aspecs.Actitvitys.Womenreadingglassses;
import com.example.aspecs.R;
import com.example.aspecs.models.Modelimages;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ModelsAdapterdemo extends FirebaseRecyclerAdapter<Modelimages, ModelsAdapterdemo.myviewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ModelsAdapterdemo(@NonNull FirebaseRecyclerOptions<Modelimages> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Modelimages model) {
        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDesc());
        holder.gender.setText(model.getGender());
        Glide.with(holder.img.getContext()).load(model.getImage()).
                error(R.drawable.dial_icon).into(holder.img);
        holder.readingglasses.setOnClickListener(view -> {
            if (model.getGender().matches("Men")) {
                Intent i = new Intent(view.getContext(), Menreadingglasses.class);
                view.getContext().startActivity(i);
            } else if (model.getGender().matches("Women")) {
                Intent i = new Intent(view.getContext(), Womenreadingglassses.class);
                view.getContext().startActivity(i);
            } else if (model.getGender().matches("Kids")) {
                Intent i = new Intent(view.getContext(), Kidsreadingglasses.class);
                view.getContext().startActivity(i);

            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new myviewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.modeldesign, parent, false));
    }

    class myviewholder extends RecyclerView.ViewHolder {
        private AppCompatImageView img;
        private CardView readingglasses;
        private AppCompatTextView title;
        private AppCompatTextView desc;
        private AppCompatTextView gender;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.ivmodels);
            title = itemView.findViewById(R.id.tvtitle);
            gender = itemView.findViewById(R.id.tvgender);
            desc = itemView.findViewById(R.id.tvdescription);
            readingglasses = itemView.findViewById(R.id.cvreadingglasses);


        }
    }
}
