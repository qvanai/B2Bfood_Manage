package com.group8.service.impl;

import com.group8.entity.BuyerQueryParam;
import com.group8.entity.Product;
import com.group8.mapper.ProductMapper;
import com.group8.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 供应商创建商品
     */
    @Override
    public void add(Product product) {
        productMapper.add(product);
    }

    /**
     * 供应商获取自有商品列表
     */
    @Override
    public List<Product> getSupplierProducts(Long userId, Integer userType) {
        //验证用户类型为供应商
        if (userType != 2) {
            throw new RuntimeException("只有供应商可以查看自有商品列表");
        }

            //查询供应商的自有商品
            return productMapper.selectBySupplierId(userId);
        }

    /**
     * 供应商更新商品
     */
    @Override
    public void update(Integer productId, Product product) {
        productMapper.update(productId, product);
    }

    /**
     * 供应商删除商品
     */
    @Override
    public void delete(Integer productId) {
        //调用mapper,执行sql
        productMapper.delete(productId);
    }

    /**
     * 采购商获取所有商品列表,分页查询,可根据keyword模糊搜索关键词,可按商品类目ID筛选
     */
    @Override
    public List<Product> getAllProducts(BuyerQueryParam queryParam) {
        //调用mapper,传入参数,执行sql
        return productMapper.getAllProducts(queryParam);
    }
}