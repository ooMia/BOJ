import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        char[] cs = sc.next().toCharArray();
        
        for (char c : cs) {
            sb.append(toggleChar(c));
        }
        System.out.println(sb.toString());
    }
    
    static char toggleChar(char c) {
        int ic = (int) c;
        if (ic >= 'a') {
            return (char) (ic - 'a' + 'A');
        }
        return (char) (ic - 'A' + 'a');
    }
}