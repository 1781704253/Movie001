package zhangyanran20181031.bwie.com.movie.mvp.presenter;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import zhangyanran20181031.bwie.com.movie.mvp.model.HttpUtils;
import zhangyanran20181031.bwie.com.movie.bean.NewBean;
import zhangyanran20181031.bwie.com.movie.mvp.view.IView;

/**
 * Created by 匹诺曹 on 2018/10/31.
 */

public class MainPresenter implements IPresenter {

    IView iView;

    public MainPresenter(IView iView) {
        this.iView = iView;
    }

    public void getData(int page, String count) {
        Observable<NewBean> toget = HttpUtils.getUtilsInstance().api.toget(page, count);
        toget.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(new Observer<NewBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewBean newBean) {
                iView.onSuccess(newBean.getResult());
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
