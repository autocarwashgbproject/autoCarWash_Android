package com.example.myapplication;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getMillisFromDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("DD.MM.YYYY HH:mm", Locale.getDefault());
        System.out.println(sdf.parse("23.04.2019 15:23").getTime());
        System.out.println(sdf.parse("10.05.2019 16:10").getTime());
        System.out.println(sdf.parse("03.06.2019 09:54").getTime());
        System.out.println(sdf.parse("13.06.2019 21:01").getTime());
        System.out.println(sdf.parse("18.07.2019 19:27").getTime());
    }

}