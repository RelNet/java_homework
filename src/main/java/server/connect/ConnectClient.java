package server.connect;

import server.connect.constant.MessageTypes;
import server.connect.error.UndefinedMessageType;

import java.io.*;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author h
 * 此类是客户端连接的任务类
 */
public class ConnectClient extends Socket implements Runnable {

    @Override
    public void run() {
        try {
            byte[] inputByte = new byte[1024];
            InputStream inputStream = this.getInputStream();

            while (true) {
                // 读取信息类型
                inputStream.read(inputByte);
                /**
                 * 如果是登陆，第一组字节是
                 */
                if (isLogin(inputByte)) {
                    inputStream.read(inputByte);
                    String loginMessage = new String(inputByte);
                    // loginMessage会以 // 分割id和password
                    int cutIndex = loginMessage.indexOf("//");

                    String id = loginMessage.substring(0, cutIndex);
                    String password = loginMessage.substring(cutIndex + 2);

                    // 验证数据库中的密码

                } else if (isRegister(inputByte)) {
//                    inputStream.read(inputByte);
//                    String loginMessage = new String(inputByte);
//                    // loginMessage会以 // 分割id和password
//                    int cutIndex = loginMessage.indexOf("//");
//
//                    String id = loginMessage.substring(0, cutIndex);
//                    String password = loginMessage.substring(cutIndex + 2);
//
//                    // 写入数据库
//
                } else if (isFile(inputByte)) {
                    inputStream.read(inputByte);
                    String toId = getId(inputByte);

                    // 数据库生成文件id
                    Integer fileId = null;

                    FileOutputStream fileOutputStream = new FileOutputStream("/tempFile/" + fileId.toString());
                    int length = 0;
                    while ((length = inputStream.read(inputByte)) > 0) {
                        fileOutputStream.write(inputByte, 0, length);
                    }
                    fileOutputStream.close();
                } else if (isText(inputByte)) {
                    inputStream.read(inputByte);
                    String toId = getId(inputByte);

                    // 获得消息
                    StringBuilder builder = new StringBuilder();
                    int length = 0;
                    while ((length = inputStream.read(inputByte)) > 0) {
                        builder.append(new String(inputByte, length));
                    }

                    // 写入数据库
                    
                } else {
                    this.close();
                    throw new UndefinedMessageType();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean isLogin(byte[] input) {
        return Arrays.equals(input, MessageTypes.login);
    }

    boolean isFile(byte[] input) {
        return Arrays.equals(input, MessageTypes.file);
    }

    boolean isRegister(byte[] input) {
        return Arrays.equals(input, MessageTypes.register);
    }

    boolean isText(byte[] input) {
        return Arrays.equals(input, MessageTypes.text);
    }

    String getId(byte[] input) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            builder.append('0' + input[i]);
        }

        return builder.toString();
    }
}
