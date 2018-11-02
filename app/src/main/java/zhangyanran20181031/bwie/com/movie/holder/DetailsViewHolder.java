package zhangyanran20181031.bwie.com.movie.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;

import zhangyanran20181031.bwie.com.movie.R;

/**
 * Created by 匹诺曹 on 2018/11/1.
 */

public class DetailsViewHolder extends RecyclerView.ViewHolder {

    public final TextView title_one, price_one,city;
    public final SimpleDraweeView image_view;

    public DetailsViewHolder(View itemView) {
        super(itemView);
        image_view = itemView.findViewById(R.id.simpleDraweeView);
        title_one = itemView.findViewById(R.id.details_name);
        price_one = itemView.findViewById(R.id.details_leixing);
        city = itemView.findViewById(R.id.details_city);
    }


    public void bindtext(String img,String desc,String desc2,String desc3) {
        image_view.setImageURI(img);
        title_one.setText(desc);
        price_one.setText(desc2);
        city.setText(desc3);
    }
}
