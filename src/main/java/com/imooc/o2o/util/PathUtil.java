package com.imooc.o2o.util;

public class PathUtil {
    // 获取系统分隔符
    private static String separator = System.getProperty("file.separator");

    public static String getImgBasePath() {
        // 根据不同的系统去选择根路径
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
//            basePath = "F:/project/image";
            basePath = "F:/java/project2/src/main/webapp/resources";
        } else {
            basePath =  "home/cuzz/image";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    public static String getShopImagePath(long shopId) {
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }
}
