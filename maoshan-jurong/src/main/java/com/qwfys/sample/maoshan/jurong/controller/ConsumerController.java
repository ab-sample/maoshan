package com.qwfys.sample.maoshan.jurong.controller;

import com.qwfys.sample.maoshan.common.vo.AccountDetailVO;
import com.qwfys.sample.maoshan.jurong.business.spec.ConsumerBusiness;
import com.qwfys.sample.maoshan.jurong.comon.enums.ResponseEnum;
import com.qwfys.sample.maoshan.jurong.comon.response.ServerResponseEntity;
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
    public ServerResponseEntity<AccountDetailVO> viewAccountDetail(@RequestHeader("Authorization") String token, @RequestBody AccountDetailRequest param) {
        ServerResponseEntity<AccountDetailVO> serverResponse = null;
        try {
            AccountDetailVO detailVO = consumerBusiness.viewAccountDetail(token, param);
            serverResponse = ServerResponseEntity.success(detailVO);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            serverResponse = ServerResponseEntity.fail(ResponseEnum.EXCEPTION);
        }

        log.info("response: {}", serverResponse);
        return serverResponse;
    }
}
