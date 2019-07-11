package com.util;

public class SearchTool {


    public static void main(String[] args) {
        System.out.println(decodeSpecialCharsWhenLikeUseSlash("_"));
    }


    /**
     * 解决sql搜索的特殊字符("_","%","'","="等)转义
     *
     * @
     *  需要转义的字符串
     * @return String
     */
    public static String decodeSpecialCharsWhenLikeUseSlash(String value) {
        String afterDecode = value;
        if (null != value) {
            afterDecode = value.replaceAll("'", "''");
            afterDecode = afterDecode.replaceAll("\\\\", "\\\\\\\\");
            afterDecode = afterDecode.replaceAll("%", "\\\\%");
            afterDecode = afterDecode.replaceAll("_", "\\\\_");
            afterDecode = afterDecode.replaceAll("=", "\\=");
        }
        return afterDecode;
    }
}
