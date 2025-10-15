package cn.icexmoon.demo.util.idgenerator;

import cn.icexmoon.demo.util.idgenerator.generatortype.CustomerIdGeneratorType;
import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * @ClassName CustomerIdGenerator
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/15 上午8:46
 * @Version 1.0
 */
@IdGeneratorType(CustomerIdGeneratorType.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD})
public @interface CustomerIdGenerator {
}
