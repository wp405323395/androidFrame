package com.wangpan.school.templateapp.net.retrofit;
import com.wangpan.school.templateapp.net.NetApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    static NetApi mApi;

    private RetrofitUtil(){

    }

    public synchronized static NetApi getNetApi(){

            if(mApi == null) {
                initNetApi();
            }
            return mApi;

    }

    private static void initNetApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.2.119:8083/school/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                //设置网络请求的Url地址
                .build();
// 创建网络请求接口的实例
        mApi = retrofit.create(NetApi.class);
    }

    /**
     * 获取okhttp拦截器
     *
     * @return
     */
    public static OkHttpClient getOkHttpClient() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                System.out.print("OkHttp====Message:" +message);
            }
        });
        loggingInterceptor.setLevel(level);
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
                .Builder();
        //OkHttp进行添加拦截器loggingInterceptor
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }
}
