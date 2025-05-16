package com.example._test.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class ResponseDto<D> {
    private boolean result; // 요청의 성공 여부 (true || false)
    private String message; // 요청 처리에 대한 설명 메시지
    private D data; // 실제 응답 데이터 (EX. 유저 정보, 게시글 목록 등) - 제네릭으로 어떤 타입이든 가능

    // cf) set 정적 메서드
    // : 매개변수로 모든 필드 값을 가짐
    // - 성공 | 실패에 따라 생성자에 원하고자 하는 데이터 처리를 하기 위함
    // - ResponseDto.set(result, message, data);

    // 성공 응답 생성
    public static <D> ResponseDto<D> setSuccess(String message, D data) {
        return ResponseDto.set(true, message, data);
    }

    // 실패 응답 생성
    public static <D> ResponseDto<D> setFailed(String message) {
        return ResponseDto.set(false, message, null);
    }
}
