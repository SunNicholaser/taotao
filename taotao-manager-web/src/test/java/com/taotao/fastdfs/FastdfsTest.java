package com.taotao.fastdfs;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by ASUS on 2017/10/31.
 */
public class FastdfsTest {
    @Test
    public void testUpload() throws IOException, MyException {
        ClientGlobal.init("D:\\github\\taotao\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageServer storageServer=null;
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);
        String[] strings = storageClient.upload_file("C:\\Users\\ASUS\\Desktop\\图片1.jpg", "jpg", null);
        for (String string: strings
             ) {
            System.out.println(string);

        }

    }
    @Test
    public void testFastDfsClient() throws Exception{
        FastDFSClient client = new FastDFSClient("D:\\github\\taotao\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        String s = client.upLoadFile("C:\\Users\\ASUS\\Desktop\\图片1.jpg");
        System.out.println(s);
    }
}
