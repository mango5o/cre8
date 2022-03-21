package com.spring.cre8.member.dto;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component("memberDTO")
public class MemberDTO {
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private String email;
	private Date joinDate;
	private int state;

	public MemberDTO() {
		
	}

	public MemberDTO(String id, String pwd, String name, String email, String nickname, int state) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.nickname = nickname;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
