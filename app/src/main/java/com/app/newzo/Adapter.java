package com.app.newzo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.newzo.native_add.NativeTemplateStyle;
import com.app.newzo.native_add.TemplateView1;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter {

    static List<Modals> data;
    static Context context;
    static Modals modals;

    NativeAdView adView;

    NativeAd nativeAd;

    View view;

    public Adapter(List<Modals> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_container_news,
                    parent,
                    false);

            return new ViewHolder(view);


        } else {


            view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_conatiner_add,
                    parent,
                    false);

            return new AdViewHolder(view);

        }

    }

    @Override
    public int getItemViewType(int position) {

        if (position % 3 != 0 || position == 0 || position == 3) {

            return 0;

        } else {
            return 1;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (getItemViewType(position) == 0) {

            modals = data.get(position);

            ((ViewHolder) holder).heading.setText(modals.getHeading());
            ((ViewHolder) holder).date.setText(modals.getDate());
            ((ViewHolder) holder).desc.setText(modals.getDesc());
            ((ViewHolder) holder).source.setText(modals.getSource());

            System.out.println("URL is " + modals.getUrl());

            Picasso.get().load(modals.getUrl()).into(((ViewHolder) holder).img);


        } else if (getItemViewType(position) == 1) {


            MobileAds.initialize(context);
            AdLoader adLoader = new AdLoader.Builder(context, "ca-app-pub-3940256099942544/2247696110")
                    .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                        @Override
                        public void onNativeAdLoaded(NativeAd nativeAd) {
                            NativeTemplateStyle styles = new
                                    NativeTemplateStyle.Builder().build();

                            // ((AdViewHolder) holder).template.setStyles(styles);
                            ((AdViewHolder) holder).template.setNativeAd(nativeAd);
                        }
                    })
                    .build();

            adLoader.loadAd(new AdRequest.Builder().build());

        }


    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView heading;
        private final TextView desc;
        private final TextView source;
        private final TextView date;
        private final ImageView img;
        private final ConstraintLayout newsLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = itemView.findViewById(R.id.heading);
            desc = itemView.findViewById(R.id.desc);
            source = itemView.findViewById(R.id.source);
            date = itemView.findViewById(R.id.date);
            img = itemView.findViewById(R.id.imageView);
            newsLayout = itemView.findViewById(R.id.newsLayout);

            System.out.println("on View Holder");

            newsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modals = data.get(getAdapterPosition());

                    context.startActivity(new Intent(context, web.class).putExtra("url", modals.getUrl()));


                }
            });

        }
    }

    static class AdViewHolder extends RecyclerView.ViewHolder {


        TemplateView1 template;

        public AdViewHolder(@NonNull View itemView) {
            super(itemView);

            template = itemView.findViewById(R.id.my_template);

        }


    }


}
