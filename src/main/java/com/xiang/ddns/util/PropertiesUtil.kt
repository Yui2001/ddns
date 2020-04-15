package com.xiang.ddns.util

import java.io.IOException
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*

/**
 * properties 文件读取
 *
 * @author xiang
 */
object PropertiesUtil {
    //指定配置文件地址resource目录下:
    var prop = Properties()

    fun getProperty(key: String?): String? {
        try {
            return String(prop.getProperty(key).toByteArray(charset("ISO-8859-1")), Charset.forName("gbk"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return null
    }

    fun getProperty(key: String?, value: String?): String {
        return getProperty(key, value)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(getProperty("AccessKeyID"))
    }

    init {
        val `is` = PropertiesUtil::class.java.classLoader.getResourceAsStream("ddns.properties")
        try {
            prop.load(`is`)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}