package zhangyanran20181031.bwie.com.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import zhangyanran20181031.bwie.com.movie.R;
import zhangyanran20181031.bwie.com.movie.bean.AbBean;
import zhangyanran20181031.bwie.com.movie.bean.NewBean;
import zhangyanran20181031.bwie.com.movie.holder.MyViewHolder;

/**
 * Created by 匹诺曹 on 2018/10/31.
 */

public class AbAdapter extends RecyclerView.Adapter<MyViewHolder>{

    Context context;
    List<AbBean.ResultBean> list = new ArrayList<>();

    public AbAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<AbBean.ResultBean> list,boolean isFresh){
        if (list!=null) {
            if(isFresh) {
                this.list.clear();
            }
            this.list.addAll(list);
        }
    }

    public List<AbBean.ResultBean> getData() {
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
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("detailUrl",list.get(position).getDetailUrl());
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
