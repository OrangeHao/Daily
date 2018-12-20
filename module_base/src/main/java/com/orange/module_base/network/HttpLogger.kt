package com.orange.module_base.network

import android.util.Log

import com.orhanobut.logger.Logger

import okhttp3.logging.HttpLoggingInterceptor

/**
 * created by czh on 2018/5/10
 */
class HttpLogger : HttpLoggingInterceptor.Logger {

    private val mMessage = StringBuilder()

    override fun log(message: String) {
        var message = message
        // 请求或者响应开始
        if (message.startsWith("--> POST") || message.startsWith("--> GET")) {
            mMessage.setLength(0)
        }
        // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
        if (message.startsWith("{") && message.endsWith("}") || message.startsWith("[") && message.endsWith("]")) {
            message = formatJson(decodeUnicode(message))
        }
        mMessage.append(message + "\n")
        // 响应结束，打印整条日志
        if (message.startsWith("<-- END HTTP")) {
            Logger.t(TAG).d(mMessage.toString())
        }
    }

    companion object {
        private val TAG = "OKHttp-----"


        /**
         * 格式化json字符串
         *
         * @param jsonStr 需要格式化的json串
         * @return 格式化后的json串
         */
        fun formatJson(jsonStr: String?): String {
            if (null == jsonStr || "" == jsonStr) {
                return ""
            }
            val sb = StringBuilder()
            var last = '\u0000'
            var current = '\u0000'
            var indent = 0
            for (i in 0 until jsonStr.length) {
                last = current
                current = jsonStr[i]
                //遇到{ [换行，且下一行缩进
                when (current) {
                    '{', '[' -> {
                        sb.append(current)
                        sb.append('\n')
                        indent++
                        addIndentBlank(sb, indent)
                    }
                    //遇到} ]换行，当前行缩进
                    '}', ']' -> {
                        sb.append('\n')
                        indent--
                        addIndentBlank(sb, indent)
                        sb.append(current)
                    }
                    //遇到,换行
                    ',' -> {
                        sb.append(current)
                        if (last != '\\') {
                            sb.append('\n')
                            addIndentBlank(sb, indent)
                        }
                    }
                    else -> sb.append(current)
                }
            }
            return sb.toString()
        }

        /**
         * 添加space
         *
         * @param sb
         * @param indent
         */
        private fun addIndentBlank(sb: StringBuilder, indent: Int) {
            for (i in 0 until indent) {
                sb.append('\t')
            }
        }


        /**
         * http 请求数据返回 json 中中文字符为 unicode 编码转汉字转码
         *
         * @param theString
         * @return 转化后的结果.
         */
        fun decodeUnicode(theString: String): String {
            var aChar: Char
            val len = theString.length
            val outBuffer = StringBuffer(len)
            var x = 0
            while (x < len) {
                aChar = theString[x++]
                if (aChar == '\\') {
                    aChar = theString[x++]
                    if (aChar == 'u') {
                        var value = 0
                        for (i in 0..3) {
                            aChar = theString[x++]
                            when (aChar) {
                                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> value = (value shl 4) + aChar.toInt() - '0'.toInt()
                                'a', 'b', 'c', 'd', 'e', 'f' -> value = (value shl 4) + 10 + aChar.toInt() - 'a'.toInt()
                                'A', 'B', 'C', 'D', 'E', 'F' -> value = (value shl 4) + 10 + aChar.toInt() - 'A'.toInt()
                                else -> throw IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.")
                            }

                        }
                        outBuffer.append(value.toChar())
                    } else {
                        if (aChar == 't') {
                            aChar = '\t'
                        } else if (aChar == 'r') {
                            aChar = '\r'
                        } else if (aChar == 'n') {
                            aChar = '\n'
                        }
                        outBuffer.append(aChar)
                    }
                } else {
                    outBuffer.append(aChar)
                }
            }
            return outBuffer.toString()
        }
    }

}



