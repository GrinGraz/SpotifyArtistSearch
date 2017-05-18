package cl.gringraz.spotifyartistsearch.injection.component;

import cl.gringraz.spotifyartistsearch.injection.PerActivity;
import cl.gringraz.spotifyartistsearch.injection.module.ActivityModule;
import cl.gringraz.spotifyartistsearch.ui.main.MainActivity;
import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
