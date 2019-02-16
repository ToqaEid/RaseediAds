package com.toqa.raseediads.ui.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.toqa.raseediads.R;
import com.toqa.raseediads.model.pojo.Ad;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.AdsViewHolder> {

    public interface AdClickListener{
        void onAdClick(Ad ad);
    }

    private List<Ad> adList;
    private AdClickListener adClickListener;

    public AdsAdapter() {
        adList = new ArrayList<>();
    }


    @NonNull
    @Override
    public AdsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_ads, parent, false);
        return new AdsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdsViewHolder holder, final int position) {
        holder.fillData(adList.get(position));
        holder.itemView.setOnClickListener(view -> {
            if (adClickListener != null) {
                adClickListener.onAdClick(adList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return adList == null ? 0 : adList.size();
    }


    public void setAdClickListener(AdClickListener adClickListener) {
        this.adClickListener = adClickListener;
    }

    public void addItems(List<Ad> ads) {
        this.adList.addAll(ads);
        notifyDataSetChanged();
    }

    public void clear() {
        final int size = adList.size();
        adList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public class AdsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.drawee_ad)
        SimpleDraweeView drawee_ad;

        @BindView(R.id.textView_title)
        TextView textView_title;

        public AdsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void fillData(Ad ad) {

            if (ad != null) {
                drawee_ad.setImageURI(ad.getPicture() == null ? "" : ad.getPicture());
                textView_title.setText(ad.getTitle());
            }
        }
    }
}