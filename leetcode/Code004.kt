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
    fun isMatch4(s: String, p: String): Boolean {
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

    /**
     * 新的算法  通过测试用例：331 / 354  最后几道测试用例了，但是肯定是过不了了， '.'完全处理不了，算法思路有问题.无法判断带‘。*’应该匹配多少个字符  感觉必须使用迭代，穷举出来，不然没办法比对
     * 不行，写道最后卡住了  ‘。’ 处理不了啊
     */
    fun isMatch(s: String, p: String): Boolean {

        when {
            p == ".*" -> return true
            !p.contains("*") && !p.contains(".") -> return s == p
            else -> {
                //合并s中相同的字母
                var listS = ArrayList<ObjectS>()
                var listP = ArrayList<ObjectP>()

                var lastS = ""
                for (i in s.indices) {
                    var objectS: ObjectS? = null
                    objectS = if (lastS == s[i].toString()) listS.last()
                    else ObjectS()
                    objectS.s = s[i].toString()
                    objectS.num += 1

                    if (lastS != s[i].toString()) listS.add(objectS)
                    lastS = s[i].toString()
                }

                var lastP = ""
                var lastPHasStart = false
                for (i in p.indices) {
                    //如果上个字符含有✳号 需要跳过一次
                    if (lastPHasStart) {
                        lastPHasStart = false
                        continue
                    }
                    var objectP: ObjectP? = null
                    objectP = if (lastP == p[i].toString()) listP.last()
                    else ObjectP()

                    var stringP = ""
                    if (i + 2 <= p.length) stringP = p.substring(i, i + 2)
                    objectP.p = p[i].toString()
                    if (stringP.contains("*")) {
                        objectP.minNum += 0
                        objectP.hasStar = true
                        lastPHasStart = true
                    } else objectP.minNum += 1

                    if (lastP != p[i].toString()) listP.add(objectP)
                    lastP = p[i].toString()
                }


                //开始比较

                for (i in listS.indices) {


                }

                var startS = 0
                var startP = 0


                var num = 0
                for (i in listP.indices) {
                    num += listP.get(i).minNum
                }

                if (s.length < num) return false//除开*号字符，多比s字符多，不符合

                var lastLen = s.length - num //这是*号字符能补位的最大个数，每次补位后需要--

//                println("listS = ${listS.toString()} , listP = ${listP.toString()}")
                //aaaa  *aa.

                while (true) {
                    var objectS = listS[startS]
                    var objectP = listP[startP]

//                    println("s = ${objectS.s} , p = ${objectP.p} , num = $num")
                    //不含*字符都不一样 肯定有问题 直接return
                    if (!objectP.hasStar) {
                        if (objectS.s != objectP.p && objectP.p != ".") return false
                    } else {
                        if (objectS.s != objectP.p && objectP.p != ".") {
                            startP++
                            //P走完了
                            if (startP == listP.size)
                                return startS == listS.size && listS.last().num == 0//S也需要走完，且字符数量已经消除完毕
                            else
                                continue
                        }
                    }


                    //如果*号字符则不需要判断数量了，只需要lastLen减去差值
                    if (objectP.hasStar) {
                        if (objectS.num > objectP.minNum) {
                            objectS.num -= objectP.minNum
                            if (objectS.num > 0) {
                                if (objectS.num - lastLen >= 0) {
                                    objectS.num -= lastLen
                                    lastLen = 0
                                } else {
                                    lastLen -= objectS.num
                                    objectS.num = 0
                                }
                            }
                            objectP.minNum = 0
                        } else {
                            objectP.minNum -= objectS.num
                            objectS.num = 0
                        }
                    } else {
                        if (objectS.num >= objectP.minNum) {
                            objectS.num -= objectP.minNum
                            objectP.minNum = 0
                        } else {
                            objectP.minNum -= objectS.num
                            objectS.num = 0
                        }
                    }

                    if (objectS.num == 0) startS++
                    else if (objectS.num < 0) return false

                    if (objectP.hasStar) {
                        if (objectP.minNum == 0 && lastLen == 0) startP++
                    } else if (objectP.minNum == 0) startP++


                    //S循环走完了
                    if (startS == listS.size) {
                        if (startP == listP.size) return true
                        else {
                            //剩下的如果都是 * 号字符那么匹配
                            for (i in startP until listP.size) {
                                if (listP[i].minNum == 0) continue
                                else return false//否则不匹配
                            }
                            return true
                        }
                    }

                    //P走完了
                    if (startP == listP.size) {
                        return startS == listS.size && listS.last().num == 0//S也需要走完，且字符数量已经消除完毕
                    }
                }
            }
        }
    }

    class ObjectP() {
        var p = ""//字符
        var minNum = 0//不含*的数量
        var hasStar = false //是否含星号
    }

    class ObjectS {
        var s = ""
        var num = 0
    }


    /**
     * 新算法，操，真的难啊:
     * 如果优先比对不带*号的，然后截取剩下的再做比对
     */
    fun isMatch5(s: String, p: String): Boolean {
        return true
    }

    /**
     * 回溯算法
     * 写的稀烂
     * 哈哈哈哈 我以为我了解了回溯算法和动态规划就无敌了，做题了才发现我还是个弟弟
     */
    var matchable = false
    var matchLen = 0
    fun backTrack(i: Int, j: Int, s: String, p: String) {
        if (i >= s.length && j >= p.length) {
            if (i >= s.length - 1 && j >= p.length - 1 && matchLen == s.length) matchable = true
            return
        }

//        println("i = $i , s[i] = ${s[i].toString()} ,j = $j , p[j] = ${p[j].toString()} , matchLen = $matchLen")

        if (i > s.length - 1) {
            backTrack(s.length - 1, j + 2, s, p)
        } else if (j > p.length - 1) {
            if (p[p.length - 1].toString() == "*")
                backTrack(i + 1, p.length - 2, s, p)
            else
                backTrack(i + 1, p.length - 1, s, p)
        } else {
            if (j < p.length - 1 && p[j + 1].toString() == "*") {
                if (s[i] == p[j] || p[j].toString() == ".") {
                    matchLen++
                    backTrack(i + 1, j, s, p)
                    backTrack(i, j + 2, s, p)
                    matchLen--
                } else {
                    backTrack(i, j + 2, s, p)
                }
            } else {
                if (s[i] == p[j] || p[j].toString() == ".") {
                    matchLen++
                    backTrack(i + 1, j + 1, s, p)
                }
            }
        }
    }

    /**
     * 动态规划
     * 失败  把自己给绕糊涂了
     *
     */
    fun dynamicProgram(s: String, p: String): Boolean {
        //1.dp[i][j] 判断i长度的s和j长度的j 能匹配的s的长度
        var dp = Array(s.length + 1) { BooleanArray(p.length + 1) }
        //2.初始值
        dp[0][0] = true
        dp[0][1] = false  // “” 和 “a”这种任意字符或者"." 都无法匹配
        for (i in 1..s.length) dp[i][0] = false
        var index = 2;
        while (index <= p.length) {
            if (p[index - 1].toString() == "*") {
                dp[0][index] = true && dp[0][index - 2]
            }
            index += 2
        }
        //3.关系判断
        //如果p的最后以为是字母
        //如果p的最后一位是* -
        //               最后第二位是字母/最后第二位是.
        //如果p的最后一位是.

        var i = 1
        while (i <= s.length) {
            var j = if (p.length >= 2 && p[1].toString() == "*") 2 else 1
            while (j <= p.length) {
                if (p[j - 1].toString() == ".") {//p的最后一位为 .
                    dp[i][j] = dp[i - 1][j - 1]
                } else if (p[j - 1].toString() == "*") {//p 的最后一位 为 *
                    if (p[j - 2].toString() == ".") {
                        for (k in 0..i) {//说明只要任意长度的s能和长度为j-2的p匹配，后面的就能匹配
                            if (dp[k][j - 2]) {
                                dp[i][j] = true
                                break
                            }
                            dp[i][j] = false//否则不匹配
                        }
                    } else {
                        for1@ for (k in 0..i) {//说明只要任意长度的s能和长度为j-2的p匹配且剩余的s都是重复的p[J-2]，后面的就能匹配
                            if (dp[k][j - 2]) {
                                var charLast = s.substring(k, i)
                                for (n in charLast.indices) {
                                    if (charLast[n].toString() == p[j - 2].toString()) continue
                                    else {
                                        dp[i][j] = false
                                        continue@for1
                                    }
                                }
                                dp[i][j] = true
                                break
                            }
                        }
                    }
                } else
                    dp[i][j] = dp[i - 1][j - 1] && s[i - 1].toString() == p[j - 1].toString()

                if (j + 1 <= p.length - 1 && p[j + 1].toString() == "*") j += 2
                else j++
            }
            i++
        }

        return (dp[s.length][p.length])
    }
}

fun main(args: Array<String>) {
    var code = Code004()
//    println("-------------aa bcbcbcaccbc aa bc/.* a*aa* .* b* . c* .* a* ----")
//    code.backTrack(0, 0, "aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*")
//    println("${code.matchable}")
//    println("${code.isMatch1("aabcbcbcaccbcaabc", ".*a*aa*.*b*.c*.*a*")}")
////    println("-------------a caa bb accbbacaa bbbb/a* .* b* .* a* a a* a* ----")
////    println("${code.isMatch("acaabbaccbbacaabbbb", "a*.*b*.*a*aa*a*")}")
////    println("-------------abcdede/ab.*de----")

    println("${code.dynamicProgram("abcdede", "ab.*de")}")
    println("${code.isMatch1("abcdede", "ab.*de")}")
    println("-----------------")
////    println("${code.isMatch("abc", ".")}")
//    println("-----------------")
//    println("${code.backTrack(0, 0, "ab", ".*..")}")
//    println("${code.matchable}")
    println("${code.dynamicProgram("ab", ".*..")}")
    println("${code.isMatch1("ab", ".*..")}")
//    println("-----------------")
////    println("${code.isMatch("abc", "c*")}")
////    println("-----------------")
    println("${code.dynamicProgram("aa", "a*")}")
    println("${code.isMatch1("aa", "a*")}")
    println("-----------------")
//    println("${code.isMatch("abc", "")}")
//    println("-----------------")
//    println("${code.isMatch("abc", "e*f*c")}")
//    println("-----------------")
//    println("${code.isMatch("abc", "e*a*c")}")
//    println("${code.backTrack(0, 0, "aab", "c*a*b")}")
//    println("${code.matchable}")
//    println("${code.isMatch("aab", "c*a*b")}")//"aab" "c*a*b"
    println("-----------------")
//    println("${code.isMatch("aa", "a*")}")//"aab" "c*a*b"
//    println("-----------------")

    //"mississippi"
    //"mis*is*ip*."
    println("${code.dynamicProgram("mississippi", "mis*is*ip*.")}")
//    println("${code.matchable}")
    println(
        "${
            code.isMatch1(
                "mississippi",
                "mis*is*ip*."
            )
        }"
    )
    //"mi ss i ss i pp i" "mi s* i s* i p* ."
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
//    println("${code.backTrack(0, 0, "a", "ab")}")
//    println("${code.matchable}")
//    println("${code.dynamicProgram( "av", "aa")}")
//    println("${code.isMatch("av", "aa")}")//"aaa" "a.a"
    println("${code.dynamicProgram( "aa", ".")}")
    println("${code.isMatch1("aa", ".")}")//"aaa" "a.a"
    println("-----------------")
    println("${code.dynamicProgram( "abcd", "d*")}")
    println("${code.isMatch("abcd", "d*")}")//"aaa" "a.a"
    println("-----------------")
    println("${code.dynamicProgram( "ab", ".*c")}")
    println("${code.isMatch("ab", ".*c")}")//"aaa" "a.a"
    println("-----------------")


}