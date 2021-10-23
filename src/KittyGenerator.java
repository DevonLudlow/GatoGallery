import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

public class KittyGenerator {
    private String kittyXName ;
    private String kittyXDescription;



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

         if (userInput.equals("wasabi")) {
             System.out.println("Wasabi is an adorable black kitty who loves playing with trash and definitely sees demons at night.");
         } else if (userInput.equals("gura")) {
             System.out.println("Gura is a fatass white cat who will stop at nothing to obtain her next meal...even if it's you.");
         } else if (userInput.equals("ivy")) {
             System.out.println("Ivy is a racoon-lookin-ass scaredy cat who bites elbows and hides under the bed for 3-5 business hours if she hears any noise above 10 decibels.");
         } else if (userInput.equals("goku")) {
             System.out.println("Honestly our database doesn't have much info on Goku...only that he is black and terrified of literally everything.  And he's got a cool name.");
         } else if (userInput.equals("poppy")) {
             System.out.println("Poppy is a fluffy (slightly overweight but we don't talk about it) white kitty who has funny eyes and loooovves hard pets.");
         } else if (userInput.equals("clover")) {
             System.out.println("Clovey Baloney is an orange tabby-cat who screams constantly and loves human food.");
         } else if (userInput.equals("lemon")) {
             System.out.println("Lemon is old, orange and grumpy.  She likes Charlotte, but not really anyone else.");
         } else if (userInput.equals(kittyXName)){
             System.out.println(kittyXDescription);
         }
         else {

             System.out.print("Kimmty not found in our database! Would you like to add your kimmty to our database?(yes/no) ");
             String yesNo = readInput();
             if (yesNo.equals("yes")) {
                 System.out.print("Enter your cat's name: ");
                 kittyXName = readInput();
                 kittyXName = kittyXName.toLowerCase();
                 System.out.print("add a cute lil description of your feline friend! ");
                 kittyXDescription = readInput();
                 System.out.println("Welcome to our kitty database " + kittyXName + "!");


             }


         }
     }

}












