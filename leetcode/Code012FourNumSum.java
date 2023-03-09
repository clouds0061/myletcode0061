package leetcode;


import java.util.ArrayList;
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

    List<List<Integer>> list = new ArrayList<>();

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

    public static void main(String[] args) {
        Code012FourNumSum code = new Code012FourNumSum();

        int [] a = {2,2,2,2,2};
        code.fourSum(a,8);
    }
}