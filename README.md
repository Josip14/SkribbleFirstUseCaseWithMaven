## Skribble programmatically filling out PDF's with form fields using a .csv file
What are we doing here?
- we make an application that takes 3 arguments(csvFilePath, pdfFilePath, savingLocation)
- the .csv file has to have the same order as the PDF form fields
  for this you can easily comment the "for loop" out, and it prints you in the console the right order
- finally, every pdf which is filled out gets saved on the local machine in the path you specified

You can have a look at these webpages to get more information about the hole project,
there are information I looked at, so the application works properly
- https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
- https://svn.apache.org/viewvc/pdfbox/trunk/examples/src/main/java/org/apache/pdfbox/examples/interactive/form/
- https://svn.apache.org/viewvc/pdfbox/trunk/examples/src/main/java/org/apache/pdfbox/examples/interactive/form/FillFormField.java?revision=1873147&view=markup  
- https://pdfbox.apache.org/1.8/cookbook/fill-form-field.html
