package zhangyanran20181031.bwie.com.movie.mvp.presenter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zhangyanran20181031.bwie.com.movie.bean.AbBean;
import zhangyanran20181031.bwie.com.movie.mvp.model.HttpUtils;
import zhangyanran20181031.bwie.com.movie.mvp.view.IView;

/**
 * Created by 匹诺曹 on 2018/10/31.
 */

public class AbPresenter implements IPresenter {

    IView iView;

    public AbPresenter(IView iView) {
        this.iView = iView;
    }

    public void getData(int page, String count) {
        Observable<AbBean> toget = HttpUtils.getUtilsInstance().api.abget(page, count);
        toget.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<AbBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(AbBean abBean) {
                iView.onSuccessAb(abBean.getResult());
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