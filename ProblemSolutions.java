
/******************************************************************
 *
 *   Nick Cwikla / COMP272-001
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

import java.util.*;
import java.util.PriorityQueue;

public class ProblemSolutions {

    /**
     * Priority Queue (PQ) Game
     *
     * PQ1 Problem Statement:
     * -----------------------
     *
     * You are given an array of integers of boulders where boulders[i] is the
     * weight of the ith boulder.
     *
     * We are playing a game with the boulders. On each turn, we choose the heaviest
     * two boulders and smash them together. Suppose the heaviest two boulders have
     * weights x and y. The result of this smash is:
     *
     *    If x == y, both boulders are destroyed, and
     *    If x != y, the boulder of weight x is destroyed, and the boulder of
     *               weight y has new weight y - x.
     *
     * At the end of the game, there is at most one boulder left.
     *
     * Return the weight of the last remaining boulder. If there are no boulders
     * left, return 0.
     *
     *
     * Example 1:
     *
     * Input: boulders = [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
     * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
     * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
     * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
     * value of the last stone.
     *
     * Example 2:
     *
     * Input: boulders = [1]
     * Output: 1
     *
     *
     *
     * RECOMMENDED APPROACH
     *
     * Initializing Priority Queue in reverse order, so that it gives
     * max element at the top. Taking top Elements and performing the
     * given operations in the question as long as 2 or more boulders;
     * returning the 0 if queue is empty else return pq.peek().
     */

  public static int lastBoulder(int[] boulders) {
     // Initialize priority queue in reverse order, to get mex element at top.
      PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
     // Each boulder is added to the priority queue.
      for (int boulder : boulders) {
          pq.offer(boulder);
      }
     // Boulders keep being added until less than 1 are left.
      while (pq.size() > 1) {
         // Acquire the top boulder.
          int first = pq.poll();
         // Acquire the boulder that is second-to-first.
          int second = pq.poll();
         // If they are different and do not equal each other, difference of both is put back in queue.
          if (first != second) {
              pq.offer(first - second);
          }
      }
     // Return the last remnants or 0 if nothing else is left.
      return pq.isEmpty() ? 0 : pq.peek();
  }


    /**
     * Method showDuplicates
     *
     * This method identifies duplicate strings in an array list. The list
     * is passed as an ArrayList<String> and the method returns an ArrayList<String>
     * containing only unique strings that appear more than once in the input list.
     * This returned array list is returned in sorted ascending order. Note that
     * this method should consider case (strings are case-sensitive).
     *
     * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse", "Lion", "CAT"
     * the method would return an ArrayList<String> containing: "Dog", "Lion"
     *
     * @param  input an ArrayList<String>
     * @return       an ArrayList<String> containing only unique strings that appear
     *               more than once in the input list. They will be in ascending order.
     */

    public static ArrayList<String> showDuplicates(ArrayList<String> input) {
       // Initialize ArrayList for storing duplicates.
        ArrayList<String> duplicates = new ArrayList<>();
       // Go through all the strings in the ArrayList.
        for (int i = 0; i < input.size(); i++) {
           // Acquire the string to check.
            String current = input.get(i);
           // Compare with the other strings in the list.
            for (int j = i + 1; j < input.size(); j++) {
               // If it is a duplicate and not in the list, then.
                if (current.equals(input.get(j)) && !duplicates.contains(current)) {
                   // Duplicate is added to the list.
                    duplicates.add(current);
                }
            }
        }
       // List of duplicates is returned.
        return duplicates;
    }


    /**
     * Finds pairs in the input array that add up to k.
     *
     * @param input   Array of integers
     * @param k       The sum to find pairs for

     * @return an ArrayList<String> containing a list of strings. The ArrayList
     *        of strings needs to be ordered both within a pair, and
     *        between pairs in ascending order. E.g.,
     *
     *         - Ordering within a pair:
     *            A string is a pair in the format "(a, b)", where a and b are
     *            ordered lowest to highest, e.g., if a pair was the numbers
     *            6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
     *         - Ordering between pairs:
     *            The ordering of strings of pairs should be sorted in lowest to
     *            highest pairs. E.g., if the following two string pairs within
     *            the returned ArraryList, "(3, 6)" and "(2, 7), they should be
     *            ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
     *
     *         Example output:
     *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
     *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
     *
     *  HINT: Considering using any Java Collection Framework ADT that we have used
     *  to date, though HashSet. Consider using Java's "Collections.sort()" for final
     *  sort of ArrayList before returning so consistent answer. Utilize Oracle's
     *  Java Framework documentation in its use.
     */

    public static ArrayList<String> pair(int[] input, int k) {
       // Check how many times number appears in inputted array.
        HashMap<Integer, Integer> pairNumber = new HashMap<>();
       // ArrayList for storing the pairs.
        ArrayList<String> pairs = new ArrayList<>();
       // Count the occurrences for every number in the array.
        for (int i : input) {
            pairNumber.put(i, pairNumber.getOrDefault(i, 0) + 1);
        }
       // Go through each unique number in the HashMap.
        for (Map.Entry<Integer, Integer> entry : pairNumber.entrySet()) {
           // Acquire the number.
            int i = entry.getKey();
           // Determine the difference needed to make k.
            int different = k - i;
           // Make sure the difference exists in the map.
            if (pairNumber.containsKey(different)) {
               // If the number is equal to difference and appears less than twice, skip.
                if (i == different && pairNumber.get(i) < 2) {
                    continue;
                }
               // Go down through the count for number.
                pairNumber.put(i, pairNumber.get(i) - 1);
               // Go down through the count for the difference.
                pairNumber.put(different, pairNumber.get(different) - 1);
               // Make sure the number counts are not negative.
                if (pairNumber.get(i) >= 0 && pairNumber.get(different) >= 0) {
                   // Add the pairs in ascending order.
                    pairs.add("(" + Math.min(i, different) + ", " + Math.max(i, different) + ")");
                }
            }
        }
       // Use the Collections framework to sort the list of paired numbers.
        Collections.sort(pairs);
       // Return the sorted list.
        return pairs;
    }
}