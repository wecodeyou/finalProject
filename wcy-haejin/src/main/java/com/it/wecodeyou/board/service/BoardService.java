package com.it.wecodeyou.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.wecodeyou.board.model.BoardVO;
import com.it.wecodeyou.board.repository.IBoardMapper;

@Service
public class BoardService implements IBoardService {
	
	@Autowired
	private IBoardMapper dao;
	
	
	@Override
	public void insert(BoardVO rvo) {
		dao.insertBoard(rvo);

	}

	@Override
	public void delete(BoardVO rvo) {
		dao.deleteBoard(rvo);

	}

	@Override
	public void update(BoardVO rvo) {
		dao.updateBoard(rvo);

	}

	@Override
	public List<BoardVO> list() {
	
		return dao.getAllInfo();
	}

}
