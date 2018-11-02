package zhangyanran20181031.bwie.com.movie.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import zhangyanran20181031.bwie.com.movie.R;

/**
 * Created by 匹诺曹 on 2018/10/31.
 */

public class MyViewHolder  extends RecyclerView.ViewHolder {

    public final SimpleDraweeView image_view;
    public final TextView title_one, price_one;

    public MyViewHolder(View itemView) {
        super(itemView);
        image_view = itemView.findViewById(R.id.image_view);
        title_one = itemView.findViewById(R.id.title_one);
        price_one = itemView.findViewById(R.id.price_one);
    }


    public void bindtext(String img, String desc, String desc2) {
        image_view.setImageURI(img);
        title_one.setText(desc);
        price_one.setText(desc2);
    }
}
