package com.imooc.o2o.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest {

	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Test
	@Ignore
	public void testAInsertProduct() throws Exception {
		Shop shop1 = new Shop();
		shop1.setShopId(5L);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(2L);
		Product product1 = new Product();
		product1.setProductName("测试1");
		product1.setProductDesc("测试Desc1");
		product1.setImgAddr("test1");
		product1.setPriority(0);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(pc1);
		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1, effectedNum);
	}

	@Test
	public void testBQueryProductList() throws Exception {
		Product product = new Product();
		Shop shop = new Shop();
		shop.setShopId(5L);
		product.setShop(shop);
		List<Product> productList = productDao.queryProductList(product, 0, 3);
		for (Product product1 : productList) {
			System.out.println("###############");
			System.out.println(product1.getProductName());
		}
		System.out.println(productList.size());

	}

//	@Test
//	public void testCQueryProductByProductId() throws Exception {
//		long productId = 1;
//		ProductImg productImg1 = new ProductImg();
//		productImg1.setImgAddr("图片1");
//		productImg1.setImgDesc("测试图片1");
//		productImg1.setPriority(1);
//		productImg1.setCreateTime(new Date());
//		productImg1.setProductId(productId);
//		ProductImg productImg2 = new ProductImg();
//		productImg2.setImgAddr("图片2");
//		productImg2.setPriority(1);
//		productImg2.setCreateTime(new Date());
//		productImg2.setProductId(productId);
//		List<ProductImg> productImgList = new ArrayList<ProductImg>();
//		productImgList.add(productImg1);
//		productImgList.add(productImg2);
//		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
//		assertEquals(2, effectedNum);
//		Product product = productDao.queryProductByProductId(productId);
//		assertEquals(2, product.getProductImgList().size());
//		effectedNum = productImgDao.deleteProductImgByProductId(productId);
//		assertEquals(2, effectedNum);
//	}
//
//	@Test
//	public void testDUpdateProduct() throws Exception {
//		Product product = new Product();
//		product.setProductId(1L);
//		product.setProductName("第一个产品");
//		int effectedNum = productDao.updateProduct(product);
//		assertEquals(1, effectedNum);
//	}
//
//	@Ignore
//	@Test
//	public void testEDeleteShopAuthMap() throws Exception {
//		int effectedNum = productDao.deleteProduct(2, 1);
//		assertEquals(1, effectedNum);
//	}
}
