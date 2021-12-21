package com.zkzy.portal.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 *
 * @author admin
 */
public class StringHelper extends StringUtils {

    /**
     * 字符连接符
     */
    private static final char SEPARATOR = '_';
    /**
     * 默认编码
     */
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * 转换为字节数组
     *
     * @param str 字符串
     * @return byte[] byte [ ]
     */
    public static byte[] getBytes(String str) {
        if (str == null) {
            throw new NullPointerException("str cannot be null");
        }
        return str.getBytes(DEFAULT_CHARSET);
    }

    /**
     * 转换为字节数组
     *
     * @param bytes 字节数组
     * @return String string
     */
    public static String toString(byte[] bytes) {
        return new String(bytes, DEFAULT_CHARSET);
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true boolean
     */
    public static boolean inString(String str, String... strs) {
        if (str != null) {
            for (String s : strs) {
                if (str.equals(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 替换掉HTML标签方法
     *
     * @param html the html
     * @return the string
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        return m.replaceAll("");
    }

    /**
     * 驼峰命名法工具
     *
     * @param s the s
     * @return String  toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") == "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }

        String ls = s.toLowerCase();

        StringBuilder sb = new StringBuilder(ls.length());
        boolean upperCase = false;
        for (int i = 0; i < ls.length(); i++) {
            char c = ls.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    /**
     * 驼峰命名法工具
     *
     * @param s the s
     * @return String  toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") == "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        String cs = toCamelCase(s);
        return cs.substring(0, 1).toUpperCase() + cs.substring(1);
    }

    /**
     * 驼峰命名法工具
     *
     * @param s the s
     * @return String  toCamelCase("hello_world") == "helloWorld" toCapitalizeCamelCase("hello_world") == "HelloWorld" toUnderScoreCase("helloWorld") = "hello_world"
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * Trim to default string.
     *
     * @param str          字符串
     * @param defaultValue 默认值
     * @return String string
     */
    public static String trimToDefault(final String str, String defaultValue) {
        final String ts = trim(str);
        return isEmpty(ts) ? defaultValue : ts;
    }

    /**
     * 将字符串全部转换成大写
     *
     * @param str
     * @return
     */
    public static String upperCase(String str) {
        StringBuffer sb = new StringBuffer();
        if (str != null) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                sb.append(Character.toUpperCase(c));
            }
        }
        return sb.toString();
    }

    /**
     * 判断CharSequence的长度是否大于0
     *
     * @author majianbo Mar 10, 2011 9:46:08 AM
     * @param charSequence
     * @return true 长度大于0 false 长度为0或为空
     */
    public static boolean hasLength(CharSequence charSequence) {
        return (charSequence != null) && (charSequence.length() > 0);
    }

    /**
     * 判断字符串长度是否大于0
     *
     * @author majianbo Mar 10, 2011 9:46:50 AM
     * @param string
     * @return true 长度大于0 false 长度为0或为空
     */
    // public static boolean hasLength(String string) {
    // return (string != null) && (string.length() > 0) ;
    // }
    /**
     * 判断CharSequence中是否有内容 如果是以下空格字符则算空内容 它是 Unicode 空格字符（SPACE_SEPARATOR、LINE_SEPARATOR 或
     * PARAGRAPH_SEPARATOR),但不是非中断空格（'\u00A0'、'\u2007'、'\u202F'） 它是 '\u0009',HORIZONTAL TABULATION 它是 ' ',LINE FEED 它是
     * '\u000B',VERTICAL TABULATION 它是 '\u000C',FORM FEED 它是 ' ',CARRIAGE RETURN 它是 '\u001C',FILE SEPARATOR 它是 '\u001D',GROUP
     * SEPARATOR 它是 '\u001E',RECORD SEPARATOR 它是 '\u001F',UNIT SEPARATOR
     *
     * @author majianbo Mar 10, 2011 10:04:43 AM
     * @param charSequence
     * @return true 有内容 false 没有内容
     */
    public static boolean hasText(CharSequence charSequence) {
        if (!hasLength(charSequence))
            return false;
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(charSequence.charAt(i)))
                return true;
        }
        return false;
    }

    /**
     * 将字符串的首字母大写
     *
     * @author majianbo Mar 10, 2011 9:04:54 PM
     * @param string
     * @return
     */
    public static String upperFirstChar(String string) {
        //Assert.notNull(string);
        assert(null!=string);
        return toUpperCase(string.substring(0, 1)) + string.substring(1);
    }

    /**
     * 将字符串的首字母小写
     *
     * @author majianbo Mar 10, 2011 9:07:25 PM
     * @param string
     * @return
     */
    public static String lowerFirstChar(String string) {
        //Assert.notNull(string);
        assert(null!=string);
        return toLowerCase(string.substring(0, 1)) + string.substring(1);
    }

    /**
     * 将某个字符串按某个特殊的字符进行驼峰式命名如(int_str_float变成intStrFloat)
     *
     * @author majianbo Mar 10, 2011 10:07:55 PM
     * @param string
     * @param c
     * @return
     */
    public static String toCasing(String string, char c) {
        //Assert.notNull(string);
        assert(null!=string);
        StringBuilder builder = new StringBuilder();
        int length = string.length();
        for (int i = 0; i <= length - 1; i++) {
            char temp = string.charAt(i);
            if (c != temp) {
                builder.append(lowerFirstChar(String.valueOf(temp)));
            } else {
                // 下一个字符
                char next = string.charAt(i + 1);
                if (next != c) {
                    if (builder.toString().length() < 1) {
                        // 如果是字符串的首字母则小写
                        builder.append(lowerFirstChar(String.valueOf(next)));
                    } else {
                        // 如果不是首字母则大写
                        builder.append(upperFirstChar(String.valueOf(next)));
                    }
                    i++;
                }
            }
        }

        return builder.toString();
    }

    /**
     * 将字符串中带'_'特殊字符的字符串以驼峰式命名
     *
     * @author majianbo Mar 10, 2011 10:09:30 PM
     * @param string
     * @return
     */
    public static String toHumpCasing(String string) {
        String[] items = string.split("_");
        StringBuilder rs = new StringBuilder();
        rs.append(items[0]);
        for (int i = 1; i < items.length; i++) {
            String s = items[i];
            rs.append(s.substring(0, 1).toUpperCase() + s.substring(1));
        }
        return rs.toString();
    }

    /**
     * 将类似于驼峰命名的字符串装换成使用字符分割的字符串
     *
     * @param humpStr
     * @param c
     * @return
     */
    public static String fromHump(String humpStr, char c) {
        //Assert.notNull(humpStr);
        assert(null!=humpStr);
        int length = humpStr.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= length - 1; i++) {
            char temp = humpStr.charAt(i);
            if (Character.isUpperCase(temp)) {
                char lower = Character.toLowerCase(temp);
                if (builder.length() > 0) {
                    builder.append(c).append(lower);
                } else {
                    builder.append(lower);
                }
            } else {
                builder.append(temp);
            }
        }
        return builder.toString();
    }

    /**
     * 将驼峰命名的字符串转换成使用‘_’分割的字符串(数据库表字段名称)
     *
     * @param humpStr
     * @return
     */
    public static String fromHump(String humpStr) {
        return fromHump(humpStr, '_');
    }

    /**
     * 将为null的字符串和字符串字面值为"null"的字符串转为""返回
     *
     * @author majianbo Mar 10, 2011 9:35:25 AM
     * @param string
     * @return
     */
    public static String nullToEmpty(String string) {
        return string == null || "NULL".equalsIgnoreCase(string) ? "" : string.trim();
    }

    /**
     * 将字符串数组用给定的连接符连接成字符串
     *
     * @author majianbo Mar 10, 2011 10:17:18 AM
     * @param srcArray
     * @param delimiter
     * @return
     */
    public static String merge(String[] srcArray, String delimiter) {
        //Assert.notNull(srcArray);
        //Assert.notNull(delimiter);
        assert(null!=srcArray);
        assert(null!=delimiter);
        int length = srcArray.length;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(srcArray[i]).append(delimiter);
        }
        int lengthBuilder = builder.toString().length();
        int delLength = delimiter.length();
        builder.replace(lengthBuilder - delLength, lengthBuilder, "");
        return builder.toString();
    }

    /**
     * 将字符串中某个子串用新的字符串代替(子串忽略大小写)
     *
     * @author majianbo Mar 10, 2011 11:18:31 AM
     * @param content
     * @param oldString
     * @param replaceString
     * @return
     */
    public static String replaceIgnoreCase(String content, String oldString, String replaceString) {
        //Assert.notNull(content);
        //Assert.notNull(oldString);
        //Assert.notNull(replaceString);
        assert(null!=content);
        assert(null!=oldString);
        assert(null!=replaceString);
        // 将所给的字符串均转换为小写
        String oldStr = oldString.toLowerCase(Locale.getDefault());
        String conStr = content.toLowerCase(Locale.getDefault());
        StringBuilder builder = new StringBuilder();
        int oldLength = oldStr.length();
        int conLength = conStr.length();
        String temp;
        for (int i = 0; i < conLength; i++) {
            // 小标是否大于字符串长度
            if (i + oldLength < conLength) {
                temp = conStr.substring(i, i + oldLength);
            } else {
                temp = conStr.substring(i, conLength);
            }
            if (oldStr.equals(temp)) {
                // 如果字符串相同，则替换成新的字符串
                builder.append(replaceString);
                // 下标移动
                i += oldLength - 1;
            } else {
                builder.append(content.charAt(i));
            }
        }
        return builder.toString();
    }

    /**
     * 将字符串中某个子串用新的字符串代替(子串区分大小写)
     *
     * @author majianbo Mar 10, 2011 12:02:05 PM
     * @param content
     * @param oldString
     * @param replaceString
     * @return
     */
    public static String replace(String content, String oldString, String replaceString) {
        //Assert.notNull(content);
        //Assert.notNull(oldString);
        //Assert.notNull(replaceString);
        assert(null!=content);
        assert(null!=oldString);
        assert(null!=replaceString);
        // 将所给的字符串均转换为小写
        StringBuilder builder = new StringBuilder();
        int oldLength = oldString.length();
        int conLength = content.length();
        String temp;
        for (int i = 0; i < conLength; i++) {
            // 小标是否大于字符串长度
            if (i + oldLength < conLength) {
                temp = content.substring(i, i + oldLength);
            } else {
                temp = content.substring(i, conLength);
            }
            if (oldString.equals(temp)) {
                // 如果字符串相同，则替换成新的字符串
                builder.append(replaceString);
                // 下标移动
                i += oldLength - 1;
            } else {
                builder.append(content.charAt(i));
            }
        }
        return builder.toString();
    }

    /**
     * 查找字符串在某个字符串数组中的位置
     *
     * @author majianbo Mar 10, 2011 11:52:22 AM
     * @param string
     * @param strArrays
     * @return -1表示不存在该字符串
     */
    public static int getStrIndex(String string, String[] strArrays) {
        //Assert.notNull(strArrays);
        //Assert.notNull(string);
        assert(null!=strArrays);
        assert(null!=string);
        for (int i = 0; i < strArrays.length; i++) {
            if (string.equals(strArrays[i]))
                return i;
        }
        return -1;
    }

    /**
     * 判断字符串是否包含中文
     *
     * @author wangyun Apr 1, 2011 8:54:11 AM
     * @param str
     * @return
     */
    public static boolean includeChinese(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if ((charArray[i] >= 0x4e00) && (charArray[i] <= 0x9fbb)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两个字符串是否相同(都为null为相同)
     *
     * @author majianbo Mar 26, 2012 5:01:28 PM
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        return str1 != null ? str1.equals(str2) : false;
    }

    /**
     * 将Null转换为默认值
     *
     * @author majianbo Jun 27, 2012 2:11:22 PM
     * @param value
     * @param defaultValue
     * @return
     */
    public static String getNotNullValue(String value, String defaultValue) {
        return value == null ? defaultValue : value.trim();
    }

    public static String getNotNullValue(Object value, String defaultValue) {
        return value == null ? defaultValue : value.toString();
    }

    /**
     * 将Null转换为""
     *
     * @author majianbo Jun 27, 2012 2:11:38 PM
     * @param value
     * @return
     */
    public static String safeNullValue(String value) {
        return getNotNullValue(value, "");
    }

    public static String safeNullValue(Object value) {
        return getNotNullValue(value, "");
    }

    /**
     * 将字符串转换为小写
     *
     * @author majianbo Aug 14, 2012 2:37:44 PM
     * @param value
     * @return
     */
    public static String toLowerCase(String value) {
        return value.toLowerCase(Locale.getDefault());
    }

    /**
     * 将字符串转换为大写
     *
     * @author majianbo Aug 14, 2012 2:37:57 PM
     * @param value
     * @return
     */
    public static String toUpperCase(String value) {
        return value.toUpperCase(Locale.getDefault());
    }

    /**
     * 将字符串根据长度填充前缀如 1 长度为4 填充0 则填充后为0001
     *
     * @author majianbo Aug 14, 2012 2:43:39 PM
     * @param value
     * @param length
     * @param prefix
     * @return
     */
    public static String fillPrefix(String value, int length, char prefix) {
        String result = value;
        if (!ObjectUtils.isStringEmpty(value)) {
            while (result.length() < length) {
                result = prefix + result;
            }
        }
        return result;
    }

    /**
     * 将1,2,3的字符串修改为'1','2','3'的形式
     *
     * @author majianbo Jul 10, 2013 10:26:52 AM
     * @param ids
     * @return
     */
    public static String convertQuote(String ids) {
        StringBuilder builder = new StringBuilder(100);
        if (ObjectUtils.isStringNotEmpty(ids)) {
            String[] arrayIds = ids.split(",");
            if (ObjectUtils.isArrayNotEmpty(arrayIds)) {
                for (String id : arrayIds) {
                    if (builder.length() > 0) {
                        builder.append(",");
                    }
                    builder.append("'").append(id).append("'");
                }
            }
        }
        return builder.toString();
    }

    /**
     * 将数组装换成如下的字符串(1,2,3),用于sql查询
     *
     * @author majianbo 2014-3-7 下午3:58:18
     * @param ids
     * @return
     */
    public static String convertBrackets(String[] ids) {
        String temp = "(-1)";
        if (ObjectUtils.isArrayNotEmpty(ids)) {
            temp = Arrays.toString(ids).replace("[", "(").replace("]", ")");
        }
        return temp;
    }

    /**
     * 是否含有指定的特殊字符
     *
     * @param:
     * @return:
     * @author:lidejun
     * @date:2015-7-28
     */
    public static boolean isSpecialChar(String str, String rex) {
        boolean isSpecial = false;

        int size = rex.length();
        for (int i = 0; i < size; i++) {
            if (str.indexOf(rex.charAt(i)) != -1) {
                isSpecial = true;
                break;
            }
        }

        return isSpecial;
    }

    /**
     * 传入一个字符,返回可以用在sql 语句  in后面的字符串,如 ;aaa,bbb,ccc,123  ==> 'aaa','bbb','ccc','123'
     * @param oldStr
     * @param oldRegex
     * @return
     */
    public static String sqlInParamStr(String oldStr,String oldRegex){
        if(oldStr==null || oldRegex==null){
            return "";
        }
        String[] arr=oldStr.split(oldRegex);
        for (int i=0;i<arr.length;i++){
            arr[i]="'"+arr[i]+"'";
        }
        return StringUtils.join(arr, ",");
    }

    public static void main(String[] args) {
        String string = "powerdevice_info";
        System.out.println(isSpecialChar(string, "\\/:*?\"<|'%>&"));
    }

}
