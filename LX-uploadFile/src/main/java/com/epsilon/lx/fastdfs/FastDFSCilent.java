package com.epsilon.lx.fastdfs;

import com.epsilon.lx.enums.ErrorCode;
import com.epsilon.lx.exception.FastDFSException;
import com.epsilon.lx.fastdfs.pool.ConnectionPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class FastDFSCilent {

    private ConnectionPool FDFSPool = new ConnectionPool();

    /**
     * 文件最大的大小
     */
    private int maxFileSize = 100 * 1000 * 1000;

    /**
     * 路径分隔符 linux系统分隔符
     */
    public static final String SEPARATOR = "/";
    /**
     * Point
     */
    public static final String POINT = ".";
    /**
     * 文件名称Key
     */
    private static final String FILENAME = "filename";


    /**
     * 使用 MultipartFile 上传
     *
     * @param file         MultipartFile
     * @param descriptions 文件描述信息
     * @return 文件路径
     * @throws FastDFSException file为空则抛出异常
     */
    public String upload(MultipartFile file, Map<String, String> descriptions) throws FastDFSException {
        if (file == null || file.isEmpty()) {
            throw new FastDFSException(ErrorCode.FILE_ISNULL);
        }
        String path = null;
        try {
            path = upload(file.getInputStream(), file.getOriginalFilename(), descriptions);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FastDFSException(ErrorCode.FILE_ISNULL);
        }
        return path;
    }

    /**
     * 上传通用方法
     * @param is  流
     * @param filename  文件名
     * @param description  文件描述
     * @return 组名+文件路径，如：group1/M00/00/00/wKgz6lnduTeAMdrcAAEoRmXZPp870.jpeg
     * @throws FastDFSException
     */
    public String upload(InputStream is, String filename, Map<String, String> description) throws FastDFSException {
        if (is == null) {
            throw new FastDFSException(ErrorCode.FILE_ISNULL);
        }
        try {
            if (is.available() > maxFileSize) {
                throw new FastDFSException(ErrorCode.FILE_OUT_SIZE);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        filename = toLocal(filename);
        String path=null;

        // 文件描述
        NameValuePair[] nvps = null;
        List<NameValuePair> nvpsList = new ArrayList<>();
        // 文件名后缀
        String suffix = getFilenameSuffix(filename);
        if (!(suffix.equalsIgnoreCase("mp4")
                || suffix.equalsIgnoreCase("jpg")
                || suffix.equalsIgnoreCase("png")))
            throw new FastDFSException(ErrorCode.FILE_TYPE_ERROR,"上传格式必须为MP4、jpg、png");
        // 文件名
        if (StringUtils.isNotBlank(filename)) {
            nvpsList.add(new NameValuePair(FILENAME, filename));
        }
        // 描述信息
        if (description != null && description.size() > 0) {
            description.forEach((key, value) -> {
                nvpsList.add(new NameValuePair(key, value));
            });
        }
        if (nvpsList.size() > 0) {
            nvps = new NameValuePair[nvpsList.size()];
            nvpsList.toArray(nvps);
        }

        TrackerServer trackerServer = FDFSPool.checkout(log.getName());
        StorageClient1 storageClient = new StorageClient1(trackerServer,null);
        try {
            // 读取流
            byte[] fileBuff = new byte[is.available()];
            is.read(fileBuff, 0, fileBuff.length);

            // 上传
            path = storageClient.upload_file1(fileBuff, suffix, nvps);

            if (StringUtils.isBlank(path)) {
                throw new FastDFSException(ErrorCode.FILE_UPLOAD_FAILED);
            }

            if (log.isDebugEnabled()) {
                log.debug("upload file success, return path is {}", path);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new FastDFSException(ErrorCode.FILE_UPLOAD_FAILED);
        } catch (MyException e) {
            e.printStackTrace();
            throw new FastDFSException(ErrorCode.FILE_UPLOAD_FAILED);
        } finally {
            // 关闭流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FDFSPool.checkin(trackerServer,log.getName());
        }
        return path;
    }

    /**
     * 获取文件名称的后缀
     *
     * @param filename 文件名 或 文件路径
     * @return 文件后缀
     */
    public static String getFilenameSuffix(String filename) {
        String suffix = null;
        String originalFilename = filename;
        if (StringUtils.isNotBlank(filename)) {
            if (filename.contains(SEPARATOR)) {
                filename = filename.substring(filename.lastIndexOf(SEPARATOR) + 1);
            }
            if (filename.contains(POINT)) {
                suffix = filename.substring(filename.lastIndexOf(POINT) + 1);
            } else {
                if (log.isErrorEnabled()) {
                    log.error("filename error without suffix : {}", originalFilename);
                }
            }
        }
        return suffix;
    }



    /**
     * 转换路径中的 '\' 为 '/' <br>
     * 并把文件后缀转为小写
     *
     * @param path 路径
     * @return
     */
    public static String toLocal(String path) {
        if (StringUtils.isNotBlank(path)) {
            path = path.replaceAll("\\\\", SEPARATOR);

            if (path.contains(POINT)) {
                String pre = path.substring(0, path.lastIndexOf(POINT) + 1);
                String suffix = path.substring(path.lastIndexOf(POINT) + 1).toLowerCase();
                path = pre + suffix;
            }
        }
        return path;
    }
}
