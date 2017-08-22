package com.nanodegree.popularmovies.main.component;

import com.nanodegree.popularmovies.common.CustomScope;
import com.nanodegree.popularmovies.main.module.MainModule;
import com.nanodegree.popularmovies.main.view.Activity.MainActivity;
import com.nanodegree.popularmovies.service.component.ServiceComponent;

import dagger.Component;

/**
 * Created by luan_ on 04/06/2017.
 */

@CustomScope
@Component(dependencies = ServiceComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivitym);
}
