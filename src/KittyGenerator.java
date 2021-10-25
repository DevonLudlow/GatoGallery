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
    // hi
    //hello

    public void removeKitty(String userInput) throws IOException {
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
                        "and leaves of catnip");
                dataBase.removeKitty(kittyXName);

            }
                     } else {
            run();
        }

                }
    public void run() throws IOException {

        while (true) {
            String firstPrompt = "Hello! Welcome to Gato Gallery, an international library of kittens!\n" +
                    "Please choose from the list of functions below:\n" +
                    "                                           add new kitty\n" +
                    "                                           search existing kitty\n" +
                    "                                           remove kitty\n";
            System.out.println(firstPrompt);
            String chooseFunction = readInput();
            if(chooseFunction.equals("add new kitty")) {
                System.out.print("Enter your kimmtty's name here: ");
                System.out.print("Search for kimmtty here: ");
                String userInput = readInput();
                userInput = userInput.toLowerCase();
                printCatBio(userInput);
            }else if (yesNo.equals("no")) {
                System.out.println("what would you like to do? ");
                String rmvRqst = readInput();
                if (rmvRqst.equals("remove kitty")) {
                    removeKitty(kittyXName);
                }else {
                    run();
                }
            }
        }


    }

    public String readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInput = br.readLine();
        return userInput;
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



}
