package com.movitech.library.utils;

import com.alibaba.fastjson.util.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 作者：Yann.Yang on 2016/6/28 17:44
 * 邮箱：Yann.Yang@movit-tech.com
 * 描述：字节转化
 */
public class ByteUtils {
    /**
     * Convert byte array to object
     *
     * @param bytes
     * @return
     */
    public static Object byteToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            return ois.readObject();
        } finally {
            IOUtils.close(ois);
        }
    }

    /**
     * Convert object to byte array
     *
     * @param obj
     * @return
     */
    public static byte[] objectToByte(Object obj) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(oos);
            IOUtils.close(bos);
        }
        return null;
    }
}
