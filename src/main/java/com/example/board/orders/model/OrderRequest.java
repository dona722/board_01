// OrderRequest.java
package com.example.board.orders.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotBlank(message = "주문자 이름은 필수입니다")
    private String customerName;
    
    @NotBlank(message = "주문자 연락처는 필수입니다")
    private String customerPhone;
    
    @NotBlank(message = "배송 주소는 필수입니다")
    private String address;
    
    @NotBlank(message = "비밀번호는 필수입니다")
    private String password;

    @NotNull(message = "주문 상품은 필수입니다")
    private List<OrderItemRequest> items;
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemRequest {
        @NotNull(message = "도서 ID는 필수입니다")
        private Long bookId;
        
        @NotNull(message = "수량은 필수입니다")
        private Integer quantity;
    }
}