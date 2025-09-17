
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Day3 {
    private static String getInput(String filename) {
        StringBuilder input = new StringBuilder();

        try (BufferedReader bf = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bf.readLine()) != null) {
                input.append(line);
            }
        } catch (IOException e ) {
            e.printStackTrace();
        }

        return input.toString();
        
    }

    private static int part1(String input) {
        int answer = 0;
        for(int i = 0; i < input.length(); i++) {
            String subString = input.substring(i);
            
            if (subString.matches("^mul\\(\\d{1,3},\\d{1,3}\\).*")) {
                int firstNumber = Integer.parseInt(subString.substring(subString.indexOf('(')+1, subString.indexOf(',')));
                int secondNumber = Integer.parseInt(subString.substring(subString.indexOf(',')+1, subString.indexOf(')')));

                answer += firstNumber * secondNumber;
            }
        }
        return answer;
    }

    private static int part2(String input) {
        int answer = 0;
        boolean instructionMulEnabled = true;
        
        for(int i = 0; i < input.length(); i++) {
            String subString = input.substring(i);
            if (instructionMulEnabled && subString.matches("^mul\\(\\d{1,3},\\d{1,3}\\).*")) {
                int firstNumber = Integer.parseInt(subString.substring(subString.indexOf('(')+1, subString.indexOf(',')));
                int secondNumber = Integer.parseInt(subString.substring(subString.indexOf(',')+1, subString.indexOf(')')));

                answer += firstNumber * secondNumber;
            }
            else if (subString.matches("^do\\(\\).*")) {
                instructionMulEnabled = true;
            }
            else if (subString.matches("^don\\'t\\(\\).*")) {
                instructionMulEnabled = false;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String input = getInput("input.txt");
        System.out.println(String.format("Part 1 answer = %d", part1(input)));
        System.out.println(String.format("Part 2 answer = %d", part2(input)));
    }
}