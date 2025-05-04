package com.example.aspecs.Adapters;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.aspecs.Actitvitys.Homepage;
import com.example.aspecs.R;
import com.example.aspecs.models.Readingglassesmodel;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewholder> {

    ArrayList<Readingglassesmodel> modellist;
    Context context;

    public CartAdapter(ArrayList<Readingglassesmodel> model, Context context) {
        this.modellist = model;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartAdapter.viewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cartdesign,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.brandname.setText(modellist.get(position).getBrandname());
        holder.size.setText(modellist.get(position).getSize());
        holder.discount.setText(modellist.get(position).getDiscount());
        holder.price.setText(modellist.get(position).getPrice());
        Intent intent1 = ((Activity) context).getIntent();
        String q = (String) holder.quatity.getText();
        holder.quatity.setText(q + intent1.getStringExtra("qua"));
        //Notification
        notificationchannel();
       // Bitmap bitmap = BitmapFactory.decodeFile(modellist.get(position).getImageurl());

        holder.buybtn.setOnClickListener(view -> {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(view.getContext(),"1");
            Bitmap bitmap = BitmapFactory.decodeResource(view.getResources(), R.drawable.wiredframe);
            Log.d("bitmapppp", String.valueOf(bitmap));
            Intent intent = new Intent(context, Homepage.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            builder.setSmallIcon(R.drawable.ivperson)
                    .setLargeIcon(bitmap)
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .bigPicture(bitmap))
                    .setContentIntent(pendingIntent)
                    .setContentTitle("Notification")
                    .setSubText("hi, its from admin him self")
                    .setContentText("Order is placed");
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
            managerCompat.notify(12, builder.build());

        });

        Glide.with(holder.frameimage.getContext()).load(modellist.get(position).getImageurl()).
                error(R.drawable.dial_icon).into(holder.frameimage);
        Log.d("errorrr",""+modellist.get(position).getBrandname()+"  "+modellist.get(position).getImageurl());
      /*  holder.readingdata.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), Productdetails_activity.class);
            i.putExtra("brandname",modellist.get(position).getBrandname());
            i.putExtra("image",modellist.get(position).getImageurl());
            i.putExtra("price",modellist.get(position).getPrice());
            i.putExtra("rating",modellist.get(position).getRating());
            i.putExtra("discount",modellist.get(position).getDiscount());
            i.putExtra("size",modellist.get(position).getSize());
            i.putExtra("subimage1",modellist.get(position).getSubimage1());
            i.putExtra("subimage2",modellist.get(position).getSubimage2());
            i.putExtra("subimage3",modellist.get(position).getSubimage3());
            view.getContext().startActivity(i);
        });*/
    }

    @Override
    public int getItemCount() {
        return modellist.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        CardView readingdata;
        private ToggleButton favorite;
        private AppCompatImageView frameimage;
        private AppCompatTextView brandname;
        private AppCompatTextView size;
        AppCompatButton buybtn;
        private AppCompatTextView quatity;
        private AppCompatTextView rating;
        private AppCompatTextView discount;
        private AppCompatTextView price;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            frameimage = itemView.findViewById(R.id.ivframeimage);
            brandname = itemView.findViewById(R.id.tvbrandname);
            buybtn = itemView.findViewById(R.id.btnbuy);
            quatity = itemView.findViewById(R.id.tvquantity);

            size = itemView.findViewById(R.id.tvsize);
//            rating = itemView.findViewById(R.id.tvrating);
            discount = itemView.findViewById(R.id.tvdiscount);
            price = itemView.findViewById(R.id.tvprice);
//            favorite = itemView.findViewById(R.id.ivfavorite);
            readingdata = itemView.findViewById(R.id.cvreadingglasses);
        }
    }
       private void notificationchannel(){
           if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
               NotificationChannel channel = new NotificationChannel("1","Buy_notification", NotificationManager.IMPORTANCE_DEFAULT);
               NotificationManager manager = context.getSystemService(NotificationManager.class);
               manager.createNotificationChannel(channel);
           }

       }
}
