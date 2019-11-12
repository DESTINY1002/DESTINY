package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Enzo Cotter on 2019/10/19.
 */
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }

        List<TreeNode> traverseNodes = new ArrayList<>();

        traverseNodes.add(root);

        return breadthFirstSearch(traverseNodes);

    }

    private List<List<Integer>> breadthFirstSearch(List<TreeNode> traverseNodes) {
        if (null == traverseNodes || traverseNodes.isEmpty()) {
            return null;
        }

        List<Integer> currentValues = new ArrayList<>();
        List<TreeNode> nextTraverseNodes = new ArrayList<>();
        traverseNodes.stream().forEach(each -> {
            if (null == each) {
                return;
            }
            currentValues.add(each.val);
            if (each.left != null) {
                nextTraverseNodes.add(each.left);
            }
            if (each.right != null) {
                nextTraverseNodes.add(each.right);
            }
        });

        List<List<Integer>> result = new ArrayList<>();
        result.add(currentValues);
        List<List<Integer>> sub = breadthFirstSearch(nextTraverseNodes);
        if (null != sub) {
            result.addAll(sub);
        }
        return result;
    }

    public boolean isValidBST(TreeNode root) {
        if (null == root) {
            return true;
        }
        boolean flag = isTreeLess(root.left, root.val) && isTreeMore(root.right, root.val);
        if (!flag) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);

    }

    private boolean isTreeLess(TreeNode root, int max) {
        if (null == root) {
            return true;
        }
        return root.val < max && isTreeLess(root.left, max) && isTreeLess(root.right, max);
    }

    private boolean isTreeMore(TreeNode root, int min) {
        if (null == root) {
            return true;
        }
        return root.val > min && isTreeMore(root.left, min) && isTreeMore(root.right, min);
    }

    //寻找两个有序数组的中位数
    public double findMedianSortedArrays(int[] A, int[] B) {
        int sum = A.length + B.length;
        int[] C = new int[sum];
        int AIndex = 0;
        int BIndex = 0;
        int CIndex = 0;
        while (CIndex < sum) {
            if (AIndex == A.length) {
                C[CIndex++] = B[BIndex++];
                continue;
            }
            if (BIndex == B.length) {
                C[CIndex++] = A[AIndex++];
                continue;
            }
            if (A[AIndex] < B[BIndex]) {
                C[CIndex++] = A[AIndex++];
            }
            else {
                C[CIndex++] = B[BIndex++];
            }
        }
        if (sum % 2 == 0) {
            return (C[sum / 2 - 1] + C[sum / 2]) / 2.0;
        }
        else {
            return C[sum / 2];
        }
    }

    public double findMedianSortedArrays1(int[] A, int[] B) {
        int sum = A.length + B.length;
        int AIndex = 0;
        int BIndex = 0;
        int left = -1;
        int right = -1;
        for (int i = 0; i <= sum / 2; i++) {
            left = right;
            if (AIndex == A.length) {
                right = B[BIndex++];
                continue;
            }
            if (BIndex == B.length) {
                right = A[AIndex++];
                continue;
            }
            if (A[AIndex] < B[BIndex]) {
                right = A[AIndex++];
            }
            else {
                right = B[BIndex++];
            }
        }
        if (sum % 2 == 0) {
            return (left + right) / 2.0;
        }
        else {
            return right;
        }
    }

    //无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        Set<Character> window = new HashSet<>();
        int left = 0;
        int right = 0;
        int max = 0;
        while (right < s.length() && left < s.length()) {
            if (!window.contains(s.charAt(right))) {
                window.add(s.charAt(right++));
                max = Math.max(max, right - left);
            }
            else {
                window.remove(s.charAt(left++));
            }
        }
        return max;
    }

    //最长回文子串
    public String longestPalindrome(String s) {
        if (null == s || s.length() == 0) {
            return s;
        }
        int len = s.length();
        int left = 0;
        int right = 0;
        for (int i = 0; i < len; i++) {
            int tmpLen1 = expand(s, i, i);
            int tmpLen2 = expand(s, i, i + 1);
            int tmpmax = Math.max(tmpLen1, tmpLen2);
            if (tmpmax > (right - left + 1)) {
                left = i - (tmpmax - 1) / 2;
                right = i + tmpmax / 2;
            }
        }
        return s.substring(left, right + 1);
    }

    private int expand(String s, int i, int i1) {
        while (i >= 0 && i1 < s.length()) {
            if (s.charAt(i) == s.charAt(i1)) {
                i--;
                i1++;
            }
            else {
                break;
            }
        }
        return i1 - i - 1;
    }

    //正则表达式匹配
    public boolean isMatch(String s, String p) {
        if (null == p || p.length() == 0) {
            return null == s || s.length() == 0;
        }

        boolean isFirstMatch = s.length() > 0 && ((s.charAt(0) == p.charAt(0)) || (p.charAt(0) == '.'));

        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (isMatch(s, p.substring(2))) || (isFirstMatch && isMatch(s.substring(1), p));
        }
        else {
            return isFirstMatch && isMatch(s.substring(1), p.substring(1));
        }

    }

    //最长有效括号
    public int longestValidParentheses(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                }
                else {
                    max = Math.max(i - stack.peek(), max);
                }
            }
        }
        return max;
    }

    public int longestValidParentheses1(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len];
        dp[0] = 0;
        int longest = 0;
        for (int i = 1; i < len; i++) {
            if ('(' == s.charAt(i)) {
                dp[i] = 0;
            }
            else if (')' == s.charAt(i)) {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i > 2 ? dp[i - 2] : 0) + 2;
                }
                else if (s.charAt(i - 1) == ')' && i - dp[i - 1] >= 1) {
                    if (s.charAt(i - dp[i - 1] - 1) == '(') {
                        dp[i] = dp[i - 1] + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                }
            }
            if (dp[i] > longest) {
                longest = dp[i];
            }
        }
        return longest;
    }

    //最大子序和
    public int maxSubArray(int[] nums) {
        if (null == nums || nums.length == 0) {
            return 0;
        }

        int maxHere = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxHere = Math.max(nums[i], maxHere + nums[i]);
            max = Math.max(max, maxHere);
        }
        return max;
    }

    //螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> output = new ArrayList<>();
        int row = matrix.length;
        int line = matrix[0].length;
        int loopNum = Math.min(row, line) / 2 + Math.min(row, line) % 2;
        for (int i = 0; i < loopNum; i++) {
            for (int a = i; a < line - i; a++) {
                output.add(matrix[i][a]);
            }
            for (int b = i + 1; b < row - i; b++) {
                output.add(matrix[b][line - i - 1]);
            }
            for (int c = line - i - 2; c >= i && row - 1 - i > i; c--) {
                output.add(matrix[row - 1 - i][c]);
            }
            for (int d = row - 2 - i; d >= i + 1 && i < line - i - 1; d--) {
                output.add(matrix[d][i]);
            }
        }
        return output;
    }

    //跳跃游戏
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        boolean[] dp = new boolean[nums.length];
        dp[0] = true;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && nums[j] >= i - j) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[nums.length - 1];
    }

    //逆波兰表达式求值
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (isDigit(tokens[i])) {
                stack.push(Integer.parseInt(tokens[i]));
            }
            else {
                int op2 = stack.pop();
                int op1 = stack.pop();
                stack.push(cal(op1, op2, tokens[i]));
            }
        }
        return stack.pop();

    }

    public static int cal(int op1, int op2, String token) {
        int ret = 0;
        switch (token) {
            case "*":
                ret = op1 * op2;
                break;
            case "+":
                ret = op1 + op2;
                break;
            case "-":
                ret = op1 - op2;
                break;
            case "/":
                ret = op1 / op2;
                break;
        }
        return ret;
    }

    public static boolean isDigit(String st) {
        Pattern pattern = Pattern.compile("^-?[0-9]+(\\.[0-9]+)?$");
        Matcher matcher = pattern.matcher(st);
        return matcher.matches();
    }

    // 解析数学表达式，包含MIN、MAX
    public Integer transform(String input) {
        input = input.replaceAll("[+\\-\\*\\/,()]|MAX|MIN", " $0 ");
        String[] split = input.split("\\s+");
        List<String> array = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < split.length; i++) {
            if ("MAX".equals(split[i]) || "MIN".equals(split[i])) {
                stack.push(split[i]);
            }
            else if (",".equals(split[i])) {
                array.add(stack.pop());
            }
            else if (!"".equals(split[i])) {
                array.add(split[i]);
            }
        }

        List<String> post = new ArrayList<>();
        Stack<String> postStack = new Stack<>();
        for (String each : array) {
            if (!isOperator(each)) {
                post.add(each);
            }
            else {
                if (postStack.isEmpty() || "(".equals(each)) {
                    postStack.add(each);
                }
                else if (")".equals(each)) {
                    String tmp = postStack.pop();
                    while (!tmp.equals("(") && !postStack.isEmpty()) {
                        post.add(tmp);
                        tmp = postStack.pop();
                    }
                }
                else {
                    while (getPriority(postStack.peek()) > getPriority(each)) {
                        post.add(postStack.pop());
                    }
                    postStack.push(each);
                }
            }
        }
        while (!postStack.isEmpty()) {
            post.add(postStack.pop());
        }

        Stack<Integer> calStack = new Stack<>();
        for (String each : post) {
            if (!isOperator(each)) {
                calStack.push(Integer.valueOf(each));
            }
            else {
                int op2 = Integer.valueOf(calStack.pop());
                int op1 = Integer.valueOf(calStack.pop());
                calStack.push(calculate(op1, op2, each));
            }
        }

        return calStack.pop();
    }

    private int calculate(int op1, int op2, String each) {
        int ret = 0;
        switch (each) {
            case "*":
                ret = op1 * op2;
                break;
            case "+":
                ret = op1 + op2;
                break;
            case "-":
                ret = op1 - op2;
                break;
            case "/":
                ret = op1 / op2;
                break;
            case "MAX":
                ret = op1 > op2 ? op1 : op2;
                break;
            case "MIN":
                ret = op1 < op2 ? op1 : op2;
                break;
        }
        return ret;
    }

    private int getPriority(String op) {
        int ret = 0;
        switch (op) {
            case "MAX":
            case "MIN":
                ret = 1;
                break;
            case "+":
            case "-":
                ret = 2;
                break;
            case "*":
            case "/":
                ret = 3;
                break;
            default:
                ret = 0;
                break;
        }
        return ret;
    }

    private boolean isOperator(String op) {
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("MAX") || op
                .equals("MIN") || op.equals("(") || op.equals(")");
    }

    //盛最多水的容器
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int max = 0;
        while (i < j) {
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j]) {
                i++;
            }
            else {
                j--;
            }
        }
        return max;
    }

    //整数转罗马数字
    public String intToRoman(int num) {
        int flag = 1;
        int left = num / 10;
        int mod = num % 10;
        StringBuilder builder = new StringBuilder();
        while (left != 0 || mod != 0) {
            builder.insert(0, getCurrent(mod, flag));
            flag++;
            mod = left % 10;
            left = left / 10;
        }
        return builder.toString();
    }

    private String getCurrent(int mod, int flag) {
        Map<Integer, String> mapSmall = new HashMap<>();
        mapSmall.put(1, "I");
        mapSmall.put(2, "X");
        mapSmall.put(3, "C");
        mapSmall.put(4, "M");
        Map<Integer, String> mapBig = new HashMap<>();
        mapBig.put(1, "V");
        mapBig.put(2, "L");
        mapBig.put(3, "D");
        if (4 == mod) {
            return mapSmall.get(flag) + mapBig.get(flag);
        }
        if (9 == mod) {
            return mapSmall.get(flag) + mapSmall.get(flag + 1);
        }
        if (0 == mod) {
            return "";
        }
        if (mod < 4) {
            return String.join("", Collections.nCopies(mod, mapSmall.get(flag)));
        }
        else {
            return mapBig.get(flag) + String.join("", Collections.nCopies(mod - 5, mapSmall.get(flag)));
        }
    }

    //交换字符使得字符串相同
    public int minimumSwap(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return -1;
        }

        int x = 0;
        int y = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                continue;
            }
            if ('x' == s1.charAt(i)) {
                x++;
            }
            else {
                y++;
            }
        }
        if (x % 2 == y % 2) {
            return x % 2 == 0 ? (x + y) / 2 : x / 2 + y / 2 + 2;
        }
        return -1;
    }

    //递减元素使数组呈锯齿状
    public int movesToMakeZigzag(int[] nums) {
        int old = 0;
        int even = 0;
        for (int i = 0; i < nums.length; i++) {
            int min;
            if (i == 0) {
                min = nums[i + 1];
            }
            else if (i == nums.length - 1) {
                min = nums[i - 1];
            }
            else {
                min = Math.min(nums[i + 1], nums[i - 1]);
            }
            int num = min > nums[i] ? 0 : nums[i] - min + 1;
            if (i % 2 == 0) {
                even += num;
            }
            else {
                old += num;
            }
        }
        return Math.min(old, even);
    }

    //颜色分类
    public void sortColors(int[] nums) {
        int p0 = 0, p2 = nums.length - 1;
        int cur = 0;
        while (cur <= p2 && p0 <= p2) {
            if (nums[cur] == 1 || cur < p0) {
                cur++;
                continue;
            }
            if (0 == nums[cur]) {
                if (p0 != cur) {
                    int tmp = nums[p0];
                    nums[p0] = nums[cur];
                    nums[cur] = tmp;
                }
                p0++;
            }
            else {
                int tmp = nums[p2];
                nums[p2] = nums[cur];
                nums[cur] = tmp;
                p2--;
            }
        }
    }

    //括号生成
    public List<String> generateParenthesis(int n) {
        List<String> output = new ArrayList<>();
        for (int i = 1; i < 2 * n - 1; i++) {
            for (int j = i + 1; j < 2 * n - 1; j++) {
                StringBuilder builder = new StringBuilder();
                builder.append('(');
                builder.append(String.join("", Collections.nCopies(i - 1, ")")));
                builder.append("(");
                builder.append(String.join("", Collections.nCopies(j - 1 - i, ")")));
                builder.append("(");
                builder.append(String.join("", Collections.nCopies(2 * n - 1 - j, ")")));
                if (isValid(builder)) {
                    output.add(builder.toString());
                }
            }
        }
        return output;
    }

    private boolean isValid(StringBuilder builder) {
        Stack<Character> stack = new Stack();
        for (int i = 0; i < builder.length(); i++) {
            if ('(' == builder.charAt(i)) {
                stack.push(builder.charAt(i));
            }
            else {
                if (stack.peek() == '(') {
                    stack.pop();
                }
            }
        }
        return stack.isEmpty();
    }

    //合并区间
    public int[][] merge(int[][] intervals) {
        for (int i = 0; i < intervals.length; i++) {
            for (int j = i; j < intervals.length; j++) {
                if (intervals[j][0] < intervals[i][0]) {
                    int[] tmp = intervals[j];
                    intervals[j] = intervals[i];
                    intervals[i] = tmp;
                }
            }
        }

        List<int[]> list = new ArrayList<>();

        int idx = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (0 == i) {
                list.add(intervals[0]);
                continue;
            }
            if (intervals[i][0] <= list.get(idx)[1] && intervals[i][0] >= list.get(idx)[0]) {
                list.get(idx)[0] = Math.min(intervals[i][0], list.get(idx)[0]);
                list.get(idx)[1] = Math.max(list.get(idx)[1], intervals[i][1]);
            }
            else {
                list.add(intervals[i]);
                idx++;
            }
        }
        int[][] result = new int[list.size()][];
        return list.toArray(result);
    }

    //优势洗牌
    public int[] advantageCount(int[] A, int[] B) {
        List<Integer> listA = IntStream.of(A).boxed().collect(Collectors.toList());
        listA.sort(Comparator.naturalOrder());
        int[] C = new int[A.length];
        for (int i = 0; i < B.length; i++) {
            boolean flag = false;
            int idx = Integer.MIN_VALUE;
            for (Integer each : listA) {
                if (each > B[i]) {
                    flag = true;
                    idx = each;
                    break;
                }
            }
            if (flag) {
                C[i] = idx;
                listA.remove(Integer.valueOf(C[i]));
            }
            else {
                C[i] = listA.get(0);
                listA.remove(Integer.valueOf(C[i]));
            }
        }
        return C;
    }

    //汉明距离总和
    public int totalHammingDistance(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                String a = Integer.toBinaryString(nums[i]);
                String b = Integer.toBinaryString(nums[j]);
                for (int n = 0; n < Math.min(a.length(), b.length()); n++) {
                    if (a.charAt(a.length() - 1 - n) != b.charAt(b.length() - 1 - n)) {
                        sum++;
                    }
                }
                if (a.length() != b.length()) {
                    String tt = a.length() > b.length() ? a : b;
                    for (int n = Math.min(a.length(), b.length()); n < tt.length(); n++) {
                        if ('1' == tt.charAt(tt.length() - 1 - n)) {
                            sum++;
                        }
                    }
                }

            }
        }
        return sum;
    }

    public int totalHammingDistance1(int[] nums) {
        if (null == nums || nums.length == 0){
            return 0;
        }
        int len = nums.length;

        int sum = 0;

        for (int i=0; i<30;i++){
            int oneCount = 0;
            int zeroCount = 0;
            for (int j=0;j<len;j++){
                int tmp = nums[j] & 1;
                if (1 == tmp){
                    oneCount++;
                }
                nums[j] = nums[j] >> 1;
                if (nums[j] == 0){
                    zeroCount ++;
                }
            }
            sum += oneCount*(len - oneCount);
            if (zeroCount == len){
                break;
            }
        }
        return sum;
    }
}

