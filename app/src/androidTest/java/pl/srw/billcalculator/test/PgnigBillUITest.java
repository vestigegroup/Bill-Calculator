package pl.srw.billcalculator.test;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import java.util.regex.Pattern;

import pl.srw.billcalculator.R;
import pl.srw.billcalculator.form.MainActivity;
import pl.srw.billcalculator.settings.GeneralPreferences;
import pl.srw.billcalculator.testutils.HistoryGenerator;
import pl.srw.billcalculator.testutils.SoloHelper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Kamil Seweryn.
 */
public class PgnigBillUITest extends ActivityInstrumentationTestCase2<MainActivity> {

    public PgnigBillUITest() {
        super(MainActivity.class);
    }

    private Solo solo;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        GeneralPreferences.markFirstLaunch();
        HistoryGenerator.clear();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testCalculateBillAndStoreItAccordingToPrices() {
        setPricesInSettings();
        inputValues();
        // calculate
        solo.clickOnButton(2);

        verifyBillCalculation();
        solo.goBack();

        changePricesInSettings();
        verifyBillStoredInHistory();
    }

    private void setPricesInSettings() {
        solo.clickOnActionBarItem(R.id.action_settings);
        solo.clickOnText(SoloHelper.getString(solo, R.string.pgnig_prices));

        setPrice(R.string.wspolczynnik_konwersji, "11.094");
        setPrice(R.string.paliwo_gazowe, "0.11815");
        setPrice(R.string.settings_oplata_abonamentowa, "6.97");
        setPrice(R.string.dystrybucyjna_stala, "22.62");
        setPrice(R.string.dystrybucyjna_zmienna, "0.03823");

        solo.goBack();
        solo.goBack();
    }

    private void inputValues() {
        // change bill type
        SoloHelper.switchBill(solo);
        assertThat(SoloHelper.isPgnigForm(solo),is(true));

        // type readings
        solo.enterText(0, "6696");
        solo.enterText(1, "7101");

        // change from date 2014.10.10
        solo.clickOnButton(0);
        solo.setDatePicker(0, 2014, 9, 10);
        solo.clickOnView(solo.getView(android.R.id.button1));
        // change to date 2014.12.15
        solo.clickOnButton(1);
        solo.setDatePicker(0, 2014, 11, 15);
        solo.clickOnView(solo.getView(android.R.id.button1));
    }

    private void verifyBillCalculation() {
        // verify na dzień 2014.10.10
        assertTrue(solo.searchText("10/10/2014"));
        // verify na dzień 2014.12.15
        assertTrue(solo.searchText("15/12/2014"));
        // verify odczyty
        assertTrue(solo.searchText(Pattern.quote("6696 [m³]")));
        assertTrue(solo.searchText(Pattern.quote("7101 [m³]")));
        // verify zużycie 405 m3
        assertTrue(solo.searchText(Pattern.quote("405 [m³]")));
        assertTrue(solo.searchText(Pattern.quote("Zużycie razem: 405 [m³]")));
        // verify współ.konw
        assertTrue(solo.searchText("Wsp. konwersji: 11.094"));
        // verify ilość 4493 kWh
        assertTrue(solo.searchText(Pattern.quote("Zużycie razem: 4493 [kWh]")));
        // verify ilość 2 m-c
        assertTrue(solo.searchText("2.000"));

        // verify cenny netto
        assertTrue(solo.searchText("0.11815"));
        assertTrue(solo.searchText("6.97000"));
        assertTrue(solo.searchText("22.62000"));
        assertTrue(solo.searchText("0.03823"));
        // verify warotść netto 530.85 13,94 45,24 171,77
        assertTrue(solo.searchText("530.85"));
        assertTrue(solo.searchText("13.94"));
        assertTrue(solo.searchText("45.24"));
        assertTrue(solo.searchText("171.77"));

        // VAT 122,10 3,21 10,41 39,51
        // wartość brutto 652,95 17,15 55,65 211,28
        // verify Razem 761,80 175,23 937,03
        assertTrue(solo.searchText("761.80"));
        assertTrue(solo.searchText("175.23"));
        assertTrue(solo.searchText(Pattern.quote("Wartość faktury brutto: 937.03 zł")));
    }

    private void setPrice(final int labelId, final String text) {
        solo.clickOnText(SoloHelper.getString(solo, labelId));
        solo.clearEditText(0);
        solo.enterText(0, text);
        solo.clickOnView(solo.getView(android.R.id.button1));
    }

    private void changePricesInSettings() {
        solo.clickOnActionBarItem(R.id.action_settings);
        solo.clickOnText(SoloHelper.getString(solo, R.string.pgnig_prices));

        setPrice(R.string.wspolczynnik_konwersji, "11.172");
        setPrice(R.string.settings_oplata_abonamentowa, "8.67");

        solo.goBack();
        solo.goBack();
    }

    private void verifyBillStoredInHistory() {
        solo.clickOnActionBarItem(R.id.action_history);

        // verify bill visible on list
        assertTrue(solo.searchText("10/10/2014 - 15/12/2014"));
        assertThat(SoloHelper.getDrawableFromRow(solo, 0), is(R.drawable.pgnig));
        assertTrue(solo.searchText("6696 - 7101"));
        assertTrue(solo.searchText(Pattern.quote("937.03 zł")));

        // verify bill values again
        solo.clickOnText("10/10/2014 - 15/12/2014");
        assertTrue(solo.searchText(Pattern.quote("6696 [m³]")));
        assertTrue(solo.searchText(Pattern.quote("7101 [m³]")));
        assertTrue(solo.searchText(Pattern.quote("Zużycie razem: 4493 [kWh]")));
        assertTrue(solo.searchText(Pattern.quote("Wartość faktury brutto: 937.03 zł")));

        // verify prices from history are used
        assertTrue(solo.searchText("Wsp. konwersji: 11.094"));
        assertFalse(solo.searchText("Wsp. konwersji: 11.172"));
        assertTrue(solo.searchText("6.97000"));
        assertFalse(solo.searchText("8.67"));
    }

}
