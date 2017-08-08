package com.wanggs.core.util;

/**
 * Created by Wgs on 2017/8/6.
 */
public class WeiXinPayUrl {
    /**
     * 微信预支付url
     */
    public static final String PRE_PAY_API = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 刷卡支付url
     */
    public static final String MICRO_PAY_API = "https://api.mch.weixin.qq.com/pay/micropay";

    /**
     * 支付查询url
     */
    public static final String PAY_QUERY_API = "https://api.mch.weixin.qq.com/pay/orderquery";

    /**
     * 退款url
     */
    public static final String REFUND_API = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    /**
     * 退款查询url
     */
    public static final String REFUND_QUERY_API = "https://api.mch.weixin.qq.com/pay/refundquery";

    /**
     * 撤销url
     */
    public static final String REVERSE_API = "https://api.mch.weixin.qq.com/secapi/pay/reverse";

    /**
     * 关闭订单url
     */
    public static final String CLOSE_ORDER_API = "https://api.mch.weixin.qq.com/pay/closeorder";

    /**
     * 下载对账单url
     */
    public static final String DOWNLOAD_BILL_API = "https://api.mch.weixin.qq.com/pay/downloadbill";

    /**
     * 统计上报url
     */
    public static final String REPORT_API = "https://api.mch.weixin.qq.com/payitil/report";

    private WeiXinPayUrl() {
    }
}
