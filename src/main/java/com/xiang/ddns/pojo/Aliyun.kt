package com.xiang.ddns.pojo

import java.io.Serializable

/**
 * 阿里云DNS 云解析对象
 * @author xiang
 */
class Aliyun : Serializable {
    /** IPV4地址，当前电脑在公网的ip地址  */
    var ipV4: String? = null
    /** 主机记录，如果要解析@.exmaple.com，主机记录要填写"@”，而不是空  */
    var rr: String? = null
    /** 解析记录类型，参见解析记录类型格式  */
    var type: String? = null
    /** 解析记录的ID，此参数在添加解析时会返回，在获取域名解析列表时会返回  */
    var recordId: String? = null
    /** 生效时间，默认为600秒（10分钟），参见TTL定义说明。购买VIP可以升级为1秒生效。没必要1秒  */
    var ttl: Long? = null

    companion object {
        /** 序列号  */
        private const val serialVersionUID = 1L
    }
}