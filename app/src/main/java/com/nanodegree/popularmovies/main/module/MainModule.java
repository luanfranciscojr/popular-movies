package com.nanodegree.popularmovies.main.module;

import com.nanodegree.popularmovies.common.CustomScope;
import com.nanodegree.popularmovies.main.view.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luan_ on 04/06/2017.
 */

@Module
public class MainModule {

    private final MainView mView;

    public MainModule(MainView mView) {
        this.mView = mView;
    }

    @Provides
    @CustomScope
    MainView providerMainView() {
        return mView;
    }
}
