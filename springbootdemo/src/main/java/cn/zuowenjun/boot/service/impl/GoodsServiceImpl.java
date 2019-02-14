package cn.zuowenjun.boot.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import cn.zuowenjun.boot.domain.*;
import cn.zuowenjun.boot.mapper.GoodsCategoryMapper;
import cn.zuowenjun.boot.mapper.GoodsMapper;
import cn.zuowenjun.boot.service.GoodsService;

@Service
public class GoodsServiceImpl implements GoodsService {
	
	private static Logger logger=LoggerFactory.getLogger(GoodsServiceImpl.class);
	
	@Autowired
	private GoodsMapper goodsMapper;
	
	@Autowired
	private GoodsCategoryMapper categoryMapper;
	
	@Override
	public List<Goods> getGoodsListByPage(int pageSize,int pageNo){
		return goodsMapper.getListByPage(pageSize, pageNo);
	}
	
	@Override
	public List<Goods> getGoodsListByCategory(int categoryId) {
		return goodsMapper.getList(categoryId);
	}
	
	@Override
	public List<Goods> getGoodsListByMultIds(int... goodsIds) {
		return goodsMapper.getListByMultIds(goodsIds);
	}
	
	@Override
	public Goods getGoods(int id) {
		return goodsMapper.get(id);
	}
	
	@Transactional
	@Override
	public void insertGoods(Goods goods, MultipartFile uploadGoodsPic) {
		String picPath= saveGoodsPic(uploadGoodsPic);
		if(picPath!=null && !picPath.isEmpty()) {
			
			goods.setPicture(picPath);
		}
		goodsMapper.insert(goods);
		
		GoodsCategory gcate= categoryMapper.get(goods.getCategoryId());
		gcate.setGoodsCount(gcate.getGoodsCount()+1);
		categoryMapper.update(gcate);
		
		logger.info("inserted new goods - id:" + goods.getId());
	}
	
	@Override
	public void updateGoods(Goods goods,MultipartFile uploadGoodsPic) {
		String picPath= saveGoodsPic(uploadGoodsPic);
		if(picPath!=null && !picPath.isEmpty()) {
			
			goods.setPicture(picPath);
		}
		 goodsMapper.update(goods);
		 
		 logger.info("update goods - id:" + goods.getId());
	}
	
	@Transactional
	@Override
	public void deleteGoods(int id) {
		Goods g= goodsMapper.get(id);
		goodsMapper.delete(g.getId());
		
		GoodsCategory gcate= categoryMapper.get(g.getCategoryId());
		gcate.setGoodsCount(gcate.getGoodsCount()-1);
		categoryMapper.update(gcate);
		
		//如果有图片，则同时删除图片
		if(g.getPicture()!=null && !g.getPicture().isEmpty()) {
			
			String picPath= getRequest().getServletContext().getRealPath("/") + g.getPicture();
			File file = new File(picPath);
			if(file.exists()) {
				file.delete();
			}
		}
		
		logger.info("deleted goods - id:" + g.getId());
	}
	
	@Override
	public List<GoodsCategory> getAllGoodsCategoryList(){
		return categoryMapper.getAll();
	}
	
	@Override
	public void insertGoodsCategory(GoodsCategory goodsCategory) {
		categoryMapper.insert(goodsCategory);
	}
	
	@Override
	public void updateGoodsCategory(GoodsCategory goodsCategory) {
		categoryMapper.update(goodsCategory);
	}
	
	@Override
	public void deleteGoodsCategory(int id) {
		categoryMapper.delete(id);
	}


	private String saveGoodsPic(MultipartFile uploadGoodsPic) {
		
		if(uploadGoodsPic==null || uploadGoodsPic.isEmpty()) {
			return null;
		}
		
		String fileName = uploadGoodsPic.getOriginalFilename();
		
		String extName = fileName.substring(fileName.lastIndexOf("."));
		
        String newFileName=UUID.randomUUID().toString()+extName;
		File file = new File(getFileSavePath(newFileName));
		if(!file.exists()) {
			file.getParentFile().mkdirs();
		}
		
		
		try {
			uploadGoodsPic.transferTo(file);
			//return file.toURI().toURL().toString();
			return getUrlPath(file.getAbsolutePath());
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}



	private String getFileSavePath(String fileName) {
        String realPath =getRequest().getServletContext().getRealPath("/uploadimgs/");
        return realPath + fileName;
	}

	private String getUrlPath(String filePath) {
		String rootPath= getRequest().getServletContext().getRealPath("/");
		return filePath.replace(rootPath, "").replaceAll("\\\\", "/");
	}

	private HttpServletRequest getRequest() {
        HttpServletRequest  request= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
	
	
}
