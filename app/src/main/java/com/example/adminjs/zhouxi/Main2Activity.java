package com.example.adminjs.zhouxi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adminjs.zhouxi.bean.EventBean;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Adminjs on 2017/12/2.
 */

public class Main2Activity extends Activity {
    @BindView(R.id.simpledraweeview)
    SimpleDraweeView simpledraweeview;
    @BindView(R.id.name)
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }
    @Subscribe(sticky = true)
    public void event(EventBean eventBean){
        simpledraweeview.setImageURI(eventBean.getUrl());
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
        roundingParams.setRoundAsCircle(true);
        simpledraweeview.getHierarchy().setRoundingParams(roundingParams);
        name.setText(eventBean.getTitle());
        Toast.makeText(this,""+eventBean.getUrl()+""+eventBean.getTitle(),Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
