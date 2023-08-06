package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Input
//  file1:
//      centerX centerY
//      r
//  file2:
//      pointX pointY
// Output
//  position
public class Task2 {
    public static void main(String[] args) {

        String line;
        float centerX, centerY, r, pointX, pointY, position;

        try {
            BufferedReader readerFile1 = new BufferedReader(new FileReader(args[0]));
            line = readerFile1.readLine();
            String[] cirCoords = line.split(" ");
            centerX = Float.parseFloat(cirCoords[0]);
            centerY = Float.parseFloat(cirCoords[1]);
            r = Float.parseFloat(readerFile1.readLine());

            BufferedReader readerFile2 = new BufferedReader(new FileReader(args[1]));
            while ((line = readerFile2.readLine()) != null) {
                String[] pointCoords = line.split(" ");
                pointX = Float.parseFloat(pointCoords[0]);
                pointY = Float.parseFloat(pointCoords[1]);

                position = (float) Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));
                if (position == r) {
                    System.out.println(0);
                }
                if (position < r) {
                    System.out.println(1);
                }
                if (position > r) {
                    System.out.println(2);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

