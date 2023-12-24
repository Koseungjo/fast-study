package org.delivery.api.domain.token.business;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.db.user.UserEntity;

@Business
@RequiredArgsConstructor
public class TokenBusiness {

//    private final

    public TokenResponse issueToken(UserEntity userEntity){
        return null;
    }
}
