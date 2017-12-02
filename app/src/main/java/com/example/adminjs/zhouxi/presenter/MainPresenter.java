package com.example.adminjs.zhouxi.presenter;

import android.support.v4.app.FragmentActivity;

import com.example.adminjs.zhouxi.bean.Bean;
import com.example.adminjs.zhouxi.model.MainModelImpl;
import com.example.adminjs.zhouxi.model.ModelCallBack;
import com.example.adminjs.zhouxi.view.IMainView;

/**
 * Created by Adminjs on 2017/12/2.
 */

public class MainPresenter {
    private IMainView iView ;
    private MainModelImpl mainModel ;

    public MainPresenter(IMainView iView) {
        this.iView = iView;
        this.mainModel = new MainModelImpl();
    }

    public void get() {
        mainModel.getData(new ModelCallBack() {
            @Override
            public void onSuccess(Bean bean) {
                if (iView != null) {
                    iView.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Exception e) {
                if (iView != null) {
                    iView.onFailure(e);
                }
            }
        });
    }
  /*  public void post(){
        mainModel.postData(new ModelCallBack() {
            @Override
            public void onSuccess(Bean bean) {
                if(iView != null){
                    iView.onSuccess(bean);
                }
            }

            @Override
            public void onFailure(Exception e) {
                if(iView != null){
                    iView.onFailure(e);
                }
            }
        });
    }*/
}
