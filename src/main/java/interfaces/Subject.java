package interfaces;

import java.nio.file.Path;
import java.util.concurrent.Callable;

/***
 * Cette interface est utilisé pour notifié un callable lorsque le sujet change
 */
public interface Subject {
    /***
     * Cette methode est utilisé pour enregistrer un callable a appeler lorsque la directoryToWatch change
     * @param observer le suject sur lequel on appel la method call
     * @param directoryToWatch le repertoire a observer
     */
    void register(Callable<Integer> observer, Path directoryToWatch);

    /**
     * Cette methode est utilisé pour supprimer un callable a appeler lorsque la directoryToWatch
     * change
     *
     * @param observer le sujet a supprimer
     */
    void unregister(Callable<Integer> observer);
}
