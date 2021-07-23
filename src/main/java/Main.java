import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException, CsvException {

        //.csv file path
        String fileName = "C:\\Users\\41786\\OneDrive\\Desktop\\Daten.csv";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the file path from your CSV document: ");
        String csvFilePath = scanner.nextLine();
        // we make out of the rows of the .csv file an object and can get them from there
        List<PersonObject> persons = new CsvToBeanBuilder(new FileReader(csvFilePath))
                .withType(PersonObject.class)
                .build()
                .parse();

        //persons.forEach(x -> System.out.println(x.getEmail().substring(0,x.getEmail().length()-2)));
        System.out.println("Enter the file path to your PDF file which should be filled out: ");
        String pdfFilePath = scanner.nextLine();

        // here we can see how many fields our PDF with form fields has
        String formTemplate = "C:\\Users\\41786\\OneDrive\\Desktop\\FormField.pdf";
        /**PDDocument pdfDocument = Loader.loadPDF(new File(pdfFilePath));
        PDDocumentCatalog documentCatalog = pdfDocument.getDocumentCatalog();
        PDAcroForm acroForm1 = documentCatalog.getAcroForm();
        List<PDField> fields = acroForm1.getFields();
        System.out.println(fields.size() + " top-level fields were found on the form");

        // here we see what the names of the fields are, we need them so we can fill them out
        for (PDField field : fields) {
            System.out.println(field.getPartialName());
            System.out.println(field.getValueAsString());
        }*/
        System.out.println("Enter the path to the folder where the documents should be saved: ");
        String savingPath = scanner.nextLine();

        try (PDDocument pdfDocument1 = Loader.loadPDF(new File(pdfFilePath))) {
            // get the document catalog
            PDAcroForm acroForm = pdfDocument1.getDocumentCatalog().getAcroForm();

            // as there might not be an AcroForm entry a null check is necessary
            for (int i = 1; i<persons.size(); i++){
                if (acroForm != null) {

                // Retrieve an individual field and set its value.
                    // We can add as much as there are form field to fill them out.
                PDTextField firstNameTextBox = (PDTextField) acroForm.getField( "Given Name Text Box" );
                firstNameTextBox.setValue(persons.get(i).getFirstName());
                PDTextField lastNameTextBox = (PDTextField) acroForm.getField("Family Name Text Box");
                lastNameTextBox.setValue(persons.get(i).getLastName());
                PDComboBox countryComboBox = (PDComboBox) acroForm.getField("Country Combo Box");
                countryComboBox.setValue(persons.get(i).getEmail());
                //System.out.println(field.getValueAsString());

                    // just to test for an other pdf document
                /**PDTextField firstNameTextBox = (PDTextField) acroForm.getField( "Name_First" );
                firstNameTextBox.setValue(persons.get(i).getFirstName());
                PDTextField lastNameTextBox = (PDTextField) acroForm.getField("Name_Last");
                lastNameTextBox.setValue(persons.get(i).getLastName());
                 */

            }

            // Save and close the filled out form.
            //pdfDocument1.save("C:\\Users\\41786\\OneDrive\\Desktop\\"+persons.get(i).getFirstName()+".pdf");
            pdfDocument1.save(savingPath + "\\"+ persons.get(i).getFirstName() + ".pdf");
            }
        }
    }
}