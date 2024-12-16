package com.example.board.post.db;

public enum InquiryStatus {
    PENDING("답변대기"),
    IN_PROGRESS("답변중"),
    ANSWERED("답변완료"),
    CLOSED("종료");

    private final String description;

    InquiryStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}