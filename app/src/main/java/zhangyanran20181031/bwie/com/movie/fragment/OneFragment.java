package zhangyanran20181031.bwie.com.movie.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zhangyanran20181031.bwie.com.movie.R;
import zhangyanran20181031.bwie.com.movie.onefragment.AboutFragment;
import zhangyanran20181031.bwie.com.movie.onefragment.PopularFragment;
import zhangyanran20181031.bwie.com.movie.onefragment.ShowingFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {


    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;
    @BindView(R.id.qiehuan)
    ImageView qiehuan;
    private ArrayList<String> layout;
    private ArrayList<Fragment> fragment;
    private int tiao = 0;
    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        unbinder = ButterKnife.bind(this, view);
        //创建集合

        layout = new ArrayList<>();
        layout.add("热门影片");
        layout.add("正在上映");
        layout.add("即将上映");
        fragment = new ArrayList<>();
        fragment.add(new PopularFragment());
        fragment.add(new ShowingFragment());
        fragment.add(new AboutFragment());

        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        vp.setAdapter(adapter);
        vp.setOffscreenPageLimit(layout.size());
        tabLayout.setupWithViewPager(vp);
        return view;
    }

    @OnClick(R.id.qiehuan)
    public void onViewClicked() {
        tiao = tiao += 1;
        EventBus.getDefault().post(tiao);
    }


    class MyPagerAdapter extends FragmentPagerAdapter {


        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragment.get(position);
        }

        @Override
        public int getCount() {
            return layout.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return layout.get(position);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
