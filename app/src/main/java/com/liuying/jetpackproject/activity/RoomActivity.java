package com.liuying.jetpackproject.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.liuying.jetpackproject.R;
import com.liuying.jetpackproject.room.DBInstance;
import com.liuying.jetpackproject.room.UserInfo;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RoomActivity extends AppCompatActivity {
    private List<UserInfo> infoList;
    private TextView mTv;
    private StringBuffer stringBuffer = new StringBuffer();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        mTv = findViewById(R.id.tv_content);
        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBInstance.getInstance().getUserDao().getAll()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<UserInfo>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<UserInfo> userInfos) {
                                infoList = userInfos;
                                if (infoList != null && !infoList.isEmpty()) {
                                    stringBuffer.append("查询:" + JSON.toJSON(infoList).toString()).append("\n");
                                } else {
                                    stringBuffer.append("查询:无数据").append("\n");
                                }
                                mTv.setText(stringBuffer);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });
        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (infoList != null && infoList.size() > 0) {
                    infoList.get(0).setAge(infoList.get(0).getAge() + 1);
                    DBInstance.getInstance().getUserDao().update(infoList.get(0))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onComplete() {
                                    stringBuffer.append("修改：success").append("\n");
                                    mTv.setText(stringBuffer);
                                }

                                @Override
                                public void onError(Throwable e) {

                                }
                            });
                }
            }
        });
        findViewById(R.id.btn_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBInstance.getInstance().getUserDao().insertAll(new UserInfo("张三", "男", 27))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                stringBuffer.append("插入：success").append("\n");
                                mTv.setText(stringBuffer);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });
        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBInstance.getInstance().getUserDao().delete("张三")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(Integer integer) {
                                stringBuffer.append("删除：success").append("\n");
                                mTv.setText(stringBuffer);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });

    }
}
