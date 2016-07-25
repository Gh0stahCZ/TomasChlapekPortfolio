package com.tomaschlapek.portfolio.storage;

import android.content.Context;

import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.network.model.Portfolio;
import com.tomaschlapek.portfolio.network.model.Project;
import com.tomaschlapek.portfolio.network.services.PortfolioService;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by tomaschlapek on 6/7/16.
 */
public class PortfolioRepositoryImpl implements PortfolioRepository {

  private Context mContext;

  //  @Inject
  Retrofit mRetrofit;

  public PortfolioRepositoryImpl(Context context, Retrofit retrofit) {
    this.mContext = context;
    this.mRetrofit = retrofit;
  }

  @Override
  public List<Project> getProjects() {

    return null;
  }

  @Override
  public Observable<Portfolio> getPortfolio() {

    return Observable
      .concat(getPortfolioFromCache(), getPortfolioFromNetwork())
      .first(data -> data != null);
    //.first(data -> data.isUpToDate());



    //    try {
    //      Project project = call.execute().body();
    //      return project;
    //    } catch (IOException e) {
    //      Timber.e("Failure: " + e.getLocalizedMessage());
    //      e.printStackTrace();
    //      return null;
    //    }

    //    portfolioObservable.enqueue(new Callback<Portfolio>() {
    //      @Override
    //      public void onResponse(Call<Portfolio> call, Response<Portfolio> response) {
    //        if (response.isSuccessful()) {
    //          Timber.d("Successful response");
    //          portfolioCallback.response(response.body());
    //        } else {
    //          Timber.e("Unsuccessful response");
    //        }
    //      }
    //
    //      @Override
    //      public void onFailure(Call<Portfolio> call, Throwable t) {
    //        Timber.e("Failure");
    //      }
    //    });
    //    return null;
  }

  public Observable<Portfolio> getPortfolioFromNetwork() {
    PortfolioService service = mRetrofit.create(PortfolioService.class);

    return service.getPortfolios();
  }

  public Observable<Portfolio> getPortfolioFromCache() {
    // TODO Implement loading data from cache (REALM DB)
    return Observable.just(null);
  }

}
