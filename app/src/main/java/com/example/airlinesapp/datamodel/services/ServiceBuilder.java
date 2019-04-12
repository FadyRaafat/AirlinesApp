package com.example.airlinesapp.datamodel.services;

import com.example.airlinesapp.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit service builder that use as starter from any class
 */
public class ServiceBuilder {
    private static final String URL = "https://www.kayak.com/h/mobileapis/directory/";
    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request request = chain.request()
                            .newBuilder()
                            .build();
                    return chain.proceed(request); }})
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS);
    private static Retrofit.Builder builder;

    private static Retrofit retrofit;

    /**
     * create service to use
     *
     * @param serviceType interface where all define methods of service
     * @param <T>         any type of service
     * @return instant of service to use
     */
    public static <T> T buildService(Class<T> serviceType) {
        if (BuildConfig.DEBUG) {
            // Create logger
            HttpLoggingInterceptor logger =
                    new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttp.addInterceptor(logger);
        }
        builder = new Retrofit.Builder()
                .baseUrl(URL)
                .client(okHttp.build())
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        return retrofit.create(serviceType);
    }



}
