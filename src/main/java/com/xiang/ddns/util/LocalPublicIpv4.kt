package com.xiang.ddns.util

import org.jsoup.Jsoup
import java.io.IOException
import java.net.InetAddress
import java.net.UnknownHostException

/**
 * 获取公网ip
 * @author xiang
 */
class LocalPublicIpv4 {
    @Throws(UnknownHostException::class)
    fun publicip(): String {
        try { // 打开连接
            val doc = Jsoup.connect("http://chaipip.com/").get()
            val eles = doc.select("#ip")
            return eles.attr("value")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return InetAddress.getLocalHost().hostAddress
    }

    companion object {
        @Throws(UnknownHostException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val ip = LocalPublicIpv4()
            println(ip.publicip())
        }
    }
}