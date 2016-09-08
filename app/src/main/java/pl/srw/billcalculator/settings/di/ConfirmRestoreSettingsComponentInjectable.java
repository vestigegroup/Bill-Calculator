package pl.srw.billcalculator.settings.di;

import pl.srw.billcalculator.settings.restore.ConfirmRestoreSettingsDialogFragment;
import pl.srw.mfvp.MvpActivity;
import pl.srw.mfvp.di.component.MvpActivityScopeComponent;

public interface ConfirmRestoreSettingsComponentInjectable<T extends MvpActivity> extends MvpActivityScopeComponent<T> {
    void inject(ConfirmRestoreSettingsDialogFragment fragment);
}
