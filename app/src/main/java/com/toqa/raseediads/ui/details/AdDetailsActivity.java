package com.toqa.raseediads.ui.details;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.toqa.raseediads.R;
import com.toqa.raseediads.model.pojo.Ad;
import com.toqa.raseediads.ui.base.BaseActivity;
import com.toqa.raseediads.utils.Utils;

import butterknife.BindView;

public class AdDetailsActivity extends BaseActivity<AdDetailsPresenter> implements AdDetailsView {

    @BindView(R.id.drawee_ad)
    SimpleDraweeView draweeView_ad;

    @BindView(R.id.textView_title)
    TextView textView_title;

    @BindView(R.id.textView_url)
    TextView textView_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();

        final Ad ad = getIntent().getParcelableExtra(Utils.EXTRA_AD);
        if (ad != null) {
            draweeView_ad.setImageURI(ad.getPicture() == null ? "" : ad.getPicture());
            textView_title.setText(ad.getTitle());
            textView_url.setOnClickListener(view -> {
                String url = ad.getUrl();
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            });
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_ad_details;
    }
}
