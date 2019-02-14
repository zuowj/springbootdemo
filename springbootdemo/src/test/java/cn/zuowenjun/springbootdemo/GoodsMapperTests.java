package cn.zuowenjun.springbootdemo;


import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.zuowenjun.boot.SpringbootdemoApplication;
import cn.zuowenjun.boot.domain.Goods;
import cn.zuowenjun.boot.domain.GoodsCategory;
import cn.zuowenjun.boot.mapper.*;
import org.junit.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringbootdemoApplication.class)
public class GoodsMapperTests {

	@Autowired(required=true)
	private GoodsMapper goodsMapper;
	
	@Autowired(required=true)
	private GoodsCategoryMapper goodsCategoryMapper;
	
	
	@Test
	public void testInsertGoodsCategory() {
		goodsCategoryMapper.insert(new GoodsCategory(0,"编程书箱",1,1,"梦在旅途",new Date()));
		
		int size= goodsCategoryMapper.getAll().size();
		Assert.assertTrue(size>0);
	}
	
	@Test
	public void testInsert() {
		goodsMapper.insert(new Goods(0,
				"JAVA从入门到精通秘籍，值得拥有",
				"http://img105.job1001.com/upload/adminnew/2014-09-04/1409822424-4J2KEAU.jpg",
				BigDecimal.valueOf(68.88),"精读此书，理解此书中内容，将成为JAVA高手！测试",1,"zuowenjun",new Date()
				 ));
		
		int size= goodsMapper.getList(1).size();
		Assert.assertEquals(1, size);
		
	}
	
	
	@Test
	public void testUpdate() {
		Goods goods= goodsMapper.get(1);
		goods.setTitle("JAVA从入门到精通秘籍，值得拥有," + "2019特惠热卖666");
		goods.setPrice(BigDecimal.valueOf(28.99));
		goods.setIntroduction(goods.getIntroduction() + "www.zuowenjun.cn,大减价，赶快行动吧！");
		goodsMapper.update(goods);
		
		Goods updatedGoods=goodsMapper.get(1);
		Assert.assertEquals(goods.getTitle(), updatedGoods.getTitle());
	}
	

}
