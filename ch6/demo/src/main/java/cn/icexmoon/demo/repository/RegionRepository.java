package cn.icexmoon.demo.repository;

import cn.icexmoon.demo.dto.RegionDTO;
import cn.icexmoon.demo.entity.Region;
import com.blinkfox.fenix.jpa.FenixJpaRepository;
import com.blinkfox.fenix.jpa.QueryFenix;
import com.blinkfox.fenix.jpa.transformer.UnderscoreTransformer;

import java.util.List;

/**
 * @ClassName RegionRepository
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/11 上午10:10
 * @Version 1.0
 */
public interface RegionRepository extends FenixJpaRepository<Region, Long> {
    @QueryFenix(resultType = RegionDTO.class, nativeQuery = true, resultTransformer = UnderscoreTransformer.class)
    List<RegionDTO> getAll();
}
