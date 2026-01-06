package com.group8.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

/**
 * 售后申请实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AfterSales {
    /**
     * 售后申请ID
     */
    private Integer id;
    
    /**
     * 订单ID
     */
    private Integer orderId;
    
    /**
     * 订单项ID
     */
    private Integer orderItemId;
    
    /**
     * 采购商ID
     */
    private Integer buyerId;
    
    /**
     * 供应商ID
     */
    private Integer sellerId;
    
    /**
     * 售后类型: 1-退货退款, 2-仅退款, 3-换货
     */
    private Integer afterSalesType;
    
    /**
     * 售后状态: 1-待审核,2-审核通过,3-审核拒绝,4-待退货,5-待收货,6-已完成,7-已关闭
     */
    private Integer status;
    
    /**
     * 申请原因
     */
    private String reason;
    
    /**
     * 问题描述
     */
    private String description;
    
    /**
     * 问题图片URL数组
     */
    private List<String> images;
    
    /**
     * 申请退款金额
     */
    private Double refundAmount;
    
    /**
     * 实际退款金额
     */
    private Double actualRefundAmount;
    
    /**
     * 审核意见
     */
    private String reviewComment;
    
    /**
     * 物流公司
     */
    private String shippingCompany;
    
    /**
     * 运单号
     */
    private String trackingNumber;
    
    /**
     * 退货备注
     */
    private String returnNote;
    
    /**
     * 收货状态: 1-已收货, 2-商品有问题
     */
    private Integer receiveStatus;
    
    /**
     * 收货备注
     */
    private String receiveNote;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * 更新时间
     */
    private Date updateTime;
}