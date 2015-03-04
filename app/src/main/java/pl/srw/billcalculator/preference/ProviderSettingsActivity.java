package pl.srw.billcalculator.preference;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import pl.srw.billcalculator.BackableActivity;
import pl.srw.billcalculator.R;

/**
 * Created by Kamil Seweryn.
 */
public class ProviderSettingsActivity extends BackableActivity {

    private static final String FRAGMENT_TAG = "SettingsFragment";
    public static final String EXTRA_PRIVIDER_NAME = "PRIVIDER_NAME";

    public enum Provider {
        PGE, PGNIG;
    }

    public static Intent createIntent(final Context context, final Provider type) {
        Intent i = new Intent(context, ProviderSettingsActivity.class);
        i.putExtra(EXTRA_PRIVIDER_NAME, type.toString());
        return i;    
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ProviderSettingsFragment preferenceFragment;
        if (savedInstanceState == null) {
            preferenceFragment = getProviderSettingsFragment();
            getFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, preferenceFragment, FRAGMENT_TAG)
                    .commit();

        } else {
            preferenceFragment = (ProviderSettingsFragment) getCurrentFragment();
        }

        if (getActionBar() != null && preferenceFragment != null)
            getActionBar().setTitle(preferenceFragment.getTitleResource());
    }

    private ProviderSettingsFragment getProviderSettingsFragment() {
        ProviderSettingsFragment fragment;
        final Provider provider = Provider.valueOf(getProviderFromIntent());
        if (provider == Provider.PGNIG)
            fragment = new PgnigSettingsFragment();
        else
            fragment = new PgeSettingsFragment();
        return fragment;
    }

    private String getProviderFromIntent() {
        return getIntent().getStringExtra(EXTRA_PRIVIDER_NAME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_help) {
            showHelp();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showHelp() {
        final Intent intent = ProviderSettingsHelpActivity.createIntent(this, getHelpResource());
        startActivity(intent);
    }

    private int getHelpResource() {
        return ((ProviderSettingsFragment) getCurrentFragment()).getHelpLayoutResource();
    }

    private Fragment getCurrentFragment() {
        return getFragmentManager().findFragmentByTag(FRAGMENT_TAG);
    }
}
