import cn.icexmoon.SessionFactoryUtil;
import cn.icexmoon.entity.Car2;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @ClassName DbTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/12/16 上午8:33
 * @Version 1.0
 */
public class DbTests {
    private SessionFactory sessionFactory;
    @BeforeEach
    public void beforeEach(){
        sessionFactory = SessionFactoryUtil.getSessionFactory();
    }

    @Test
    public void test(){
        sessionFactory.inTransaction(session -> {
            session.setHibernateFlushMode(FlushMode.AUTO);
            Car2 car = new Car2();
            car.setBrand("BMW");
            car.setOwner("Icex");
            session.persist( car);
            List<Car2> cars = session.createNativeQuery("select * from car2", Car2.class)
                    .getResultList();
            System.out.println(cars);
        });
    }
}
