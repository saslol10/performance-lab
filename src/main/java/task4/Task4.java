package task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Task4 {
    public static void main(String[] args) {
        String line;
        try {
            List<String> fileStream = Files.readAllLines(Paths.get(args[0]));
            int len = fileStream.size();
            BufferedReader readerFile1 = new BufferedReader(new FileReader(args[0]));
            int [] nums = new int [len];
            int i = 0;
            int sum = 0;
            int min = Integer.MAX_VALUE;

            while ((line = readerFile1.readLine()) != null) {
                nums[i] = Integer.parseInt(line);
                sum += nums[i];
                if(min > nums[i]){
                    min = nums[i];
                }
                i++;
            }
            int mid = sum/len;
            System.out.println(Math.abs(mid - min) * len);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
