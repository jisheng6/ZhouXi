package com.example.adminjs.zhouxi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.adminjs.zhouxi.adapter.MyAdapter;
import com.example.adminjs.zhouxi.fragment.Fragment_01;
import com.example.adminjs.zhouxi.fragment.Fragment_02;
import com.example.adminjs.zhouxi.fragment.Fragment_03;
import com.example.adminjs.zhouxi.fragment.Fragment_04;
import com.example.adminjs.zhouxi.fragment.Fragment_05;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private RadioGroup group;
    private ArrayList<Fragment> list;
    private ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager)findViewById(R.id.pager);
        group = (RadioGroup) findViewById(R.id.group);
        list = new ArrayList<>();
        list.add(new Fragment_01());
        list.add(new Fragment_02());
        list.add(new Fragment_03());
        list.add(new Fragment_04());
        list.add(new Fragment_05());
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(),list);
        pager.setAdapter(myAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                group.check(group.getChildAt(position).getId());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radio_01:
                        pager.setCurrentItem(0,false);
                        break;
                    case R.id.radio_02:
                        pager.setCurrentItem(1,false);
                        break;
                    case R.id.radio_03:
                        pager.setCurrentItem(2,false);
                        break;
                    case R.id.radio_04:
                        pager.setCurrentItem(3,false);
                        break;
                    case R.id.radio_05:
                        pager.setCurrentItem(4,false);
                        break;
                }
            }
        });
        pager.setOffscreenPageLimit(5);
    }
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    Toast.makeText(context,"当前所连接的网络可用",Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
        Toast.makeText(context,"网络不可用",Toast.LENGTH_SHORT).show();
        return false;
    }

}
