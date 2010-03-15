/**
 * 
 */
package com.ydk.account.utility;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author Zhang Yu 
 */

public class YdkRandomString {

 


 public static String getRandomString(int nRandomCount) {
  
   String[] a = { /*"0", "1", "2",*/ "3", "4", "5", "6", "7", "8", "9", "A",
   "B", "C", "D", "E", "F", "G", "H", "I", "G", "K", "L", "M", "N", "O",
   "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c",
   "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
   "r", "s", "t", "u", "v", "w", "x", "y", "z" };
   
//  String[] a = { "a", "b", "c", "d", "e", "f", "g", "h", "g", "k", "m",
//    "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
  String strRand = "";
  int nRand;
  
  
  int LengthOfRandom = a.length;
  

  Random random = new Random();
  
  for (int i = 0; i < nRandomCount; i++) {
   nRand = random.nextInt(LengthOfRandom);
   strRand += a[nRand];
  }
  
  return strRand;
 }
 
}
