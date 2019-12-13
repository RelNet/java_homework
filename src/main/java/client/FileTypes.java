package client;

public class FileTypes {
    boolean isImage(byte[] input) {
        String tempString = new String(input);
        int dotIndex = tempString.lastIndexOf('.');

        if (dotIndex != -1) {
            String fileType = tempString.substring(dotIndex);

            switch (fileType) {
                case "jpg":
                case "JPG":
                case "jpeg":
                case "JPEG":
                case "png":
                case "PNG":
                    return true;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }
}
