package cn.zuowenjun.boot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zuowenjun.boot.domain.*;

public interface ShoppingCartMapper {
	
	List<ShoppingCart> getList(String shopper);
	
	void insert(ShoppingCart shoppingCart);
	
	void update(ShoppingCart shoppingCart);
	
	void deleteItem(int id);
	
	void delete(String shopper);
	
	int getBuyCount(String shopper);
	
	ShoppingCart get(@Param("shopper") String shopper,@Param("goodsId") int goodsId); 
}
