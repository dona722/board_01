package com.example.board.board.db;

public enum BoardType {
    NOTICE("공지사항"),
    INQUIRY("문의게시판"),
    ORDER_INQUIRY("주문문의");

    private final String description;

    BoardType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}