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

        /**
         * example of the .csv file path args[0]-> "C:\\Users\\41786\\OneDrive\\Desktop\\Data.csv";
         */
        List<String[]> persons;
        try (CSVReader reader = new CSVReader(new FileReader(args[0]))) {
            persons = reader.readAll();
        }
        /**
         * See what the reader outputs
         * persons.forEach(x -> System.out.println(Arrays.toString(x)));
         */

        /**
         * Start iteration from second row because the first is the header
         */
        for (int i = 1; i<persons.size(); i++){
            try {
                /**
                 * example of a pdf file path with formFields args[1]-> "C:\\Users\\41786\\OneDrive\\Desktop\\FormField.pdf";
                 */

                PDDocument pdfDocument = Loader.loadPDF(new File(args[1]));
                PDDocumentCatalog documentCatalog = pdfDocument.getDocumentCatalog();
                PDAcroForm acroForm = documentCatalog.getAcroForm();
                List<PDField> fields = acroForm.getFields();

                /**
                 * If you want to check what the order and what type the fields have uncomment the code below
                 */

                /**
                for (int l = 0; l<fields.size();l++){
                    System.out.println(fields.get(l).getPartialName());
                    System.out.println(fields.get(l).getFieldType());
                }
                 */

                /**
                 * Check for too short rows and give an information where it is and that it can cause errors
                 * because the form filling happens in order and if there are some missing  arguments everything gets postponed
                 */
                if (persons.get(i).length < persons.get(0).length) {
                    System.out.println("Hint: row number " + i + " is not complete and can cause false filling of the form fields in the PDF: "
                            + (persons.get(0).length - persons.get(i).length) + " argument(s) is/are missing");
                }
                /**
                 * Check if the input row has to much arguments and in which row it causes an error
                 */
                else if (persons.get(i).length > persons.get(0).length){
                    System.out.println("Row number " + i + " has too much arguments and causes an error in the PDF: "
                            + (persons.get(i).length - persons.get(0).length) + " argument(s) is/are redundant" );
                    System.out.println(persons.get(i)[0] + " row number: " + i + " is NOT saved");
                }

                /**
                 * this line makes everything happen and writes the data from the .csv file into
                 * the empty PDF for each row in the .csv file
                 */
                for (int j = 0; j < persons.get(i).length; j++) {
                    acroForm.getField(fields.get(j).getPartialName()).setValue(persons.get(i)[j]);
                }

                /**
                 * Save the filled out PDF
                 * savingPath example args[2] ->  C:\\Users\\41786\\OneDrive\\Desktop
                 * the i is just to prevent errors if there are more the one of the same name, but you can change it as you like
                 */
                pdfDocument.save(args[2] + "\\" + persons.get(i)[0] + " " + i + ".pdf");
                System.out.println(persons.get(i)[0] +" row number: "+ i + " is saved");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        /**
         * If all fields got filled out, let the user know that it was successfully
         */
        System.out.println("Successfully filled out and saved all PDF's for each person in this folder: "+args[2]);
    }
}
