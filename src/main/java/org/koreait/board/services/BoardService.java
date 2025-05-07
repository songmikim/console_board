package org.koreait.board.services;

import org.koreait.board.controllers.RequestBoard;
import org.koreait.board.mappers.BoardMapper;
import org.koreait.board.validators.BoardRegisterValidator;
import org.koreait.board.validators.BoardSaveValidator;
import org.koreait.global.configs.DBConn;
import org.koreait.global.services.Bean;
import org.koreait.global.services.Configuration;
import org.koreait.global.validators.Validator;

@Configuration
public class BoardService {
    @Bean
    public BoardMapper boardMapper() {
        return DBConn.getInstance().getSession().getMapper(BoardMapper.class);
    }

    @Bean
    public BoardRegisterValidator boardValidator() {return new BoardRegisterValidator(boardMapper());}

    @Bean
    public BoardRegisterService registerService() { return new BoardRegisterService(boardValidator(), boardMapper()); }

    @Bean
    public BoardSaveService saveService() {
        return new BoardSaveService(boardMapper(), boardSaveValidator());
    }

    @Bean
    public Validator<RequestBoard> boardSaveValidator() {
        return new BoardSaveValidator();

    }

    @Bean
    public BoardInfoService infoService() {
        return new BoardInfoService();
    }



    @Bean
    public BoardDeleteService deleteService() { return new BoardDeleteService(boardMapper());}


}

