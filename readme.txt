THIS IS A README OUTLINING BASIC STARTUP INSTRUCTIONS

Shopping Companion is an all-in-one application that simplifies groceries by allowing users to search for the cheapest versions of products across many grocers. Once the user has decided which products to purchase, they will be able to create multiple shopping lists for different grocery shops. The backend of the program is built in Java, and the user interface using JavaFX. MySQL was also used to handle certain functions of the program that required storage and interaction with a particular user's prior activity on the application. 

STEPS TO BUILD AN EXECUTABLE:

First, compile the Java files by opening a command prompt or terminal window and navigate to the directory where the .Java files are located. Then, use the command "javac *.java". This will create .class files for every corresponding .java file. 
Now, in the same directory, create a file named "Manifest.txt" that has the following line in its contents: "Main-Class: Shopping_Companion.class". Now, in a command prompt or terminal window, use the command "jar -cvfm Shopping_Companion.jar manifest.txt *.class"
Finally, the newly created .jar file can be run using the command "java -jar Shopping_Companion.jar"
The .jar file can be converted to a .exe file using Launch4J or a similar application. Using Launch4J, specify the path to your .jar file, the output directory, the icon for the .exe, and set the Minimum JRE Version in the JRE tab to 1.8, and check the JDK Required box. Finally, add the following arguments to the JVM Options text field:
 --module-path "C:\path\to\javafx-sdk-20\lib" --add-modules javafx.controls,javafx.fxml
With the path to your SDK "lib" folder in the quotations. 


STEPS TO RUN APPLICATION:

Click on the included .exe file in this folder to run Shopping Companion. Click on the "Sign Up" button to make an account using your name and email address, and set a password. Next, you can login using your new credentials. The "Multi-Store Item Search" page will open. Here, you can enter your keyword in the text field and hit enter or hit the "Search" button to search for it. This will take you to the search results page, where you can see results for Dollarama, Costco, and Wholesale Club and add items to each of your store carts. The "My Carts" button seen on the top right of both the Multi Store Item Search and the search results pages will display all three of your store carts, and also allows you to clear a cart or remove a specific item from the cart. 

ADDITIONAL SOFTWARE REQUIRED:
1) Selenium
2) mySQL database
3) htmlunit
4) jsoup
5) Chrome Driver
6) Latest JRE Version (Minimum V1.8)
7) Java JDK and SDK V20
8) JavaFX
9) Launch4J
