/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.core;

/**
 * The ReportWriter interface defines a method for writing report data.
 */
public class FileWriter implements ReportWriter {

    /**
     * Writes the given report data to a CSV file.
     * @param reportData the data to write to the report
     */
    @Override
    public void writeReport(String reportData) {
        try {
            java.nio.file.Files.write(
                java.nio.file.Paths.get("report.csv"),
                reportData.getBytes(),
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.APPEND
            );
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}

