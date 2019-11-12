package practice;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Enzo Cotter on 2019/10/19.
 */
public class SolutionTest {

    @Test
    public void isLoopString() {
        System.out.println(new Solution().longestPalindrome(""));
    }

    @Test
    public void isMatch() {
        System.out.println(new Solution().isMatch("aab", "c*a*b"));
    }

    @Test
    public void longestValidParentheses() {
        System.out.println(new Solution().longestValidParentheses("()"));
    }

    @Test
    public void longestValidParentheses1() {
        Assert.assertEquals(new Solution().longestValidParentheses("()"), 2);
        Assert.assertEquals(new Solution().longestValidParentheses(")()())"), 4);
    }

    @Test
    public void spiralOrder() {
        int[][] input = new int[3][4];
        input[0] = new int[]{1, 2, 3, 4};
        input[1] = new int[]{5, 6, 7, 8};
        input[2] = new int[]{9, 10, 11, 12};
        System.out.println(new Solution().spiralOrder(input));
    }

    @Test
    public void canJump() {
        System.out.println(new Solution().canJump(new int[]{3, 2, 1, 0, 4}));
    }

    @Test
    public void isDigit() {
        Assert.assertTrue(Solution.isDigit("0.1434223"));
        Assert.assertTrue(Solution.isDigit("123"));
    }

    @Test
    public void evalRPN() {
        Assert.assertEquals(9, new Solution().evalRPN(new String[]{"2", "1", "+", "3", "*"}));

        Assert.assertEquals(6, new Solution().evalRPN(new String[]{"4", "13", "5", "/", "+"}));

        Assert.assertEquals(22, new Solution()
                .evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    @Test
    public void transform() {
        System.out.println(new Solution().transform("MAX(5*6/MIN(2,3),16)"));
    }

    @Test
    public void intToRoman() {
        System.out.println(new Solution().intToRoman(1994));
    }

    @Test
    public void minimumSwap() {
        System.out.println(new Solution().minimumSwap("xxyyxyxyxx", "xyyxyxxxyx"));
    }

    @Test
    public void movesToMakeZigzag() {
        System.out.println(new Solution().movesToMakeZigzag(new int[]{9,6,1,6,2}));
    }

    @Test
    public void sortColors() {
        new Solution().sortColors(new int[]{0,1});
    }

    @Test
    public void generateParenthesis() {
        File file = new File("D:\\Repository\\SVN\\OWS-Trunk\\publish\\ICTSM\\ICTSM 9.10.0 EXT\\WFM\\WFM_App\\");
        String[] list = file.list();
        for (int  i=0;i< list.length;i++){
            System.out.println(list[i]);
        }
    }

    @Test
    public void advantageCount() {
        System.out.println(
                IntStream.of(new Solution().advantageCount(new int[]{12,24,8,32}, new int[]{13,25,32,11})).boxed()
                        .collect(Collectors.toList()));
        System.out.println(Integer.toBinaryString(2));
    }

    @Test
    public void totalHammingDistance() {
        System.out.println(new Solution().totalHammingDistance1(new int[]{6,1,8,6,8}));
    }
}
