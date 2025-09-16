
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Day2 {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> input = getInput("input.txt");
        System.out.println(String.format("Part 1 answer = %d", part1(input)));
        System.out.println(String.format("Part 2 answer = %d", part2(input)));
    }

    private static ArrayList<ArrayList<Integer>> getInput(String filename) {
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            String[] stringLevels;

            while((line = br.readLine()) != null) {
                ArrayList<Integer> report = new ArrayList<>();
                stringLevels = line.split(" ");
                for (String level : stringLevels) {
                    report.add(Integer.valueOf(level));
                }
                input.add(report);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    private static boolean isReportValid(ArrayList<Integer> report) {
        boolean levelsAreIncreasing = report.get(0) < report.get(1);
        
        for (int i = 0; i < report.size()-1; i++) {
            if (levelsAreIncreasing && report.get(i) >= report.get(i+1)) {
                return false;
            }
            else if (!levelsAreIncreasing && report.get(i) <= report.get(i+1)) {
                return false;
            }

            if(Math.abs(report.get(i) - report.get(i+1)) > 3 || Math.abs(report.get(i) - report.get(i+1)) < 1) {
                return false;
            }
        }

        return true;
    }

    private static int part1(ArrayList<ArrayList<Integer>> input) {
        int answer = 0;

        for(ArrayList<Integer> report : input) {
            if (isReportValid(report)) {
                answer++;
            }
        }

        return answer;
    }

    private static boolean problemDamperer(ArrayList<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            int removedLevel = report.remove(i);
            if(isReportValid(report)) {
                return true;
            }
            report.add(i, removedLevel);
        }

        return false;
    }

    private static int part2(ArrayList<ArrayList<Integer>> input) {
        int answer = 0;
        
        for(ArrayList<Integer> report : input) {
            if (isReportValid(report) || problemDamperer(report)) {
                answer++;
            }
        }

        return answer;
    }




}