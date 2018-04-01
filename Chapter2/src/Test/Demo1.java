package Test;

import org.bouncycastle.util.Pack;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class Demo1 {
    @Test
    public void func1() {
        Path absolute = Paths.get("E:\\", "Corejava2", "Chapter1", "src", "optional", "OptionalTest.java");
/*        Path workRelative = Paths.get("work");
        Path basePath = Paths.get("E:\\");
        Path workPath = basePath.resolve(workRelative);
        System.out.println(workPath);
        Path p = Paths.get("/home", "fred", "myprog.properties");
        Path parent = p.getParent();
        Path file = p.getFileName();
        Path root = p.getRoot();
        */
/*

        System.out.println("path:" + p);
        System.out.println("parent:" + parent);
        System.out.println("file:" + file);
        System.out.println("root:" + root);
*/
        Path source = Paths.get("E:\\", "Corejava2");
        Path target = Paths.get("E:\\", "TESTCorejava");
        try {
            Files.walk(source).forEach(p -> {
                try {
                    Path q = target.resolve(source.relativize(p));
                    System.out.println(source.relativize(p));
                    if (Files.isDirectory(p)) {
                        Files.createDirectories(q);
                    }
                    else
                        Files.copy(p,q);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

/*        try {
            //String contents = new String(Files.readAllBytes(absolute), StandardCharsets.UTF_8);
            //System.out.println(contents);
        } catch (IOException e) {
            e.printStackTrace();
        }
 */
    }

    @Test
    public void visitDirTree() throws IOException {
        Files.walkFileTree(Paths.get("E:\\", "Corejava2"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes attrs) throws IOException {
                System.out.println(path);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.SKIP_SIBLINGS;
            }
        });
    }

    @Test
    public void deleteDirTree() throws IOException {
        //Delete the directory tree starting at root
        Files.walkFileTree(Paths.get("E:\\", "TESTCorejava"), new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                if (e != null) throw e;
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Test
    public void listZipFile() throws IOException {
        FileSystem fs = FileSystems.newFileSystem(Paths.get("D:\\package\\corejava.zip"), null);
        Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
