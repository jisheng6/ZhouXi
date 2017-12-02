package com.example.adminjs.zhouxi.model;

import com.example.adminjs.zhouxi.IApplication;
import com.example.adminjs.zhouxi.bean.Bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Adminjs on 2017/12/2.
 */
//http://v.juhe.cn/toutiao/index?type="+data+"&key=2f092bd9ce76c0257052d6d3c93c11b4
public class MainModelImpl {
    public void getData(final ModelCallBack callBack){
        Call<Bean>call = IApplication.iGetDataBase.get("2f092bd9ce76c0257052d6d3c93c11b4");
        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                Bean bean = response.body();
                callBack.onSuccess(bean);
                IApplication.daoSession.getDataBeanDao().insertInTx(bean.getResult().getData());
            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {
                 callBack.onFailure(new Exception(""));
            }
        });
    }
   /* public void postData(final ModelCallBack callBack){

        Call<Bean> call =  IApplication.iGetDataBase.post("2f092bd9ce76c0257052d6d3c93c11b4");

        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                Bean bean = response.body() ;
                callBack.onSuccess(bean);

            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

                callBack.onFailure(new Exception(""));
            }
        });

    }*/

}
