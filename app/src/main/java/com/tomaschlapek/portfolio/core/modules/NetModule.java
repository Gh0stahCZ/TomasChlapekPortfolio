package com.tomaschlapek.portfolio.core.modules;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.tomaschlapek.portfolio.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tomaschlapek on 6/7/16.
 */
@Module
public class NetModule {

  @Provides
  @Singleton
    //  @Named("Api")
  OkHttpClient provideOkHttpClient(Context context) {
    return createApiClient(context);
  }

  @Provides
  @Singleton
  public Retrofit provideRetrofit(/*@Named("Api") */OkHttpClient client,
    GsonConverterFactory gsonConverterFactory) {
    return new Retrofit.Builder()
      .baseUrl(BuildConfig.HOST)
      .client(client)
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .addConverterFactory(gsonConverterFactory)
      .build();
  }

  @Provides
  @Singleton
  public GsonConverterFactory provideGson() {
    Gson gson = new GsonBuilder()
      .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
      /*.registerTypeAdapter(Portfolio.class, new PortfolioDeseralizerJson())*/
      .create();
    return GsonConverterFactory.create(gson);
  }

  @Provides
  @Singleton
  public Picasso providePicasso(Application app, OkHttpClient client) {
    return new Picasso.Builder(app)
      .downloader(new OkHttp3Downloader(client))
      //      .listener((picasso, uri, e) -> Timber.e(e, " Failed to load image: %s", uri))
      .build();
  }

  /**
   * Creates OkHttp client with enabled Stetho logging and 10MiB cache.
   *
   * @return Instance of OkHttp client.
   */
  private OkHttpClient createApiClient(Context context) {
    int cacheSize = 10 * 1024 * 1024; // 10 MiB
    Cache cache = new Cache(context.getCacheDir(), cacheSize);

    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    //    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    //
    //    // Track headers and requests
    //    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    //    builder.networkInterceptors().add(httpLoggingInterceptor);

//    builder.networkInterceptors().add(new TimeoutInterceptor());
    builder.cache(cache);
    return builder.build();
  }
}
