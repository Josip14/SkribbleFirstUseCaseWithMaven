## Skribble programmatically filling out PDF's with form fields using a .csv file
What are we doing here?
- we make an application that takes 3 arguments(csvFilePath, pdfFilePath, savingLocation)
- the .csv file gets transformed into an object so we can easily use it
- then we write for each object into the pdf form fields the requested variables (name, birthdate ...)
- finally every pdf which is filled out differently gets saved on the local machine
