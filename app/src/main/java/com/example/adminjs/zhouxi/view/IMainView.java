package com.example.adminjs.zhouxi.view;
import com.example.adminjs.zhouxi.bean.Bean;

/**
 * Created by muhanxi on 17/12/1.
 */

public interface IMainView {


    public void onSuccess(Bean bean);
    public void onFailure(Exception e);

}
