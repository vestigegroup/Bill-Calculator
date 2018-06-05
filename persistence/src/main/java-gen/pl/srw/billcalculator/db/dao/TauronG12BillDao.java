package pl.srw.billcalculator.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.internal.SqlUtils;

import java.util.ArrayList;
import java.util.List;

import pl.srw.billcalculator.db.TauronG12Bill;
import pl.srw.billcalculator.db.TauronPrices;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TAURON_G12_BILL".
*/
@SuppressWarnings("ALL")
public class TauronG12BillDao extends AbstractDao<TauronG12Bill, Long> {

    public static final String TABLENAME = "TAURON_G12_BILL";

    /**
     * Properties of entity TauronG12Bill.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ReadingDayFrom = new Property(1, Integer.class, "readingDayFrom", false, "READING_DAY_FROM");
        public final static Property ReadingDayTo = new Property(2, Integer.class, "readingDayTo", false, "READING_DAY_TO");
        public final static Property ReadingNightFrom = new Property(3, Integer.class, "readingNightFrom", false, "READING_NIGHT_FROM");
        public final static Property ReadingNightTo = new Property(4, Integer.class, "readingNightTo", false, "READING_NIGHT_TO");
        public final static Property DateFrom = new Property(5, java.util.Date.class, "dateFrom", false, "DATE_FROM");
        public final static Property DateTo = new Property(6, java.util.Date.class, "dateTo", false, "DATE_TO");
        public final static Property AmountToPay = new Property(7, Double.class, "amountToPay", false, "AMOUNT_TO_PAY");
        public final static Property PricesId = new Property(8, Long.class, "pricesId", false, "PRICES_ID");
    }

    private DaoSession daoSession;


    public TauronG12BillDao(DaoConfig config) {
        super(config);
    }
    
    public TauronG12BillDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TAURON_G12_BILL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"READING_DAY_FROM\" INTEGER," + // 1: readingDayFrom
                "\"READING_DAY_TO\" INTEGER," + // 2: readingDayTo
                "\"READING_NIGHT_FROM\" INTEGER," + // 3: readingNightFrom
                "\"READING_NIGHT_TO\" INTEGER," + // 4: readingNightTo
                "\"DATE_FROM\" INTEGER," + // 5: dateFrom
                "\"DATE_TO\" INTEGER," + // 6: dateTo
                "\"AMOUNT_TO_PAY\" REAL," + // 7: amountToPay
                "\"PRICES_ID\" INTEGER);"); // 8: pricesId
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TAURON_G12_BILL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TauronG12Bill entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer readingDayFrom = entity.getReadingDayFrom();
        if (readingDayFrom != null) {
            stmt.bindLong(2, readingDayFrom);
        }
 
        Integer readingDayTo = entity.getReadingDayTo();
        if (readingDayTo != null) {
            stmt.bindLong(3, readingDayTo);
        }
 
        Integer readingNightFrom = entity.getReadingNightFrom();
        if (readingNightFrom != null) {
            stmt.bindLong(4, readingNightFrom);
        }
 
        Integer readingNightTo = entity.getReadingNightTo();
        if (readingNightTo != null) {
            stmt.bindLong(5, readingNightTo);
        }
 
        java.util.Date dateFrom = entity.getDateFrom();
        if (dateFrom != null) {
            stmt.bindLong(6, dateFrom.getTime());
        }
 
        java.util.Date dateTo = entity.getDateTo();
        if (dateTo != null) {
            stmt.bindLong(7, dateTo.getTime());
        }
 
        Double amountToPay = entity.getAmountToPay();
        if (amountToPay != null) {
            stmt.bindDouble(8, amountToPay);
        }
 
        Long pricesId = entity.getPricesId();
        if (pricesId != null) {
            stmt.bindLong(9, pricesId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TauronG12Bill entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer readingDayFrom = entity.getReadingDayFrom();
        if (readingDayFrom != null) {
            stmt.bindLong(2, readingDayFrom);
        }
 
        Integer readingDayTo = entity.getReadingDayTo();
        if (readingDayTo != null) {
            stmt.bindLong(3, readingDayTo);
        }
 
        Integer readingNightFrom = entity.getReadingNightFrom();
        if (readingNightFrom != null) {
            stmt.bindLong(4, readingNightFrom);
        }
 
        Integer readingNightTo = entity.getReadingNightTo();
        if (readingNightTo != null) {
            stmt.bindLong(5, readingNightTo);
        }
 
        java.util.Date dateFrom = entity.getDateFrom();
        if (dateFrom != null) {
            stmt.bindLong(6, dateFrom.getTime());
        }
 
        java.util.Date dateTo = entity.getDateTo();
        if (dateTo != null) {
            stmt.bindLong(7, dateTo.getTime());
        }
 
        Double amountToPay = entity.getAmountToPay();
        if (amountToPay != null) {
            stmt.bindDouble(8, amountToPay);
        }
 
        Long pricesId = entity.getPricesId();
        if (pricesId != null) {
            stmt.bindLong(9, pricesId);
        }
    }

    @Override
    protected final void attachEntity(TauronG12Bill entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TauronG12Bill readEntity(Cursor cursor, int offset) {
        TauronG12Bill entity = new TauronG12Bill( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // readingDayFrom
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // readingDayTo
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // readingNightFrom
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // readingNightTo
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)), // dateFrom
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)), // dateTo
            cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7), // amountToPay
            cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8) // pricesId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TauronG12Bill entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setReadingDayFrom(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setReadingDayTo(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setReadingNightFrom(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setReadingNightTo(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setDateFrom(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
        entity.setDateTo(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
        entity.setAmountToPay(cursor.isNull(offset + 7) ? null : cursor.getDouble(offset + 7));
        entity.setPricesId(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TauronG12Bill entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TauronG12Bill entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TauronG12Bill entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getTauronPricesDao().getAllColumns());
            builder.append(" FROM TAURON_G12_BILL T");
            builder.append(" LEFT JOIN TAURON_PRICES T0 ON T.\"PRICES_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected TauronG12Bill loadCurrentDeep(Cursor cursor, boolean lock) {
        TauronG12Bill entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        TauronPrices tauronPrices = loadCurrentOther(daoSession.getTauronPricesDao(), cursor, offset);
        entity.setTauronPrices(tauronPrices);

        return entity;    
    }

    public TauronG12Bill loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<TauronG12Bill> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<TauronG12Bill> list = new ArrayList<TauronG12Bill>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<TauronG12Bill> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<TauronG12Bill> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
