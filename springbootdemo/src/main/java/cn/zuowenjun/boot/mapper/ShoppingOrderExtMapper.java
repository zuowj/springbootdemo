package cn.zuowenjun.boot.mapper;

import java.util.List;

import cn.zuowenjun.boot.domain.ShoppingOrder;

public interface ShoppingOrderExtMapper {
	
	List<ShoppingOrder> selectAllByShopper(String shopper);
}
