package cn.zuowenjun.boot.mapper;

import cn.zuowenjun.boot.domain.ShoppingOrderDetail;
import java.util.List;

public interface ShoppingOrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShoppingOrderDetail record);

    ShoppingOrderDetail selectByPrimaryKey(Integer id);

    List<ShoppingOrderDetail> selectAll();

    int updateByPrimaryKey(ShoppingOrderDetail record);

    List<ShoppingOrderDetail> selectByOrderId(int shoppingOrderId);

    void deleteByOrderId(int shoppingOrderId);
}