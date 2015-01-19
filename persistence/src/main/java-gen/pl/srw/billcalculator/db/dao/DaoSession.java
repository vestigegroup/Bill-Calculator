package pl.srw.billcalculator.db.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import pl.srw.billcalculator.db.PgePrices;
import pl.srw.billcalculator.db.PgeBill;
import pl.srw.billcalculator.db.PgeG12Prices;
import pl.srw.billcalculator.db.PgeG12Bill;
import pl.srw.billcalculator.db.PgnigPrices;
import pl.srw.billcalculator.db.PgnigBill;

import pl.srw.billcalculator.db.dao.PgePricesDao;
import pl.srw.billcalculator.db.dao.PgeBillDao;
import pl.srw.billcalculator.db.dao.PgeG12PricesDao;
import pl.srw.billcalculator.db.dao.PgeG12BillDao;
import pl.srw.billcalculator.db.dao.PgnigPricesDao;
import pl.srw.billcalculator.db.dao.PgnigBillDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig pgePricesDaoConfig;
    private final DaoConfig pgeBillDaoConfig;
    private final DaoConfig pgeG12PricesDaoConfig;
    private final DaoConfig pgeG12BillDaoConfig;
    private final DaoConfig pgnigPricesDaoConfig;
    private final DaoConfig pgnigBillDaoConfig;

    private final PgePricesDao pgePricesDao;
    private final PgeBillDao pgeBillDao;
    private final PgeG12PricesDao pgeG12PricesDao;
    private final PgeG12BillDao pgeG12BillDao;
    private final PgnigPricesDao pgnigPricesDao;
    private final PgnigBillDao pgnigBillDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        pgePricesDaoConfig = daoConfigMap.get(PgePricesDao.class).clone();
        pgePricesDaoConfig.initIdentityScope(type);

        pgeBillDaoConfig = daoConfigMap.get(PgeBillDao.class).clone();
        pgeBillDaoConfig.initIdentityScope(type);

        pgeG12PricesDaoConfig = daoConfigMap.get(PgeG12PricesDao.class).clone();
        pgeG12PricesDaoConfig.initIdentityScope(type);

        pgeG12BillDaoConfig = daoConfigMap.get(PgeG12BillDao.class).clone();
        pgeG12BillDaoConfig.initIdentityScope(type);

        pgnigPricesDaoConfig = daoConfigMap.get(PgnigPricesDao.class).clone();
        pgnigPricesDaoConfig.initIdentityScope(type);

        pgnigBillDaoConfig = daoConfigMap.get(PgnigBillDao.class).clone();
        pgnigBillDaoConfig.initIdentityScope(type);

        pgePricesDao = new PgePricesDao(pgePricesDaoConfig, this);
        pgeBillDao = new PgeBillDao(pgeBillDaoConfig, this);
        pgeG12PricesDao = new PgeG12PricesDao(pgeG12PricesDaoConfig, this);
        pgeG12BillDao = new PgeG12BillDao(pgeG12BillDaoConfig, this);
        pgnigPricesDao = new PgnigPricesDao(pgnigPricesDaoConfig, this);
        pgnigBillDao = new PgnigBillDao(pgnigBillDaoConfig, this);

        registerDao(PgePrices.class, pgePricesDao);
        registerDao(PgeBill.class, pgeBillDao);
        registerDao(PgeG12Prices.class, pgeG12PricesDao);
        registerDao(PgeG12Bill.class, pgeG12BillDao);
        registerDao(PgnigPrices.class, pgnigPricesDao);
        registerDao(PgnigBill.class, pgnigBillDao);
    }
    
    public void clear() {
        pgePricesDaoConfig.getIdentityScope().clear();
        pgeBillDaoConfig.getIdentityScope().clear();
        pgeG12PricesDaoConfig.getIdentityScope().clear();
        pgeG12BillDaoConfig.getIdentityScope().clear();
        pgnigPricesDaoConfig.getIdentityScope().clear();
        pgnigBillDaoConfig.getIdentityScope().clear();
    }

    public PgePricesDao getPgePricesDao() {
        return pgePricesDao;
    }

    public PgeBillDao getPgeBillDao() {
        return pgeBillDao;
    }

    public PgeG12PricesDao getPgeG12PricesDao() {
        return pgeG12PricesDao;
    }

    public PgeG12BillDao getPgeG12BillDao() {
        return pgeG12BillDao;
    }

    public PgnigPricesDao getPgnigPricesDao() {
        return pgnigPricesDao;
    }

    public PgnigBillDao getPgnigBillDao() {
        return pgnigBillDao;
    }

}
