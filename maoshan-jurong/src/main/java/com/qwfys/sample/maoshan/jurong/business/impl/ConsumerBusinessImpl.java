package com.qwfys.sample.maoshan.jurong.business.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qwfys.sample.maoshan.common.result.HuaResult;
import com.qwfys.sample.maoshan.common.vo.AccountDetailVO;
import com.qwfys.sample.maoshan.jurong.business.spec.ConsumerBusiness;
import com.qwfys.sample.maoshan.jurong.request.AccountDetailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * @author liuwenke
 * @since 0.0.1
 */

@Slf4j
@Service
public class ConsumerBusinessImpl implements ConsumerBusiness {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AccountDetailVO viewAccountDetail(String token, AccountDetailRequest param) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        ObjectMapper mapper = new ObjectMapper();
        String body = null;
        try {
            body = mapper.writeValueAsString(param);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
        HttpEntity<?> httpEntity = new HttpEntity<>(body, headers);

        String apiUrl = "http://127.0.0.1:19000/provider/account/detail";
        HttpMethod httpMethod = HttpMethod.POST;
        ResponseEntity<HuaResult<AccountDetailVO>> responseEntity = restTemplate.exchange(
                apiUrl,
                httpMethod,
                httpEntity,
                new ParameterizedTypeReference<HuaResult<AccountDetailVO>>() {
                }
        );

        Assert.notNull(responseEntity, "responseEntity为空");
        HuaResult<AccountDetailVO> huaResult = responseEntity.getBody();
        Assert.notNull(huaResult, "result不能为空");
        Assert.isTrue(huaResult.getIsSuccess(), "code:" + huaResult.getResultCode() + " message:" + huaResult.getResultMessage());
        AccountDetailVO accountDetail = huaResult.getData();
        return accountDetail;
    }
}
