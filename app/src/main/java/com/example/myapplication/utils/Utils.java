package com.example.myapplication.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static String regex = "^((\\+7|7|8)+([0-9]){10})$";

    public static boolean isValidMobile(String phone) {
        Pattern ptrn = Pattern.compile(regex);
        Matcher matcher = ptrn.matcher(phone);
        return matcher.find();
    }
}
