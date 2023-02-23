package leetcode

import com.sun.source.tree.LineMap

/***
 * 罗马数字转整数
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。

给定一个罗马数字，将其转换成整数

1 <= s.length <= 15
s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999] 内
题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。

来源：力扣（LeetCode）
链接：https://leetcode.cn/problems/roman-to-integer
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Code007RoomNum2Int {

    /**
     * 就是昨天得题目 反过来了呀
     *
     * [M   ,CM ,D  ,CD  ,C ,XC ,L,XL ,X,IX,V,IV,I]
     * [1000,900,500,400,100,90,50,40,10,9 ,5,4 ,1]
     *
     * 经过昨天别人的例子 今天通过的格外快 格外舒服
     */
    fun romanToInt(s: String): Int {
        var intNum = 0
        var stfRoom = StringBuffer(s)
        var ints = arrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
        var rooms = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
        for (i in rooms.indices) {
            while (stfRoom.startsWith(rooms[i])) {
                intNum += ints[i]
                stfRoom.replace(0, rooms[i].length, "")
            }
        }
        return intNum
    }


    /**
     * 看到的一种奇妙的解法
     */
    fun roman2Int(s: String): Int {
        var intNum = 0
        var stfRoman = StringBuffer(s)
        var map: Map<String, Int> = mapOf<String, Int>(
            "I" to 1,
            "IV" to 4,
            "V" to 5,
            "IX" to 9,
            "X" to 10,
            "XL" to 40,
            "L" to 50,
            "XC" to 90,
            "C" to 100,
            "CD" to 400,
            "D" to 500,
            "CM" to 900,
            "M" to 1000,
        )

        var intOld = 0
        var index = stfRoman.length - 1
        while (index >= 0) {
            var roman = stfRoman[index].toString()
            var it: Int? = map.get(roman)
            if (it != null) {
                if (it >= intOld) {
                    intNum += it
                    intOld = it
                } else intNum -= it
            }
            index--
        }

        return intNum
    }
}


fun main() {
    var code = Code007RoomNum2Int()
    println("MCMXCIV == ${code.roman2Int("MCMXCIV")}")
    println("III == ${code.roman2Int("III")}")
}


























