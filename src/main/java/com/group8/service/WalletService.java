package com.group8.service;

import com.group8.entity.Wallet;
import com.group8.entity.TransactionRecord;
import java.util.Map;
import java.util.List;

/**
 * 钱包服务接口
 */
public interface WalletService {
    /**
     * 根据用户ID获取钱包信息
     * @param userId 用户ID
     * @return 钱包信息
     */
    Wallet getByUserId(Integer userId);
    
    /**
     * 为用户创建钱包
     * @param userId 用户ID
     * @return 钱包信息
     */
    Wallet createWallet(Integer userId);
    
    /**
     * 钱包充值
     * @param userId 用户ID
     * @param amount 充值金额
     * @param paymentMethod 支付方式
     * @param remark 充值备注
     * @return 交易记录
     */
    TransactionRecord recharge(Integer userId, Double amount, String paymentMethod, String remark);
    
    /**
     * 钱包提现
     * @param userId 用户ID
     * @param amount 提现金额
     * @param bankCardNumber 银行卡号
     * @param bankName 银行名称
     * @param remark 提现备注
     * @return 交易记录
     */
    TransactionRecord withdraw(Integer userId, Double amount, String bankCardNumber, String bankName, String remark);
    
    /**
     * 钱包支付
     * @param userId 付款方用户ID
     * @param amount 支付金额
     * @param payeeId 收款方ID
     * @param remark 支付备注
     * @return 交易记录
     */
    TransactionRecord pay(Integer userId, Double amount, Integer payeeId, String remark);
    
    /**
     * 获取钱包交易记录
     * @param userId 用户ID
     * @param params 查询参数
     * @return 交易记录列表
     */
    List<TransactionRecord> getTransactionRecords(Integer userId, Map<String, Object> params);
    
    /**
     * 获取钱包交易记录总数
     * @param userId 用户ID
     * @param params 查询参数
     * @return 交易记录总数
     */
    int getTransactionRecordsCount(Integer userId, Map<String, Object> params);
}