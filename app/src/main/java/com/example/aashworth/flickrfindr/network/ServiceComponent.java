package com.example.aashworth.flickrfindr.network;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceComponent {

    public static final String FLICKR_BASE_URL = "https://api.flickr.com/services/rest/";
    private static final String FLICKR_API_KEY = "1508443e49213ff84d566777dc211f2a";
    private static final String FLICKR_RETURN_FORMAT = "json";

    public static FlickrPhotoService getFlickrPhotoService(Retrofit retrofit) {
        return retrofit.create(FlickrPhotoService.class);
    }

    public static Retrofit getRetrofit(String baseUrl, OkHttpClient.Builder client) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static OkHttpClient.Builder getClientWithInterceptor(Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor);
    }

    public static Interceptor getFlickrInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalHttpUrl = originalRequest.url();

                HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", FLICKR_API_KEY)
                        .addQueryParameter("format", FLICKR_RETURN_FORMAT)
                        .build();

                Request.Builder requestBuilder = originalRequest.newBuilder().url(newHttpUrl);
                Request newRequest = requestBuilder.build();
                return chain.proceed(newRequest);
            }
        };
    }


}
