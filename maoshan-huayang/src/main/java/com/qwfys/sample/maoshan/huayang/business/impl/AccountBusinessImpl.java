package com.qwfys.sample.maoshan.huayang.business.impl;

import com.qwfys.sample.maoshan.common.vo.AccountDetailVO;
import com.qwfys.sample.maoshan.common.vo.RoleVO;
import com.qwfys.sample.maoshan.huayang.business.spec.AccountBusiness;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuwenke
 * @since 0.0.1
 */

@Service
public class AccountBusinessImpl implements AccountBusiness {
    @Override
    public AccountDetailVO findAccountDetail() {

        List<RoleVO> roles = new ArrayList<>();
        roles.add(
                new RoleVO()
                        .setId(11L)
                        .setCode("ADMIN")
                        .setName("管理员")
        );

        roles.add(
                new RoleVO()
                        .setId(12L)
                        .setCode("BUYER")
                        .setName("买家")
        );

        roles.add(
                new RoleVO()
                        .setId(13L)
                        .setCode("SELLER")
                        .setName("商家")
        );

        AccountDetailVO detailVO = new AccountDetailVO()
                .setUserId(1639178930477944839L)
                .setUserName("welcome")
                .setFullName("welcome")
                .setTelephone("13800000000")
                .setRoles(roles);
        return detailVO;
    }
}
