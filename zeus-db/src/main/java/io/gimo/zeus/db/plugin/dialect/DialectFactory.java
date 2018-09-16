package io.gimo.zeus.db.plugin.dialect;

import org.apache.ibatis.session.Configuration;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库方言工厂,产生方言对象
 *
 * @author GimoXie
 * @since 2016年5月18日 下午1:32:23
 **/
public class DialectFactory {
    private static Logger LOGGER = LoggerFactory.getLogger(DialectFactory.class);
    public static String dialectClass = null;

    public static Dialect buildDialect(Configuration configuration) {
        if (dialectClass == null) {
            synchronized (DialectFactory.class) {
                if (dialectClass == null) {
                    dialectClass = configuration.getVariables().getProperty("dialectClass");
                }
            }
        }
        Dialect dialect = null;
        try {
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (Exception e) {
            LOGGER.error("请检查 mybatis-config.xml 中  dialectClass 是否配置正确?", e);
        }
        return dialect;
    }
}
