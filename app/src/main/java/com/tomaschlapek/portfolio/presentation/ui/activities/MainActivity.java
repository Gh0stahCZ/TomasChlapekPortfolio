package com.tomaschlapek.portfolio.presentation.ui.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tomaschlapek.portfolio.AndroidApplication;
import com.tomaschlapek.portfolio.R;
import com.tomaschlapek.portfolio.components.AppComponent;
import com.tomaschlapek.portfolio.components.DaggerAppComponent;
import com.tomaschlapek.portfolio.data.SharedPreferencesManager;
import com.tomaschlapek.portfolio.presentation.presenters.MainPresenter.View;

import javax.inject.Inject;

import timber.log.Timber;

public class MainActivity extends BaseActivity implements View {

    SharedPreferencesManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefManager = ((AndroidApplication) getApplication()).getAppComponent()
          .provideSharedPreferenceManager();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
