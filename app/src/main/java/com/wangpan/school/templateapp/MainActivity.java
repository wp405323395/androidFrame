package com.wangpan.school.templateapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wangpan.school.templateapp.bean.User;
import com.wangpan.school.templateapp.net.response.HttpResult;
import com.wangpan.school.templateapp.net.retrofit.RetrofitUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loopRequest();
        singleRequest();
    }

    private void singleRequest() {
        Observable observable1 = RetrofitUtil.getNetApi().getGirls2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        //在被观察者中添加观察者
        observable1.subscribe(new Observer<HttpResult<List<User>>>() {
                    private Disposable mDisposable;
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("wangwang","onSubscribe");
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(HttpResult<List<User>> value) {
                        Log.i("wangwang","onNext");
                        List<User> users = value.getData();
                        Log.i("wangpanapan","kdkdkd");
                        mDisposable.dispose();//注销
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wangwang","onError");
                        mDisposable.dispose();//注销
                    }

                    @Override
                    public void onComplete() {
                        Log.i("wangwang","onComplete");
                    }
                });
    }

    Consumer consumer1 = new Consumer<HttpResult<List<User>>>() {
        @Override
        public void accept(HttpResult<List<User>> listHttpResult) throws Exception {
            Log.i("wangpanapan","----------------");
        }
    };
    Consumer comsumer2 = new Consumer<HttpResult<User>>() {
        @Override
        public void accept(HttpResult<User> userHttpResult) throws Exception {
            Log.i("wangpanapan","----------");
        }
    };

    /**
     * Schedulers.io() 代表io操作的线程, 通常用于网络,读写文件等io密集型的操作；
     Schedulers.computation() 代表CPU计算密集型的操作, 例如需要大量计算的操作；
     Schedulers.newThread() 代表一个常规的新线程；
     AndroidSchedulers.mainThread() 代表Android的主线程
     */
    public void loopRequest() {
        //定义被观察者
        Observable observable1 = RetrofitUtil.getNetApi().getGirls2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        //定义被观察者
        final Observable observable2 = RetrofitUtil.getNetApi().addGirl("ACup",19)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        observable1.doOnNext(consumer1).observeOn(Schedulers.io())
                .flatMap(new Function<HttpResult<List<User>>,ObservableSource<HttpResult<User>>>() {
                    @Override
                    public ObservableSource<HttpResult<User>> apply(HttpResult<List<User>> listHttpResult) throws Exception {
                        return observable2;
                    }
                }).observeOn(AndroidSchedulers.mainThread()).subscribe(comsumer2, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.i("wangpanapan","----------");
                    }
                });

    }

}
