package com.javaex.vo;

public class BoardVO {

	// 필드
	private int no;
	private String title;
	private String content;
	private int hit;
	private String regDate;
	private int userNo;
	private String name;
	private int gruopNo;
	private int orderNo;
	private int depth;

	// 생성자
	public BoardVO() {
		super();
	}

	// 메소드-gs

	public BoardVO(int no, String title, String content, int hit, String regDate, int userNo, String name, int gruopNo,
			int orderNo, int depth) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
		this.name = name;
		this.gruopNo = gruopNo;
		this.orderNo = orderNo;
		this.depth = depth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
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

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getGruopNo() {
		return gruopNo;
	}

	public void setGruopNo(int gruopNo) {
		this.gruopNo = gruopNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	// 메소드 일반
	@Override
	public String toString() {
		return "RboardVO [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", regDate="
				+ regDate + ", userNo=" + userNo + ", name=" + name + ", gruopNo=" + gruopNo + ", orderNo=" + orderNo
				+ ", depth=" + depth + ", getNo()=" + getNo() + ", getTitle()=" + getTitle() + ", getContent()="
				+ getContent() + ", getHit()=" + getHit() + ", getRegDate()=" + getRegDate() + ", getUserNo()="
				+ getUserNo() + ", getName()=" + getName() + ", getGruopNo()=" + getGruopNo() + ", getOrderNo()="
				+ getOrderNo() + ", getDepth()=" + getDepth() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
