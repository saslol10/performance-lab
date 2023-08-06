package task1;

import java.util.Scanner;

// круговой массив
// Input n m
// Output arr
public class Task1 {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int n = console.nextInt();
        int m = console.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++)
        {
            arr[i] = i + 1;
        }

        int current = 0;
        do {
            System.out.print(arr[current]);
            current = (current + m - 1) % n;
        } while (current != 0);
    }
}
