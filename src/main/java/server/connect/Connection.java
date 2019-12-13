package server.connect;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author h
 * 此类用于运行连接任务
 */
public class Connection {
    ServerSocket serverSocket;
    Integer port;

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    // 配置连接client的线程池参数
    private static final Integer MAX_CONNECT_THREADS = 6;
    private static final Integer MAX_CONNECT_QUEUE_SIZE = 1000;
    private static final Long MAX_CONNECT_THREADS_ACTIVE = 1000L;
    private static final Integer MAX_CONNECT_CORE_POOL_SIZE = 4;
    ExecutorService executorPool;

    public Connection(Integer port) throws IOException {
        serverSocket = new ServerSocket(port);
        this.port = port;
        this.executorPool = new ThreadPoolExecutor(MAX_CONNECT_CORE_POOL_SIZE, MAX_CONNECT_THREADS,
                MAX_CONNECT_THREADS_ACTIVE, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(MAX_CONNECT_QUEUE_SIZE));
    }

    public void addClientConnection(ConnectClient client) {
        executorPool.execute(client);
    }
}
