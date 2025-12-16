package cn.icexmoon;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernatePersistenceConfiguration;
import org.hibernate.tool.schema.Action;

/**
 * @ClassName SessionFactoryUtil
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/16 上午8:44
 * @Version 1.0
 */
public class SessionFactoryUtil {
    public static SessionFactory getSessionFactory(){
        SessionFactory sessionFactory =
                new HibernatePersistenceConfiguration("Bookshop", Main.class)
                        // PostgreSQL
                        .jdbcUrl("jdbc:mysql://localhost:3306/jpa")
                        // Credentials
                        .jdbcCredentials("root", "mysql")
                        // Automatic schema export
                        .schemaToolingAction(Action.UPDATE)
                        // SQL statement logging
                        .showSql(true, true, true)
                        // Create a new SessionFactory
                        .createEntityManagerFactory();
        return sessionFactory;
    }
}
