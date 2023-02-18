package leetcode

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

'.' 匹配任意单个字符
'*' 匹配零个或多个前面的那一个元素

所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/regular-expression-matching
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Code004 {

    //第一种，直接上正则表达式
    fun isMatch1(s: String, p: String): Boolean {
        return s.matches(p.toRegex())
    }

    fun isMatch2(s: String, p: String): Boolean {
        var stf = StringBuffer(s)
        var stf2 = StringBuffer(p)
        return when {
            p == ".*" -> true
            p.contains("*") -> {
                var indexP = 0
                var indexS = 0
                var lastStartMatch = false
                while (indexP < stf2.length && indexS < stf.length) {
                    var stringS = stf.substring(indexS, indexS + 1)
                    var stringP = stf2.substring(indexP, indexP + 1)
                    var end = if (indexP + 2 <= stf2.length) indexP + 2 else indexP + 1

                    if (stf2.substring(indexP, end).contains("*")) {
                        if (stringS == stringP) {
                            indexS++
                            lastStartMatch = false
                        } else {
                            if (indexS > 0 && lastStartMatch) return false
                            indexP += 2
                            lastStartMatch = true
                        }
                    } else {
                        if (stringP == ".") {
                            indexS++
                            indexP++
                            lastStartMatch = true
                        } else if (stringS == stringP) {
                            indexS++
                            indexP++
                            lastStartMatch = true
                        } else return false
                    }
                }
                if (indexP == p.length && indexS < s.length - 1) return false
                return true
            }
            p.contains(".") && !p.contains("*") -> {
                var indexP = 0
                var indexS = 0
                while (indexP < stf2.length && indexS < stf.length) {
                    if (indexP == p.length - 1 && indexS < s.length - 1) return false
                    if (indexS == s.length - 1 && indexP < p.length - 1) return false

                    var stringS = stf.substring(indexS, indexS + 1)
                    var stringP = stf2.substring(indexP, indexP + 1)
                    if (stringP == ".") {
                        indexS++
                        indexP++
                    } else if (stringS == stringP) {
                        indexS++
                        indexP++
                    } else return false

                }
                return true
            }
            else -> s == p
        }
    }

    /**
     * 不行 上面那个方法，写的我自己都已经想不清楚了 重新向重新写
     */
    fun isMatch3(s: String, p: String): Boolean {
        var stfS = StringBuffer(s)
        var stfP = StringBuffer(p)
        var s = p.replace("*", "")
        var difference = if (stfS.length - s.length > 0) stfS.length - s.length else 0//获取差值
        when {
            //含有 '.' 或者 '*' 需要处理
            p.equals(".*") -> return true
            p.contains("*") || p.contains(".") -> {
                var startS = 0
                var startP = 0
                while (true) {
                    var strings = stfS.substring(startS, startS + 1)
                    var stringP = stfP.substring(startP, startP + 1)

                    var endP = if (startP + 2 > p.length) startP + 1 else startP + 2
                    var string = stfP.substring(startP, endP)
                    if (string.contains("*")) {
                        if ((stringP.equals(".") || strings == stringP) && difference > 0) {
                            difference--
                            startS++
                        } else {
                            startP += 2
                        }
                    } else {
                        if (stringP.equals(".") || strings == stringP) {
                            startS++
                            startP++
                        } else return false
                    }


                    if (startS == stfS.length && startP == stfP.length ||
                        startS == stfS.length && startP == stfP.length - 2
                    ) return true
                    if (startS > stfS.length - 1 || startP > stfP.length - 1) return false
                }
            }
            else -> return s == p
        }

        return s.matches(p.toRegex())
    }

    /***
     * 写着写着 人又傻了，写的太复杂了 ，不行重新想  rua圾
     *
     * 这次的想法是把每个单词的数量统计一下，合并相同的，按顺序比较字母和数量  字母相同且p的数量大于等于s那说明能匹配   我觉得我得想法很好，就是代码能力有点弱啊
     */
    fun isMatch(s: String, p: String): Boolean {
        var stfS = StringBuffer(s)
        var stfP = StringBuffer(p)
        var listMap = ArrayList<HashMap<String, Int>>()
        var mapString = HashMap<String, Int>()
        when {
            //含有 '.' 或者 '*' 需要处理
            p == ".*" -> return true
            p.contains("*") -> {
                var sLen = stfS.length
                var pLen = stfP.length

                var index = 0
                var lastP = ""
                while (index < stfP.length) {
                    var pString = stfP.substring(index, index + 1)
                    var end = if (index + 2 >= stfP.length) stfP.length else index + 2
                    var string = stfP.substring(index, end)
                    var mapStringS = HashMap<String, Int>()
                    if (string.contains("*")) {
                        for (j in 0 until sLen) {
                            var sString = stfS.substring(j, j + 1)
                            if (mapStringS.contains(sString)) {
                                var num = mapStringS.get(sString)
                                num = num!! + 1
                                mapStringS.put(sString, num)
                            } else mapStringS.put(sString, 1)

                            if (pString == "." || sString == pString) {
                                if (mapString.contains(sString)) {
                                    var num = mapString.get(sString)
                                    num = num!! + 1
                                    mapString.put(sString, num)
                                } else mapString.put(sString, 1)
                            }
                        }
                        index += 2
                    } else {
                        mapString.put(pString, 1)
                        index++
                    }
                    if (listMap.size > 0) {
                        var lastMap = listMap.get(listMap.size - 1)
                        var num = lastMap.get(lastP)
                        if (num == null) num = 0
                        if (lastP == pString && !pString.equals(".")) num =
                            num?.plus(mapString.get(lastP)!!)
                        if (num != null) {
                            mapString.put(lastP, num)
                        }
                    }
                    listMap.add(mapString)
                    lastP = pString
                }



                println("listMap = ${listMap.toString()} listMapS = ${mapString.toString()}")

//                for (i in 0 until listMap.size) {
//                    var keys = listMapS.get(i).keys
//                    var keyp = listMap.get(i).keys
//                    var ss = listMapS.get(i).get(keys.first())
//                    var sp = listMap.get(i).get(keyp.first())
//
//                    return (keys != keyp) || ss!! > sp!!
//                }
                return true

            }
            else -> return s == p
        }
    }

}

fun main(args: Array<String>) {
    var code = Code004()
//    println("-----------------")
//    println("${code.isMatch("abc",".")}")
//    println("-----------------")
//    println("${code.isMatch("abc","c*")}")
//    println("-----------------")
//    println("${code.isMatch("aa","a*")}")
//    println("-----------------")
//    println("${code.isMatch("abc","")}")
//    println("-----------------")
//    println("${code.isMatch("abc","e*f*c")}")
//    println("-----------------")
//    println("${code.isMatch("abc","e*a*c")}")
//    println("${code.isMatch("aab", "c*a*b")}")//"aab" "c*a*b"
//    println("-----------------")
//    println("${code.isMatch("aa", "a*")}")//"aab" "c*a*b"
//    println("-----------------")
//    println(
//        "${
//            code.isMatch(
//                "mississippi",
//                "mis*is*ip*."
//            )
//        }"
//    )//"mi ss i ss i pp i" "mi s* i s* i p* ."
//    println(
//        "${
//            code.isMatch(
//                "mississippi",
//                "mis*is*p*."
//            )
//        }"
//    )//"mi ss i ss i pp i"   "mi s* i s*  p* ."
//////    println("-----------------")
//    println("${code.isMatch("aaa", "a.a")}")//"aaa" "a.a"
////    println("-----------------")
//    println("${code.isMatch("aa", "a*")}")//"aaa" "a.a"
//    println("${code.isMatch("aa", ".")}")//"aaa" "a.a"
//    println("${code.isMatch("abcd", "d*")}")//"aaa" "a.a"
    println("${code.isMatch("ab", ".*c")}")//"aaa" "a.a"


}