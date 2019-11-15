package com.jiangconglin.algrithm.sorting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * SortingUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Aug 28, 2019</pre>
 */
public class SortingUtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: insertSorting(int[] input)
     */
    @Test
    public void testInsertSorting() throws Exception {
        int[] a = new int[]{3, 2, 6, 5, 0, 8, 4};
        a = SortingUtil.insertSorting(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }


} 
