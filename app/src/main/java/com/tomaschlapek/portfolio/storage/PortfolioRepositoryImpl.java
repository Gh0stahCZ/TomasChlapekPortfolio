package com.tomaschlapek.portfolio.storage;

import android.content.Context;

import com.tomaschlapek.portfolio.domain.interactors.impl.ShowAllProjectsInteractorImpl.PortfolioCallback;
import com.tomaschlapek.portfolio.domain.repository.PortfolioRepository;
import com.tomaschlapek.portfolio.network.model.Portfolio;
import com.tomaschlapek.portfolio.network.model.Project;
import com.tomaschlapek.portfolio.network.services.PortfolioService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import timber.log.Timber;

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
  public Project getProject(final PortfolioCallback portfolioCallback) {
    PortfolioService service = mRetrofit.create(PortfolioService.class);
    Call<Portfolio> call = service.getPortfolios();

    //    try {
    //      Project project = call.execute().body();
    //      return project;
    //    } catch (IOException e) {
    //      Timber.e("Failure: " + e.getLocalizedMessage());
    //      e.printStackTrace();
    //      return null;
    //    }

    call.enqueue(new Callback<Portfolio>() {
      @Override
      public void onResponse(Call<Portfolio> call, Response<Portfolio> response) {
        if (response.isSuccessful()) {
          Timber.d("Successful response");
          portfolioCallback.response(response.body());
        } else {
          Timber.e("Unsuccessful response");
        }
      }

      @Override
      public void onFailure(Call<Portfolio> call, Throwable t) {
        Timber.e("Failure");
      }
    });
    return null;
  }
}
