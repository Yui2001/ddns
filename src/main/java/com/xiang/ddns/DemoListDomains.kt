package com.xiang.ddns

import com.aliyuncs.alidns.model.v20150109.DescribeDomainsRequest
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsResponse
import com.xiang.ddns.pojo.Aliyun
import com.xiang.ddns.util.AliDdnsUtils

/**
 * 阿里sdk
 *
 * @author xiang
 */
class DemoListDomains {
    /**
     * 设置参数
     *
     * @param request
     */
    fun setParam(request: DescribeDomainsRequest?, yun: Aliyun) { // 设置参数
        request!!.putQueryParameter("RecordId", yun.recordId)
        request.putQueryParameter("RR", yun.rr)
        request.putQueryParameter("Type", yun.type)
        request.putQueryParameter("Value", yun.ipV4)
        request.putQueryParameter("TTL", yun.ttl)
    }

    /**
     * 解析DNS信息
     */
    fun analysisDns(yun: Aliyun) {
        val actionName = "UpdateDomainRecord"
        val request = AliDdnsUtils.getRequest(actionName)
        val response: DescribeDomainsResponse
        setParam(request, yun)
        try {
            response = AliDdnsUtils.client!!.getAcsResponse(request)
            val list = response.domains
            for (domain in list) {
                println(domain.domainName)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}