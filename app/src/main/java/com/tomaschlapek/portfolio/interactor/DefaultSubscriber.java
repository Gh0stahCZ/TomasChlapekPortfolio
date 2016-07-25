package com.tomaschlapek.portfolio.interactor;

/**
 * Created by tomaschlapek on 22/7/16.
 * Default subscriber base class to be used whenever you want default error handling.
 */
public class DefaultSubscriber<T> extends rx.Subscriber<T> {
  @Override
  public void onCompleted() {
    // no-op by default.
  }

  @Override
  public void onError(Throwable e) {
    // no-op by default.
  }

  @Override
  public void onNext(T t) {
    // no-op by default.
  }
}