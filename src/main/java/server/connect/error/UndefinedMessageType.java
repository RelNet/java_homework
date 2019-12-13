package server.connect.error;

public class UndefinedMessageType extends UnsupportedOperationException {
    @Override
    public void printStackTrace() {
        System.out.println("不支持的消息类型");
    }
}
