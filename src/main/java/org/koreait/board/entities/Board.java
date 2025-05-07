package org.koreait.board.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private long seq;
    private String subject;
    private String content;
    private LocalDateTime regDt;
    private LocalDateTime modDt;
    public void setPoster(String poster) {

    }

    public void setPosterId(int posterId) {
    }

    public Object getPosterId() {
        return null;
    }
    // 1
}