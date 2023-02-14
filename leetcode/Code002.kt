package leetcode

import kotlin.math.sin

/**
 *
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * 函数 myAtoi(string s) 的算法如下：
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−2`31,  2`31 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2`31 的整数应该被固定为 −2`31 ，大于 2`31 − 1 的整数应该被固定为 2`31 − 1 。
 * 返回整数作为最终结果。
 * 注意：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 */
class Code002 {

    fun myAtoi(s: String): Int {
        var firstIndex: Int =
            s.indexOfFirst { it ->
                it.toString().matches("-?\\d+(\\.\\d+)?".toRegex())
            }//用正则表达式判断第一个数字的下标
//        println("==============fistIndex = $firstIndex")
        //第一数字之前的如果是非空格字符，则返回0
        var start = if (firstIndex - 1 < 0) 0 else firstIndex - 1
//        var sign =
//            if (s.length > 0 && s.substring(start, start + 1)
//                    .equals("-")
//            ) true else false//true 是负数，false 是正数
        var endIndex = 0
        var sign = true

        when {
            s.length > 0 && s.substring(start, start + 1).equals("-") -> {
                sign = true
                endIndex = if (firstIndex - 1 < 0) 0 else firstIndex - 1
            }
            s.length > 0 && s.substring(start, start + 1).equals("+") -> {
                sign = false
                endIndex = if (firstIndex - 1 < 0) 0 else firstIndex - 1
            }
            else -> {
                sign = false
                endIndex = if (firstIndex < 0) 0 else firstIndex
            }
        }


//        //如果第一个数字之前存在a-z,A-Z,则返回0
//        var endIndex = if (sign) {
//            if (firstIndex - 1 < 0) 0 else firstIndex - 1
//        } else {
//            if (firstIndex < 0) 0 else firstIndex
//        } //负数要额为排除掉- 号


        var string = s.substring(0, endIndex)

        if (string.contains(".*[a-z]|[A-Z]|[-]|[+]|[.]+.*".toRegex())) return 0
        when {
            firstIndex < 0 -> return 0
            firstIndex == 0 && s.length == 1 -> return s.toInt()
        }

        var end = firstIndex + 1//从量子字符的字符串开始判断，因为第一个已经确认为数字了
        var num = s.substring(firstIndex, firstIndex + 1)
        var continueWhile = true
        do {
            var strNum = num
            when {
                s.substring(firstIndex, end).matches("-?\\d+(\\.\\d+)?".toRegex()) -> strNum =
                    s.substring(firstIndex, end)
                else -> {
                    strNum = num
                    continueWhile = false
                }
            }
            num = strNum //每次更新
            if (num.startsWith("0")) firstIndex++
            end++
        } while (continueWhile && end < s.length + 1)


        when {
            sign && num.length > 11 -> return Int.MIN_VALUE
            !sign && num.length > 10 -> return Int.MAX_VALUE
        }
        var result = if (sign) -num.toLong() else num.toLong()
        when {
            result <= Int.MIN_VALUE -> return Int.MIN_VALUE
            result >= Int.MAX_VALUE -> return Int.MAX_VALUE
            else -> return result.toInt()
        }
    }

    //明显第一种方法问题很大，需要重新想想优化一下
    fun myAtoi2(s: String): Int {
        if (s.equals("") || s.equals(".")) return 0
        var startIndex = 0
        var endIndex = s.length

        var start = 0
        var end = s.length
        while (start < end) {
            var strStart = s.substring(start, start + 1)
            var strEnd = s.substring(end - 1, end)
            println("strStart = $strStart ||  strEnd = $strEnd")
            //前面的
            when {
                //找到的是数字
                strStart.equals("-?\\d+(\\.\\d+)?".toRegex()) -> {
                    start = startIndex
                }
                //数字以外的东西
                else -> {
                    if (start < startIndex) {
                        end = startIndex
                        break
                    }
                }
            }

            //后面的
            when {
                //找到的是数字
                strEnd.equals("-?\\d+(\\.\\d+)?".toRegex()) -> {
                    end = endIndex
                }
                //数字以外的东西
                else -> {
                    end = endIndex - 1
                }
            }

            startIndex++
            endIndex--
        }


        var num: String = s.substring(start, end)
        var i = if (start - 1 < 0) 0 else start - 1
        var result = when (s.substring(i, start)) {
            "-" -> -num.toLong()
            "+" -> num.toLong()
            else -> 0
        }

        when {
            result <= Int.MIN_VALUE -> return Int.MIN_VALUE
            result >= Int.MAX_VALUE -> return Int.MAX_VALUE
            else -> return result.toInt()
        }
    }
}


fun main(args: Array<String>) {
    println(Code002().myAtoi2("ssfa452179hahoe"))
    println("----------")
    println(Code002().myAtoi2("ssfa-343434343434hahoe"))
    println("----------")
    println(Code002().myAtoi2("-343434343434hahoe"))
    println("----------")
    println(Code002().myAtoi2("43434343434hahoe"))
    println("----------")
    println(Code002().myAtoi2("434343sfad43434hahoe"))
    println("----------")
//    println(Code002().myAtoi2("words and 987"))
//    println(Code002().myAtoi2("drewqeqwe333sdasd"))
//    println(Code002().myAtoi2("a-333sdasd"))
//    println(Code002().myAtoi2("--333sdasd"))
//    println(Code002().myAtoi2("+-333sdasd"))
//    println(Code002().myAtoi2("  333sdasd"))
//    println(Code002().myAtoi2(" -333sdasd"))
//    println(Code002().myAtoi2(" -42"))
//    println(Code002().myAtoi2(" .-42"))
//    println(Code002().myAtoi2("."))
//    println(Code002().myAtoi2("1"))
//    println(Code002().myAtoi2("-2"))
//    println(Code002().myAtoi2("+2"))
//    println(Code002().myAtoi2(""))
//    println(Code002().myAtoi2("20000000000000000000"))
//    println(Code002().myAtoi2("0000000000012345678"))
}