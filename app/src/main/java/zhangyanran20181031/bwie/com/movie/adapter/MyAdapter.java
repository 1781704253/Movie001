package zhangyanran20181031.bwie.com.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import zhangyanran20181031.bwie.com.movie.R;
import zhangyanran20181031.bwie.com.movie.activity.Details;
import zhangyanran20181031.bwie.com.movie.holder.MyViewHolder;
import zhangyanran20181031.bwie.com.movie.bean.NewBean;

/**
 * Created by 匹诺曹 on 2018/10/31.
 */

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{

    Context context;
    List<NewBean.ResultBean> list = new ArrayList<>();

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<NewBean.ResultBean> list,boolean isFresh){
        if (list!=null) {
            if(isFresh) {
                this.list.clear();
            }
            this.list.addAll(list);
        }
    }

    public List<NewBean.ResultBean> getData() {
        return list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        String img= list.get(position).getImageUrl();
        String desc=list.get(position).getName();
        String desc2=list.get(position).getSummary();
        holder.bindtext(img,desc,desc2);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Details.class);
                intent.putExtra("idid", list.get(position).getId()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
