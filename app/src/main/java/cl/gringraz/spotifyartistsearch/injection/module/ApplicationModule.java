package cl.gringraz.spotifyartistsearch.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import cl.gringraz.spotifyartistsearch.data.remote.APIService;
import cl.gringraz.spotifyartistsearch.injection.ApplicationContext;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    APIService provideAPIService() {
        return APIService.Creator.newAPIService();
    }

}
