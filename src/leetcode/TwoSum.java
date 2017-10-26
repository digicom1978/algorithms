package leetcode;

import java.util.HashMap;

public class TwoSum {
	public TwoSum() {}

	// is input array sorted?  => not guaranteed
	// what if there is no solution?
	// no negative?

	public int[] getIndicesNaive(int[] nums, int target) {

		int[] indices = new int[2];
		for( int i=0; i<nums.length-1; i++ ) {
			for( int j=i+1; j<nums.length; j++ ) {
				if( nums[i] + nums[j] == target ) {
					indices[0] = i;
					indices[1] = j;
					return indices;
				}
				else if( nums[i] + nums[j] > target )
					continue;
			}
		}

		return indices;
	}

	public int[] getIndices2(int[] nums, int target) {

		int[] indices = new int[2];
		for( int i=0; i<nums.length-1; i++ ) {
			if( nums[i] > target)
				continue;
			for( int j=i+1; j<nums.length; j++ ) {
				if( nums[j] > target)
					continue;
				if( nums[i] + nums[j] == target ) {
					indices[0] = i;
					indices[1] = j;
					return indices;
				}
				else if( nums[i] + nums[j] > target )
					continue;
			}
		}

		return indices;
	}

	public int[] getIndicesPhase2(int[] nums, int target) throws IllegalArgumentException {

		HashMap hNums = new HashMap();
		for( int i=0; i<nums.length; i++ )
			hNums.put(nums[i], i);

		for( int i=0; i<nums.length-1; i++ ) {
			if( (target - nums[i]) == nums[i] )
				continue;
			if( hNums.containsKey(target - nums[i]) ) {
				return new int[] {i, (int)hNums.get(target-nums[i]) };
			}
		}

		throw new IllegalArgumentException("No solution on this array");
	}

	public int[] getIndicesPhase1(int[] nums, int target) throws IllegalArgumentException {

		HashMap hNums = new HashMap();
		for( int i=0; i<nums.length; i++ ) {
			hNums.put(nums[i], i);

			if( (target - nums[i]) == nums[i] )
				continue;
			if( hNums.containsKey(target - nums[i]) ) {
				return new int[] {i, (int)hNums.get(target-nums[i]) };
			}

		}


		throw new IllegalArgumentException("No solution on this array");
	}

	public static void main(String[] args) {
		TwoSum ts = new TwoSum();
		int[] nums = {2,7,11,15};
		int[] nums4 = {201,31,2,7,13,1233,16,22,15,16,34,22,24,27,14,321,222,113,132,3123,231,2211,312,14123,123,1153,15341,23};


		System.out.println("Brute Force");

		int[] returnedIndices = ts.getIndicesNaive(nums, 9);
		System.out.println(returnedIndices[0] + ", " + returnedIndices[1]);

		returnedIndices = ts.getIndicesNaive(nums4, 36);
		System.out.println(returnedIndices[0] + ", " + returnedIndices[1]);

		System.out.println("Little bit improved Brute Force");
		returnedIndices = ts.getIndices2(nums, 9);
		System.out.println(returnedIndices[0] + ", " + returnedIndices[1]);

		returnedIndices = ts.getIndices2(nums4, 36);
		System.out.println(returnedIndices[0] + ", " + returnedIndices[1]);

		System.out.println("Using HashMap with two phases");
		returnedIndices = ts.getIndicesPhase2(nums4, 36);
		System.out.println(returnedIndices[0] + ", " + returnedIndices[1]);

		System.out.println("Using HashMap with one phase");
		returnedIndices = ts.getIndicesPhase1(nums4, 36);
		System.out.println(returnedIndices[0] + ", " + returnedIndices[1]);
	}
}
