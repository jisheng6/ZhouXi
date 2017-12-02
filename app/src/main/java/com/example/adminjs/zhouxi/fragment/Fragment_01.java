package com.example.adminjs.zhouxi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adminjs.zhouxi.IApplication;
import com.example.adminjs.zhouxi.R;
import com.example.adminjs.zhouxi.adapter.IApdater;
import com.example.adminjs.zhouxi.bean.Bean;
import com.example.adminjs.zhouxi.bean.DataBean;
import com.example.adminjs.zhouxi.presenter.MainPresenter;
import com.example.adminjs.zhouxi.view.IMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.adminjs.zhouxi.MainActivity.isNetworkAvailable;

/**
 * Created by Adminjs on 2017/12/2.
 */
//http://v.juhe.cn/toutiao/index?type="+data+"&key=2f092bd9ce76c0257052d6d3c93c11b4
public class Fragment_01 extends Fragment implements IMainView{
    private MainPresenter presenter;
    private IApdater adapter;
    private RecyclerView recycleview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_01, container, false);
        recycleview = view.findViewById(R.id.recyclerview);
        presenter = new MainPresenter(this);
        presenter.get();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        adapter = new IApdater(getActivity());

        boolean b = isNetworkAvailable(getActivity());
        if (b){
            recycleview.setLayoutManager(manager);
            recycleview.setAdapter(adapter);
            List<DataBean> dataBeans = IApplication.daoSession.getDataBeanDao().loadAll();
            for (DataBean bean : dataBeans){
                System.out.println(bean.toString());
            }
        }else{
            Toast.makeText(getActivity(),"当前网络不可用，请检查网络！",Toast.LENGTH_SHORT).show();
        }

        return view;
    }
    @Override
    public void onSuccess(Bean bean) {
        adapter.addData(bean);
    }

    @Override
    public void onFailure(Exception e) {

    }
}
