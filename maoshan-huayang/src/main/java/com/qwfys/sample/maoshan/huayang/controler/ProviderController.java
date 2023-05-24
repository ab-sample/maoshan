package com.qwfys.sample.maoshan.huayang.controler;

import com.qwfys.sample.maoshan.common.dto.AccountDetailDTO;
import com.qwfys.sample.maoshan.common.result.Result;
import com.qwfys.sample.maoshan.common.vo.AccountDetailVO;
import com.qwfys.sample.maoshan.huayang.business.spec.AccountBusiness;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuwenke
 * @since 0.0.1
 */
@Slf4j
@RestController
@Tag(name = "服务方管理")
public class ProviderController {

    @Autowired
    private AccountBusiness accountBusiness;

    @ResponseBody
    @PostMapping("/provider/account/detail")
    @Operation(summary = "获取服务方账号详情")
    public Result<AccountDetailVO> findAccountDetail(@RequestHeader("Authorization") String token, @RequestBody AccountDetailDTO dto) {
        Assert.notNull(token, "token不能为空");
        Assert.notNull(dto.getUserId(), "userID不能为空");

        AccountDetailVO detailVO = accountBusiness.findAccountDetail();
        Result<AccountDetailVO> result = Result.data(detailVO);
        log.info("response: {}", result);
        return result;
    }
}
