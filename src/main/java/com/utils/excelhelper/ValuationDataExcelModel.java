/**
 *Copyright (c) 2017, ShangHai HOWBUY INVESTMENT MANAGEMENT Co., Ltd.
 *All right reserved.
 *
 *THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF HOWBUY INVESTMENT
 *MANAGEMENT CO., LTD.  THE CONTENTS OF THIS FILE MAY NOT BE DISCLOSED
 *TO THIRD PARTIES, COPIED OR DUPLICATED IN ANY FORM, IN WHOLE OR IN PART,
 *WITHOUT THE PRIOR WRITTEN PERMISSION OF HOWBUY INVESTMENT MANAGEMENT
 * CO., LTD.
*/

package com.utils.excelhelper;

import com.utils.StringUtil;
import com.utils.excelhelper.annotation.ExcelField;

/**
 * @description:(TODO 请在此添加描述)
 * @reason:TODO ADD REASON(可选)
 * @author 危常焕
 * @date 2017年5月3日 上午11:14:30
 * @since JDK 1.6
 */
public class ValuationDataExcelModel {
    /** 日期 */
    @ExcelField(name = "日期")
    private String tradeDate;
    // /** 基金代码 */
    // @ExcelField(name = "基金代码")
    // private String transactionAccountId;
    /** 投管产品代码 -新华 **/
    @ExcelField(name = "基金代码")
    private String investProductCode;
    /** 基金名称 */
    @ExcelField(name = "基金名称")
    private String investorName;
    /** 资产单元编号 */
    @ExcelField(name = "资产单元编号", defaultValue = " ")
    private String assetUnitId = " ";
    /** 资产单元名称 */
    @ExcelField(name = "资产单元名称", defaultValue = " ")
    private String assetUnitName = "";
    /** 投资类新 */
    @ExcelField(name = "投资类型", defaultValue = " ")
    private String investType;
    /** 证券代码 */
    @ExcelField(name = "证券代码")
    private String fundCode;
    /** 证券名称 */
    @ExcelField(name = "证券名称")
    private String fundNameAbbr;
    /** 杂项分类 */
    @ExcelField(name = "杂项分类")
    private String fundType;
    /** 执行确认号 */
    @ExcelField(name = "执行确认号")
    private String appSheetSerialNo;
    /** 业务类别 */
    @ExcelField(name = "业务类别")
    private String businessCode;
    /** 转入证券代码 */
    @ExcelField(name = "转入证券代码")
    private String codeOfTargetFund;
    /** 转入证券名称 */
    @ExcelField(name = "转入证券名称")
    private String targetFundNameAbbr;
    /** 金额 */
    @ExcelField(name = "金额")
    private String amount = "0";
    /** 数量 */
    @ExcelField(name = "数量")
    private String confirmedVol = "0";
    /** 费用 */
    @ExcelField(name = "费用")
    private String charge = "0";
    /** 清算金额 */
    @ExcelField(name = "清算金额")
    private String settleAmount = "0";
    /** 万份收益计提方式 **/
    @ExcelField(name = "万份收益计提方式", defaultValue = " ")
    private String calWay;

    private String formatString(String s) {
        if (StringUtil.isEmpty(s)) {
            return "";
        }
        if (8 <= s.length()) {
            String s1 = s.substring(0, 4);
            String s2 = s.substring(4, 6);
            String s3 = s.substring(6, 8);
            return s1 + "/" + s2 + "/" + s3;
        } else {
            return "";
        }
    }

    public String getTradeDate() {
        return formatString(tradeDate);
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    // public String getTransactionAccountId() {
    // return "'" + transactionAccountId;
    // }

    public String getInvestProductCode() {
        return "'" + investProductCode;
    }

    public void setInvestProductCode(String investProductCode) {
        this.investProductCode = investProductCode;
    }

    // public void setTransactionAccountId(String transactionAccountId) {
    // this.transactionAccountId = transactionAccountId;
    // }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getAssetUnitId() {
        return assetUnitId;
    }

    public void setAssetUnitId(String assetUnitId) {
        this.assetUnitId = assetUnitId;
    }

    public String getAssetUnitName() {
        return assetUnitName;
    }

    public void setAssetUnitName(String assetUnitName) {
        this.assetUnitName = assetUnitName;
    }

    public String getInvestType() {
        return investType;
    }

    public void setInvestType(String investType) {
        this.investType = investType;
    }

    public String getFundCode() {
        return "'" + fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundNameAbbr() {
        return fundNameAbbr;
    }

    public void setFundNameAbbr(String fundNameAbbr) {
        this.fundNameAbbr = fundNameAbbr;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getAppSheetSerialNo() {
        if (null == appSheetSerialNo) {
            return "";
        }
        return "'" + appSheetSerialNo;
    }

    public void setAppSheetSerialNo(String appSheetSerialNo) {
        this.appSheetSerialNo = appSheetSerialNo;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getCodeOfTargetFund() {
        if (null == codeOfTargetFund) {
            return "";
        }
        return "'" + codeOfTargetFund;
    }

    public void setCodeOfTargetFund(String codeOfTargetFund) {
        this.codeOfTargetFund = codeOfTargetFund;
    }

    public String getTargetFundNameAbbr() {
        return targetFundNameAbbr;
    }

    public void setTargetFundNameAbbr(String targetFundNameAbbr) {
        this.targetFundNameAbbr = targetFundNameAbbr;
    }

    public String getAmount() {
        if (null == amount) {
            return "0";
        }
        return "'" + amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getConfirmedVol() {
        return "'" + confirmedVol;
    }

    public void setConfirmedVol(String confirmedVol) {
        this.confirmedVol = confirmedVol;
    }

    public String getCharge() {
        return "'" + charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(String settleAmount) {
        this.settleAmount = settleAmount;
    }

    public String getCalWay() {
        return calWay;
    }

    public void setCalWay(String calWay) {
        this.calWay = calWay;
    }

}
