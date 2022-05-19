package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WatcherTest {
    private final int timeToSync = 15000;

    @Test
    void registerTest() throws IOException, InterruptedException {
        List<Integer> l = new ArrayList<>(4);
        Path path = Files.createTempDirectory("test");
        path.toFile().deleteOnExit();
        Watcher watcher = new Watcher();
        watcher.register(
                () -> {
                    l.add(1);
                    return 0;
                },
                path);
        Files.createFile(path.resolve("test.txt"));
        Thread.sleep(timeToSync);
        assertEquals(1, l.size());
    }

    @Test
    void registerMultipleFilesTest() throws IOException, InterruptedException {
        List<Integer> l = new ArrayList<>(4);
        Path path = Files.createTempDirectory("test");
        path.toFile().deleteOnExit();
        Watcher watcher = new Watcher();
        watcher.register(
                () -> {
                    l.add(1);
                    return 0;
                },
                path);
        Files.createFile(path.resolve("test.txt"));
        // this is used to make sure the event is triggered to time and not 1 time with a count of
        // two
        Thread.sleep(1000);
        Files.createFile(path.resolve("test2.txt"));
        Thread.sleep(timeToSync);
        assertEquals(2, l.size());
    }

    @Test
    void registerFilesInSubDir() throws IOException, InterruptedException {
        List<Integer> l = new ArrayList<>(4);
        Path path = Files.createTempDirectory("test");
        path.toFile().deleteOnExit();
        var s1 = Files.createDirectories(path.resolve("subdir1"));
        var s2 = Files.createDirectories(path.resolve("subdir2"));

        Watcher watcher = new Watcher();
        watcher.register(
                () -> {
                    l.add(1);
                    return 0;
                },
                path);
        Files.createFile(s1.resolve("test.txt"));
        // this is used to make sure the event is triggered to time and not 1 time with a count of
        // two
        Thread.sleep(1000);
        Files.createFile(s2.resolve("test2.txt"));
        Thread.sleep(timeToSync);
        // depending on os it might trigger multiple time or not because of the upper level dir
        // beeing registered
        assertTrue(2 <= l.size(), "2 <= " + l.size());
    }
}
