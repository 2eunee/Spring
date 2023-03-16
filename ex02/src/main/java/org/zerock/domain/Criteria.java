package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class Criteria {
	
	private int pageNum;
	private int amount;
	
	private String type; //제목(T),내용(C),작성자(W)
	private String keyword; //검색한 글
	
	public Criteria() {
		this(1,10); 
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String[] getTypeArr() {
		return type == null? new String[] {}: type.split("");
	}
	
	
}
