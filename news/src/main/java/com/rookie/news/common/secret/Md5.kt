package com.rookie.news.common.secret

import java.security.MessageDigest


/**
 * Author: FK
 * Date：  2018/5/25.
 */
class Md5 {
    companion object {
        private val hexDigits = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f")

        fun encode(param: String): String {
            val md5 = MessageDigest.getInstance("MD5")
            return byteArrayToHexString(md5.digest(param.toByteArray()))
        }

        private fun byteArrayToHexString(b: ByteArray): String {
            val resultSb = StringBuilder()
            for (aB in b) resultSb.append(byteToHexString(aB))

            return resultSb.toString()
        }

        private fun byteToHexString(b: Byte): String {
            var n = b.toInt()
            if (n < 0)
                n += 256
            val d1 = n / 16
            val d2 = n % 16
            return hexDigits[d1] + hexDigits[d2]
        }
    }
}