package org.koreait.board.mappers;

import org.koreait.board.entities.Board;
import org.koreait.global.paging.SearchForm;

import java.util.List;

public interface BoardMapper {
    void register(Board board);

    <T> ScopedValue<T> get(int posterId);

    List<Board> getList(SearchForm search);
}
