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
import java.util.List;


public class Main {

    public static void main(@org.jetbrains.annotations.NotNull String[] args) throws IOException, CsvException {

        // example of the .csv file path args[0]-> "C:\\Users\\41786\\OneDrive\\Desktop\\Data.csv";
        List<String[]> persons;
        try (CSVReader reader = new CSVReader(new FileReader(args[0]))) {
            persons = reader.readAll();
            /**
             * See what the reader outputs
             * persons.forEach(x -> System.out.println(Arrays.toString(x)));
             */
        }

        // here we print the order of the formFields so we know how the .csv file should look like
        /**
         for(int i = 0; i<fields.size(); i++){
         System.out.println(fields.get(i).getPartialName());
        }*/

        for (int i = 1; i<persons.size(); i++){
            try {
                // example of a pdf file path with formFields args[1]-> "C:\\Users\\41786\\OneDrive\\Desktop\\FormField.pdf";
                // need to set the arguments
                PDDocument pdfDocument = Loader.loadPDF(new File(args[1]));
                PDDocumentCatalog documentCatalog = pdfDocument.getDocumentCatalog();
                PDAcroForm acroForm = documentCatalog.getAcroForm();
                List<PDField> fields = acroForm.getFields();

                // Check for the right length of the rows so it gets filled correctly else we see which row is not complete
                if (persons.get(i).length < persons.get(0).length) {
                    System.out.println("Row number " + i + " is not complete and can cause false filling of the form fields");
                }
                // set the values of the .csv file into the form fields
                for (int j = 0; j < persons.get(i).length; j++) {
                    acroForm.getField(fields.get(j).getPartialName()).setValue(persons.get(i)[j]);
                    }

                // Save the filled out PDF
                // savingPath example args[2] ->  C:\\Users\\41786\\OneDrive\\Desktop
                pdfDocument.save(args[2] + "\\"+ persons.get(i)[0] + ".pdf");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Successfully filled out and saved for each person in this folder: "+args[2]);
    }
}
