--PART 1--
1. Open Eclipse and create a new project with whatever name you'd like to choose. Do not create a module if asked.
2. In the newly created project folder, create a new package under the folder labeled "src" that has the name of "application". Do not include the parenthesis.
3. Inside of this newly created package, insert the java files that were included in the folder labeled "src" that is in this .ZIP file.
4. Inside of the "src" folder (not the "application" package) that is in the eclipse project, insert the .PNG files that were included in the folder labeled "src" that is in this .ZIP file.

--PART 2--
1. Go to https://gluonhq.com/products/javafx/ and download the JavaFX Windows SDK.
2. Open the downloaded .ZIP file and open the folder labeled "javafx-sdk-11.0.2".
3. Extract the previously mentioned folder's content to the following path: "C:\javafx".
4. Open Eclipse and goto the drop-down menu that is located at the top of the screen and select the option labeled "Window" and select the option labeled "Preferences".
5. Then, select the drop-down that is labeled "Java" and is located on the left side of the window that has just opened.
6. Then, select the drop-down that is labeled "Build Path".
7. Next, select the option that is labeled "User Libraries".
8. Click the button that is labeled "New" and is located on the right side of the preferences window.
9. In the box that has just appeared, enter the following "JavaFX". Do not include the parenthesis.
10. Then, select the item that has just been created.
11. Then, click the button that is labeled "Add External JARs".
12. Browse to the following path in the window that has just appeared: "C:\javafx\lib". Select all of the .JAR files that are listed in the previously mentioned folder.
13. Next, select the button labeled "Open".
14. Click the button labeled "Apply and Close".

--PART 3--
1. Click the main folder in the package explorer that contains this project.
2. Go to the drop-down menu that is located at the top of the screen and select the option labeled "Project" and select the option labeled "Properties".
3. Then, select the drop-down that is labeled "Java Build Path" and is located on the left side of the window that has just opened.
4. Next, select the tab that is labeled "Libraries".
5. Click the item that is labeled "Classpath".
6. Press the button that is labeled "Add Library".
7. In the window that has just appeared, select the item labeled "User Library". Then press the button labeled "Next".
8. Check the box next to the option that is labeled "JavaFX". Then click the button labeled "Finish".
9. Click the button that is labeled "Apply and Close".

--PART 4--
1. Go to the drop-down menu that is located at the top of the screen and select the option labeled "Run" and select the option labeled "Run Configurations".
2. Select the tab that is labeled "Arguments".
3. In the box labeled "VM arguments", enter the following text: "--module-path=C:\javafx\lib --add-modules=javafx.controls". Do not include the parenthesis.
4. Press the button labeled "Apply".
5. Press the button labeled "Close".
****WANRING - IF THE COMPILER SAYS ANYTHING ABOUT THE ARGUMENTS MENTIONED ABOVE, REMOVE THEM FROM THE RUN CONFIGURATION AND PROCEED TO RUN THE PROJECT WITHOUT THEM****

--PART 5--
1. Run the program.
2. Click the button labeled "Settings" and choose your prefered options for your game.
3. Click the button labeled "Save", the click the button labeled "Back".
4. Click the button labeled "Play" and enjoy the game of Tic-Tac-Toe.