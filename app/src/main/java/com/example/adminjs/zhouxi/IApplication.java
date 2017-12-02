package com.example.adminjs.zhouxi;

import android.app.Application;

import com.example.adminjs.zhouxi.dao.DaoMaster;
import com.example.adminjs.zhouxi.dao.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.greendao.database.Database;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Adminjs on 2017/12/2.
 */
//http://v.juhe.cn/toutiao/index?type="+data+"&key=2f092bd9ce76c0257052d6d3c93c11b4
public class IApplication extends Application{

    public static DaoSession daoSession;
    public static IGetDataBase iGetDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
      Retrofit retrofit = new Retrofit.Builder().baseUrl("http://v.juhe.cn")
              .addConverterFactory(GsonConverterFactory.create())
              .build();
        iGetDataBase = retrofit.create(IGetDataBase.class);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "text");
        Database database = helper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
    }
}
