package zhangyanran20181031.bwie.com.movie.mvp.presenter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zhangyanran20181031.bwie.com.movie.bean.ShowBean;
import zhangyanran20181031.bwie.com.movie.mvp.model.HttpUtils;
import zhangyanran20181031.bwie.com.movie.mvp.view.IView;

/**
 * Created by 匹诺曹 on 2018/10/31.
 */

public class ShowPresenter implements IPresenter {

    IView iView;

    public ShowPresenter(IView iView) {
        this.iView = iView;
    }

    public void getData(int page, String count) {
        Observable<ShowBean> toget = HttpUtils.getUtilsInstance().api.showget(page, count);
        toget.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<ShowBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ShowBean showBean) {
                iView.onSuccessShow(showBean.getResult());
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
        if (iView!=null){
            iView=null;
        }
    }
}
