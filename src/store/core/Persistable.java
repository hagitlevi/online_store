/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the Persistable interface representing objects that can be saved to a file.
 */
package store.core;

/**
 * The Persistable interface represents objects that can be saved to a file.
 */
public interface Persistable {
    public void saveToFile(String path);
}
