package com.movitech.library.utils;

/**
 * 作者：Yann.Yang on 2016/6/28 17:41
 * 邮箱：Yann.Yang@movit-tech.com
 * 描述：数组工具类
 */
public class ArrayUtils {
    private static final String DELIMITER = ",";

    /**
     * Judge whether a array is null.
     *
     * @param array
     * @return
     */
    public static <T> boolean isEmpty(T[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * 遍历数组
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> String traverseArray(T[] array) {
        if (!isEmpty(array)) {
            int len = array.length;
            StringBuilder builder = new StringBuilder(len);
            int i = 0;
            for (T t : array) {
                if (t == null) {
                    continue;
                }
                builder.append(t.toString());
                i++;
                if (i < len) {
                    builder.append(DELIMITER);
                }
            }
            return builder.toString();
        }
        return null;
    }
}
