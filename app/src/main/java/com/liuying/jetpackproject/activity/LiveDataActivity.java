package com.liuying.jetpackproject.activity;

import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.liuying.jetpackproject.R;

public class LiveDataActivity extends AppCompatActivity {
    private MyModel myModel;
    private TextView mTv;
    private Button mBtn;
    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        mTv = findViewById(R.id.tv_content);
        mBtn = findViewById(R.id.btn_update);

        CustomLiveData.getInstance(this).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String networkInfo) {
                stringBuffer.append("onNetChanged: " + networkInfo).append("\n");
                mTv.setText(stringBuffer);
            }
        });


        //myModel =    ViewModelProviders.of(this).get(MyModel.class); 老版本方法
        myModel = new ViewModelProvider(this).get(MyModel.class);
        myModel.getData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                stringBuffer.append(s).append("\n");
                mTv.setText(stringBuffer);
            }
        });
        /**
         *模拟网络请求后返回数据
         */
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stringBuffer.append("数据请求中....").append("\n");
                mTv.setText(stringBuffer);

                mBtn.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        myModel.getData().postValue("===我是请求后的消息");
                    }
                }, 5000);
            }
        });
    }
}
