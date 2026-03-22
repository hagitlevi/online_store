/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.core;

/**
 * The SalesReport class generates a sales report using the provided ReportWriter.
 */
public class SalesReport extends Report {
    public SalesReport(ReportWriter writer){
        super(writer);
    }

    /**
     * Generate the sales report.
     */
    @Override
    public void generate() {
        String reportData = "Sales Report Data...";
        this.reportwriter.writeReport(reportData);
    }
}
