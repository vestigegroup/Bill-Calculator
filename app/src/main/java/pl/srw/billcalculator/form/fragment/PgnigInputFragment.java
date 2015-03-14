package pl.srw.billcalculator.form.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import butterknife.InjectView;
import hugo.weaving.DebugLog;
import pl.srw.billcalculator.R;
import pl.srw.billcalculator.adapter.PreviousReadingsAdapter;
import pl.srw.billcalculator.intent.BillActivityIntentFactory;
import pl.srw.billcalculator.intent.BillStoringServiceIntentFactory;
import pl.srw.billcalculator.intent.IntentCreator;
import pl.srw.billcalculator.persistence.type.CurrentReadingType;
import pl.srw.billcalculator.type.BillType;

/**
* Created by Kamil Seweryn.
*/
public class PgnigInputFragment extends InputFragment {

    @InjectView(R.id.et_reading_from) AutoCompleteTextView etPreviousReading;
    @InjectView(R.id.et_reading_to) EditText etCurrentReading;

    private PreviousReadingsAdapter pgnigReadingsAdapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.form_pgnig_input, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        enableAutoComplete();
        setReadingsHint();
    }

    @Override
    public void onStart() {
        super.onStart();
        updateAutoComplete();
    }

    private void enableAutoComplete() {
        etPreviousReading.setAdapter(getPgnigReadingsAdapter());
    }

    private PreviousReadingsAdapter getPgnigReadingsAdapter() {
        if (pgnigReadingsAdapter == null) {
            pgnigReadingsAdapter = new PreviousReadingsAdapter(getActivity(), CurrentReadingType.PGNIG_TO);
        }
        return pgnigReadingsAdapter;
    }

    @DebugLog
    private void updateAutoComplete() {
        pgnigReadingsAdapter.updateAll();
    }

    @DebugLog
    protected void markHistoryChanged() {
        ((PreviousReadingsAdapter) etPreviousReading.getAdapter()).notifyInputDataChanged();
    }

    private void setReadingsHint() {
        etCurrentReading.setHint(R.string.reading_hint_m3);
        etPreviousReading.setHint(R.string.reading_hint_m3);
    }

    @Override
    protected Intent getBillActivityIntent() {
        IntentCreator intentCreator = BillActivityIntentFactory.of(getActivity(), BillType.PGNIG);
        return provideExtra(intentCreator);
    }

    @Override
    protected Intent getBillStorerIntent() {
        IntentCreator intentCreator = BillStoringServiceIntentFactory.of(getActivity(), BillType.PGNIG);
        return provideExtra(intentCreator);
    }

    private Intent provideExtra(final IntentCreator intentCreator) {
        return intentCreator.from(etPreviousReading, etCurrentReading, bFromDate, bToDate);
    }

    @Override
    protected boolean validateReadings() {
        return validateMissingValue(etPreviousReading) && validateMissingValue(etCurrentReading)
                && validateValueOrder(etPreviousReading, etCurrentReading);
    }
}