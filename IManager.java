package model.interfaces;

import java.util.List;

public interface IManager<T> {
    void them(T item) throws Exception;
    List<T> layDanhSach();
    void luuFile(String fileName) throws Exception;
    void docFile(String fileName) throws Exception;
}
