package leetCode;
import java.util.*;

public class ejs {


    /*
    #78.SUBSETS

    Given an integer array nums of unique elements, return all possible subsets (the power set).
    The solution set must not contain duplicate subsets. Return the solution in any order.

    EXAMPLE 1
    Input: nums = [1,2,3]
    Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]

    EXAMPLE 2
    Input: nums = [0]
    Output: [[],[0]]
     */

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> solution = new ArrayList<>();
        subsetsAux(nums,0,new ArrayList<>(),solution);
        return solution;
    }

    public void subsetsAux(int[] nums, int n, List<Integer> state, List<List<Integer>> solution){
        solution.add(new ArrayList<>(state));
        for(int i = n; i< nums.length; i++){
            state.add(nums[i]);
            subsetsAux(nums,i+1,state,solution);
            state.remove(state.size()-1);
        }
    }


    /*
    #77.COMBINATIONS

    Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
    You may return the answer in any order.

    Example 1:
    Input: n = 4, k = 2
    Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
    Explanation: There are 4 choose 2 = 6 total combinations.
    Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.

    Example 2:
    Input: n = 1, k = 1
    Output: [[1]]
    Explanation: There is 1 choose 1 = 1 total combination.
     */

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> solution = new ArrayList<>();
        combineAux(n,k,1,new ArrayList<>(),solution);
        return solution;
    }
    public void combineAux(int n, int k, int level, List<Integer> state, List<List<Integer>> solution){
        if(state.size() == k){
            solution.add(new ArrayList<>(state));
            return;
        }else{
            for(int i = level; i<=n; i++){
                state.add(i);
                combineAux(n,k,i+1,state,solution);
                state.remove(state.size()-1);
            }
        }
    }


    /*
    #46.Permutations
    Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

    Example 1:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

    Example 2:
    Input: nums = [0,1]
    Output: [[0,1],[1,0]]

    Example 3:
    Input: nums = [1]
    Output: [[1]]
     */


    public List<List<Integer>> permute(int[] nums){
        List<List<Integer>> solution = new ArrayList<>();
        permuteAux(nums, 0, new ArrayList<>(),solution);
        return solution;
    }
    public void permuteAux(int[] nums, int start, List<Integer> state, List<List<Integer>> solution){
        if(state.size() == nums.length){
            solution.add(new ArrayList<>(state));
            return;
        }else{
            for(int i = start; i<nums.length; i++){
                if (state.contains(nums[i])){
                    continue;
                }else{
                    state.add(nums[i]);
                    permuteAux(nums,start,state,solution);
                    state.remove(state.size()-1);
                }
            }
        }
    }



    /*
    40. COMBINATION/SUM/2
    Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
    Each number in candidates may only be used once in the combination.
    Note: The solution set must not contain duplicate combinations.

    EXAMPLE 1:
    Input: candidates = [10,1,2,7,6,1,5], target = 8
    Output: [[1,1,6],[1,2,5],[1,7],[2,6]]

    EXAMPLE 2:
    Input: candidates = [2,5,2,1,2], target = 5
    Output: [[1,2,2],[5]]
     */



    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> solution = new ArrayList<>();
        combinationSum2Helper(candidates, target, 0, new ArrayList<>(), solution);
        return solution;
    }
    public void combinationSum2Helper(int[] candidates, int target, int start, List<Integer> state, List<List<Integer>> solutions){
        if(target == 0){
            solutions.add(new ArrayList<>(state));
            return;
        }else{
            for(int i = start; i< candidates.length; i++){
                if(i > start && candidates[i] == candidates[i-1]){continue;}
                state.add(candidates[i]);
                combinationSum2Helper(candidates, target-candidates[i], i+1, state, solutions);
                state.remove(state.size()-1);
            }
        }
    }


    /*
    90.SUBSETS II

    Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
    The solution set must not contain duplicate subsets. Return the solution in any order.

    EXAMPLE 1:
    Input: nums = [1,2,2]
    Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]

    EXAMPLE 2:
    Input: nums = [0]
    Output: [[],[0]]
     */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> solution = new ArrayList<>();
        subsetsWithDupHelper(nums,0,new ArrayList<>(), solution);
        return solution;
    }
    public void subsetsWithDupHelper(int[] nums, int start, List<Integer> state, List<List<Integer>> solution){
        solution.add(new ArrayList<>(state));
        for(int i = start; i< nums.length; i++ ){
            if(i > start && nums[i] == nums[i-1]){continue;}
            state.add(nums[i]);
            subsetsWithDupHelper(nums,i+1, state, solution);
            state.remove(state.size()-1);
        }
    }

    /*
    47. PERMUTATIONS-II
    Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
    EXAMPLE 1:
    Input: nums = [1,1,2]
    Output:
    [[1,1,2],[1,2,1],[2,1,1]]

    EXAMPLE 2:
    Input: nums = [1,2,3]
    Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> solution = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        permuteUniqueHelper(nums, used, new ArrayList<>(), solution);
        return solution;
    }

    public void permuteUniqueHelper(int[] nums, boolean[] used, List<Integer> state, List<List<Integer>> solution) {
        if (state.size() == nums.length) {
            solution.add(new ArrayList<>(state));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            used[i] = true;
            state.add(nums[i]);
            permuteUniqueHelper(nums, used, state, solution);
            state.remove(state.size() - 1);
            used[i] = false;
        }
    }

    /*
    216.COMBINATION-SUM-III

    Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
    Only numbers 1 through 9 are used.
    Each number is used at most once.
    Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

    EXAMPLE 1:
    Input: k = 3, n = 7
    Output: [[1,2,4]]
    Explanation:
    1 + 2 + 4 = 7
    There are no other valid combinations.

    EXAMPLE 2:
    Input: k = 3, n = 9
    Output: [[1,2,6],[1,3,5],[2,3,4]]
    Explanation:
    1 + 2 + 6 = 9
    1 + 3 + 5 = 9
    2 + 3 + 4 = 9
    There are no other valid combinations
     */

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> solution = new ArrayList<>();
        combinationSum3Helper(k, n,1, new ArrayList<>(), solution);
        return solution;
    }
    public void combinationSum3Helper(int k, int n, int start, List<Integer> state, List<List<Integer>> solution ){
        if(n == 0 && state.size() == k){
            solution.add(new ArrayList<>(state));
            return;
        }else{
            for(int i = start; i<10; i++){
                if(i > n){continue;}
                state.add(i);
                combinationSum3Helper(k,n-i,i+1,state,solution);
                state.remove(state.size()-1);
            }
        }
    }

}
