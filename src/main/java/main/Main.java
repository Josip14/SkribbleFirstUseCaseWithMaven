package main;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException, CsvException {

        // example of the .csv file path args[0]-> "C:\\Users\\41786\\OneDrive\\Desktop\\Data.csv";
        List<String[]> persons;
        try (CSVReader reader = new CSVReader(new FileReader(args[0]))) {
            List<String[]> readAllPers = reader.readAll();
            persons = readAllPers;
            readAllPers.forEach(x -> System.out.println(Arrays.toString(x)));
        }

        // example of a pdf file path with formFields args[1]-> "C:\\Users\\41786\\OneDrive\\Desktop\\FormField.pdf";
        // need to set the arguments
        PDDocument pdfDocument = Loader.loadPDF(new File(args[1]));
        PDDocumentCatalog documentCatalog = pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm1 = documentCatalog.getAcroForm();
        List<PDField> fields = acroForm1.getFields();

        // here we print the names of the fields out so we see what the names are from it
        for(int i = 0; i<fields.size(); i++){
         System.out.println(fields.get(i).getPartialName());
        }

        for (int i = 1; i<persons.size(); i++){
            try (PDDocument pdfDocument1 = Loader.loadPDF(new File(args[1]))) {
                // get the document catalog
                PDAcroForm acroForm = pdfDocument1.getDocumentCatalog().getAcroForm();
                // as there might not be an acroForm entry a null check is necessary
                if (acroForm != null) {
                    // Retrieve an individual field and set its value.
                    // We can add as much as there are form field to fill them out.
                    // we can retrieve the names of the form fields with the code above with the forLoop
                    for (int j = 0; j < fields.size(); j++) {
                        acroForm.getField(fields.get(j).getPartialName()).setValue(persons.get(i)[j]);
                }
            // Save and close the filled out form.
            //savingPath example args[2] ->  C:\\Users\\41786\\OneDrive\\Desktop
            pdfDocument1.save(args[2] + "\\"+ persons.get(i)[0] + ".pdf");
            }
        }
    }
}
}
