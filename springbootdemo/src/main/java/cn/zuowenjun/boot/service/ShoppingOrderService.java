package cn.zuowenjun.boot.service;

import java.util.List;

import cn.zuowenjun.boot.domain.*;

public interface ShoppingOrderService {
	
	ShoppingOrder getShoppingOrder(int id);
	
	List<ShoppingOrder> getShoppingOrderList(String shopper);
	
	List<ShoppingOrderDetail> getShoppingOrderDetail(int orderId);
	
	boolean createShoppingOrderByShopper(String shopper);
	
	void insertShoppingOrderWithDetail(ShoppingOrder order,List<ShoppingOrderDetail> orderDetails);
	
	void deleteShoppingOrderDetail(int orderDetailId);
	
	void deleteShoppingOrderWithDetail(int orderId);
	
	void updateShoppingOrder(ShoppingOrder order);
	
	List<ShoppingCart> getShoppingCartList(String shopper);
	
	int getShoppingCartBuyCount(String shopper);
	
	void insertShoppingCart(ShoppingCart shoppingCart);
	
	void deleteShoppingCart(int shoppingCartId);
	
	void clearShoppingCart(String shopper);
	
	
}
