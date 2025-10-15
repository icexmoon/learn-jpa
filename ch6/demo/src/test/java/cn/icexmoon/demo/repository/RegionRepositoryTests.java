package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.entity.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName RegionRepositoryTests
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/11 上午10:11
 * @Version 1.0
 */
@SpringBootTest
public class RegionRepositoryTests {
    @Autowired
    private RegionRepository regionRepository;

    @Test
    public void testAddRegion() {
        regionRepository.deleteAll();
        addRegion("110000", "北京", 1);
        addRegion("110101", "北京市,东城区",  2);
        addRegion("110102", "北京市,西城区",  2);
        addRegion("110103", "北京市,崇文区",  2);
        addRegion("130000", "河北省",  1);
        addRegion("130100", "河北省,石家庄市",  2);
        addRegion("130102", "河北省,石家庄市,长安区",  3);
        addRegion("130104", "河北省,石家庄市,桥西区",  3);
        addRegion("130105", "河北省,石家庄市,新华区",  3);
    }

    private void addRegion(String code, String name, int level){
        Region region = new Region();
        region.setCode(code);
        region.setName(name);
        region.setLevel(level);
        regionRepository.save(region);
    }

    @Test
    public void testGetAll() {
        System.out.println(regionRepository.getAll());
    }
}
