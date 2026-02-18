package util;

import java.io.File;

public class FileHandler {
    public final static String USER_DIRECTORY = System.getProperty("user.home");
    public final static File PROGRAM_FOLDER = new File(USER_DIRECTORY, "Documents\\My Shop");
    public final static File ASSETS_FOLDER = new File(PROGRAM_FOLDER + "\\ShopAssets");
    public final static File IMAGE_FOLDER = new File(PROGRAM_FOLDER + "\\ProductImages");

    public static void setDirectories() {
        ASSETS_FOLDER.mkdir();
        PROGRAM_FOLDER.mkdir();
        IMAGE_FOLDER.mkdir();
    }
}
