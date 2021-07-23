## Skribble programmatically filling out PDF's with form fields using a .csv file
What are we doing here?
- we make an application that takes 3 arguments(csvFilePath, pdfFilePath, savingLocation)
- the .csv file gets transformed into an object so we can easily use it
- then we write for each object into the pdf form fields the requested variables (name, birthdate ...)
- finally every pdf which is filled out differently gets saved on the local machine

The PersonObject class can be extended as much as you have .csv file columns to be named
you can have a look at these webpages to get more information about the hole project,
there are informations I looked at so the application works properly
- https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
- https://svn.apache.org/viewvc/pdfbox/trunk/examples/src/main/java/org/apache/pdfbox/examples/interactive/form/
- https://pdfbox.apache.org/1.8/cookbook/fill-form-field.html
