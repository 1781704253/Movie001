package zhangyanran20181031.bwie.com.movie.mvp.view;

import java.util.List;

import zhangyanran20181031.bwie.com.movie.bean.AbBean;
import zhangyanran20181031.bwie.com.movie.bean.NewBean;
import zhangyanran20181031.bwie.com.movie.bean.ShowBean;

/**
 * Created by 匹诺曹 on 2018/10/31.
 */

public interface IView {
    void onSuccess(List<NewBean.ResultBean> list);
    void onSuccessShow(List<ShowBean.ResultBean> result);
    void onSuccessAb(List<AbBean.ResultBean> abresult);
}
