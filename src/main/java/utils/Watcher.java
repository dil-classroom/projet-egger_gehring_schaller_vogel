package utils;

import static java.nio.file.StandardWatchEventKinds.*;

import interfaces.Subject;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Watcher class pour le file system. Elle est utilisé pour notifier un callable lors d'un
 * changement d'un repertoire ou de ses sous repertoires.
 */
public class Watcher implements Subject {
    private Callable<Integer> obs;
    private Path watchedDir;
    WatchService watchService;

    /**
     * Cette methode enregistre un watch service sur un repertoire et tout ses sous repertoires.
     *
     * @param path le chemin a observer
     * @param service le service a utiliser
     * @throws IOException
     */
    private void registerAllDirectoryInPath(Path path, WatchService service) throws IOException {
        Files.walkFileTree(
                path,
                new SimpleFileVisitor<>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attrs)
                            throws IOException {

                        file.register(service, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
                        Logger.getAnonymousLogger()
                                .log(Level.INFO, "Registered directory : " + file);
                        return FileVisitResult.CONTINUE;
                    }
                });
    }

    @Override
    public void register(Callable<Integer> observer, Path directoryToWatch) {
        obs = observer;
        watchedDir = directoryToWatch;

        try {
            watchService = FileSystems.getDefault().newWatchService();

            Logger.getAnonymousLogger()
                    .log(Level.INFO, "registered root directory : " + watchedDir);
            watchedDir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
            registerAllDirectoryInPath(watchedDir, watchService);

            new Thread(
                            () -> {
                                while (true) {
                                    try {
                                        WatchKey key = watchService.take();
                                        for (var event : key.pollEvents()) {
                                            if (event.kind() == ENTRY_CREATE) {
                                                Logger.getAnonymousLogger()
                                                        .log(
                                                                Level.INFO,
                                                                "File or dir "
                                                                        + event.context()
                                                                        + " created,"
                                                                        + " registering...");

                                                registerAllDirectoryInPath(
                                                        watchedDir, watchService);
                                            }
                                            if (event.kind() == ENTRY_CREATE
                                                    || event.kind() == ENTRY_MODIFY
                                                    || event.kind() == ENTRY_DELETE) {
                                                Logger.getAnonymousLogger()
                                                        .log(
                                                                Level.INFO,
                                                                "File "
                                                                        + event.context()
                                                                        + " changed");
                                                var ret = obs.call();
                                            }
                                        }
                                        // reset the key because without it the next poll won't work
                                        key.reset();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                    .start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unregister(Callable<Integer> observer) {
        obs = null;
        watchedDir = null;
    }
}
