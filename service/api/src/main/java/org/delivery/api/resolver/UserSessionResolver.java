package org.delivery.api.resolver;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.user.service.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 체크, 어노테이션 체크

        // 어노테이션이 있느지 체크
        var annotation = parameter.hasMethodAnnotation(UserSession.class);
        // 파라미터의 타입 체크
        var parameterType = parameter.getParameterType().equals(User.class);

        return (annotation && parameterType);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // support parameter 에서 true 반환시 여기 실행

        // request context holder 에서 찾아오기

        var requestContext = RequestContextHolder.getRequestAttributes();
        var userId = Long.parseLong(String.valueOf(requestContext.getAttribute("userId", RequestAttributes.SCOPE_REQUEST)));
        var userEntity = userService.getUserWithThrow(userId);
        // 사용자 정보 세팅
        return User.toEntity(userEntity);
    }
}
