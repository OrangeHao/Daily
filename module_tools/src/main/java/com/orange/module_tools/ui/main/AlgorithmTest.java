package com.orange.module_tools.ui.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * created by czh on 2019/2/13
 */
public class AlgorithmTest {

    public int numJewelsInStones(String J, String S) {
        int result=0;
        Set<Character> map= new HashSet<>();
        for (int i=0;i<J.length();i++){
            map.add(J.charAt(i));
        }
        for(int i=0;i<S.length();i++){
            if (map.contains(S.charAt(i))){
                result++;
            }
        }
        return result;
    }


    class Solution {
        public int romanToInt(String s) {
            int lenth=s.length();
            int result=0;
            for (int i=0;i<lenth;i++){
                int temp=getValueByChar(s.charAt(i));
                if (i<lenth-1 && temp<getValueByChar(s.charAt(i+1))){
                    result=result-temp;
                }else {
                    result=result+temp;
                }
            }
            return result;
        }

        public int getValueByChar(char a){
            switch (a){
                case 'I':
                    return 1;
                case 'V':
                    return 5;
                case 'X':
                    return 10;
                case 'L':
                    return 50;
                case 'C':
                    return 100;
                case 'D':
                    return 500;
                case 'M':
                    return 1000;
                default:
                    return 0;
            }
        }
    }

    //n皇后

    public static int sum;
    public int nQueens(int n) {
        // write code here
        sum=0;
        int[] cols=new int[n];
        helper(cols,n,0);

        return sum;
    }

    private void helper(int[] cols, int n, int row){
        if(row==n){
            sum++;
            return;
        }

        for(int i=0;i<n;i++){
            if(isValid(cols,row,i)){
                cols[row]=i;
                helper(cols,n,row+1);
            }
        }
    }


    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res=new ArrayList<>();
        int[]des=new int[n];
        for (int i=0;i<n;i++){
            des[i]=-1;
        }
        helpers(des,n,0,res);
        return res;
    }

    private void helpers(int [] des,int n,int row,List<List<String>> res){
        if (n==row){
            List<String> one=new ArrayList<>();
            for (int i=0;i<n;i++){
                StringBuilder sb=new StringBuilder();
                for (int j=0;j<n;j++){
                    if (des[i]==j){
                        sb.append("Q");
                    }else {
                        sb.append(".");
                    }
                }
                one.add(sb.toString());
            }
            res.add(one);
            return;
        }
        for (int i=0;i<n;i++){
            if (isValid(des,row,i)){
                des[row]=i;
                helpers(des,n,row+1,res);
            }
        }
    }

    private boolean isValid(int[] des,int row,int col){
        for (int i=0;i<row;i++){
            if (des[i]==col || (row-i)==(Math.abs(col-des[i]))){
                return false;
            }
        }
        return true;
    }



    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) {
            minLen = Math.min(minLen, str.length());
        }
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle)) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return strs[0].substring(0, (low + high) / 2);
    }

    private boolean isCommonPrefix(String[] strs, int len){
        String str1 = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }


    //有序括号

    private HashMap<Character, Character> mappings;
    public void Solution() {
        this.mappings = new HashMap<>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }
    public boolean isValid(String s) {
      Stack<Character> stack=new Stack<>();
      for (int i=0;i<s.length();i++){
          Character temp=s.charAt(i);
          if (mappings.containsKey(temp)){
              Character top=stack.isEmpty()?'#':stack.pop();
              if (!top.equals(mappings.get(temp))){
                return false;
              }
          }else {
              stack.push(temp);
          }
      }
      return stack.isEmpty();
    }



    //合并链表
    public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);
        ListNode cur=head;
        while (l1!=null && l2!=null){
            if (l1.val<=l2.val){
                cur.next=l1;
                cur=cur.next;
                l1=l1.next;
            }else {
                cur.next=l2;
                cur=cur.next;
                l2=l2.next;
            }
        }
        if (l1==null){
            cur.next=l2;
        }else {
            cur.next=l1;
        }
        return head.next;
    }


    //26. 删除排序数组中的重复项
    public int removeDuplicates(int[] nums) {
        if (nums.length==0){
            return 0;
        }
        int i=0;
        for (int j=1;j<nums.length;j++){
            if (nums[j]!=nums[i]){
                i++;
                nums[i]=nums[j];
            }
        }
        return i+1;
    }

    //27 Remove Element
    public int removeElement(int[] nums, int val) {
        if (nums.length==0){
            return 0;
        }
        int i=0;
        for (int j=0;j<nums.length;j++){
            if (nums[j]!=val){
                nums[i]=nums[j];
                i++;
            }
        }
        return i;
    }

    public int removeElement2(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }

}
