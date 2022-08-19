package com.drbhagwat;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
  /**
   * Given an integer, this method extracts each digit of that integer
   * and stores them into successive nodes of a linked list.
   *
   * @param i    - an integer - should not be negative
   * @param list - a list where you want to store each digit of the integer
   */
  public static void addIntToList(int i, List<Character> list) {
    if ((i < 0) || (list == null)) {
      throw new IllegalArgumentException("The given integer should be >=0 " +
          "and the list should not be null");
    }

    if (i <= 9) { // single digit int
      list.add((char) (i + '0'));
    } else { // the int is double digits or more

      while (i >= 10) {
        int remainder = i % 10;
        list.add((char) (remainder + '0'));
        i /= 10;
      }
      list.add((char) (i + '0'));
    }
  }

  /**
   * Given two lists, this method adds each digit stored in successive nodes
   * of those lists and stores the result into that new list
   *
   * @param list1 - first list, represents the first integer backwards
   * @param list2 - second list, represents the second integer backwards
   * @return - the new list, representing the sum, backwards
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
        Character first = list1.get(i + minimumSize);
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
          Character second = list2.get(i + minimumSize);
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