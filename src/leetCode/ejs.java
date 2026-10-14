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

}
