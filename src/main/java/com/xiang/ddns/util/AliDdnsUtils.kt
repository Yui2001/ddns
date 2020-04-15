package com.xiang.ddns.util

import com.aliyuncs.DefaultAcsClient
import com.aliyuncs.IAcsClient
import com.aliyuncs.alidns.model.v20150109.DescribeDomainRecordsRequest
import com.aliyuncs.alidns.model.v20150109.DescribeDomainsRequest
import com.aliyuncs.exceptions.ClientException
import com.aliyuncs.http.FormatType
import com.aliyuncs.http.MethodType
import com.aliyuncs.http.ProtocolType
import com.aliyuncs.profile.DefaultProfile
import com.aliyuncs.profile.IClientProfile

/**
 * 阿里云解析工具
 *
 * @author xiang
 */
object AliDdnsUtils {
    /**
     * 获取客户端数据
     *
     * @return
     */
    var client: IAcsClient? = null

    /**
     * 获取一次inter连接，修改DNS
     */
    fun getRequest(actionName: String?): DescribeDomainsRequest {
        val request = DescribeDomainsRequest()
        request.protocol = ProtocolType.HTTPS // 指定访问协议
        request.acceptFormat = FormatType.JSON // 指定api返回格式
        request.method = MethodType.POST // 指定请求方法
        // 设置请求action
        request.actionName = actionName
        return request
    }

    /**
     * 获取一次inter连接,查询dns
     */
    fun getRequestQuery(actionName: String?): DescribeDomainRecordsRequest {
        val request = DescribeDomainRecordsRequest()
        request.protocol = ProtocolType.HTTPS // 指定访问协议
        request.acceptFormat = FormatType.JSON // 指定api返回格式
        request.method = MethodType.POST // 指定请求方法
        // 设置请求action
        request.actionName = actionName
        return request
    }

    init {
        val regionId = "cn-hangzhou" // 必填固定值，必须为“cn-hanghou”
        val accessKeyId = PropertiesUtil.getProperty("AccessKeyID") // your
        // accessKey
        val accessKeySecret = PropertiesUtil.getProperty("AccessKeySecret") // your
        // accessSecret
        val profile: IClientProfile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret)
        // 若报Can not find endpoint to access异常，请添加以下此行代码
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns", "alidns.aliyuncs.com")
        } catch (e: ClientException) {
            e.printStackTrace()
        }
        client = DefaultAcsClient(profile)
    }
}