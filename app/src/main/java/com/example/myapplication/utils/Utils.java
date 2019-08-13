package com.example.myapplication.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean isValidMobile(String phone) {
        String regex = "^((\\+7|7|8)+([0-9]){10})$";
        Pattern ptrn = Pattern.compile(regex);
        Matcher matcher = ptrn.matcher(phone);
        return matcher.find();
    }
}
