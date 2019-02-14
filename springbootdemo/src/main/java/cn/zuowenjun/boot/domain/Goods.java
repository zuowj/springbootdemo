package cn.zuowenjun.boot.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Goods {
	private int id;
	private String title;
	private String picture;
	private BigDecimal price;
	private String introduction;
	private int categoryId;
	private String lastEditBy;
	private Date lastEditTime;
	
	public Goods() {
		
	}
	
	public Goods(int id,String title,String picture,
			BigDecimal price,String introduction,int categoryId,String lastEditBy,Date lastEditTime) {
		this.setId(id);
		this.setTitle(title);
		this.setPicture(picture);
		this.setPrice(price);
		this.setIntroduction(introduction);
		this.setCategoryId(categoryId);
		this.setLastEditBy(lastEditBy);
		this.setLastEditTime(lastEditTime);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
