import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        
        StringBuilder sb = new StringBuilder();
        sb.append(n).append(" is ").append(n % 2 == 0 ? "even" : "odd");
        System.out.println(sb.toString());
    }
}