package cn.zuowenjun.boot.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.zuowenjun.boot.domain.*;


public interface GoodsService {
	
	List<Goods> getGoodsListByPage(int pageSize,int pageNo);
	
	List<Goods> getGoodsListByCategory(int categoryId);
	
	List<Goods> getGoodsListByMultIds(int...goodsIds);
	
	Goods getGoods(int id);
	
	void insertGoods(Goods goods,MultipartFile uploadGoodsPic);
	
	void updateGoods(Goods goods,MultipartFile uploadGoodsPic);
	
	void deleteGoods(int id);
	
	List<GoodsCategory> getAllGoodsCategoryList();
	
	void insertGoodsCategory(GoodsCategory goodsCategory);
	
	void updateGoodsCategory(GoodsCategory goodsCategory);
	
	void deleteGoodsCategory(int id);
	
}
