package com.group8.controller;

import com.group8.entity.BuyerQueryParam;
import com.group8.entity.Product;
import com.group8.entity.Result;
import com.group8.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 供应商创建商品
     */
    @PostMapping("/add")
    public Result add(@RequestBody Product product) {
        log.info("商品添加：{}", product);
        productService.add(product);
        return Result.success(product);
    }

    /**
     * 供应商获取自有商品列表
     */
    @GetMapping("/supplier/{userId}/{userType}")
    public Result getSupplierProducts(@PathVariable Long userId,
                                      @PathVariable  Integer userType)
    {
        log.info("获取商品列表：{}", userId);
        List<Product> products = productService.getSupplierProducts(userId, userType);
        return Result.success(products);
    }

    /**
     * 供应商更新商品
     */
    @PutMapping("/{productId}")
    public Result update(@PathVariable Integer productId, @RequestBody Product product) {
        log.info("更新商品：{}", product);
        productService.update(productId, product);
        return Result.success(product);
    }

    /**
     * 供应商删除商品
     */
    @DeleteMapping("/{productId}")
    public Result delete(@PathVariable Integer productId) {
        log.info("删除商品：{}", productId);
        productService.delete(productId);
        return Result.success();
    }

    /**
     * 采购商获取所有商品列表,分页查询,可根据keyword模糊搜索关键词,可按商品类目ID筛选
     * 使用BuyerQueryParam类
     */
    @GetMapping
    public Result getAllProducts(BuyerQueryParam queryParam) {
        log.info("获取商品列表：{}", queryParam);
        List<Product> products = productService.getAllProducts(queryParam);
        return Result.success(products);
    }




}
