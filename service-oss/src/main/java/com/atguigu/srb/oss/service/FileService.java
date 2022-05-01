package com.atguigu.srb.oss.service;

import java.io.InputStream;

/**
 * @author wanfeng
 * @created 2022/4/30 20:39
 * @package com.atguigu.srb.oss.service
 */
public interface FileService {
    /**
     * 文件上传至阿里云
     */
    String upload(InputStream inputStream, String module, String fileName);

    /**
     * 根据路径删除文件
     * @param url
     */
    void removeFile(String url);
}
