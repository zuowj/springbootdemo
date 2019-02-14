package cn.zuowenjun.boot.domain;

import java.util.Date;

public class GoodsCategory {
	private int id;
	private String categoryName;
	private int goodsCount;
	private int serialNo;
	private String lastEditBy;
	private Date lastEditTime;
	
	public GoodsCategory() {
		
	}
	
	public GoodsCategory(int id,String categoryName,int goodsCount,int serialNo,String lastEditBy,Date lastEditTime) {
		this.id=id;
		this.categoryName=categoryName;
		this.goodsCount=goodsCount;
		this.serialNo=serialNo;
		this.lastEditBy=lastEditBy;
		this.lastEditTime=lastEditTime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	
	public String getLastEditBy() {
		return lastEditBy;
	}
	public void setLastEditBy(String lastEditBy) {
		this.lastEditBy = lastEditBy;
	}
	
	public Date getLastEditTime() {
		return lastEditTime;
	}
	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}
	
}
