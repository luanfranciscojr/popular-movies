package com.nanodegree.popularmovies.service.module

import com.google.gson.*
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton


/**
 * Created by luanfernandes on 31/08/17.
 */
@Module
class ServiceModule {


    companion object {
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/"
    }

    private val baseUrl = " https://api.themoviedb.org/3/"
    private val apiKey = ""


    @Provides
    @Singleton
    internal fun provideGson(): Gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Date::class.java, object: JsonDeserializer<Date> {
                var df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
                override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
                    try {
                        return df.parse(json.asString)
                    } catch (e: ParseException) {
                        return null
                    }
                }

            }).create()

    @Provides
    @Singleton
    internal fun provideOkhttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.addInterceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder().addQueryParameter("api_key", apiKey).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
        return client.build()
    }

    @Provides
    @Singleton
    internal fun retrofit(gson: Gson, client: OkHttpClient): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson)).client(client)
            .baseUrl(baseUrl)
            .build()
}
