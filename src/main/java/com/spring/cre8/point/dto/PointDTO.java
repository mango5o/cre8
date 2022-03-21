package com.spring.cre8.point.dto;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component("PointDTO")
public class PointDTO {
	private Integer point_no; // 포인트 일련번호
	private Integer point_id; // 발생한 사용자 포인트
	private Integer currentPoint; // 현재포인트
	private Integer incomePoint; // 적립된 포인트(+)
	private Integer outgoPoint; // 지출한 포인트(-)
	private Integer pointCase; // 포인트 사용 case
	private Integer boardCode; // 포인트 이동이 발생한 게시글
	private String donator; // 기부한, 기부받은사람
	private Date pointDate; // 포인트 적립 시간

	public PointDTO() {
	}

	public PointDTO(Integer point_no, Integer point_id, Integer currentPoint, Integer incomePoint, Integer outgoPoint,
			Integer pointCase, Integer boardCode, String donator, Date pointDate) {
		super();
		this.point_no = point_no;
		this.point_id = point_id;
		this.currentPoint = currentPoint;
		this.incomePoint = incomePoint;
		this.outgoPoint = outgoPoint;
		this.pointCase = pointCase;
		this.boardCode = boardCode;
		this.donator = donator;
		this.pointDate = pointDate;
	}

	public Integer getPoint_no() {
		return point_no;
	}

	public void setPoint_no(Integer point_no) {
		this.point_no = point_no;
	}

	public Integer getPoint_id() {
		return point_id;
	}

	public void setPoint_id(Integer point_id) {
		this.point_id = point_id;
	}

	public Integer getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(Integer currentPoint) {
		this.currentPoint = currentPoint;
	}

	public Integer getIncomePoint() {
		return incomePoint;
	}

	public void setIncomePoint(Integer incomePoint) {
		this.incomePoint = incomePoint;
	}

	public Integer getOutgoPoint() {
		return outgoPoint;
	}

	public void setOutgoPoint(Integer outgoPoint) {
		this.outgoPoint = outgoPoint;
	}

	public Integer getPointCase() {
		return pointCase;
	}

	public void setPointCase(Integer pointCase) {
		this.pointCase = pointCase;
	}

	public Integer getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(Integer boardCode) {
		this.boardCode = boardCode;
	}

	public String getDonator() {
		return donator;
	}

	public void setDonator(String donator) {
		this.donator = donator;
	}

	public Date getPointDate() {
		return pointDate;
	}

	public void setPointDate(Date pointDate) {
		this.pointDate = pointDate;
	}

}
