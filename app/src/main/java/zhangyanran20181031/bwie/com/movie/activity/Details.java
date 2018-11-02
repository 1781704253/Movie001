package zhangyanran20181031.bwie.com.movie.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;
import zhangyanran20181031.bwie.com.movie.R;
import zhangyanran20181031.bwie.com.movie.bean.DetailsBean;
import zhangyanran20181031.bwie.com.movie.mvp.presenter.DetailsPresenter;
import zhangyanran20181031.bwie.com.movie.mvp.view.DetailsView;

public class Details extends AppCompatActivity implements DetailsView {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.simpleDraweeView)
    SimpleDraweeView simpleDraweeView;
    @BindView(R.id.details_name)
    TextView detailsName;
    @BindView(R.id.details_leixing)
    TextView detailsLeixing;
    @BindView(R.id.details_pianchang)
    TextView detailsPianchang;
    @BindView(R.id.details_city)
    TextView detailsCity;
    @BindView(R.id.xiang_gao)
    ImageView xiangGao;
    private DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        initData();
        String sID = getIntent().getStringExtra("idid");
        int id = Integer.parseInt(sID);
        detailsPresenter.getData(id);

    }

    private void initData() {
        detailsPresenter = new DetailsPresenter(this);
    }


    @OnClick(R.id.fanhui)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void onSuccesDetails(DetailsBean detailsBean) {
        simpleDraweeView.setImageURI(detailsBean.getResult().getImageUrl());
        detailsName.setText(detailsBean.getResult().getName());
        detailsLeixing.setText(detailsBean.getResult().getMovieTypes());
        detailsPianchang.setText(detailsBean.getResult().getDuration());
        detailsCity.setText(detailsBean.getResult().getPlaceOrigin());
        Glide.with(this).load(detailsBean.getResult().getImageUrl()).bitmapTransform(new BlurTransformation(this, 25)).into(xiangGao);
    }



}
