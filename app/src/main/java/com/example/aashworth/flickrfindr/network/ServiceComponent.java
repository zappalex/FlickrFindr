package com.example.aashworth.flickrfindr.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceComponent {
    public static final String FLICKR_BASE_URL = "https://api.flickr.com/services/rest/";
    private static final String FLICKR_API_KEY = "1508443e49213ff84d566777dc211f2a";
    private static final String FLICKR_RETURN_FORMAT = "json";
    private static final String FLICKR_SEARCH_METHOD = "flickr.photos.search";
    private static final String FLICKR_NO_JSON = "1";
    private static final String FLICKR_PAGES_NUMBER = "1";
    private static final String FLICKR_RESULTS_PER_PAGE = "26";

    public static FlickrPhotoService getFlickrPhotoService(Retrofit retrofit) {
        return retrofit.create(FlickrPhotoService.class);
    }

    public static Retrofit getRetrofit(String baseUrl, OkHttpClient.Builder client) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(CoroutineCallAdapterFactory.create())
                .build();
    }

    public static OkHttpClient.Builder getClientWithInterceptor(Interceptor customInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(customInterceptor)
                .addInterceptor(getLoggingInterceptor());
    }

    public static Interceptor getFlickrSearchPhotosInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl originalHttpUrl = originalRequest.url();
                HttpUrl newHttpUrl = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", FLICKR_API_KEY)
                        .addQueryParameter("format", FLICKR_RETURN_FORMAT)
                        .addQueryParameter("method", FLICKR_SEARCH_METHOD)
                        .addQueryParameter("nojsoncallback", FLICKR_NO_JSON)
                        .addQueryParameter("per_page", FLICKR_RESULTS_PER_PAGE)
                        .addQueryParameter("page", FLICKR_PAGES_NUMBER)
                        .build();

                Request.Builder requestBuilder = originalRequest.newBuilder().url(newHttpUrl);
                Request newRequest = requestBuilder.build();
                return chain.proceed(newRequest);
            }
        };
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

}
