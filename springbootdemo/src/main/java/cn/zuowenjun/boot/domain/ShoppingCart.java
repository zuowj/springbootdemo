package cn.zuowenjun.boot.domain;

import java.util.Date;

public class ShoppingCart {
	private int id;
	private String shopper;
	private int goodsId;
	private int qty;
	private Date addedTime;
	private Goods inGoods;
	
	public ShoppingCart() {
		
	}
	
	public ShoppingCart(int id,String shopper,int goodsId,int qty,Date addedTime) {
		this.id=id;
		this.shopper=shopper;
		this.goodsId=goodsId;
		this.qty=qty;
		this.addedTime=addedTime;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getShopper() {
		return shopper;
	}
	public void setShopper(String shopper) {
		this.shopper = shopper;
	}
	
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public Date getAddedTime() {
		return addedTime;
	}
	public void setAddedTime(Date addedTime) {
		this.addedTime = addedTime;
	}

	public Goods getInGoods() {
		return inGoods;
	}

	public void setInGoods(Goods inGoods) {
		this.inGoods = inGoods;
	}

	

}
