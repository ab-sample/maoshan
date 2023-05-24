package com.qwfys.sample.maoshan.jurong.business.spec;

import com.qwfys.sample.maoshan.common.vo.AccountDetailVO;
import com.qwfys.sample.maoshan.jurong.request.AccountDetailRequest;

/**
 * @author liuwenke
 * @since 0.0.1
 */
public interface ConsumerBusiness {
    AccountDetailVO viewAccountDetail(String token, AccountDetailRequest param);
}
