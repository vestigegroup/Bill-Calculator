package pl.srw.billcalculator.test.form.view;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;
import org.threeten.bp.format.TextStyle;

import java.util.Locale;

import pl.srw.billcalculator.form.MainActivity;
import pl.srw.billcalculator.testutils.SoloHelper;
import pl.srw.billcalculator.util.Dates;

/**
 * Created by Kamil Seweryn.
 */
public class DatePickingButtonUITest extends ActivityInstrumentationTestCase2<MainActivity> {
    
    private Solo solo;

    public DatePickingButtonUITest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testDatePickerInitializedWithButtonText() {
        final String dateToText = solo.getButton(1).getText().toString();
        final LocalDate localDate = Dates.parse(dateToText);

        solo.clickOnButton(1);
        
        assertTrue(solo.searchText(""+localDate.getDayOfMonth()));
        assertTrue(solo.searchText(""+localDate.getMonth().getDisplayName(TextStyle.SHORT, Locale.getDefault())));
        assertTrue(solo.searchText(""+localDate.getYear()));
    }
    
    public void testDatePickerSetTextOnButton() {
        SoloHelper.setDateOnButton(solo, 0, 2012, Month.MARCH, 16);
        
        assertEquals("16/03/2012",solo.getButton(0).getText().toString());
    }
}