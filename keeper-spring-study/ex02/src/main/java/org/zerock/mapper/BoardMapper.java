package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVo;

import java.util.List;

public interface BoardMapper {

    // @Select("select * from tbl_board where bno > 0") xml로 대체
    public List<BoardVo> getList();

    public void insert(BoardVo board);

    public void insertSelectKey(BoardVo boardVo);

    public BoardVo read(Long bno);

    public int delete(Long bno);

    public int update(BoardVo boardVo);
}
