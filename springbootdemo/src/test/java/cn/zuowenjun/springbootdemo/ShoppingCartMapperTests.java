package cn.zuowenjun.springbootdemo;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.model.TestTimedOutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import cn.zuowenjun.boot.SpringbootdemoApplication;
import cn.zuowenjun.boot.domain.ShoppingCart;
import cn.zuowenjun.boot.mapper.ShoppingCartMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootdemoApplication.class)
public class ShoppingCartMapperTests {
	
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
	
	@Test
	public void testInsert() {
		ShoppingCart shoppingCart=new ShoppingCart();
		shoppingCart.setShopper("zuowenjun");
		shoppingCart.setGoodsId(1);
		shoppingCart.setQty(2);
		shoppingCart.setAddedTime(new Date());
		shoppingCartMapper.insert(shoppingCart);
		
		int id=shoppingCart.getId();
		Assert.assertTrue(id>0);
	}
	
	@Transactional
	@Test
	public void testUpdate()  {
		List<ShoppingCart> resultList= shoppingCartMapper.getList("zuowenjun");
		ShoppingCart shoppingCart=resultList.get(0);
		shoppingCart.setQty(30);
		shoppingCartMapper.update(shoppingCart);
		
		ShoppingCart shoppingCart2=resultList.get(0);

		Assert.assertSame(shoppingCart.getQty(),shoppingCart2.getQty());
	}
	
	@Transactional
	@Test
	public void testDeleteWithTran() throws TestTimedOutException {
		List<ShoppingCart> resultList= shoppingCartMapper.getList("zuowenjun");//有多条记录
		for(int i=0;i<resultList.size();i++) {
			ShoppingCart item= resultList.get(i);
			shoppingCartMapper.deleteItem(item.getId());
		}

		throw new TestTimedOutException(1, TimeUnit.SECONDS);
	}
}
