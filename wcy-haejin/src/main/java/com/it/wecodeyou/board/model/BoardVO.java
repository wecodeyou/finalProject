package com.it.wecodeyou.board.model;

import java.sql.Timestamp;

public class BoardVO {
	private long boardNo;
	private String boardTitle;
	private String boardDetail;
	private String boardType;
	private Timestamp boardCreatedAt;
	
	public BoardVO(long boardNo, String boardTitle, String boardDetail, String boardType, Timestamp boardCreatedAt) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardDetail = boardDetail;
		this.boardType = boardType;
		this.boardCreatedAt = boardCreatedAt;
	}

	public BoardVO(String boardTitle, String boardDetail, String boardType) {
		super();
		this.boardTitle = boardTitle;
		this.boardDetail = boardDetail;
		this.boardType = boardType;
	}

	public long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(long boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardDetail() {
		return boardDetail;
	}

	public void setBoardDetail(String boardDetail) {
		this.boardDetail = boardDetail;
	}

	public String getBoardType() {
		return boardType;
	}

	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}

	public Timestamp getBoardCreatedAt() {
		return boardCreatedAt;
	}

	public void setBoardCreatedAt(Timestamp boardCreatedAt) {
		this.boardCreatedAt = boardCreatedAt;
	}


}
