package cn.zuowenjun.springbootdemo;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.zuowenjun.boot.SpringbootdemoApplication;
import cn.zuowenjun.boot.domain.*;
import cn.zuowenjun.boot.mapper.GoodsMapper;
import cn.zuowenjun.boot.mapper.ShoppingOrderDetailMapper;
import cn.zuowenjun.boot.mapper.ShoppingOrderMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootdemoApplication.class)
public class ShoppingOrderMapperTests {

	@Autowired
	private ShoppingOrderMapper shoppingOrderMapper;
	
	@Autowired
	private ShoppingOrderDetailMapper shoppingOrderDetailMapper;
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Transactional
	@Rollback(false) //不加这个，默认测试完后自动回滚
	@Test
	public void testInsertShoppingOrder() {

		Goods goods= goodsMapper.get(1);
		
		ShoppingOrder shoppingOrder=new ShoppingOrder();
		shoppingOrder.setShopper("zuowenjun");
		shoppingOrder.setIscompleted(false);
		shoppingOrder.setTotalprice(BigDecimal.valueOf(0));
		shoppingOrder.setTotalqty(1);
		shoppingOrder.setCreateby("zuowenjun");
		shoppingOrder.setCreatetime(new Date());
		
		int orderId= shoppingOrderMapper.insert(shoppingOrder);
		shoppingOrder.setId(orderId);
		
		ShoppingOrderDetail shoppingOrderDetail=new ShoppingOrderDetail();
		shoppingOrderDetail.setGoodsid(goods.getId());
		shoppingOrderDetail.setShoppingorderid(shoppingOrder.getId());
		shoppingOrderDetail.setQty(10);
		shoppingOrderDetail.setTotalprice(BigDecimal.valueOf(shoppingOrderDetail.getQty()).multiply(goods.getPrice()));
		shoppingOrderDetail.setCreateby("zuowenjun");
		shoppingOrderDetail.setCreatetime(new Date());
		
		shoppingOrderDetailMapper.insert(shoppingOrderDetail);
		
		List<ShoppingOrderDetail> orderDetails= shoppingOrderDetailMapper.selectByOrderId(shoppingOrder.getId());
		if(orderDetails!=null && orderDetails.size()>0) {
			for(ShoppingOrderDetail od:orderDetails) {
				System.out.println("id:" + od.getId() + ",goodsid:" + od.getGoodsid());
			}
		}
		
		Assert.assertTrue(orderDetails.size()>0); 
	}
}
