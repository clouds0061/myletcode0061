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
    fun isMatch2(s: String, p: String): Boolean {
        return s.matches(p.toRegex())
    }

    fun isMatch(s: String, p: String): Boolean {
        var stf = StringBuffer(s)
        var stf2 = StringBuffer(p)
        var size = 0
        return when {
            p == ".*" -> true
            p.contains("*") -> {
                var indexP = 0
                var indexS = 0
                do {
                    var stringS = stf.substring(indexS, indexS + 1)
                    var stringP = stf2.substring(indexP, indexP + 1)
//                    println("strings = $stringS , stringP = $stringP")
                    var end = if (indexP + 2 <= stf2.length) indexP + 2 else indexP + 1
                    if (stf2.substring(indexP, end).contains("*")) {
                        if (stringS == stringP) {
                            indexS++
                            size++
                        }
                        else indexP += 2
                    } else {
                        if (stringP == ".") {
                            indexS++
                            indexP++
                            size++
                        } else if (stringS == stringP) {
                            indexS++
                            indexP++
                        } else return false
                    }
                } while (indexP < stf2.length && indexS < stf.length)
                if (size < stf.length) return false//说明s中还有字符没有比对完，说明不能匹配
                return true
            }
            p.contains(".") && !p.contains("*") -> {
                var indexP = 0
                var indexS = 0
                do {
                    var stringS = stf.substring(indexS, indexS + 1)
                    var stringP = stf2.substring(indexP, indexP + 1)
                    if (stringP == ".") {
                        indexS++
                        indexP++
                        size++
                    } else if (stringS == stringP) {
                        indexS++
                        indexP++
                        size++
                    } else return false

                } while (indexP < stf2.length && indexS < stf.length)
                if (size < stf.length) return false//说明s中还有字符没有比对完，说明不能匹配
                return true
            }
            else -> s == p
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
    println("${code.isMatch("mississippi", "mis*is*ip*.")}")//"mi ss i ss i pp i" "mi s* i s* i p* ."
    println("${code.isMatch("mississippi", "mis*is*p*.")}")//"mi ss i ss i pp i" "mi s* i s*  p* ."
////    println("-----------------")
    println("${code.isMatch("aaa", "a.a")}")//"aaa" "a.a"
//    println("-----------------")
    println("${code.isMatch("aa", "a*")}")//"aaa" "a.a"
    println("${code.isMatch("aa", ".")}")//"aaa" "a.a"


}