package pl.srw.billcalculator;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import pl.srw.billcalculator.PgeBill;

import pl.srw.billcalculator.PgeBillDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig pgeBillDaoConfig;

    private final PgeBillDao pgeBillDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        pgeBillDaoConfig = daoConfigMap.get(PgeBillDao.class).clone();
        pgeBillDaoConfig.initIdentityScope(type);

        pgeBillDao = new PgeBillDao(pgeBillDaoConfig, this);

        registerDao(PgeBill.class, pgeBillDao);
    }
    
    public void clear() {
        pgeBillDaoConfig.getIdentityScope().clear();
    }

    public PgeBillDao getPgeBillDao() {
        return pgeBillDao;
    }

}
