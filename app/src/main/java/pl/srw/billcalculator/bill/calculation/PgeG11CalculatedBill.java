package pl.srw.billcalculator.bill.calculation;

import java.math.BigDecimal;

import lombok.Getter;
import pl.srw.billcalculator.pojo.IPgePrices;

/**
 * Created by Kamil Seweryn.
 */
@SuppressWarnings("FieldCanBeLocal")
@Getter
public class PgeG11CalculatedBill extends CalculatedEnergyBill {

    private final int consumption;

    private final BigDecimal zaEnergieCzynnaNetCharge;
    private final BigDecimal skladnikJakosciowyNetCharge;
    private final BigDecimal oplataSieciowaNetCharge;

    private final BigDecimal zaEnergieCzynnaVatCharge;
    private final BigDecimal skladnikJakosciowyVatCharge;
    private final BigDecimal oplataSieciowaVatCharge;

    public PgeG11CalculatedBill(final int readingFrom, final int readingTo, final String dateFrom, final String dateTo, final IPgePrices prices) {
        super(dateFrom, dateTo, prices.getOplataAbonamentowa(), prices.getOplataPrzejsciowa(), prices.getOplataStalaZaPrzesyl());
        consumption = readingTo - readingFrom;

        zaEnergieCzynnaNetCharge = multiplyAndAddToSum(prices.getZaEnergieCzynna(), consumption);
        skladnikJakosciowyNetCharge = multiplyAndAddToSum(prices.getSkladnikJakosciowy(), consumption);
        oplataSieciowaNetCharge = multiplyAndAddToSum(prices.getOplataSieciowa(), consumption);

        zaEnergieCzynnaVatCharge = multiplyVatAndAddToSum(zaEnergieCzynnaNetCharge);
        skladnikJakosciowyVatCharge = multiplyVatAndAddToSum(skladnikJakosciowyNetCharge);
        oplataSieciowaVatCharge = multiplyVatAndAddToSum(oplataSieciowaNetCharge);
    }

    @Override
    public int getTotalConsumption() {
        return consumption;
    }
}
