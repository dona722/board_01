package com.example.board.orders.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    
    // 고객 정보
    @NotBlank(message = "주문자 이름을 입력해주세요")
    private String customerName;
    
    @NotBlank(message = "연락처를 입력해주세요")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바른 연락처 형식이 아닙니다")
    private String customerPhone;
    
    @NotBlank(message = "배송 주소를 입력해주세요")
    private String address;
    
    // 주문 비밀번호
    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min = 4, max = 4, message = "비밀번호는 4자리여야 합니다")
    @Pattern(regexp = "^\\d{4}$", message = "비밀번호는 4자리 숫자여야 합니다")
    private String password;
    
    // 주문 항목
    private List<OrderItemRequest> items;
    
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemRequest {
        private Long bookId;
        private Integer quantity;
    }
}