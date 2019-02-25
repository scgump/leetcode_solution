package com.leetcode.simon.algo.string;

import com.leetcode.simon.Solution;

/**
 * https://leetcode.com/problems/reverse-words-in-a-string/description/
 *
 * Given an input string, reverse the string word by word.
 *
 * A word is defined as a sequence of non-space characters.
 * Input string may contain leading or trailing spaces.
 * However, your reversed string should not contain leading or trailing spaces.
 * You need to reduce multiple spaces between two words to a single space in the reversed string.
 */
public class ReverseWords implements Solution {

    /**
     * entry
     */
    private String reverseWords(String s) {
        if (s == null) return null;
        char[] raw = s.toCharArray();

        // reverse the whole string
        reverseArray(raw, 0, raw.length-1);

        // detect each words and reverse
        reverseEachWord(raw);

        // clean spaces
        return cleanSpace(raw);
    }

    /**
     * reverse the char array in-place, within index [start, end]
     */
    private void reverseArray(char[] a, int start, int end) {
        char tmp;
        while (start < end) {
            tmp = a[start];
            a[start++] = a[end];
            a[end--] = tmp;
        }
    }

    /**
     * detect words in char array, and reverse the word in-place
     */
    private void reverseEachWord(char[] a) {
        int i = 0, j = 0;
        for (int k = 0; k < a.length; k++) {
            if (a[k] != ' ') {
                j++;
            } else {
                if (i != j) {
                    reverseArray(a, i, j-1);
                }
                i = j = k + 1;
           }
        }

        // push the last word if no tail space
        if (i != j) {
            reverseArray(a, i, j-1);
        }
    }

    /**
     * trim and merge spaces
     */
    private String cleanSpace(char[] a) {
        // trim
        int start = 0, end = a.length - 1;
        while (start <= end && a[start] == ' ') {
            start++;
        }
        while (start <= end && a[end] == ' ') {
            end--;
        }
        for (int i = 0; i < end-start+1; i++) {
            a[i] = a[start + i];
        }

        // merge spaces
        int p = 0, q = 0, len = 0;
        for (int i = 0; i < end-start+1; i++) {
            if (a[i] != ' ') {
                a[p] = a[q];
                p++;
                q++;
                len = p;
            } else {
                if (a[i-1] == ' ') {
                    q++;
                } else {
                    a[p] = a[q];
                    p++;
                    q++;
                }
            }
        }

        return new String(a).substring(0, len);
    }

    public void launchTestCase() {

        String s = "   the  sky   is    blue   ";

        System.out.println(reverseWords(s));
    }
}
