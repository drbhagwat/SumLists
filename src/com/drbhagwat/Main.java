package com.drbhagwat;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
  /**
   * Given an integer, this method extracts each digit in that integer (from
   * last digit to the first digit) and stores each digit into successive
   * nodes of the linked list.
   *
   * @param i    - an integer - should not be negative
   * @param list - a list where you need to store each digit of the integer
   */
  public static void addIntToList(int i, List<Character> list) {
    if (i < 0) {
      throw new IllegalArgumentException("The given integer should be >=0");
    }

    if (i <= 9) { // the int is 9 max (handles 0-9)
      list.add((char) (i + '0'));
    } else { // the int is at least 10

      while (i >= 10) {
        int remainder = i % 10;
        list.add((char) (remainder + '0'));
        i = i / 10;
      }
      list.add((char) (i + '0'));
    }
    System.out.println(list);
  }

  /**
   * Given two lists, this method adds each digit stored in successive nodes
   * of those lists, creates a new list, and stores the result into that new
   * list
   *
   * @param list1 - first list, representing the first integer backwards
   * @param list2 - second list, representing the second integer backwards
   * @return - the sum representing the sum, backwards
   */
  public static List<Character> add(List<Character> list1,
                                    List<Character> list2) {
    List<Character> sumList = new LinkedList<>();
    int carry = 0;
    int size1 = list1.size();
    int size2 = list2.size();
    int minimumSize = Math.min(size1, size2);

    for (int i = 0; i < minimumSize; i++) {
      Character first = list1.get(i);
      Character second = list2.get(i);
      int sum = (first - '0') + (second - '0') + carry;

      if (sum > 9) {
        sum = sum % 10;
        carry = 1;
      } else {
        carry = 0;
      }
      sumList.add((char) (sum + '0'));
    }

    if (size1 > minimumSize) {
      for (int i = 0; i < (size1 - minimumSize); i++) {
        Character first = list1.get(i);
        int sum = (first - '0') + carry;

        if (sum > 9) {
          sum = sum % 10;
          carry = 1;
        } else {
          carry = 0;
        }
        sumList.add((char) (sum + '0'));
      }
    } else {
      if (size2 > minimumSize) {
        for (int i = 0; i < (size2 - minimumSize); i++) {
          Character second = list2.get(i);
          int sum = (second - '0') + carry;

          if (sum > 9) {
            sum = sum % 10;
            carry = 1;
          } else {
            carry = 0;
          }
          sumList.add((char) (sum + '0'));
        }
      }
    }
    if (carry == 1) {
      sumList.add((char) (carry + '0'));
    }
    return sumList;
  }

  public static void main(String[] args) {
    LinkedList<Character> firstList = new LinkedList<>();
    LinkedList<Character> secondList = new LinkedList<>();

    Scanner scanner = new Scanner(System.in);
    int firstInt = scanner.nextInt();
    addIntToList(firstInt, firstList);

    int secondInt = scanner.nextInt();
    addIntToList(secondInt, secondList);

    System.out.println(add(firstList, secondList));
  }
}