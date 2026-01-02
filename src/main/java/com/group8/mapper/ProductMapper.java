package com.group8.mapper;

import com.group8.entity.BuyerQueryParam;
import com.group8.entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    /**
     * 供应商创建商品
     * @param product
     */
    void add(Product product);

    /**
     * 供应商获取自有商品列表
     * @param userId
     * @return
     */

    List<Product> selectBySupplierId(Long userId);



    /** * 供应商更新商品
     * @param productId
     * @param product
     **/
    void update(@Param("productId") Integer productId,  @Param("product") Product product);

    /**
     * 供应商删除商品
     */
    @Delete("delete from products where productId=#{productId}")
    void delete(Integer productId);

    /**
     * 采购商获取所有商品列表,分页查询,可根据keyword模糊搜索关键词,可按商品类目ID筛选
     */
    List<Product> getAllProducts(@Param("queryParam") BuyerQueryParam queryParam);

}
