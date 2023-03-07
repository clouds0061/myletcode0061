package leetcode

import kotlin.math.abs

/**
 * 最接近得三数之和
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。

返回这三个数的和。

假定每组输入只存在恰好一个解。



示例 1：

输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。

示例 2：

输入：nums = [0,0,0], target = 1
输出：0



提示：

3 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
-104 <= target <= 104
 *
 */
class Code010ThreeSumClosest {

    /**
     * 算法一  想错了 这个不对，这个就是三数之和，只是判断条件又略微的区别
     */
    fun threeSumClosest1(nums: IntArray, target: Int): Int {
        var sum = 0
        var i = nums[0]
        var j = nums[1]
        var k = nums[2]
        var tempI = 0
        var tempJ = 0
        if (nums.size == 3) return nums[0] + nums[1] + nums[2]
        for (a in 3 until nums.size) {
            var ii = nums[a]
            tempI = i
            tempJ = j
            if (abs(target - i) < abs(target - ii)) i = nums[a]
            if (abs(target - tempI) < abs(target - j)) {
                j = tempI
            }
            if (abs(target - tempJ) < abs(target - k)) {
                k = tempJ
            }
        }
        return i + j + k
    }

    /**
     * 最简单的算法，直接三个for循环
     * 完成，这样写内存消耗很低，但是耗时特别多
     */
    fun threeSumClosest2(nums: IntArray, target: Int): Int {
        var sum = Int.MAX_VALUE
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                for (k in j + 1 until nums.size) {
                    var sumIJK = nums[i] + nums[j] + nums[k]
                    if (sumIJK == target) return target
                    if (Math.abs(target - sum) > abs(target - sumIJK)) sum = sumIJK
                }
            }
        }
        return sum
    }

    /**
     * 最简单的算法，直接三个for循环
     * 完成，这样写内存消耗很低，但是耗时特别多
     * 优化一下看看  等于没有优化,时间没什么变化，看来这种方法一般般
     */
    fun threeSumClosest3(nums: IntArray, target: Int): Int {
        var sum = Int.MAX_VALUE
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                var start = j + 1
                var end = nums.size - 1
                while (start <= end) {
                    var sumStart = nums[i] + nums[j] + nums[start]
                    if (sumStart == target) return target
                    if (Math.abs(sumStart - target) < Math.abs(sum - target)) sum = sumStart


                    var sumEnd = nums[i] + nums[j] + nums[end]
                    if (sumEnd == target) return target
                    if (Math.abs(sumEnd - target) < Math.abs(sum - target)) sum = sumEnd
                    start++
                    end--
                }
            }
        }
        return sum
    }

    /**
     * 必须找到新算法，for循环不能用
     */
    fun threeSumClosest(nums: IntArray, target: Int): Int {
        var sum = Int.MAX_VALUE

        return sum
    }
}

fun main() {

}