package com.example.adminjs.zhouxi.adapter;

import android.content.Context;
import android.content.Intent;
import android.renderscript.Element;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adminjs.zhouxi.Main2Activity;
import com.example.adminjs.zhouxi.R;
import com.example.adminjs.zhouxi.bean.Bean;
import com.example.adminjs.zhouxi.bean.DataBean;
import com.example.adminjs.zhouxi.bean.EventBean;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adminjs on 2017/12/2.
 */

public class IApdater extends RecyclerView.Adapter<IApdater.IViewHolder>{
   Context context;
   List<DataBean>list;
    public IApdater(Context context) {
        this.context = context;
    }
    public void addData(Bean bean){
        if (list == null){
            list = new ArrayList<>();
        }
        list.addAll(bean.getResult().getData());
        notifyDataSetChanged();
    }
    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.layout,null);
        return new IViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, final int position) {
       holder.simpleDraweeView.setImageURI(list.get(position).getThumbnail_pic_s());
       holder.textView.setText(list.get(position).getTitle());
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setRoundAsCircle(true);
        holder.simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);
        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().postSticky(new EventBean(list.get(position).getThumbnail_pic_s(),list.get(position).getTitle()));
                context.startActivity(new Intent(context, Main2Activity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class IViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.adapter_simpledraweeview)
    SimpleDraweeView simpleDraweeView;
    @BindView(R.id.adapter_textview)
    TextView textView;
    public IViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
}
