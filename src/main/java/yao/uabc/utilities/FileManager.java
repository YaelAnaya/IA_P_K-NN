package yao.uabc.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import yao.uabc.model.Data;
import yao.uabc.model.Point;

public class FileManager {

    /**
     * Reads a file and returns a list of points with the data and the id of the spot where the point is located.
     * @param path the path of the file to read from the resources folder.
     *             The file must be a csv file with the following format:
     *
     * @return a list of points with the data and the id of the spot where the point is located.
     */
    private static List<Data> readFile(Path path) {
        List<Data> data = new ArrayList<>();
        try (var stream = Files.lines(path)) {
            stream.skip(1).forEach(line -> {
                var values = line.split(",");
                data.add(new Point(values));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static List<Data> loadDataset(CharSequence... paths) {
        return readFile(Path.of("src", "main", "resources", paths[0].toString(), paths[1].toString()));
    }




}
