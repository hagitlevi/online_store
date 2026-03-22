/**
 * student ID: 316242951
 * student name: Hagit Levi
 * this file defines the BookProduct class representing a book product in the store.
 */
package store.core;

/**
 * The Report abstract class represents a report that can be generated using a ReportWriter.
 */
public abstract class Report {

    protected ReportWriter reportwriter;

    /**
     * Constructor for Report class.
     * @param writer the ReportWriter to use for generating the report
     */
    public Report(ReportWriter writer){
        this.reportwriter = writer;
    }

    /**
     * Generate the report.
     */
    public abstract void generate();


}
