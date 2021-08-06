package com.example.demo.domain;

public class BoardDTO {
	private Long Idx;
	private String title;
	private String content;
	private String deleteYN;
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	public Long getIdx() {
		return Idx;
	}
	public void setIdx(Long Idx) {
		this.Idx = Idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}