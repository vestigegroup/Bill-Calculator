package pl.srw.billcalculator.persistence;

import pl.srw.billcalculator.db.PgeG12Bill;
import pl.srw.billcalculator.db.dao.HistoryDao;
import pl.srw.billcalculator.db.dao.PgeBillDao;
import pl.srw.billcalculator.db.dao.PgeG12BillDao;
import pl.srw.billcalculator.db.dao.PgnigBillDao;
import pl.srw.billcalculator.persistence.type.BillType;

/**
 * Created by Kamil Seweryn.
 */
public final class Triggers {

    public static final String[] BILL_INSERT_TRIGGERS =
            {insertTrigger("bill_pge_insert_trigger", PgeBillDao.TABLENAME, BillType.PGE.toString()),
             insertTrigger("bill_pgeG12_insert_trigger", PgeG12BillDao.TABLENAME, BillType.PGE_G12.toString()),
             insertTrigger("bill_pgnig_insert_trigger", PgnigBillDao.TABLENAME, BillType.PGNIG.toString())};

    private static String insertTrigger(final String triggerName, final String tableName, final String billType) {
        return "CREATE TRIGGER " + triggerName + " AFTER INSERT ON " + tableName + " BEGIN " +
                getInnerInsert(billType) + " END;";
    }

    private static String getInnerInsert(final String billType) {
        return "INSERT INTO " + HistoryDao.TABLENAME +
                "(" +
                HistoryDao.Properties.DateFrom.columnName + "," + HistoryDao.Properties.BillType.columnName + "," + HistoryDao.Properties.BillId.columnName +
                ") VALUES(" +
                "new." + PgeBillDao.Properties.DateFrom.columnName + ", '" + billType + "', new." + PgeBillDao.Properties.Id.columnName +
                ");";
    }

}
