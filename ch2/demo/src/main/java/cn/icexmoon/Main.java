package cn.icexmoon;

import cn.icexmoon.entity.Customer;
import jakarta.persistence.*;
import org.hibernate.cfg.JdbcSettings;
import org.hibernate.jpa.HibernatePersistenceConfiguration;
import org.hibernate.tool.schema.Action;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;



/**
 * @ClassName ${NAME}
 * @Description ${DESCRIPTION}
 * @Author icexmoon@qq.com
 * @Date 2025/10/3 下午7:15
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa-demo");
//        EntityManagerFactory entityManagerFactory = getEntityManagerFactory();
//        EntityManagerFactory entityManagerFactory = getEntityManagerFactory2();
        EntityManagerFactory entityManagerFactory = getEntityManagerFactory3();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
        customer.setLastName("icexmoon");
        customer.setAge(18);
        LocalDate localDate = LocalDate.of(2000, 1, 1);
        ZoneId zoneId = ZoneId.systemDefault();
        customer.setBirth(Date.from(localDate.atStartOfDay(zoneId).toInstant()));
        customer.setCreateTime(new Date());
        entityManager.persist(customer);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    /**
     * 用编程的方式创建 EntityManagerFactory
     * @return
     */
    private static EntityManagerFactory getEntityManagerFactory(){
        return new PersistenceConfiguration("jpa-demo")
                .managedClass(Customer.class)
                .property(PersistenceConfiguration.JDBC_DRIVER,"com.mysql.cj.jdbc.Driver")
                .property(PersistenceConfiguration.JDBC_URL, "jdbc:mysql://localhost:3306/jpa")
                .property(PersistenceConfiguration.JDBC_USER, "root")
                .property(PersistenceConfiguration.JDBC_PASSWORD, "mysql")
                .property(PersistenceConfiguration.SCHEMAGEN_DATABASE_ACTION, Action.ACTION_UPDATE)
                .property(JdbcSettings.SHOW_SQL, true)
                .property(JdbcSettings.FORMAT_SQL, true)
                .property(JdbcSettings.HIGHLIGHT_SQL, true)
                .createEntityManagerFactory();
    }

    private static EntityManagerFactory getEntityManagerFactory2(){
        return new HibernatePersistenceConfiguration("jpa-demo")
                .managedClass(Customer.class)
                .jdbcUrl("jdbc:mysql://localhost:3306/jpa")
                .jdbcCredentials("root", "mysql")
                .jdbcDriver("com.mysql.cj.jdbc.Driver")
                .schemaToolingAction(Action.UPDATE)
                .showSql( true, true, true)
                .createEntityManagerFactory();
    }

    private static EntityManagerFactory getEntityManagerFactory3(){
        return new HibernatePersistenceConfiguration("jpa-demo", Main.class)
                .jdbcUrl("jdbc:mysql://localhost:3306/jpa")
                .jdbcCredentials("root", "mysql")
                .jdbcDriver("com.mysql.cj.jdbc.Driver")
                .schemaToolingAction(Action.UPDATE)
                .showSql( true, true, true)
                .createEntityManagerFactory();
    }
}