/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.core;

/**
 * The ConsoleWriter class implements the ReportWriter interface to write reports to the console.
 */
public class ConsoleWriter implements ReportWriter {

    /**
     * Writes the report data to the console.
     * @param reportData the data of the report to be written
     */
    @Override
    public void writeReport(String reportData) {
        System.out.println(reportData);
    }
}
