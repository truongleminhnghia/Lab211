/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Common tools in application
 *
 * @author Administrator
 */
public class MyTools {

    public static final Scanner sc = new Scanner(System.in);

    /**
     * Parsing the input string to get a boolean data (true/false)
     *
     * @param input: input string
     * @return true if the first character in input is 'T' or '1' or 'Y'
     */
    public static boolean parseBoolean(String input) {
        input = input.trim().toUpperCase(); // chuẩn hoá chuỗi
        char c = input.charAt(0); // lấy ký tự đầu của chuỗi nhập
        return c == 'T' || c == '1' || c == 'Y';
    }

    /**
     * Normalizing a date string: Using '-' to separate date parts only
     *
     * @param dateStr: input date string
     * @return new string
     */
    //" 7 ... ---2 //// 2023   " --> "7-2-2023"
    public static String normalizeDateStr(String dateStr) {
        //Loại bỏ tất cả các khaongr trống trong chuỗi nhập, dùng repaeAll()
        //với regex phù hợp " 7 ... ---2 //// 2023   " --> "7-2-2023"

        String result = dateStr.replaceAll("[\\s]+", "");
        //Thay thế tất cả các nhóm ký tự. / - bằng '-'. dùng replaceAll() với regex phù hợp
        // " 7 ... ---2 //// 2023   " --> "7-2-2023" rồi trả về kêt quả sau khi đã xử lý
        result = result.replaceAll("[./-]+", "-");
        return result;
    }

    /**
     * Parsing the input string to date data
     *
     * @param inutStr: date string input.
     * @param dateFormat: chosen fate format.
     * @return Date object if successful and null if failed
     */
    public static Date parseDate(String inputStr, String dateFormat) {
        inputStr = normalizeDateStr(inputStr); // chuẩn hoá chuỗi ngày tháng
        DateFormat formatter = new SimpleDateFormat(dateFormat); // Tạo DateFormat formatter
        try { // Dùng Formatter parse chuỗi nhập rồi trả kết quả
            return formatter.parse(inputStr);
        } catch (ParseException e) {
            System.err.print(e);
        }
        return null; // không phân tích thành công thì trả về null
    }

    /**
     * COnvert a Date object to string using a gievn date format
     *
     * @param date: Date object
     * @param dateFormat: chosen date format
     * @return date string in the given format
     */
    public static String toString(Date date, String dateFormat) {
        if (date == null) {
            return "";
        }
        //Tạo DateFormat object làm việc với định dạng trong tham số thứ hai
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        //rồi trả về kết quả sau khi đã chuyển dạng
        return formatter.format(date);
    }

    /**
     * Getting year of date data
     *
     * @param d: Date data
     * @param calendarPart: date part is declared in the class Calendar
     * @return year in date data.
     */
    public static int getPart(Date d, int calendarPart) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal.get(calendarPart);
    }

    // METHODS FOR READING DATA FROM KEYBOAD
    // Reading a boolean data 
    public static boolean readBoolean(String prompt) {
        System.out.println(prompt + ": ");
        return parseBoolean(sc.nextLine());
    }

    public static String inputStr(String pattern) {
        if (pattern != null) {
            System.out.print(pattern);
        }
        return sc.nextLine().trim();
    }

    /**
     * Reading a string using a pre-define patten
     *
     * @param prompt: prompt will be printed before inputting.
     * @param pattern: pre-define pattern of input
     * @return an input string which matches the pattern.
     */
    public static String readStr(String prompt, String pattern) {
        String input;
        boolean valid = false;
        do {
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            valid = input.matches(pattern);
        } while (valid == false);
        return input;
    }

    /**
     * Reading a date data using a pre-define date format dd-MM-yyyy /
     * MM-dd-yyyy / yyyy-MM-dd
     *
     * @param prompt: prompt will be printed before inputting
     * @param pattern: pre-define pattern of input
     * @return an input string which matches the pattern
     */
    public static Date readDate(String prompt, String dateFormat) {
        String input;
        Date d;
        do {
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            d = parseDate(input, dateFormat);
            if (d == null) {
                System.out.println("Date data is not valid!");
            }
        } while (d == null);
        return d;
    }

    public static LocalDate readLocalDate(String prompt, String dateFormat) {
        String input;
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        do {
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            try {
                date = LocalDate.parse(input, formatter);
            } catch (Exception e) {
                date = null;
            }
            if (date == null) {
                System.out.println("Date data is not valid!");
            }
        } while (date == null);
        return date;
    }

    //Nhập ngày tháng sau một ngày cho trước
    public static Date readDateAfter(String prompt, String dateFormat, Date markerDate) {
        String input;
        Date d;
        boolean ok = false;
        do {
            System.out.println(prompt + ": ");
            input = sc.nextLine().trim();
            d = parseDate(input, dateFormat);
            ok = (d != null && d.after(markerDate));
            if (d == null) {
                System.out.println("Date data is not valid!");
            }
        } while (!ok);
        return d;
    }

    //Nhập ngày tháng trước một ngày cho trước
    public static Date readDateBefore(String prompt, String dateFormat, Date markerDate) {
        String input;
        Date d;
        boolean ok = false;
        do {
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            d = parseDate(input, dateFormat);
            ok = (d != null && d.before(markerDate));
            if (d == null) {
                System.out.println("Date data is not valid!");
            }
        } while (!ok);
        return d;
    }

    // Automatically generatuing an increasing code
    // EX: P0000123 ->prefix: p, length + 7, curNumber = 123
    public static String generateCode(String prefix, int length, int curNumber) {
        String formatStr = "%0" + length + "d";
        return prefix + String.format(formatStr, curNumber);
    }

    public static void main(String[] args) {
        System.out.println("Test boolean string:");
        System.out.println(parseBoolean(" trUE "));
        System.out.println(parseBoolean(" fAlSe "));
        System.out.println(parseBoolean(" 1234 "));
        System.out.println(parseBoolean(" 01 23 "));
        System.out.println(parseBoolean(" total "));
        System.out.println(parseBoolean(" cosine "));
        System.out.println("Test normalizeDateStr(String):");
        String s = "  7 ... --- 2 / 2023  ";
        System.out.println(s + " --> " + normalizeDateStr(s));
        s = "  7 ... 2 //// 2023  ";
        System.out.println(s + " --> " + normalizeDateStr(s));

        //Test date <-->String
        System.out.println("\nTest Date <--> String");
        String[] formats = {"yyyy-MM-dd", "MM-dd-yyy", "dd-MM-yyyy"};
        String[] dStrs = {"  2023-02-21 ", "  12.--25 - 2023 ", "  7 .. 2// 2023"};
        Date d = null;
        for (int i = 0; i < 3; i++) {
            System.out.println(dStrs[i] + "(" + formats[i] + ")");
            d = parseDate(dStrs[i], formats[i]);
            if (d != null) {
                System.out.println("Year: " + getPart(d, Calendar.YEAR));
                System.out.println("-----> Result: " + d);
                System.out.println("-----> In the format: " + formats[i] + ": " + toString(d, formats[i]));
            } else {
                System.out.println("Parsing error!");
            }
        }

        //Test reading a boolean
        System.out.println("Test reading a boolean data");
        boolean b = readBoolean("Input a boolean data (T/F, 1/0, Y/N)");
        System.out.println(b + " inputted");

        //Test input a date data
        System.out.println("Test input a date data");
        d = readDate("Input a date data (dd-MM-yyyy)", "dd-MM-yyyy");
        System.out.println("Inputted date: ");
        System.out.println("In format dd-MM-yyyy: " + toString(d, "dd-MM-yyyy"));
        System.out.println("In format MM-dd-yyyy: " + toString(d, "MM-dd-yyyy"));
        System.out.println("In format yyyy-MM-dd: " + toString(d, "yyyy-MM-dd"));

        //Test inputting a phone number including 9..11 digits
        String phoneNo = readStr("Phone number(9..11 digits)", "[\\d][9,11]");
        System.out.println("Inputted phone no. :" + phoneNo);
        //Testing for generating an automatic code
        System.out.println("Testing for generating an automatic code");
        String prefix = "P";
        int curNumber = 25;
        int len = 7;
        String code = generateCode(prefix, len, curNumber);
        curNumber++;
        System.out.println("Generated code: " + code);
        code = generateCode(prefix, len, curNumber);
        System.out.println("Generated code: " + code);

        //Test reading date data before and after today.
        System.out.println("Testing reading date data before and after today");
        Date today = new Date();
        System.out.println("Today: " + MyTools.toString(d, "dd-MM-yyyy"));
        Date dBefore = MyTools.readDateBefore("Date before today", "dd-MM-yyyy", today);
        System.out.println(MyTools.toString(dBefore, "dd-MM-yyyy"));
        Date dAfter = MyTools.readDateAfter("Date after today", "dd-MM-yyyy", today);
        System.out.println(MyTools.toString(dAfter, "dd-MM-yyyy"));
    }
}
