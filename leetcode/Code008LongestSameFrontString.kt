package leetcode

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。
示例 1：

输入：strs = ["flower","flow","flight"]
输出："fl"

示例 2：

输入：strs = ["dog","racecar","car"]
输出：""
解释：输入不存在公共前缀。
提示：

1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] 仅由小写英文字母组成


 */
class Code008LongestSameFrontString {


    /**
     * 第一次
     * 1.找到最短得str
     * 2.根据str去匹配，慢慢剪短长度匹配
     * //["flower","flow","flight"]
     */
    fun longestCommonPrefix1(strs: Array<String>): String {
        var list = strs.toList().sortedBy { it -> it.length }
        var first = list[0]
        var i = 1
        var j = first.length
        var result = ""
        while (i <= j) {
            var shorCount = 0
            var longCount = 0
            var short = first.substring(0, i)
            var long = first.substring(0, j)

            for (a in 1 until list.size) {
                if (!list[a].startsWith(short)) return result
                shorCount++
            }
            if (shorCount == list.size - 1) result = short

            for (a in 1 until list.size) {
                if (!list[a].startsWith(long)) break
                longCount++
            }
            if (longCount == list.size - 1) return long

            i++
            j--
        }
        return result
    }

    /**
     * 上一种方法太土了,只打败了5.77得用户，说明优化太垃圾
     *
     */
    fun longestCommonPrefix2(strs: Array<String>): String {
        var result = StringBuffer("")
        var str = StringBuffer("")
        var list = strs.toList().sortedBy { it -> it.length }
        var first = list[0]
        for (i in list.indices) {
            str.append(list[i])
            str.append("-")
        }
        println("str = $str")
        var len = strs.size
        var j = 1
        while (j <= first.length) {
            var s = first.substring(0, j)
            println("s  =  $s")
            if (str.matches("($s[a-z]*-){$len}".toRegex())) {
                result.setLength(0)
                result.append(s)
            } else break
            j++
        }

        return result.toString()
    }

    /**
     * 这么叼得解法，效率还是一般? 那在我前面得都是什么神仙解法;
     */
    fun longestCommonPrefix3(strs: Array<String>): String {
        var result = StringBuffer("")
        var str = StringBuffer("")
        var first = strs[0]
        for (i in strs.indices) {
            str.append(strs[i])
            str.append("-")
        }
        println("first = $first")
        var len = strs.size
        var middle = if (first.length % 2 == 0) first.length / 2 else (first.length + 1) / 2
        var right = first.length
        var left = 0

        while (true) {
            var s = first.substring(0, middle)
            var lastLeft = left
            var lastRight = right
            println("s  =  $s")
            println("left = $left ,middle = $middle , right = $right ")
            if (str.matches("($s[a-z]*-){$len}".toRegex())) {
                left = middle
                middle += if ((right - left) % 2 == 0) (right - left) / 2 else (right - left + 1) / 2
                result.setLength(0)
                result.append(s)

            } else {
                right = middle
                middle -= if ((right - left) % 2 == 0) (right - left) / 2 else (right - left + 1) / 2

            }

            if (lastLeft == left && lastRight == right) return result.toString()
            println("-----------------")
        }
    }

    fun longestCommonPrefix(strs: Array<String>): String {
        var result = StringBuffer("")

        return result.toString()
    }
}


fun main() {
    var code = Code008LongestSameFrontString()
    var list = arrayListOf<String>()
    list.add("flower")
    list.add("flo")
    list.add("flower")
    list.add("flower")
    list.add("flower")
    println("最长 = ${code.longestCommonPrefix(list.toTypedArray())}")

//    var string = "flow-flower-flight-flaaa-"
//    var flag = string.matches(("(fl[a-z]*-){4}".toRegex()))
//    println("flag = $flag")
}