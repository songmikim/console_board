package org.koreait.global.paging;

import lombok.Data;

@Data
public class SearchForm {
    private int page;
    private int offset;
    private int limit;
    private String sopt; // 검색 옵션
    private String skey; // 검색 키워드
}
