package pl.srw.billcalculator.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.srw.billcalculator.settings.prices.PgePrices;
import pl.srw.billcalculator.settings.prices.PgnigPrices;
import pl.srw.billcalculator.settings.prices.RestorablePrices;
import pl.srw.billcalculator.settings.prices.TauronPrices;
import pl.srw.billcalculator.type.Provider;

@Module
public class ApplicationModule {

    public static final String GLOBAL_SHARED_PREFS = "global";
//    private static final String DEFAULT_SHARED_PREFS = "default";
    private static final String SHARED_PREFERENCES_FILE = "PreferencesFile";

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
//    @Named(DEFAULT_SHARED_PREFS) does not work (with map dependency?)
    protected SharedPreferences provideDefaultSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides(type = Provides.Type.MAP)
    @Singleton
    @DependencyMapProviderKey(Provider.PGE)
    protected RestorablePrices providePgeSharedPreferencesPrices(SharedPreferences prefs) {
        return new PgePrices(prefs);
    }

    @Provides(type = Provides.Type.MAP)
    @Singleton
    @DependencyMapProviderKey(Provider.PGNIG)
    protected RestorablePrices providePgnigSharedPreferencesPrices(SharedPreferences prefs) {
        return new PgnigPrices(prefs);
    }

    @Provides(type = Provides.Type.MAP)
    @Singleton
    @DependencyMapProviderKey(Provider.TAURON)
    protected RestorablePrices provideTauronSharedPreferencesPrices(SharedPreferences prefs) {
        return new TauronPrices(prefs);
    }

    @Provides
    @Singleton
    @Named(GLOBAL_SHARED_PREFS)
    protected SharedPreferences provideSharedPreferences() {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    protected Provider[] providerProviders() {
        return Provider.values();
    }
}
