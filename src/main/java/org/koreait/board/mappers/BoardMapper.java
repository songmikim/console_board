package org.koreait.board.mappers;

import org.apache.ibatis.annotations.Select;
import org.koreait.board.entities.Board;
import org.koreait.global.paging.SearchForm;

import java.util.List;
import java.util.Optional;

public interface BoardMapper {
    int register(Board item);
    int update(Board item);
    int delete(long seq);
    List<Board> getList(SearchForm search);
    Optional<Board> get(long seq);

    @Select("SELECT COUNT(*) FROM BOARD_DATA WHERE seq=#{seq}")
    int exists(long seq);
}