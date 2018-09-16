package io.gimo.zeus.db.plugin.dialect;

/**
 * MySQL数据库方言
 *
 * @author GimoXie
 * @since 2016年5月18日 下午1:32:52
 **/
public class MySql5Dialect extends Dialect {

    protected static final String SQL_END_DELIMITER = ";";

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return MySql5PageHelper.getLimitString(sql, offset, limit);
    }

    @Override
    public String getCountString(String sql) {
        return MySql5PageHelper.getCountString(sql);
    }
}
