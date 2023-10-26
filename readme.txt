THIS IS A README OUTLINING BASIC STARTUP INSTRUCTIONS

Shopping Companion is an all-in-one application that simplifies groceries by allowing users to search for the cheapest versions of products across many grocers. Once the user has decided which products to purchase, they will be able to create multiple shopping lists for different grocery shops. The backend of the program is built in Java, and the user interface using JavaFX. MySQL was also used to handle certain functions of the program that required storage and interaction with a particular user's prior activity on the application. 

STEPS TO BUILD AN EXECUTABLE:
First, compile the Java files by opening a command prompt or terminal window and navigate to the directory where the .Java files are located. Then, use the command "javac *.java". This will create .class files for every corresponding .java file. 
Now, in the same directory, create a file named "Manifest.txt" that has the following line in its contents: "Main-Class: Shopping_Companion.class". Now, in a command prompt or terminal window, use the command "jar -cvfm Shopping_Companion.jar manifest.txt *.class"
Finally, the newly created .jar file can be run using the command "java -jar Shopping_Companion.jar"

STEPS TO RUN APPLICATION:
The .jar file created in the steps above can be run for a command prompt or terminal window in the appropriate directory with the following command: "java -jar Shopping_Companion.jar"

ADDITIONAL SOFTWARE REQUIRED:
1) Selenium
2) mySQL database
3) xsoup
4) htmlunit
5) jsoup
6) Chrome Driver
7) Latest JRE Version