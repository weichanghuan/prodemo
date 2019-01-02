/**
 * Copyright (c) 2017, ShangHai HOWBUY INVESTMENT MANAGEMENT Co., Ltd.
 * All right reserved.
 * <p>
 * THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF HOWBUY INVESTMENT
 * MANAGEMENT CO., LTD.  THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 * TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 * WITHOUT THE PRIOR WRITTEN PERMISSION OF HOWBUY INVESTMENT MANAGEMENT
 * CO., LTD.
 */
package com.utils;

import java.util.HashMap;

/**
 * @author weichanghuan
 * @description: 交易下单导入指令业务
 * @reason: Excel解析每行数据对象
 * @date 2017/7/25 18:42
 * @since JDK 1.6
 */
public class ExcelDataRecord {

    /**
     * getExcelHead:获取Excel头信息. <br/>
     *
     * @return java.util.HashMap<java.lang.String , java.lang.String>
     * @author weichanghuan
     * @date 2017/7/26 16:59
     */
    public static HashMap<String, String> getExcelHead() {
        HashMap<String, String> map = new HashMap<>();
        map.put("交易通道", "tradeChannel");
        map.put("指令序号", "instructionSerialNo");
        map.put("产品代码", "productCode");
        map.put("产品名称", "productName");
        map.put("委托方向", "consignation");
        map.put("基金代码", "fundCode");
        map.put("基金名称", "fundName");
        map.put("指令金额", "instructionAmount");
        map.put("指令数量", "instructionNum");
        map.put("巨额赎回", "largeRedemptionFlag");
        map.put("分红方式", "dividendMethod");
        map.put("转入证券代码", "intoFundCode");
        map.put("转入证券名称", "intoFundName");
        map.put("外部撤销处理状态", "outsideCancelHandleStatus");
        map.put("指令状态", "instructionStatus");
        map.put("指令下达人", "instructionIssuer");
        map.put("下达日期", "issuerDate");
        map.put("下达时间", "issuerTime");
        map.put("业务类型", "businessType");
        map.put("投资渠道", "distributorName");
        map.put("指令修改序号", "instructionUpdateSerialNo");
        map.put("基金序号", "fundNo");
        map.put("组合名称", "groupName");
        map.put("证券代码", "securitiesCode");
        map.put("证券名称", "securitiesName");
        map.put("转入组合", "intoGroup");
        map.put("基金管理人", "fundManager");
        map.put("持仓数量", "positionNum");
        map.put("转入证券持仓数量", "intoSecuritiesPosition");
        map.put("申请传真编号", "applyFaxNo");
        map.put("申请传真打印次数", "applyFaxPrintTimes");
        map.put("撤销传真编号", "cancelFaxNo");
        map.put("撤销传真打印次数", "cancelFaxPrintTimes");
        map.put("交易市场", "tradeMarket");
        map.put("风险国家/地区", "riskPlace");
        map.put("投资类型", "investmentType");
        map.put("转入投资类型", "intoInvestmentType");
        map.put("指令操作级别", "instructionOperationLevel");
        map.put("执行状态", "executeStatus");
        map.put("交易说明", "tradeDescription");
        map.put("起始时间", "startTime");
        map.put("终止时间", "endTime");
        map.put("分发时间", "distributeTime");
        map.put("到期日期", "expireDate");
        map.put("订单状态", "orderStatus");
        map.put("错误信息", "errorMessage");
        map.put("撤销错误信息", "cancelErrorMessage");
        map.put("指令备注", "instructionRemark");
        map.put("ric", "ric");
        map.put("bloomberg", "bloomberg");
        map.put("sedol", "sedol");
        map.put("cusip", "cusip");
        map.put("isin", "isin");
        return map;
    }

    /**
     * 交易通道 00 传真
     */
    private String tradeChannel;

    /**
     * 产品代码
     */
    private String productCode;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 委托方向
     */
    private String consignation;

    /**
     * 基金代码
     */
    private String fundCode;

    /**
     * 基金简称
     */
    private String fundName;

    /**
     * 指令金额（元）
     */
    private Double instructionAmount;

    /**
     * 指令数量（份）
     */
    private Double instructionNum;

    /**
     * 巨额赎回标示
     */
    private String largeRedemptionFlag;

    /**
     * 分红方式
     */
    private String dividendMethod;

    /**
     * 转入基金代码
     */
    private String intoFundCode;

    /**
     * 转入基金简称
     */
    private String intoFundName;

    /**
     * 外部撤销处理状态
     */
    private String outsideCancelHandleStatus;

    /**
     * 指令状态
     */
    private String instructionStatus;

    /**
     * 指令下达人
     */
    private String instructionIssuer;

    /**
     * 下达日期
     */
    private String issuerDate;

    /**
     * 下达时间
     */
    private String issuerTime;

    /**
     * 指令序号
     */
    private String instructionSerialNo;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 投资渠道
     */
    private String distributorName;

    /**
     * 指令修改序号
     */
    private String instructionUpdateSerialNo;

    /**
     * 基金序号
     */
    private String fundNo;

    /**
     * 组合名称
     */
    private String groupName;

    /**
     * 证券代码
     */
    private String securitiesCode;

    /**
     * 证券名称
     */
    private String securitiesName;

    /**
     * 转入组合
     */
    private String intoGroup;

    /**
     * 基金管理人
     */
    private String fundManager;

    /**
     * 持仓数量
     */
    private Long positionNum;

    /**
     * 转入证券持仓数量
     */
    private Long intoSecuritiesPosition;

    /**
     * 申请传真编号
     */
    private String applyFaxNo;

    /**
     * 申请传真打印次数
     */
    private Long applyFaxPrintTimes;

    /**
     * 撤销传真编号
     */
    private String cancelFaxNo;

    /**
     * 撤销传真打印次数
     */
    private Long cancelFaxPrintTimes;

    /**
     * 交易市场
     */
    private String tradeMarket;

    /**
     * 风险国家/地区
     */
    private String riskPlace;

    /**
     * 投资类型
     */
    private String investmentType;

    /**
     * 转入投资类型
     */
    private String intoInvestmentType;

    /**
     * 指令操作级别
     */
    private String instructionOperationLevel;

    /**
     * 执行状态
     */
    private String executeStatus;

    /**
     * 交易说明
     */
    private String tradeDescription;

    /**
     * 起始时间
     */
    private String startTime;

    /**
     * 终止时间
     */
    private String endTime;

    /**
     * 分发时间
     */
    private String distributeTime;

    /**
     * 到期日期
     */
    private String expireDate;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 撤销错误信息
     */
    private String cancelErrorMessage;

    /**
     * 指令备注
     */
    private String instructionRemark;

    /**
     * ric
     */
    private String ric;

    /**
     * bloomberg
     */
    private String bloomberg;

    /**
     * sedol
     */
    private String sedol;

    /**
     * cusip
     */
    private String cusip;

    /**
     * isin
     */
    private String isin;

    public String getTradeChannel() {
        return tradeChannel;
    }

    public void setTradeChannel(String tradeChannel) {
        this.tradeChannel = tradeChannel;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getConsignation() {
        return consignation;
    }

    public void setConsignation(String consignation) {
        this.consignation = consignation;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public Double getInstructionAmount() {
        return instructionAmount;
    }

    public void setInstructionAmount(Double instructionAmount) {
        this.instructionAmount = instructionAmount;
    }

    public Double getInstructionNum() {
        return instructionNum;
    }

    public void setInstructionNum(Double instructionNum) {
        this.instructionNum = instructionNum;
    }

    public String getLargeRedemptionFlag() {
        return largeRedemptionFlag;
    }

    public void setLargeRedemptionFlag(String largeRedemptionFlag) {
        this.largeRedemptionFlag = largeRedemptionFlag;
    }

    public String getDividendMethod() {
        return dividendMethod;
    }

    public void setDividendMethod(String dividendMethod) {
        this.dividendMethod = dividendMethod;
    }

    public String getIntoFundCode() {
        return intoFundCode;
    }

    public void setIntoFundCode(String intoFundCode) {
        this.intoFundCode = intoFundCode;
    }

    public String getIntoFundName() {
        return intoFundName;
    }

    public void setIntoFundName(String intoFundName) {
        this.intoFundName = intoFundName;
    }

    public String getOutsideCancelHandleStatus() {
        return outsideCancelHandleStatus;
    }

    public void setOutsideCancelHandleStatus(String outsideCancelHandleStatus) {
        this.outsideCancelHandleStatus = outsideCancelHandleStatus;
    }

    public String getInstructionStatus() {
        return instructionStatus;
    }

    public void setInstructionStatus(String instructionStatus) {
        this.instructionStatus = instructionStatus;
    }

    public String getInstructionIssuer() {
        return instructionIssuer;
    }

    public void setInstructionIssuer(String instructionIssuer) {
        this.instructionIssuer = instructionIssuer;
    }

    public String getIssuerDate() {
        return issuerDate;
    }

    public void setIssuerDate(String issuerDate) {
        this.issuerDate = issuerDate;
    }

    public String getIssuerTime() {
        return issuerTime;
    }

    public void setIssuerTime(String issuerTime) {
        this.issuerTime = issuerTime;
    }

    public String getInstructionSerialNo() {
        return instructionSerialNo;
    }

    public void setInstructionSerialNo(String instructionSerialNo) {
        this.instructionSerialNo = instructionSerialNo;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getInstructionUpdateSerialNo() {
        return instructionUpdateSerialNo;
    }

    public void setInstructionUpdateSerialNo(String instructionUpdateSerialNo) {
        this.instructionUpdateSerialNo = instructionUpdateSerialNo;
    }

    public String getFundNo() {
        return fundNo;
    }

    public void setFundNo(String fundNo) {
        this.fundNo = fundNo;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSecuritiesCode() {
        return securitiesCode;
    }

    public void setSecuritiesCode(String securitiesCode) {
        this.securitiesCode = securitiesCode;
    }

    public String getSecuritiesName() {
        return securitiesName;
    }

    public void setSecuritiesName(String securitiesName) {
        this.securitiesName = securitiesName;
    }

    public String getIntoGroup() {
        return intoGroup;
    }

    public void setIntoGroup(String intoGroup) {
        this.intoGroup = intoGroup;
    }

    public String getFundManager() {
        return fundManager;
    }

    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }

    public Long getPositionNum() {
        return positionNum;
    }

    public void setPositionNum(Long positionNum) {
        this.positionNum = positionNum;
    }

    public Long getIntoSecuritiesPosition() {
        return intoSecuritiesPosition;
    }

    public void setIntoSecuritiesPosition(Long intoSecuritiesPosition) {
        this.intoSecuritiesPosition = intoSecuritiesPosition;
    }

    public String getApplyFaxNo() {
        return applyFaxNo;
    }

    public void setApplyFaxNo(String applyFaxNo) {
        this.applyFaxNo = applyFaxNo;
    }

    public Long getApplyFaxPrintTimes() {
        return applyFaxPrintTimes;
    }

    public void setApplyFaxPrintTimes(Long applyFaxPrintTimes) {
        this.applyFaxPrintTimes = applyFaxPrintTimes;
    }

    public String getCancelFaxNo() {
        return cancelFaxNo;
    }

    public void setCancelFaxNo(String cancelFaxNo) {
        this.cancelFaxNo = cancelFaxNo;
    }

    public Long getCancelFaxPrintTimes() {
        return cancelFaxPrintTimes;
    }

    public void setCancelFaxPrintTimes(Long cancelFaxPrintTimes) {
        this.cancelFaxPrintTimes = cancelFaxPrintTimes;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getRiskPlace() {
        return riskPlace;
    }

    public void setRiskPlace(String riskPlace) {
        this.riskPlace = riskPlace;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public String getIntoInvestmentType() {
        return intoInvestmentType;
    }

    public void setIntoInvestmentType(String intoInvestmentType) {
        this.intoInvestmentType = intoInvestmentType;
    }

    public String getInstructionOperationLevel() {
        return instructionOperationLevel;
    }

    public void setInstructionOperationLevel(String instructionOperationLevel) {
        this.instructionOperationLevel = instructionOperationLevel;
    }

    public String getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(String executeStatus) {
        this.executeStatus = executeStatus;
    }

    public String getTradeDescription() {
        return tradeDescription;
    }

    public void setTradeDescription(String tradeDescription) {
        this.tradeDescription = tradeDescription;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDistributeTime() {
        return distributeTime;
    }

    public void setDistributeTime(String distributeTime) {
        this.distributeTime = distributeTime;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getCancelErrorMessage() {
        return cancelErrorMessage;
    }

    public void setCancelErrorMessage(String cancelErrorMessage) {
        this.cancelErrorMessage = cancelErrorMessage;
    }

    public String getInstructionRemark() {
        return instructionRemark;
    }

    public void setInstructionRemark(String instructionRemark) {
        this.instructionRemark = instructionRemark;
    }

    public String getRic() {
        return ric;
    }

    public void setRic(String ric) {
        this.ric = ric;
    }

    public String getBloomberg() {
        return bloomberg;
    }

    public void setBloomberg(String bloomberg) {
        this.bloomberg = bloomberg;
    }

    public String getSedol() {
        return sedol;
    }

    public void setSedol(String sedol) {
        this.sedol = sedol;
    }

    public String getCusip() {
        return cusip;
    }

    public void setCusip(String cusip) {
        this.cusip = cusip;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    @Override
    public String toString() {
        return "ExcelDataRecord{" + "tradeChannel='" + tradeChannel + '\'' + ", productCode='" + productCode + '\'' + ", productName='" + productName + '\''
                + ", consignation='" + consignation + '\'' + ", fundCode='" + fundCode + '\'' + ", fundName='" + fundName + '\'' + ", instructionAmount="
                + instructionAmount + ", instructionNum=" + instructionNum + ", largeRedemptionFlag='" + largeRedemptionFlag + '\'' + ", dividendMethod='"
                + dividendMethod + '\'' + ", intoFundCode='" + intoFundCode + '\'' + ", intoFundName='" + intoFundName + '\'' + ", outsideCancelHandleStatus='"
                + outsideCancelHandleStatus + '\'' + ", instructionStatus='" + instructionStatus + '\'' + ", instructionIssuer='" + instructionIssuer + '\''
                + ", issuerDate='" + issuerDate + '\'' + ", issuerTime='" + issuerTime + '\'' + ", instructionSerialNo='" + instructionSerialNo + '\''
                + ", businessType='" + businessType + '\'' + ", distributorName='" + distributorName + '\'' + ", instructionUpdateSerialNo='"
                + instructionUpdateSerialNo + '\'' + ", fundNo='" + fundNo + '\'' + ", groupName='" + groupName + '\'' + ", securitiesCode='" + securitiesCode
                + '\'' + ", securitiesName='" + securitiesName + '\'' + ", intoGroup='" + intoGroup + '\'' + ", fundManager='" + fundManager + '\''
                + ", positionNum=" + positionNum + ", intoSecuritiesPosition=" + intoSecuritiesPosition + ", applyFaxNo='" + applyFaxNo + '\''
                + ", applyFaxPrintTimes=" + applyFaxPrintTimes + ", cancelFaxNo='" + cancelFaxNo + '\'' + ", cancelFaxPrintTimes=" + cancelFaxPrintTimes
                + ", tradeMarket='" + tradeMarket + '\'' + ", riskPlace='" + riskPlace + '\'' + ", investmentType='" + investmentType + '\''
                + ", intoInvestmentType='" + intoInvestmentType + '\'' + ", instructionOperationLevel='" + instructionOperationLevel + '\''
                + ", executeStatus='" + executeStatus + '\'' + ", tradeDescription='" + tradeDescription + '\'' + ", startTime='" + startTime + '\''
                + ", endTime='" + endTime + '\'' + ", distributeTime='" + distributeTime + '\'' + ", expireDate='" + expireDate + '\'' + ", orderStatus='"
                + orderStatus + '\'' + ", errorMessage='" + errorMessage + '\'' + ", cancelErrorMessage='" + cancelErrorMessage + '\'' + ", instructionRemark='"
                + instructionRemark + '\'' + ", ric='" + ric + '\'' + ", bloomberg='" + bloomberg + '\'' + ", sedol='" + sedol + '\'' + ", cusip='" + cusip
                + '\'' + ", isin='" + isin + '\'' + '}';
    }
}
