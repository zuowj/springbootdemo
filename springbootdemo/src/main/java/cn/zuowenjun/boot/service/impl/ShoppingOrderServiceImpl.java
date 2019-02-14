package cn.zuowenjun.boot.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zuowenjun.boot.domain.*;
import cn.zuowenjun.boot.mapper.*;
import cn.zuowenjun.boot.service.ShoppingOrderService;

@Service
public class ShoppingOrderServiceImpl implements ShoppingOrderService  {

	@Autowired
	private ShoppingOrderMapper  orderMapper;
	
	@Autowired
	private ShoppingOrderDetailMapper orderDetailMapper;
	
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	private ShoppingOrderExtMapper shoppingOrderExtMapper;
	
	@Override
	public void insertShoppingCart(ShoppingCart shoppingCart) {
		ShoppingCart cart=shoppingCartMapper.get(shoppingCart.getShopper(), shoppingCart.getGoodsId());
		if(cart==null) {
			shoppingCartMapper.insert(shoppingCart);
		}else {
			cart.setQty(cart.getQty()+shoppingCart.getQty());
			shoppingCartMapper.update(cart);
		}
	}
	
	@Override
	public void deleteShoppingCart(int shoppingCartId) {
		shoppingCartMapper.deleteItem(shoppingCartId);
	}
	
	
	@Override
	public ShoppingOrder getShoppingOrder(int id) {
		return orderMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<ShoppingOrder> getShoppingOrderList(String shopper) {
		return shoppingOrderExtMapper.selectAllByShopper(shopper);
	}

	@Override
	public List<ShoppingOrderDetail> getShoppingOrderDetail(int orderId) {
		return orderDetailMapper.selectByOrderId(orderId);
	}

	@Transactional
	@Override
	public boolean createShoppingOrderByShopper(String shopper) {
		
		List<ShoppingCart> carts= shoppingCartMapper.getList(shopper);
		if(carts==null || carts.size()<=0) {
			return false;
		}
		
		int totalQty=0;
		BigDecimal totalPrc=BigDecimal.valueOf(0);
		List<ShoppingOrderDetail> orderDetails=new ArrayList<>();
		
		for(ShoppingCart c:carts) {
			totalQty+=c.getQty();
			BigDecimal itemPrc=c.getInGoods().getPrice().multiply(BigDecimal.valueOf(c.getQty()));
			totalPrc=totalPrc.add(itemPrc);
			ShoppingOrderDetail od=new ShoppingOrderDetail();
			od.setGoodsid(c.getGoodsId());
			od.setQty(c.getQty());
			od.setTotalprice(itemPrc);
			od.setCreateby(shopper);
			od.setCreatetime(new Date());
			
			orderDetails.add(od);
		}
		
		ShoppingOrder order=new ShoppingOrder();
		order.setShopper(shopper);
		order.setTotalqty(totalQty);
		order.setTotalprice(totalPrc);
		order.setCreateby(shopper);
		order.setCreatetime(new Date());
		order.setIscompleted(false);
		
		insertShoppingOrderWithDetail(order,orderDetails);
		
		clearShoppingCart(shopper);
		
		return true;
	}
	
	@Transactional
	@Override
	public void insertShoppingOrderWithDetail(ShoppingOrder order, List<ShoppingOrderDetail> orderDetails) {
		
		orderMapper.insert(order);
		int orderId=order.getId();
		for(ShoppingOrderDetail od:orderDetails) {
			od.setShoppingorderid(orderId);
			orderDetailMapper.insert(od);
		}
	}
	

	@Override
	public void deleteShoppingOrderDetail(int orderDetailId) {
		
		orderDetailMapper.deleteByPrimaryKey(orderDetailId);
	}

	@Transactional
	@Override
	public void deleteShoppingOrderWithDetail(int orderId) {
		
		orderMapper.deleteByPrimaryKey(orderId);
		orderDetailMapper.deleteByOrderId(orderId);
	}

	@Override
	public void updateShoppingOrder(ShoppingOrder order) {
		orderMapper.updateByPrimaryKey(order);
	}

	@Override
	public List<ShoppingCart> getShoppingCartList(String shopper) {
		return shoppingCartMapper.getList(shopper);
	}

	@Override
	public int getShoppingCartBuyCount(String shopper) {
		return shoppingCartMapper.getBuyCount(shopper);
	}

	@Override
	public void clearShoppingCart(String shopper) {
		shoppingCartMapper.delete(shopper);
	}





}
