package com.qwfys.sample.maoshan.jurong.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qwfys.sample.maoshan.common.result.Result;
import com.qwfys.sample.maoshan.common.vo.AccountDetailVO;
import com.qwfys.sample.maoshan.jurong.comon.enums.ResponseEnum;
import com.qwfys.sample.maoshan.jurong.comon.response.ServerResponseEntity;
import com.qwfys.sample.maoshan.jurong.request.AccountDetailRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@Tag(name = "账号管理")
public class AccountController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/account/detail")
    @Operation(summary = "获取采购方列表")
    public ServerResponseEntity<?> viewAccountDetail(@RequestHeader("Authorization") String token, @RequestBody AccountDetailRequest param) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("token", token);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        try {
            ObjectMapper mapper = new ObjectMapper();
            String body = mapper.writeValueAsString(param);
            HttpEntity<?> httpEntity = new HttpEntity<>(body, headers);

            String apiUrl = "http://127.0.0.1:19000/api/external/v1/account/detail";
            HttpMethod httpMethod = HttpMethod.POST;
            ResponseEntity<Result<AccountDetailVO>> responseEntity = restTemplate.exchange(
                    apiUrl,
                    httpMethod,
                    httpEntity,
                    new ParameterizedTypeReference<Result<AccountDetailVO>>() {
                    }
            );

            Assert.notNull(responseEntity, "responseEntity为空");
            Result<AccountDetailVO> result = responseEntity.getBody();
            Assert.notNull(result, "result不能为空");
            Assert.isTrue(result.getIsSuccess(), "sps code:" + result.getResultCode() + " sps message:" + result.getResultMessage());
            AccountDetailVO accountDetail = result.getData();
            return ServerResponseEntity.success(accountDetail);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ServerResponseEntity.fail(ResponseEnum.UNAUTHORIZED);
        }
    }
}
