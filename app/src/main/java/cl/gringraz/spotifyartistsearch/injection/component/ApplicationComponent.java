package cl.gringraz.spotifyartistsearch.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import cl.gringraz.spotifyartistsearch.data.DataManager;
import cl.gringraz.spotifyartistsearch.data.local.DatabaseHelper;
import cl.gringraz.spotifyartistsearch.data.local.PreferencesHelper;
import cl.gringraz.spotifyartistsearch.data.remote.APIService;
import cl.gringraz.spotifyartistsearch.injection.ApplicationContext;
import cl.gringraz.spotifyartistsearch.injection.module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();
    Application application();
    APIService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();

}
