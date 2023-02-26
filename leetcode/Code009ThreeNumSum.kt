package leetcode

/**
 * 15. 三数之和

给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
你返回所有和为 0 且不重复的三元组。
注意：答案中不可以包含重复的三元组。
示例 1：
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。
示例 2：
输入：nums = [0,1,1]
输出：[]
解释：唯一可能的三元组和不为 0 。
示例 3：
输入：nums = [0,0,0]
输出：[[0,0,0]]
解释：唯一可能的三元组和为 0 。
提示：
3 <= nums.length <= 3000
-105 <= nums[i] <= 105

 */
class Code009ThreeNumSum {
    //-1,0,1,2,-1,-4]
    //-1 , -1 , 0 , 1 , 2 , 4
    fun threeSum1(nums: IntArray): List<List<Int>> {
        var list = ArrayList<ArrayList<Int>>()
        nums.sort()//排序
        if (nums[0] + nums[1] + nums[2] > 0) return list//最小的三个相加大于0
        if (nums[nums.size - 1] + nums[nums.size - 2] + nums[nums.size - 3] < 0) return list//最大的三个相加小于0

        for (i in nums.indices)
            println("[ ${nums[i]} ]")


        var min = 0
        var max = nums.size - 1
        var middle = min + 1
        while (true) {
            var left = min
            var right = max
            for1@ for (i in middle until max) {
                var sum = nums[min] + nums[i] + nums[max]
                println("in for min = $min , i = $i , max = $max || sum = $sum")
                when {
                    sum > 0 -> {

                        left--
                        break@for1
                    }
                    sum < 0 -> {
                        right++
                        break@for1
                    }
                    else -> {
                        var ints = ArrayList<Int>()
                        ints.add(nums[min])
                        ints.add(nums[i])
                        ints.add(nums[max])
                        if (!list.contains(ints))
                            list.add(ints)


                        break@for1
                    }
                }
                Thread.sleep(200)
            }
            Thread.sleep(200)
            println("in while min = $min , middle = $middle , max = $max")
            println("----------")
        }
    }

    /**
     * 上一种算法已经无以为继了
     */
    fun threeSum(nums: IntArray): List<List<Int>> {
        var list = ArrayList<ArrayList<Int>>()
        nums.sort()//排序
        if (nums[0] + nums[1] + nums[2] > 0) return list//最小的三个相加大于0
        if (nums[nums.size - 1] + nums[nums.size - 2] + nums[nums.size - 3] < 0) return list//最大的三个相加小于0

        for (i in nums.indices) {
            println("[ ${nums[i]} ]")
        }

        var min = 0
        while (min < nums.size - 2) {
            var max = nums.size - 1
            var middle = min + 1
            inner@ while (true) {
                var sum = nums[min] + nums[middle] + nums[max]
                println("inner ||  = $min , middle = $middle , max = $max || sum = $sum")
                println("--------------------------------")
                when {
                    sum < 0 -> {
                        middle++
                    }
                    sum > 0 -> {
                        max--
                    }
                    else -> {
                        var ints = ArrayList<Int>()
                        ints.add(nums[min])
                        ints.add(nums[middle])
                        ints.add(nums[max])
                        if (!list.contains(ints))
                            list.add(ints)
                        middle++
                        max--
                    }
                }
                if (middle >= max) break@inner
            }
            min++
            println("outer ||  = $min , middle = $middle , max = $max")
            println("==================================")
        }
        return list
    }
}

fun main() {
    //[-1,0,1,2,-1,-4,-2,-3,3,0,4]
    var code = Code009ThreeNumSum()
    var list = arrayListOf<Int>()
    list.add(-1)
    list.add(0)
    list.add(1)
    list.add(2)
    list.add(-1)
    list.add(-4)
    list.add(-2)
    list.add(-3)
    list.add(3)
    list.add(0)
    list.add(4)
    println("最长 = ${code.threeSum(list.toIntArray())}")

}