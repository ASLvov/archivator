package app.domain;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Directory {
    private Path mainPath;
    private List<Path> content;

    public Directory(Path mainPath) throws IOException {
        this.mainPath = mainPath;
        this.content = new ArrayList<>();
        collectFiles(mainPath);
    }

    public Path getMainPath() {
        return mainPath;
    }

    public List<Path> getContent() {
        return content;
    }

    private void collectFiles(Path path) throws IOException {
        if (Files.isRegularFile(path)) {
            Path relativizedPath = mainPath.getParent().relativize(path);
            content.add(relativizedPath);
        }

        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
                for (Path file : directoryStream) {
                    collectFiles(file);
                }
            }
        }
    }
}
