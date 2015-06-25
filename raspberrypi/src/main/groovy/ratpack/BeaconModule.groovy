package ratpack;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

public class BeaconModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(BeaconService.class).in(Scopes.SINGLETON);
    }

}