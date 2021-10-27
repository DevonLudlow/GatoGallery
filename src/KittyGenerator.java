import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

public class KittyGenerator {
    private String kittyXName;
    private String kittyXDescription;
    private Database dataBase;
    private String kittyXPronouns;



    public KittyGenerator() {
        dataBase = new Database();
    }

    public void printCatBio(String userInput) throws IOException {
        ArrayList<String> kittyNames = dataBase.listKittyNames();
        for (String kittyName: kittyNames){
            if (kittyName.equals(userInput)){
                String kittyDescription = dataBase.getKittyDescription(kittyName);
                String kittyPronouns = dataBase.getKittyPronouns(kittyName);
                System.out.println( "bio: " + kittyDescription);
                if (kittyPronouns != null) {
                    System.out.println("pronouns: " + kittyPronouns + "\n");
                }
                return;
            }
        }
        kittyNotFound();
    }

    private void addKitty() throws IOException {
        System.out.print("Amazing! Enter your cat's name here: ");
        kittyXName = readInput();
        if (readInput().equals("back")){
            run();
        }
        kittyXName = kittyXName.toLowerCase();
        System.out.println("What are your kitty's pronouns? ");
        kittyXPronouns = readInput();
        kittyXPronouns = kittyXPronouns.toLowerCase();
        System.out.print("Now, add a cute lil description of your feline friend! ");
        kittyXDescription = readInput();
        System.out.println("Welcome to our kitty database " + kittyXName + "!\n");
        dataBase.saveKitty(kittyXName, kittyXDescription, kittyXPronouns);
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
                System.out.println("We're sorry to see you go " + kittyXName + ", we wish upon you many balls of yarn " +
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

    // function called in browseKitties to loop list of kitties
    private void kittyListBrowse()  throws IOException {
        ArrayList<String> kittyNames = dataBase.listKittyNames();
        for (String browseKitties : kittyNames) {
            System.out.println(browseKitties);
        }
        System.out.println("Type in a kitty's name that you would like to view");
        String userInput = readInput();
        userInput = userInput.toLowerCase();
        printCatBio(userInput);
    }

    // browse list of kitties and print chosen cat's bio
    private void browseKitties() throws IOException {
        System.out.println("Here is a list of all the adorable cats we have so far: ");
        ArrayList<String> kittyNames = dataBase.listKittyNames();
        for (String browseKitties : kittyNames ) {
            System.out.println(browseKitties);
        }
            kittyListBrowse();
        System.out.println("Would you like to continue browsing kitties? (yes/no): ");
        String yesNo = readInput();
        if (yesNo.equals("no")) {
            anythingElse();
        } else if (yesNo.equals("yes")) {
            for (String browseKitties : kittyNames) {
                System.out.println(browseKitties);
            }
            kittyListBrowse();
        }
    }

    private void updateKitty() throws IOException {
        System.out.println("Purrfect, which kimmty will you be updating?: ");
        kittyXName = readInput();
        System.out.println("Would you like to change " + kittyXName + "'s name, bio, or pronouns?: ");
        String change = readInput();
        if (change.equals("name")) {
            System.out.println("Okay, enter your kitty's new name here: ");
             kittyXName = readInput();
             System.out.println("New name is " + kittyXName + ".  Confirm? (yes/no):" );
             String yesNoName = readInput();
             if (yesNoName.equals("no")){
                 System.out.println("Alrighty!" + kittyXName + "'s name won't be changed. ");
                 anythingElse();
             } else if (yesNoName.equals("yes")) {
                 dataBase.saveKitty(kittyXName, kittyXDescription, kittyXPronouns);
                 dataBase.listKittyNames();
                 System.out.println("1...2...3...Wowza! " + "Hello, " + kittyXName + "!" );
                 anythingElse();
             }
        }else if (change.equals("bio")){
            System.out.println("Type in " + kittyXName + "'s new bio here: ");
            kittyXDescription = readInput();
            System.out.println( kittyXName   + "'s new bio:" + kittyXDescription);
            System.out.println("Confirm changes? (yes/no): ");
            String yesNoChange = readInput();
            if (yesNoChange.equals("no")) {
                System.out.println("Okay, we'll leave " + kittyXName + "'s bio alone :) ");
                anythingElse();
            }else if (yesNoChange.equals("yes")) {
                kittyXPronouns = dataBase.getKittyPronouns(kittyXName);
                System.out.println("Hooray! " + kittyXName + "'s bio has been updated!");
                dataBase.saveKitty(kittyXName, kittyXDescription, kittyXPronouns);
                anythingElse();
            }
        }else if (change.equals("pronouns")) {
            System.out.println("Okay, enter " + kittyXName + "'s new pronouns here: ");
            kittyXPronouns = readInput();
            System.out.println("Purrfect, " + kittyXName + "'s pronouns are now:  " + kittyXPronouns + "\n");
            System.out.println("Save changes? (yes/no): ");
            String yesNoProChange = readInput();
            if (yesNoProChange.equals("no")) {
                System.out.println("Okay, " + kittyXName + "'s pronouns won't change. ");
                anythingElse();
            }else if (yesNoProChange.equals("yes")) {
               kittyXName = dataBase.getKittyDescription(kittyXName);
                dataBase.saveKitty(kittyXName, kittyXDescription, kittyXPronouns);
                System.out.println("OMG! " + kittyXName + "'s pronouns were changed to: " + kittyXPronouns);
                anythingElse();
            }

        }

        }
        
    private void anythingElse() throws IOException {
        System.out.println("Would you like to do anything else? (yes/no): ");
        String yesNo = readInput();
        String nextPrompt = "What would you like to do?\n" +
                "Please choose from the list of functions below:\n" +
                PurpleText("                                           browse gallery (b)\n" +
                YellowText("                                           search kitty (s)\n") +
                GreenText("                                            remove kitty (r)\n") +
                          "                                            update kitty (u)\n" +
                          "                                            add kitty (a) \n");
        if (yesNo.equals("no")) {
            System.out.println("We'll miss you!  Have a purrfect day! \n\n\n");
            run();
        }else if (yesNo.equals("yes")){
            System.out.println(nextPrompt);
        }String chooseFunction = readInput();
        chooseFunction = chooseFunction.toLowerCase();
        if(chooseFunction.equals("a")||chooseFunction.equals("add kitty") || chooseFunction.equals("add")) {
            addKitty();
        }else if (chooseFunction.equals("s") || chooseFunction.equals("search kitty") ||
                chooseFunction.equals("search")) {
            searchKitty();
            String userInput = readInput();
            userInput = userInput.toLowerCase();
            printCatBio(userInput);
        }else if (chooseFunction.equals("r") || chooseFunction.equals("remove kitty") ||
                chooseFunction.equals("remove")) {
            removeKitty();
        }else if (chooseFunction.equals("b") || chooseFunction.equals("browse gallery") ||
                chooseFunction.equals("browse")) {
            browseKitties();
        } else if (chooseFunction.equals("u") || chooseFunction.equals("update kitty") ||
                chooseFunction.equals("update")){
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
                    PurpleText("                                           browse gallery (b)\n") +
                    YellowText("                                           search kitty (s)\n") +
                    GreenText("                                            remove kitty (r)\n") +
                              "                                            update kitty (u)\n" +
                              "                                            add kitty (a)\n";
            System.out.println(firstPrompt);
            String chooseFunction = readInput();
            chooseFunction = chooseFunction.toLowerCase();
            if(chooseFunction.equals("a") || chooseFunction.equals("add kitty") || chooseFunction.equals("add")) {
                addKitty();
            }else if (chooseFunction.equals("s") || chooseFunction.equals("search kitty") ||
                    chooseFunction.equals("search")) {
                searchKitty();
                String userInput = readInput();
                userInput = userInput.toLowerCase();
                printCatBio(userInput);
            }else if (chooseFunction.equals("u") || chooseFunction.equals("update kitty") ||
                    chooseFunction.equals("update")) {
                updateKitty();
            }else if (chooseFunction.equals("b") || chooseFunction.equals("browse gallery") ||
                    chooseFunction.equals("browse")){
                browseKitties();
            } else if (chooseFunction.equals("r") || chooseFunction.equals("remove kitty") ||
                    chooseFunction.equals("remove")) {
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
            System.out.println("Welcome to our Gato Gallery " + kittyXName + "!");
            dataBase.saveKitty(kittyXName, kittyXDescription, kittyXPronouns);
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
