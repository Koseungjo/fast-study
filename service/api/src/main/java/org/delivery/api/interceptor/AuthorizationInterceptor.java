package org.delivery.api.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Pageable;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor url : {}", request.getRequestURI());

        // WEB, chrom 의 경우 GET, POST Option = pass
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        // 정적 리소스 통과
        if (handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        // TODO header 검증
        var accessToken = request.getHeader("authorization-token");

        Optional.ofNullable(accessToken).orElseThrow(
                        () -> new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND)
        );

        var userId = tokenBusiness.validationToken(accessToken);


        if (userId != null){
            var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            requestContext.setAttribute("userId", userId, RequestAttributes.SCOPE_REQUEST);
            return true;
        }

        throw new ApiException(ErrorCode.BAD_REQUEST, "인증실패");
    }
}
