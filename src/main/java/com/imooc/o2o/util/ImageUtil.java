package com.imooc.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.imooc.o2o.util.PathUtil.getImgBasePath;

public class ImageUtil {
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random random = new Random();
    /**
     * 创建缩略图
     */
    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
        // 获取图片的随机图片名
        String realFileName = getRandomFileName();
        // 获取图片的拓展名
        String extension = getFileExtension(thumbnail);
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(getImgBasePath() + relativeAddr);
        try {
            Thumbnails.of(thumbnail.getInputStream()).size(200, 200).outputQuality(0.25f).toFile(dest);
        } catch (IOException e) {
            throw new RuntimeException("创建缩略图失败：" + e.toString());
        }
        return relativeAddr;
    }

    /**
     * 创建路径  /home/work/cuzz/xx.jpg
     * 那么 home work xiangze 这三个文件都自动创建
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        // 如果路径不存在就递归的创建
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的拓展名
     * @param cFile
     * @return
     */
    private static String getFileExtension(CommonsMultipartFile cFile) {
        // 获取原来的文件名
        String originalFileName = cFile.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名，当前年月日时分秒 + 5位随机数
     * @return
     */
    private static String getRandomFileName() {
        // 获取随机的5位数
        int rannum = random.nextInt(89999) + 10000;
        String nowTimestr = sDateFormat.format(new Date());
        return nowTimestr + rannum;
    }
}

