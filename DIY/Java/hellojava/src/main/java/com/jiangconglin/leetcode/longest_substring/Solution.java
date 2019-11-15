package com.jiangconglin.leetcode.longest_substring;

import java.util.HashMap;

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        if (null == s || s.length() == 0) {
            return 0;
        }
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            StringBuilder subString = new StringBuilder();
            subString.append(s.charAt(i));
            int temMax = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (subString.indexOf(s.substring(j, j + 1)) < 0) {
                    subString.append(s.charAt(j));
                    temMax++;
                } else {
                    break;
                }
            }
            if (temMax > maxLength) {
                maxLength = temMax;
            }
        }
        return maxLength;
    }

    public int lengthOfLongestSubstring2(String s) {

        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(i, map.get(s.charAt(j))+1);
            }
            map.put(s.charAt(j), j);
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
}
