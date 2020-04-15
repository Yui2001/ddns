package com.xiang.ddns

import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsResponse
import com.xiang.ddns.pojo.Aliyun
import com.xiang.ddns.util.AliDdnsUtils
import com.xiang.ddns.util.LocalPublicIpv4
import com.xiang.ddns.util.PropertiesUtil
import java.net.UnknownHostException

/**
 * 调用阿里api,更新DNS域名解析
 *
 * @author xiang
 */
class UpdateDomainRecord {
    /**
     * 设置域名参数
     *
     * @param request
     */
    fun setParam(request: DescribeDomainRecordsRequest) {
        val domainName = PropertiesUtil.getProperty("DomainName")
        request.putQueryParameter("DomainName", domainName)
    }

    /**
     * 解析DNS信息
     */
    @Throws(UnknownHostException::class)
    fun analysisDns() { // 获取公网ip
        val ip = LocalPublicIpv4()
        val ipV4 = ip.publicip()
        // 获取解析的数据
        val actionName = "DescribeDomainRecords"
        val response: DescribeDomainRecordsResponse
        // 获取request
        val request = AliDdnsUtils.getRequestQuery(actionName)
        // 设置request参数
        setParam(request)
        try {
            response = AliDdnsUtils.client!!.getAcsResponse(request)
            // 声明解析对象
            val demo = DemoListDomains()
            // 获取阿里云的数据
            val list = response.domainRecords
            if (list == null || list.isEmpty()) {
                return
            }
            //更新ip
            val record = list[0]
            val yun = Aliyun()
            // 进行判定记录是否需要更新
            if (record.value == ipV4) { // 不需要更新，继续下次循环
                println("当前域名解析地址为：" + ipV4 + "不需要更新！")
            } else {
                println("更新域名：" + record.domainName)
                // 进行替换关键数据
                yun.ipV4 = ipV4
                yun.recordId = record.recordId
                yun.rr = record.rr
                yun.ttl = record.ttl
                yun.type = record.type
                println("域名更换ip开始")
                demo.analysisDns(yun)
                println("域名更换ip结束")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            println("域名更换异常")
        }
    }
}