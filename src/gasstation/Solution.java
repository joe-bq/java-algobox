package gasstation;
/*
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
 */

public class Solution {
	
	public int canCompleteCircuit(int[] gas, int[] cost) { 
		int length = gas.length;
		int[] howfar = new int[length];
		
		for (int i = 0; i < length; i++) { 
			howfar[i] = gas[i] - cost[i];
		}
		
		/*
		// starting from 1, each step one station further, until we reach the last station
		for (int i = 1; i < length; i++) {
			// for each of the station..., how far can it go
			for (int j = 0; j < length; j++) { 
				if (howfar[j] >= 0) { 
					int next_station = (j + i) % length;
					howfar[j] = howfar[j] + gas[next_station] - gas[next_station];
				}
			}
		}
		*/
		
		// which can be transformed to the following 
		
		for (int i = 0; i < length; i++) {
			for (int j = 1; j < length && howfar[i] >= 0; j++) {
				int next_station =  (i + j) % length;
				howfar[i] += gas[next_station] - cost[next_station];
			}
			
			if (howfar[i] >= 0) { 
				return i;
			}
		}
		

		return -1;
	}
	
	
	public static void main(String[] args) { 
		Solution solution = new Solution();
		/*
		int[] gas = {5, 10, 20, 6, 7};
		int[] cost  = {4, 8, 25, 6, 10 };
		*/
		int[] cost = {5, 10, 20, 6, 7};
		int[] gas = {4, 8, 25, 6, 10 };
		int station = solution.canCompleteCircuit(gas, cost);
		
		System.out.println(" the start gas station is " + station);
	}
}
