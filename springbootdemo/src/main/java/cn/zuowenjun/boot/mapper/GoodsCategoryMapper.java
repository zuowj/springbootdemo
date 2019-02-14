package cn.zuowenjun.boot.mapper;

import java.util.*;

import org.apache.ibatis.annotations.*;

import cn.zuowenjun.boot.domain.*;

public interface GoodsCategoryMapper {
	
	@Select("select * from TA_TestGoodsCategory order by serialNo")
	List<GoodsCategory> getAll();
	
	@Select("select * from TA_TestGoodsCategory where id=#{id}")
	GoodsCategory get(int id);
	
	@Insert("insert into  TA_TestGoodsCategory(categoryName,goodsCount,serialNo,lastEditBy,lastEditTime)"
			+ "values(#{categoryName},#{goodsCount},#{serialNo},#{lastEditBy},getdate())")
	@Options(useGeneratedKeys=true,keyColumn="id",keyProperty="id")
	void insert(GoodsCategory goodsCategory);
	
	@Delete("delete from TA_TestGoodsCategory where id=#{id}")
	void delete(int id);
	
	@Update("update TA_TestGoodsCategory set  categoryName=#{categoryName},goodsCount=#{goodsCount},serialNo=#{serialNo},lastEditBy=#{lastEditBy},lastEditTime=getdate()  " + 
			"where id=#{id}")
	void update(GoodsCategory goodsCategory);
}
