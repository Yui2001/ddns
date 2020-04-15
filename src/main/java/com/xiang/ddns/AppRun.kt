package com.xiang.ddns

import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 运行时入口
 * @author xiang
 */
object AppRun {
    @Throws(UnknownHostException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        while (true) {
            try {
                val date = Date()
                val sdf = SimpleDateFormat("MM-dd hh:mm:ss")
                val time = "[" + sdf.format(date) + "] "
                println(time + "开始ddns检查")
                val record = UpdateDomainRecord()
                record.analysisDns()
                Thread.sleep(60 * 1000.toLong())
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }
}