package cn.icexmoon.demo.util.idgenerator.generatortype;

import com.blinkfox.fenix.id.IdWorker;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.factory.spi.StandardGenerator;

/**
 * @ClassName CustomerIdGeneratorType
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/15 上午8:47
 * @Version 1.0
 */
public class CustomerIdGeneratorType implements IdentifierGenerator, StandardGenerator {
    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        return IdWorker.get62RadixUuid();
    }
}
