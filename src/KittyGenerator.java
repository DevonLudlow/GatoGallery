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

    public void run() throws IOException {
        System.out.println("Hello! Request information about one of our beloved kitties below. ");
        while (true) {
            System.out.print("Search for kimmtty here: ");
            String userInput = readInput();
            userInput = userInput.toLowerCase();
            printCatBio(userInput);

        }
    }

    public String readInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInput = br.readLine();
        return userInput;
    }


    String wasabi;
    String gura;
    String ivy;
    String goku;
    String poppy;
    String clover;
    String pepper;
    String lemon;

    public void printCatBio(String userInput) throws IOException {
        ArrayList<String> kittyNames = dataBase.listKittyNames();
        for (String kittyName: kittyNames){
            if (kittyName.equals(userInput)){

                String kittyDescription = dataBase.getKittyDescription(kittyName);
                System.out.println(kittyDescription);
                return;
            }
        }
        addNewKitty();





        }

    public void addNewKitty() throws IOException {
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
