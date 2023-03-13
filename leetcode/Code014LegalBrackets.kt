package leetcode

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
每个右括号都有一个对应的相同类型的左括号。



示例 1：

输入：s = "()"
输出：true

示例 2：

输入：s = "()[]{}"
输出：true

示例 3：

输入：s = "(]"
输出：false

 */
class Code014LegalBrackets {

    /**
     * 双指针  不行 "(([]){})"
     */
    fun isValid1(s: String): Boolean {
        var mapBrackets = mapOf("(" to ")", "{" to "}", "[" to "]")
        var stf = StringBuffer(s)
        var i = 0
        var j = stf.length - 1;
        if (stf.length == 1) return false
        whileOut@ while (i < j) {
            if (stf[i].toString() == "-") continue
            if (mapBrackets.containsKey(stf[i].toString())) {
                //顺序成对()xxxxx
                if (mapBrackets.get(stf[i].toString()) == stf[i + 1].toString()) {
                    i += 2
                    continue
                }
                //中间首尾成对 xxx(xxxx)xxx
                var end = i + 2
                while (end < stf.length - 2) {
                    if (mapBrackets.get(stf[i].toString()) == stf[end + 1].toString()) {
                        stf.replace(end + 1, end + 2, "-")
                        stf.replace(i, i + 1, "-")
                        continue@whileOut
                    }
                    end += 2
                }
                //首尾成对(xxxxx)
                if (mapBrackets.get(stf[i].toString()) == stf[j].toString()) {
                    i++
                    j--
                    continue
                }
                return false
            } else {
                return false
            }
        }
        return true
    }


    /**
     *
     */
    var mapBrackets = mapOf("(" to ")", "{" to "}", "[" to "]")
    fun isValid(s: String): Boolean {
        return true
    }
}