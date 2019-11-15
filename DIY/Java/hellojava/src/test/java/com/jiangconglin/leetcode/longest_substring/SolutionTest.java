package com.jiangconglin.leetcode.longest_substring;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Solution Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Oct 13, 2019</pre>
 */
public class SolutionTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: lengthOfLongestSubstring(String s)
     */
    @Test
    public void testLengthOfLongestSubstring() throws Exception {
        assertEquals(3,new Solution().lengthOfLongestSubstring2("abacab"));
    }



} 
