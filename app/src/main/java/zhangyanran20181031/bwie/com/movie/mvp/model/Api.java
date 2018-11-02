package zhangyanran20181031.bwie.com.movie.mvp.model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zhangyanran20181031.bwie.com.movie.bean.AbBean;
import zhangyanran20181031.bwie.com.movie.bean.DetailsBean;
import zhangyanran20181031.bwie.com.movie.bean.NewBean;
import zhangyanran20181031.bwie.com.movie.bean.ShowBean;

/**
 * Created by 匹诺曹 on 2018/10/31.
 */

public interface Api {
    @GET("movie/v1/findHotMovieList")
    Observable<NewBean> toget(@Query("page") int page, @Query("count") String count);
    @GET("movie/v1/findReleaseMovieList")
    Observable<ShowBean> showget(@Query("page") int page, @Query("count") String count);
    @GET("movie/v1/findComingSoonMovieList")
    Observable<AbBean> abget(@Query("page") int page, @Query("count") String count);
    @GET("movie/v1/findMoviesDetail")
    Observable<DetailsBean> detailsget(@Query("movieId") int movieId);
}
