package com.nanodegree.popularmovies.service.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luanfernandes on 18/04/17.
 */

@Module
public class ServiceModule {

    public static final String TOKEN_KEY= "TOKEN_KEY";
    public static final String TOKEN_TYPE= "TOKEN_TYPE";

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(final SharedPreferences sharedPreferences) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder();
                    requestBuilder.header("Authorization",sharedPreferences.getString(TOKEN_TYPE,"")+" "+sharedPreferences.getString(TOKEN_KEY,""));
                    original = requestBuilder.build();
                return chain.proceed(original);
            }
        });
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit retrofit(Gson gson, OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson)).
                        client(client)
                .baseUrl("")
                .build();
        return retrofit;
    }
}
