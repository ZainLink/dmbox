package com.zkzy.portal.common.web.util;

import com.zkzy.portal.common.upload.DiskFileOperator;
import com.zkzy.portal.common.upload.util.FileIndex;
import com.zkzy.portal.common.utils.FileHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Web 工具类
 *
 * @author admin
 */
public final class WebUtils {

    /**
     * 文件临时存储路径
     */
    private static final String TEMP_FILE_PATH = "/tmp";

    private WebUtils() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 文件类型转换
     *
     * @param file MultipartFile
     * @return File file
     */
    public static File transfer(MultipartFile file) {
        File upFile = new File(FileHelper.addEndSlash(new DiskFileOperator().getWorkFolderName()) + file.getOriginalFilename());
        try {
            file.transferTo(upFile);
        } catch (IllegalStateException | IOException ex) {
            ex.printStackTrace();
        }
        String a = upFile.getAbsolutePath();
        return upFile;
    }

    /**
     * 构建FileIndex
     *
     * @param file   MultipartFile
     * @param folder 文件路径
     * @return FileIndex file index
     */
    public static FileIndex buildFileIndex(MultipartFile file, String folder) {
        return new FileIndex(transfer(file), file.getOriginalFilename(), folder);
    }

    /**
     * 获取当前登录者对象
     *
     * @param <T> the type parameter
     * @return the current user
     */
    @SuppressWarnings("unchecked")
    public static <T extends UserDetails> T getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (T) authentication.getPrincipal();
    }

}
