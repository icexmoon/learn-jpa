package cn.icexmoon.demo.util.idgenerator;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

/**
 * @ClassName NanoIdGenerator
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/13 上午11:13
 * @Version 1.0
 */
@IdGeneratorType(com.blinkfox.fenix.id.NanoIdGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, FIELD})
public @interface NanoIdGenerator {
}
