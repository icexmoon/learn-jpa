package cn.icexmoon.demo.fenix.handler;

import cn.hutool.core.util.StrUtil;
import cn.icexmoon.demo.entity.User;
import cn.icexmoon.demo.util.UserHolder;
import com.blinkfox.fenix.bean.BuildSource;
import com.blinkfox.fenix.config.annotation.Tagger;
import com.blinkfox.fenix.consts.Const;
import com.blinkfox.fenix.consts.SymbolConst;
import com.blinkfox.fenix.core.FenixHandler;
import com.blinkfox.fenix.helper.ParseHelper;
import com.blinkfox.fenix.helper.XmlNodeHelper;
import org.dom4j.Node;

import java.util.Map;

/**
 * @ClassName RegionAuthHandler
 * @Description
 * @Author icexmoon@qq.com
 * @Date 2025/10/11 下午3:12
 * @Version 1.0
 */
@Tagger(value = "regionAuth")
@Tagger(value = "andRegionAuth", prefix = "and ")
public class RegionAuthHandler implements FenixHandler {
    private static final String REGION_CODE_PARAM_NAME = "region_code";

    @Override
    public void buildSqlInfo(BuildSource source) {
        Node node = source.getNode();
        String fieldText = XmlNodeHelper.getAndCheckNodeText(node, "attribute::field");
        String userIdText = XmlNodeHelper.getAndCheckNodeText(node, "attribute::userId");
        Long userId = (Long) ParseHelper.parseExpressWithException(userIdText, source.getContext());
        // 获取当前登录用户的权限
        User currentUser = UserHolder.getCurrentUser();
        if (currentUser == null || !userId.equals(currentUser.getId())) {
            throw new RuntimeException("用户权限不足！");
        }
        String regionCode = currentUser.getRegionCode();
        if (StrUtil.isEmpty(regionCode)) {
            throw new RuntimeException("用户权限不足");
        }
        Integer level = currentUser.getLevel();
        StringBuilder join = source.getSqlInfo().getJoin();
        Map<String, Object> params = source.getSqlInfo().getParams();
        if (level == null || level <= 0 || level >= 4) {
            throw new RuntimeException("用户权限不足");
        }
        // 生成类似 and region_code like :region_code 这样的 JPQL
        if (level == 1) {
            join.append(source.getPrefix()).append(fieldText)
                    .append(SymbolConst.LIKE).append(Const.COLON).append(REGION_CODE_PARAM_NAME);
            params.put(REGION_CODE_PARAM_NAME, regionCode.substring(0, 2) + "%");
        }
        else if (level == 2){
            join.append(source.getPrefix()).append(fieldText)
                    .append(SymbolConst.LIKE).append(Const.COLON).append(REGION_CODE_PARAM_NAME);
            params.put(REGION_CODE_PARAM_NAME, regionCode.substring(0, 4) + "%");
        }
        else{
            join.append(source.getPrefix()).append(fieldText)
                    .append(SymbolConst.EQUAL).append(Const.COLON).append(REGION_CODE_PARAM_NAME);
            params.put(REGION_CODE_PARAM_NAME, regionCode);
        }
    }
}
