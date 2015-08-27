package com.example.sdn4rc2.service;

public class ServiceUtils {

  public static String nameLike(String name) {
    return "(?i).*" + (name != null ? name : "") + ".*";
  }

 public static String nvl(String s) {
   if (s != null && s.length() > 0) {
     return s;
   }
   return null;
 }

 public static String nvl(Integer i) {
   if (i != null && i > 0) {
     return i.toString();
   }
   return null;
 }

 public static Integer nvlToInt(String s) {
   if (s != null && s.length() > 0) {
     return Integer.valueOf(s);
   }
   return null;
 }

 public static boolean isNotEmpty(Integer i) {
   if (i != null && i > 0) {
     return true;
   }
   return false;
 }

}
