package cn.zuowenjun.boot.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.zuowenjun.boot.domain.*;
import cn.zuowenjun.boot.service.*;

/*
 * ALL REST API 
 */
@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private ShoppingOrderService shoppingOrderService;

	@Autowired
	private ShopUserService shopUserService;
	
	private String getCurrentShopper() {
		String shopper = shopUserService.getCurrentLoginUser();
		return shopper;
	}
	
	@PostMapping(value="/login",produces = "application/json;charset=utf-8")
	public ApiResultMsg login(@RequestBody Map<String,String> requestMap) {
		String userid=requestMap.get("userid");
		String upwd=requestMap.get("upwd");
		String loginResult= shopUserService.login(userid, upwd);
		if(loginResult==null) {
			return new ApiResultMsg(0,"OK",null);
		}else {
			return new ApiResultMsg(-1,"登录失败：" + loginResult,null);
		}
		
	}

	@GetMapping(value = "/categorys", produces = "application/json;charset=utf-8")
	public List<GoodsCategory> getAllGoodsCategorys() {
		return goodsService.getAllGoodsCategoryList();
	}

	@GetMapping(value = "/goods/{cateid}", produces = "application/json;charset=utf-8")
	public List<Goods> getGoodsList(@PathVariable(name = "cateid") int categoryid) {
		return goodsService.getGoodsListByCategory(categoryid);
	}

	@GetMapping(value = "/goods", produces = "application/json;charset=utf-8")
	public List<Goods> getGoodsList(@RequestParam(name = "pagesize", required = false) String spageSize,
			@RequestParam(name = "page", required = false) String spageNo) {

		int pageSize = tryparseToInt(spageSize);
		int pageNo = tryparseToInt(spageNo);

		pageSize = pageSize <= 0 ? 10 : pageSize;
		pageNo = pageNo <= 1 ? 1 : pageNo;
		return goodsService.getGoodsListByPage(pageSize, pageNo);
	}

	@GetMapping(value = "/goodsmany", produces = "application/json;charset=utf-8")
	public List<Goods> getGoodsListByMultIds(@RequestBody int[] ids) {
		return goodsService.getGoodsListByMultIds(ids);
	}

	@PostMapping(value = "/addToShoppingCart", produces = "application/json;charset=utf-8")
	public ApiResultMsg addToShoppingCart(@RequestBody Map<String, Integer> json) {
		int goodsId = json.get("goodsid");
		int qty = json.get("goodsqty");
		ApiResultMsg msg = new ApiResultMsg();
		if (goodsId <= 0) {
			msg.setCode(101);
			msg.setMsg("该商品ID无效");
			return msg;
		}

		String shopper = getCurrentShopper();
		ShoppingCart shoppingCart = new ShoppingCart(0, shopper, goodsId, qty, new Date());

		shoppingOrderService.insertShoppingCart(shoppingCart);

		msg.setCode(0);
		msg.setMsg("添加购物车成功！");

		int cartCount = shoppingOrderService.getShoppingCartBuyCount(shopper);
		HashMap<String, Object> data = new HashMap<>();
		data.put("cartCount", cartCount);

		msg.setData(data);

		return msg;
	}

	@GetMapping(value = "/goods-{gid}", produces = "application/json;charset=utf-8")
	public Goods getGoods(@PathVariable("gid") int goodsId) {
		return goodsService.getGoods(goodsId);
	}

	@GetMapping(value = "/cartlist", produces = "application/json;charset=utf-8")
	public List<ShoppingCart> getShoppingCartList() {
		String shopper = getCurrentShopper();
		return shoppingOrderService.getShoppingCartList(shopper);
	}

	@PostMapping(value = "/deletecartitems-{mode}", produces = "application/json;charset=utf-8")
	public ApiResultMsg deleteShoppingCartItems(@PathVariable("mode") String mode,
			@RequestBody(required = false) int[] cartIds) {
		if (mode.equalsIgnoreCase("all")) {
			String shopper = getCurrentShopper();
			shoppingOrderService.clearShoppingCart(shopper);
		} else {
			for (int id : cartIds) {
				shoppingOrderService.deleteShoppingCart(id);
			}
		}

		return new ApiResultMsg(0, "删除成功！", null);
	}

	@PostMapping(value = "/createorder", produces = "application/json;charset=utf-8")
	public ApiResultMsg createShoppingOrder() {

		String shopper = getCurrentShopper();
		ApiResultMsg msg = new ApiResultMsg();
		if (shoppingOrderService.createShoppingOrderByShopper(shopper)) {
			msg.setCode(0);
			msg.setMsg("恭喜你，下单成功！");
		} else {
			msg.setCode(101);
			msg.setMsg("对不起，下单失败，请重试！");
		}

		return msg;

	}

	@RequestMapping(path = "/orders", produces = "application/json;charset=utf-8", method = RequestMethod.GET) // 等同于@GetMapping
	public List<ShoppingOrder> getShoppingOrderList() {
		String shopper = getCurrentShopper();
		return shoppingOrderService.getShoppingOrderList(shopper);
	}

	@RequestMapping(path = "/orderdetail", produces = "application/json;charset=utf-8", method = RequestMethod.POST) // 等同于@PostMapping
	public ApiResultMsg getShoppingOrderDetail(@RequestBody Map<String, String> requestJosn) {
		String orderId = requestJosn.get("orderId");
		List<ShoppingOrderDetail> orderDetails = shoppingOrderService.getShoppingOrderDetail(tryparseToInt(orderId));
		ApiResultMsg msg = new ApiResultMsg();
		if (orderDetails.size() > 0) {

			int[] goodsIds = new int[orderDetails.size()];
			for (int i = 0; i < orderDetails.size(); i++) {
				goodsIds[i] = orderDetails.get(i).getGoodsid();
			}

			List<Goods> goodsList = goodsService.getGoodsListByMultIds(goodsIds);
			HashMap<String, Object> data = new HashMap<>();
			data.put("details", orderDetails);
			data.put("goodss", goodsList);
			
			msg.setCode(0);
			msg.setData(data);

		} else {
			msg.setCode(101);
			msg.setMsg("获取订单详情信息失败！");
		}
		
		return msg;

	}
	
	//这里示例配置多个URL请求路径
	@PostMapping(path= {"/confirmOrderCompleted","/cfmordercompl"},produces="application/json;charset=utf-8")
	public ApiResultMsg confirmOrderCompleted(@RequestBody Map<String, String> requestJosn) {
		String reqOrderId = requestJosn.get("orderId");
		ApiResultMsg msg=new ApiResultMsg();
		try {
			
			int orderId=tryparseToInt(reqOrderId);
			ShoppingOrder  order= shoppingOrderService.getShoppingOrder(orderId);
			order.setIscompleted(true);
			shoppingOrderService.updateShoppingOrder(order);
			msg.setCode(0);
			msg.setMsg("确认订单完成成功（已收货）");
		}catch (Exception e) {
			msg.setCode(101);
			msg.setMsg("确认订单完成失败：" + e.getMessage());
		}
		
		return msg;
	}
	
	
	@PostMapping(path="/savegoods",produces="application/json;charset=utf-8",consumes="multipart/form-data")
	public ApiResultMsg saveGoods(@RequestParam("picture") MultipartFile gpic,HttpServletRequest request) {
		ApiResultMsg msg=new ApiResultMsg();
		try
		{
			Goods goods=new Goods();
			goods.setId(tryparseToInt(request.getParameter("id")));
			goods.setTitle(request.getParameter("title"));
			goods.setPrice(new BigDecimal(request.getParameter("price")));
			goods.setIntroduction(request.getParameter("introduction"));
			goods.setCategoryId(tryparseToInt(request.getParameter("categoryId")));
			goods.setLastEditBy(getCurrentShopper());
			goods.setLastEditTime(new Date());
			
			if(goods.getId()<=0) {
				goodsService.insertGoods(goods, gpic);
			} else {
				goodsService.updateGoods(goods, gpic);
			}
			
			msg.setCode(0);
			msg.setMsg("保存成功！");
			msg.setData(goods);
			
		}catch (Exception e) {
			msg.setCode(101);
			msg.setMsg("保存失败：" + e.getMessage());
		}
		
		return msg;
		
	}
	
	@GetMapping(path="/delgoods/{gid}",produces="application/json;charset=utf-8")
	public ApiResultMsg deleteGoods(@PathVariable("gid") int goodsId) {
		goodsService.deleteGoods(goodsId);
		ApiResultMsg msg=new ApiResultMsg();
		msg.setCode(0);
		msg.setMsg("删除商品成功！");
		
		return msg;
	}
	

	private int tryparseToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
			return -1;
		}
	}

}
