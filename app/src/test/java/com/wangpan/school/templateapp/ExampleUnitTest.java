package com.wangpan.school.templateapp;

import com.wangpan.school.templateapp.bean.User;
import com.wangpan.school.templateapp.net.response.HttpResult;
import com.wangpan.school.templateapp.net.retrofit.RetrofitUtil;

import org.junit.Test;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;

import rx.Subscriber;
import rx.schedulers.Schedulers;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Observable<HttpResult> ob = RetrofitUtil.getNetApi().getGirls();
        ob.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<HttpResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HttpResult result) {

                    }
                });
    }

    @Test
    public void testGetGirl(){
        Call<HttpResult<List<User>>> call = RetrofitUtil.getNetApi().getGirls2();
        call.enqueue(new Callback<HttpResult<List<User>>>() {
            @Override
            public void onResponse(Call<HttpResult<List<User>>> call, Response<HttpResult<List<User>>> response) {
                System.out.print("dddddddddddddd");
                if(response.isSuccessful()){
                    List<User> users = response.body().getData();
                    System.out.print("dddddddddddddd");
                    System.out.print("dddddddddddddd");
                }
            }

            @Override
            public void onFailure(Call<HttpResult<List<User>>> call, Throwable t) {
                System.out.print("eeeeeeeeeeeeeeeeeeeeeee");
                System.out.print("eeeeeeeeeeeeeeeeeeeeeee");

            }
        });
    }
}