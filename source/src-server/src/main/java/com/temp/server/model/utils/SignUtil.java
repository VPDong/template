package com.temp.server.model.utils;


import org.springframework.util.DigestUtils;

import java.util.*;

public class SignUtil {
    private static final String SEC_KEY = "0_Earth_Asia_China_Beijing";

    /**
     * 算法实现:将参数集合按照参数名的ASCII排列，
     * 把排序后的结果按照【参数+参数值+&】的方式拼接，再加上secretKey=secretKeyValue
     * 拼装好的字符串按MD5(p1=v1&p2=v2&p3=v3&secretKey=secretKeyVal)进行md5加密后，转大写
     *
     * @param params    参数集合
     * @param secretKey 秘钥
     * @return 签名
     */
    public static String sign(Map<String, Object> params, String secretKey) {
        if (secretKey == null) secretKey = SEC_KEY;

        // 对原参数集合按照ASCII顺序进行排序
        List<Map.Entry<String, Object>> kvsList = new ArrayList<>(params.entrySet());
        kvsList.sort(Comparator.comparing(obj -> (String.valueOf(obj.getKey().charAt(0)))));
        SortedMap<String, Object> parameters = new TreeMap<>();
        for (Map.Entry<String, Object> kv : kvsList) {// 根据key进行排序ASCII顺序
            String[] split = kv.toString().split("=");
            parameters.put(split[0], split.length == 1 ? null : split[1]);
        }

        // 把排序结果按照参数+参数值的方式拼接
        StringBuilder kvsStr = new StringBuilder();
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            // 去掉带sign的项
            if (null != val && !"".equals(val) && !"sign".equals(key) && !"secretKey".equals(key)) {
                kvsStr.append(key).append("=").append(val).append("&");
            }
        }
        kvsStr.append("secretKey=").append(secretKey);

        // 拼装好的字符串按secretKey进行md5加密后，转大写
        return DigestUtils.md5DigestAsHex(kvsStr.toString().getBytes()).toUpperCase();
    }
}
