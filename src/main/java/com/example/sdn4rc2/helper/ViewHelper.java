package com.example.sdn4rc2.helper;

public class ViewHelper {

  public String substring(String text, int max) {
    if (text != null && text.length() > 0) {
      if (text.length() > max) {
        return text.substring(0, max);
      } else {
        return text;
      }
    } else {
      return "";
    }
  }

}
