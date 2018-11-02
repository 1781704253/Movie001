package zhangyanran20181031.bwie.com.movie.mvp.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zhangyanran20181031.bwie.com.movie.bean.DetailsBean;
import zhangyanran20181031.bwie.com.movie.mvp.model.HttpUtils;
import zhangyanran20181031.bwie.com.movie.mvp.view.DetailsView;

/**
 * Created by 匹诺曹 on 2018/11/1.
 */

public class DetailsPresenter implements IPresenter {

    DetailsView detailsView;

    public DetailsPresenter(DetailsView detailsView) {
        this.detailsView = detailsView;
    }

    public void getData(int movieId) {
        Log.i("idid",movieId+"");
        Observable<DetailsBean> toget = HttpUtils.getUtilsInstance().api.detailsget(movieId);
        toget.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<DetailsBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DetailsBean detailsBean) {
                detailsView.onSuccesDetails(detailsBean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }



    @Override
    public void onDestroy() {
        if (detailsView!=null){
            detailsView=null;
        }
    }
}
