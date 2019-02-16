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

/**
 * Adapter class for AdList RecyclerView
 */
public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.AdsViewHolder> {

    /**
     * Single callback method interface listener on Ad item click action
     */
    public interface AdClickListener {
        void onAdClick(Ad ad);
    }

    /**
     * List of Ad objects that will fill the recycle view
     */
    private List<Ad> adList;
    private AdClickListener adClickListener;

    /**
     * Solo empty constructor
     */
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

    /**
     * Sets the adClickListener
     *
     * @param adClickListener the adClickListener to set
     */
    public void setAdClickListener(AdClickListener adClickListener) {
        this.adClickListener = adClickListener;
    }

    /**
     * add new ads to existing adList
     *
     * @param ads elements to be appended to the list
     */
    public void addItems(List<Ad> ads) {
        this.adList.addAll(ads);
        notifyDataSetChanged();
    }

    /**
     * clear adList from its data
     */
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

        /**
         * method that fills the UI of recyclerView item with data
         *
         * @param ad is the data the will be filled in the UI
         */
        public void fillData(Ad ad) {
            if (ad != null) {
                drawee_ad.setImageURI(ad.getPicture() == null ? "" : ad.getPicture());
                textView_title.setText(ad.getTitle());
            }
        }
    }
}
