package cn.zuowenjun.boot.mapper;

import cn.zuowenjun.boot.domain.ShopUser;
import java.util.List;

public interface ShopUserMapper {
    int deleteByPrimaryKey(String userid);

    int insert(ShopUser record);

    ShopUser selectByPrimaryKey(String userid);

    List<ShopUser> selectAll();

    int updateByPrimaryKey(ShopUser record);
}