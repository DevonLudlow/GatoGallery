import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class KittyGenerator {
    private String kittyXName;
    private String kittyXDescription;
    private Database dataBase;

    public KittyGenerator() {
        dataBase = new Database();
    }

    public void printCatBio(String userInput) throws IOException {
        ArrayList<String> kittyNames = dataBase.listKittyNames();
        for (String kittyName: kittyNames){
            if (kittyName.equals(userInput)){
                String kittyDescription = dataBase.getKittyDescription(kittyName);
                System.out.println(kittyDescription);
                return;
            }
        }
        kittyNotFound();
    }

    /*private void catchDuplicate(String userInput) throws IOException {
        ArrayList<String> kittyNames = dataBase.listKittyNames();

    }
          */

    private void addKitty() throws IOException {
        System.out.print("Amazing! Enter your cat's name here: ");
        kittyXName = readInput();
        kittyXName = kittyXName.toLowerCase();
        System.out.print("Now, add a cute lil description of your feline friend! ");
        kittyXDescription = readInput();
        System.out.println("Welcome to our kitty database " + kittyXName + "!\n");
        dataBase.saveKitty(kittyXName, kittyXDescription);
        dataBase.listKittyNames();
        anythingElse();

    }

    private void removeKitty() throws IOException {
        System.out.println("Would you like to remove a kitty from our database? " + "(yes/no) ");
        String yesNo = readInput();
        if (yesNo.equals("yes")) {
            System.out.println("Enter the kitty's name that you would like to remove: ");
            kittyXName = readInput();
            System.out.print("Are you sure you'd like to remove " + kittyXName + " from out database?:(" +
                    " (yes/no): ");
            String yesNoRmv = readInput();
            if (yesNoRmv.equals("no")) {
                run();
              }else if (yesNoRmv.equals("yes")){
                System.out.println("We're sorry to see you go " + kittyXName + ", we wish upon you many yarnballs " +
                        "and leaves of catnip\n");
                dataBase.removeKitty(kittyXName);
                anythingElse();
            }
                     } else {
            run();
        }
                }

    private void searchKitty() throws IOException {
        System.out.print("Search for kimmtty here: ");
        String userInput = readInput();
        userInput = userInput.toLowerCase();
        printCatBio(userInput);
        System.out.println(" ");
        anythingElse();
    }

    private void browseKitties() throws IOException {
        System.out.println("Here is a list of all the adorable cats we have so far: ");
        ArrayList<String> kittyNames = dataBase.listKittyNames();
        for (String browseKitties : kittyNames ) {
            System.out.println(browseKitties);
        }
        anythingElse();
    }

    private void updateKitty() throws IOException {
        System.out.println("Alrighty, which kimnty's bio would you like to update?: ");

    }

    private void anythingElse() throws IOException {
        System.out.println("Would you like to do anything else? (yes/no): ");
        String yesNo = readInput();
        String nextPrompt = "What would you like to do?\n" +
                "Please choose from the list of functions below:\n" +
                PurpleText("                                           add kitty\n") +
                YellowText("                                           search kitty\n") +
                GreenText("                                            remove kitty\n") +
                          "                                            update kitty\n" +
                          "                                            browse kitties\n;";
        if (yesNo.equals("no")) {
            run();
        }else if (yesNo.equals("yes")){
            System.out.println(nextPrompt);
        }String chooseFunction = readInput();
        chooseFunction = chooseFunction.toLowerCase();
        if(chooseFunction.equals("add kitty")) {
            addKitty();
        }else if (chooseFunction.equals("search kitty")) {
            searchKitty();
            String userInput = readInput();
            userInput = userInput.toLowerCase();
            printCatBio(userInput);
        }else if (chooseFunction.equals("remove kitty")) {
            removeKitty();
        }else if (chooseFunction.equals("browse kitties")) {
            browseKitties();
        } else if (chooseFunction.equals("update kitty")){
            updateKitty();
        } else {
            run();
        }
    }
    //main method to run program
    public void run() throws IOException {

        while (true) {
            String firstPrompt = "Hello! Welcome to Gato Gallery, an international library of kittens!\n" +
                    "Please choose from the list of functions below:\n" +
                    PurpleText("                                           add kitty\n") +
                    YellowText("                                           search kitty\n") +
                    GreenText("                                            remove kitty\n") +
                              "                                            update kitty\n" +
                              "                                            browse kitties\n";
            System.out.println(firstPrompt);
            String chooseFunction = readInput();
            chooseFunction = chooseFunction.toLowerCase();
            if(chooseFunction.equals("add kitty")) {
                addKitty();
            }else if (chooseFunction.equals("search kitty")) {
                searchKitty();
                String userInput = readInput();
                userInput = userInput.toLowerCase();
                printCatBio(userInput);
            }else if (chooseFunction.equals("update kitty")) {
                updateKitty();
            }else if (chooseFunction.equals("browse kitties")){
                browseKitties();
            } else if (chooseFunction.equals("remove kitty")) {
                removeKitty();
            }else {
                run();
            }
            }
        }


    public String readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInput = br.readLine();
        return userInput;
    }

    public void kittyNotFound() throws IOException {
        System.out.print("Kimmty not found in our database! Would you like to add your kimmty to our database?" +
                "(yes/no) ");
        String yesNo = readInput();
        if (yesNo.equals("yes")) {
            System.out.print("Enter your cat's name: ");
            kittyXName = readInput();
            kittyXName = kittyXName.toLowerCase();
            System.out.print("add a cute lil description of your feline friend! ");
            kittyXDescription = readInput();
            System.out.println("Welcome to our kitty database " + kittyXName + "!");
            dataBase.saveKitty(kittyXName, kittyXDescription);
            dataBase.listKittyNames();
        }
    }

    public String PurpleText(String text) {
        return "\u001B[35m" + text + "\u001B[0m";
    }

    public String GreenText(String text) {
        return "\u001B[32m" + text + "\u001B[0m";
    }

    public String YellowText(String text) {
        return "\u001B[33m" + text + "\u001B[0m";
    }


}
