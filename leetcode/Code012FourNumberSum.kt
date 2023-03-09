package leetcode


/***
 * 18.四数之和
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：

0 <= a, b, c, d < n
a、b、c 和 d 互不相同
nums[a] + nums[b] + nums[c] + nums[d] == target
你可以按 任意顺序 返回答案 。

示例 1：
输入：nums = [1,0,-1,0,-2,2], target = 0
输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
示例 2：
输入：nums = [2,2,2,2,2], target = 8
输出：[[2,2,2,2]]
提示：
1 <= nums.length <= 200
-109 <= nums[i] <= 109
-109 <= target <= 109
 */
class Code012FourNumberSum {
    var list = Array(100){IntArray(100)}
    fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
        algorithm1(0,nums,target)
        return list as List<List<Int>>;
    }

    /**
     *
     */
    var count = 0
    fun algorithm1(i: Int, nums: IntArray, target: Int) {
        if (i > nums.size - 1) {
            count ++
            return
        }

        for (j in i until nums.size) {
            list[i][j] = nums[i]
            algorithm1(i+1,nums,target - nums[i])
            list[i][j] = -1
        }
    }
}

fun main() {

}