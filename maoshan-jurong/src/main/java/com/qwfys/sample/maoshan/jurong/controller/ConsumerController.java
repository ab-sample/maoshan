package com.qwfys.sample.maoshan.jurong.controller;

import com.qwfys.sample.maoshan.common.vo.AccountDetailVO;
import com.qwfys.sample.maoshan.jurong.business.spec.ConsumerBusiness;
import com.qwfys.sample.maoshan.jurong.comon.result.MaoResultCode;
import com.qwfys.sample.maoshan.jurong.comon.result.MaoResult;
import com.qwfys.sample.maoshan.jurong.request.AccountDetailRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "消费方管理")
public class ConsumerController {

    @Autowired
    private ConsumerBusiness consumerBusiness;

    @PostMapping("/consumer/account/detail")
    @Operation(summary = "获取消费方账号详情")
    public MaoResult<AccountDetailVO> viewAccountDetail(@RequestHeader("Authorization") String token, @RequestBody AccountDetailRequest param) {
        MaoResult<AccountDetailVO> result = null;
        try {
            AccountDetailVO detailVO = consumerBusiness.viewAccountDetail(token, param);
            result = MaoResult.success(detailVO);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = MaoResult.fail(MaoResultCode.EXCEPTION);
        }

        log.info("response: {}", result);
        return result;
    }
}
