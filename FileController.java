package model;

import java.io.*;
import java.util.List;

public class FileController {

    public static <T> void luuFile(String fileName, List<T> danhSach) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(danhSach);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> docFile(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject();
        }
    }
}
