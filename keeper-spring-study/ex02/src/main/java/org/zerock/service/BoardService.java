package org.zerock.service;

import org.zerock.domain.BoardVo;

import java.util.List;

public interface BoardService {

    public void register(BoardVo board);

    public BoardVo get(Long bno);

    public boolean modify(BoardVo board);

    public boolean remove(Long bno);

    public List<BoardVo> getList();
}
