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
        var stf = StringBuffer(p)
        var stf2 = StringBuffer(s)
        return when {
            p == ".*" -> true
            p.contains("*") -> {
                if (s.length != p.replace("*", "", true).length) return false//长度不同肯定不能匹配
                var indexP = 0
                do {
                    var string = stf2.substring(indexP, indexP + 1)
                    if (stf2.substring(indexP, indexP + 2).contains("*")) {
                        indexP += 2
                    } else {
                        indexP ++
                    }

                    for1@ for (str in stf) {
                        if (!str.equals(string)) {
                            break@for1
                        }
                    }

                } while (indexP < stf2.length)
                return stf2.toString() == stf.toString()
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
    println("-----------------")
//    println("${code.isMatch("abc","e*f*c")}")
    println("-----------------")
//    println("${code.isMatch("abc","e*a*c")}")
    println("${code.isMatch("aab", "c*a*b")}")//"aab" "c*a*b"
    println("-----------------")
}