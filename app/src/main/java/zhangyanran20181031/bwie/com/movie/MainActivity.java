package zhangyanran20181031.bwie.com.movie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import zhangyanran20181031.bwie.com.movie.fragment.FourFragment;
import zhangyanran20181031.bwie.com.movie.fragment.OneFragment;
import zhangyanran20181031.bwie.com.movie.fragment.ThreeFragment;
import zhangyanran20181031.bwie.com.movie.fragment.TwoFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bottomTabBar.init(getSupportFragmentManager())
                .addTabItem("第一项", R.mipmap.ic_launcher, OneFragment.class)
                .addTabItem("第二项", R.mipmap.ic_launcher, TwoFragment.class)
                .addTabItem("第三项", R.mipmap.ic_launcher, ThreeFragment.class)
                .addTabItem("第四项", R.mipmap.ic_launcher, FourFragment.class);
    }
}
