package leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

 卧槽了 kotlin的list arrayList 真的不是人能用的 操蛋
 */
public class Code012FourNumSum {

    static List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> fourSum(int[] nums, int target) {
        algorithm1(0, nums, target);
        return list;
    }

    StringBuffer stf = new StringBuffer("");

    int count = 0;

    void algorithm1(int i, int[] nums, int target) {
        if (count == 4) {
            System.out.println("i = " + i);
            if (nums[i] == target) {
                List<Integer> integers = new ArrayList<>();
                for (i = 0; i < stf.length() - 1; i++) {
                    integers.add(Integer.valueOf(stf.substring(i, i + 1)));
                }
                if (!list.contains(integers)) list.add(integers);
            }
            return;
        }

        for (int ii : nums) {
            stf.append(nums[ii]);
            count++;
            algorithm1(i + 1, nums, target - nums[ii]);
            stf.setLength(stf.length() - 1);
            count--;
        }

    }

    /**
     * 动态规划  不行 pass
     */
    List<List<Integer>> dynamic(int[] nums, int target) {
        int[] dp = new int[nums.length];
        //1.dp[i] 表示i个数的和
        //2.初始值
        dp[0] = 0;
        //3.规律
        //dp[i]的和就是求dp[i - 1] + nus[i - 1]
        for (int i = 1; i <= nums.length; i++) {

        }

        return list;
    }

    /**
     * 回溯法
     * 超时
     * 通过了，屎一样的算法，看来只是知道了回溯，也不一定能写出完美的算法。
     * @return
     */
    int counts = 0;

    void part(int i, int[] nums, int target, List<Integer> integers) {

        if (counts == 4) {
            if (target == 0) {
                List<Integer> integerList = new ArrayList<>();
                integerList.addAll(integers);
                if (!list.contains(integerList)) {
                    list.add(integerList);
                }
            }
            return;
        }

        int jEnd = nums.length - 3 + counts;
        for (int j = i; j < jEnd; j++) {
            if (nums[j] > target && nums[j] > 0) return;//最小值直接大于target 没必须继续了
            if (j > i && nums[j] == nums[i]) continue;//去除重复的选择
            int large = nums[j];
            int end = 3 - counts;
            for (int k = 0; k < end; k++) {
                large += nums[nums.length - k - 1];
                if (large >= target) break;
            }

            if (large >= target) {
                integers.add(nums[j]);
                counts++;
                part(j + 1, nums, target - nums[j], integers);
                integers.remove(counts - 1);
                counts--;
            }

        }
    }

    /**
     * 分治法
     */
    void divideAndConquer(int[] nums, int target, List<Integer> integers) {

    }


    public static void main(String[] args) {
        Code012FourNumSum code = new Code012FourNumSum();

        list.clear();
        //[-5,5,4,-3,0,0,4,-2]
        //4
        int[] aa = {-5, 5, 4, -3, 0, 0, 4, -2};
        //[1000000000,1000000000,1000000000,1000000000]
        //-294967296
        int[] aaa = {1000000000, 1000000000, 1000000000, 1000000000};
        int[] a = {0, 0, 0, 1000000000, 1000000000, 1000000000, 1000000000};
        Arrays.sort(a);

        code.part(0, aaa, -294967296, new ArrayList<Integer>());
        System.out.println(code.list);

    }
}