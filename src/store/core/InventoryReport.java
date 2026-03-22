/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.core;

/**
 * InventoryReport class that generates inventory reports.
 */
public class InventoryReport extends Report{

    public InventoryReport(ReportWriter write){
        super(write);
    }

    /**
     * Generates an inventory report and writes it using the provided ReportWriter.
     */
    @Override
    public void generate() {
        String reportData = "Inventory Report Data...";
        this.reportwriter.writeReport(reportData);
    }
}
