package com.naekkonyang.domain.account;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 커스텀 애노테이션
@Retention(RetentionPolicy.RUNTIME) //어느시점까지 애노테이션의 메모리를 가져갈지 설정
@Target(ElementType.PARAMETER) // Type  설정 Retention 과 같이 사용
@AuthenticationPrincipal(expression = "account")
public @interface CurrentAccount {
}
