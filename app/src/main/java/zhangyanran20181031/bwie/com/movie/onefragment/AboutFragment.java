package zhangyanran20181031.bwie.com.movie.onefragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zhangyanran20181031.bwie.com.movie.R;
import zhangyanran20181031.bwie.com.movie.adapter.AbAdapter;
import zhangyanran20181031.bwie.com.movie.bean.AbBean;
import zhangyanran20181031.bwie.com.movie.bean.NewBean;
import zhangyanran20181031.bwie.com.movie.bean.ShowBean;
import zhangyanran20181031.bwie.com.movie.hualang.GalleyTransFormer;
import zhangyanran20181031.bwie.com.movie.mvp.presenter.AbPresenter;
import zhangyanran20181031.bwie.com.movie.mvp.view.IView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment implements IView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;
    @BindView(R.id.gao)
    SimpleDraweeView gao;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private int page = 1;
    private String count = "10";
    private boolean isFresh;
    private AbPresenter abPresenter;
    private AbAdapter abAdapter;
    private LinearLayoutManager layoutManager;
    private List<String> imageList = new ArrayList<>();

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        EventBus.getDefault().register(this);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
        abPresenter.getData(page, count);
    }

    private void initData() {
        abPresenter = new AbPresenter(this);
        abAdapter = new AbAdapter(getActivity());
        recyclerView.setAdapter(abAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (layoutManager.findLastVisibleItemPosition() == abAdapter.getData().size() - 1) {
                    loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                refresh();
            }
        });
    }

    private void refresh() {
        page = 1;
        isFresh = true;
        abPresenter.getData(page, count);
    }

    private void loadMore() {
        page++;
        isFresh = false;
        abPresenter.getData(page, count);
    }

    private void initView() {
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }


    @Override
    public void onSuccess(List<NewBean.ResultBean> list) {

    }

    @Override
    public void onSuccessShow(List<ShowBean.ResultBean> result) {

    }

    @Override
    public void onSuccessAb(List<AbBean.ResultBean> abresult) {
        for (int i = 0; i < abresult.size(); i++) {
            imageList.add(abresult.get(i).getImageUrl());
        }
        initHuaLangView();
        if (isFresh) {
            abAdapter.addData(abresult, true);
            isFresh = false;
            swipeRefreshLayout.setRefreshing(false);
            Log.e("myMessage", "size  " + abAdapter.getData().size());
        } else {
            abAdapter.addData(abresult, false);
        }
        abAdapter.notifyDataSetChanged();
    }

    private void initHuaLangView() {
        viewpager.setPageTransformer(true, new GalleyTransFormer());
        viewpager.setOffscreenPageLimit(5);
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                SimpleDraweeView imageView = new SimpleDraweeView(getContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setImageURI(imageList.get(position));
                String s = imageList.get(position);
                showUrlBlur(s);
                container.addView(imageView);
                return imageView;
            }
        });
        viewpager.setCurrentItem(3);
        /*设置点击ViewPager之外的部位，ViewPager跟着滑动*/
        RelativeLayout parent = (RelativeLayout) viewpager.getParent();
        parent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewpager.dispatchTouchEvent(event);
            }
        });
        viewpager.setCurrentItem(1);
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String s = imageList.get(position);
                showUrlBlur(s);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //高斯模糊
    private void showUrlBlur(String imageUrl) {
        try {
            Uri uri = Uri.parse(imageUrl);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(2, 30))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(gao.getController())
                    .setImageRequest(request)
                    .build();

            gao.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEvent(Integer integer){
        Log.i("pp",integer.toString());
        if (integer%2==0){
            gao.setVisibility(View.VISIBLE);
            viewpager.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.GONE);
        }else {
            gao.setVisibility(View.GONE);
            viewpager.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
        }
    }
}
