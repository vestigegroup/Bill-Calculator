package pl.srw.billcalculator.bill.activity;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import pl.srw.billcalculator.di.ApplicationModule;
import pl.srw.billcalculator.di.DaggerTestApplicationComponent;
import pl.srw.billcalculator.di.TestApplicationComponent;
import pl.srw.billcalculator.history.DrawerActivity;
import pl.srw.billcalculator.tester.AppTester;
import pl.srw.billcalculator.tester.BillTester;
import pl.srw.billcalculator.tester.FormTester;
import pl.srw.billcalculator.tester.HistoryTester;
import pl.srw.billcalculator.tester.ProviderSettingsTester;
import pl.srw.billcalculator.testutils.HistoryGenerator;
import pl.srw.billcalculator.type.Provider;

/**
 * Created by kseweryn on 15.07.15.
 */
@RunWith(AndroidJUnit4.class)
public abstract class AbstractVerifyBillCreationUITest {

    @Rule
    public final ActivityTestRule<DrawerActivity> testRule = new ActivityTestRule<>(DrawerActivity.class, false, false);

    @Inject HistoryGenerator historyGenerator;

    private AppTester tester = new AppTester();

    @Before
    public void setUp() throws Exception {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        TestApplicationComponent component = DaggerTestApplicationComponent.builder()
                .applicationModule(new ApplicationModule(context))
                .build();
        component.inject(this);

        historyGenerator.clear();

        testRule.launchActivity(null);
    }

    @Test
    public void checkCalculatedBillValuesAndPrices() throws Exception {
        FormTester formTester = tester.skipCheckPricesDialogIfVisible()
                .openForm(getProvider());

        ProviderSettingsTester<FormTester> settingsTester = formTester.openProviderSettings();
        changePrices(settingsTester);
        settingsTester.close();

        BillTester billTester = inputFormValuesAndCalculate(formTester);
        verifyCalculatedValues(billTester);
        billTester.close();

        revertPricesToDefault();

        verifyAndOpenBillFromHistory(tester.onHistory());
        verifyCalculatedValues(billTester);
        billTester.close();

        removeBillFromHistory(tester.onHistory());
    }

    protected abstract Provider getProvider();

    protected abstract void changePrices(ProviderSettingsTester<FormTester> settingsTester);

    protected abstract BillTester inputFormValuesAndCalculate(FormTester formTester);

    protected abstract void verifyCalculatedValues(BillTester billTester);

    private void revertPricesToDefault() {
        tester.openSettings()
                .pickProvider(getProvider())
                .restoreDefault()
                .close()
                .close();
    }

    protected abstract void verifyAndOpenBillFromHistory(HistoryTester historyTester);

    protected abstract void removeBillFromHistory(HistoryTester historyTester);
}
