package cn.zuowenjun.boot.mapper;

import java.util.*;

import org.apache.ibatis.annotations.*;

import cn.zuowenjun.boot.domain.*;

public interface GoodsMapper {
	
	@Select("select * from TA_TestGoods order by id offset (${pageNo}-1)*${pageSize} rows fetch next ${pageSize} rows only")
	List<Goods> getListByPage(int pageSize,int pageNo);
	
	@Select("select * from TA_TestGoods where categoryId=#{categoryId} order by id")
	List<Goods> getList(int categoryId);
	
	@Select("<script>select * from TA_TestGoods where id in " 
			+"<foreach item='item' index='index' collection='ids' open='(' separator=',' close=')'>#{item}</foreach>"
			+"order by id</script>")
	List<Goods> getListByMultIds(@Param("ids")int...ids);
	
	@Select("select * from TA_TestGoods where id=#{id}")
	Goods get(int id);
	

	@Insert(value="insert into TA_TestGoods(title, picture, price, introduction, categoryId, "
			+ "lastEditBy, lastEditTime) values(#{title},#{picture},#{price},#{introduction},#{categoryId},#{lastEditBy},getdate())")
	@Options(useGeneratedKeys=true,keyProperty="id",keyColumn="id")
	void insert(Goods goods);
	
	@Delete(value="delete from TA_TestGoods where id=#{id}")
	void delete(int id);
	
	@Update("update TA_TestGoods set title=#{title},picture=#{picture},price=#{price},introduction=#{introduction}," + 
			"categoryId=#{categoryId},lastEditBy=#{lastEditBy},lastEditTime=getdate()  " + 
			"where id=#{id}")
	void update(Goods goods);
	
	
}
