package leetCode;
import java.util.*;

public class ejs {


    /*
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








}
