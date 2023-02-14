package com.hqwx.codegeneration.shared.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/* loaded from: code-generation-tools.jar:com/hqwx/codegeneration/shared/utils/FileUtils.class */
public class FileUtils {
    public static int checkAndCreateFileDirectory(String fileDir) {
        if (fileDir == null || fileDir.trim().equals("")) {
            return -1;
        }
        try {
            File fileDirObj = new File(fileDir);
            if (!fileDirObj.exists() || !fileDirObj.isDirectory()) {
                if (fileDirObj.mkdir()) {
                    System.out.println(" > createFileDirectory: " + fileDir);
                    return 1;
                }
                return -1;
            }
            return 0;
        } catch (Exception err) {
            System.out.println("checkAndCreateFileDirectory_error, directory: " + fileDir + ", error: " + err.toString());
            return -1;
        }
    }

    public static File checkAndCreateFile(String fileFullPath) {
        if (fileFullPath == null || fileFullPath.trim().equals("") || fileFullPath.contains("\\.")) {
            return null;
        }
        try {
            File fileDirObj = new File(fileFullPath);
            if ((!fileDirObj.exists() || !fileDirObj.isFile()) && fileDirObj.createNewFile()) {
                System.out.println(" > createFile: " + fileFullPath);
            }
            if (fileDirObj.isFile()) {
                return fileDirObj;
            }
            return null;
        } catch (Exception err) {
            System.out.println("checkAndCreateFile_error, fileFullPath: " + fileFullPath + ", error: " + err.toString());
            return null;
        }
    }

    public static boolean checkFileDirectoryIsExist(String filePath) {
        File fileDirObj = new File(filePath);
        if (fileDirObj == null || !fileDirObj.exists() || !fileDirObj.isDirectory()) {
            return false;
        }
        return true;
    }

    public static boolean checkFileIsExist(String filePath) {
        File fileObj = new File(filePath);
        if (fileObj == null || !fileObj.exists() || !fileObj.isFile()) {
            return false;
        }
        return true;
    }

    public static boolean updateFileContent(File file, String content) {
        if (!file.exists() || !file.isFile() || content == null || content.length() <= 0) {
            return false;
        }
        try {
            OutputStream outs = new FileOutputStream(file);
            BufferedOutputStream bouts = new BufferedOutputStream(outs);
            bouts.write(content.getBytes("UTF-8"));
            bouts.flush();
            bouts.close();
            return true;
        } catch (Exception err) {
            System.out.println("checkAndCreateFile_error, fileFullPath: " + file.getAbsolutePath() + ", error: " + err.toString());
            return false;
        }
    }

    public static String replaceDirSeparator(String path) {
        if (path == null) {
            return "";
        }
        return path.trim().replaceAll("\\\\", "/");
    }

    public static boolean outputToFile(Integer overwriteExistFileFlag, String filePath, String content) {
        File file = checkAndCreateFile(filePath);
        if (file.length() > 0 && (overwriteExistFileFlag == null || overwriteExistFileFlag.intValue() != 1)) {
            PrintStream printStream = System.out;
            printStream.println(" > generateOrUpdateFileContentFailed, file already exists, not overwrite, length: " + file.length() + ", filePath: " + printStream);
            return false;
        } else if (updateFileContent(file, content)) {
            System.out.println(" > generateOrUpdateFileContentSuccess, filePath: " + filePath);
            return true;
        } else {
            System.out.println(" > generateOrUpdateFileContentFailed, filePath: " + filePath);
            return false;
        }
    }
}
