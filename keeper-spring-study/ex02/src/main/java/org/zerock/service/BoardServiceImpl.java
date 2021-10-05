package org.zerock.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVo;
import org.zerock.mapper.BoardMapper;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

    private BoardMapper mapper;

    @Override
    public void register(BoardVo board) {

        log.info("register......" + board);

        mapper.insertSelectKey(board);
    }

    @Override
    public BoardVo get(Long bno) {

        log.info("get......" + bno);

        return mapper.read(bno);
    }

    @Override
    public boolean modify(BoardVo board) {

        log.info("modify......" + board);

        return mapper.update(board) == 1;
    }

    @Override
    public boolean remove(Long bno) {

        log.info("remove......" + bno);

        return mapper.delete(bno) == 1;
    }

    @Override
    public List<BoardVo> getList() {
        log.info("getList......");

        return mapper.getList();
    }
}
