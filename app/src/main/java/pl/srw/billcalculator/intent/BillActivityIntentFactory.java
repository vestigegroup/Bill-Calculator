package pl.srw.billcalculator.intent;

import android.content.Context;

import pl.srw.billcalculator.bill.PgeBillActivity;
import pl.srw.billcalculator.bill.PgnigBillActivity;
import pl.srw.billcalculator.type.BillType;

/**
 * Created by Kamil Seweryn.
 */
public final class BillActivityIntentFactory {

    private BillActivityIntentFactory() {}

    public static IntentCreator of(final Context context, final BillType billType) {
        switch (billType) {
            case PGE:
                return new IntentCreator(context, PgeBillActivity.class);
            case PGNIG:
                return new IntentCreator(context, PgnigBillActivity.class);
        }
        throw new RuntimeException("Type " + billType + " is not handled.");
    }

}
