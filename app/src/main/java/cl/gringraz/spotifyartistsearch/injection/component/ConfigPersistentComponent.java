package cl.gringraz.spotifyartistsearch.injection.component;

import cl.gringraz.spotifyartistsearch.injection.ConfigPersistent;
import cl.gringraz.spotifyartistsearch.injection.module.ActivityModule;
import dagger.Component;

@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

}