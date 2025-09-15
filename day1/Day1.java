import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class Day1 {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> inputs = getInputs("input.txt");
        
        System.out.println(String.format("Part 1 answer = %d", part1(inputs.get(0), inputs.get(1))));
        System.out.println(String.format("Part 2 answer = %d", part2(inputs.get(0), inputs.get(1))));

    }

    private static ArrayList<ArrayList<Integer>> getInputs(String filename) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            String[] nums;

            while ((line = br.readLine()) != null) {
                nums = line.split("   ");
                list1.add(Integer.valueOf(nums[0]));
                list2.add(Integer.valueOf(nums[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return new ArrayList<>(List.of(list1, list2));
    }

    private static int part1(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        int totalDistance = 0; 

        Collections.sort(list1);
        Collections.sort(list2);

        for (int i = 0; i < list1.size(); i++) {
            totalDistance += Math.abs(list1.get(i) - list2.get(i));
        }

        return totalDistance;
    }

    private static int part2(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int similarityScore = 0; 

        for (int i = 0; i < list1.size(); i++) {
            int n = list1.get(i);
            if (!map.containsKey(n)) {
                int x = 0;
                for (int j = 0; j < list2.size(); j++) {
                    if (list2.get(j) == n) {
                        x++;
                    }
                }
                map.put(n, x);
                similarityScore += n*x;
            }
            else {
                similarityScore += n*map.get(n);
            }
        }

        return similarityScore;
    }


}