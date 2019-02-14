package cn.zuowenjun.boot.mapper;

import cn.zuowenjun.boot.domain.ShoppingOrder;
import java.util.List;

public interface ShoppingOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingOrder record);

    ShoppingOrder selectByPrimaryKey(Integer id);

    List<ShoppingOrder> selectAll();

    int updateByPrimaryKey(ShoppingOrder record);
}