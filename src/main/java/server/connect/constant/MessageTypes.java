package server.connect.constant;

public class MessageTypes {
    public static byte[] login = {1, 122, 2, 28, 87, 67, 32, 4};
    public static byte[] file = {54, 32, 56, 67, 2, 3, 66, 10};
    public static byte[] register = {67, 111, 80, 34, 21, 65, 88, 1};
    public static byte[] text = {64, 11, 21, 33, 88, 55, 31, 57};

    public static void init() {
        byte[] temp = null;

        temp = login;
        login = new byte[1024];
        for (int i = 0; i < temp.length; i++) {
            login[i] = temp[i];
        }

        temp = file;
        file = new byte[1024];
        for (int i = 0; i < temp.length; i++) {
            file[i] = temp[i];
        }

        temp = register;
        register = new byte[1024];
        for (int i = 0; i < temp.length; i++) {
            register[i] = temp[i];
        }

        temp = text;
        text = new byte[1024];
        for (int i = 0; i < temp.length; i++) {
            text[i] = temp[i];
        }
    }
}
