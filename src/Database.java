import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Database {

    private ObjectMapper objectMapper;
    private DefaultPrettyPrinter printer;

    public Database() {
        this.objectMapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);
        this.printer = new DefaultPrettyPrinter()
                .withObjectIndenter(new DefaultIndenter("  ", "\n"))
                .withArrayIndenter(new DefaultIndenter("  ", "\n"));
    }

    /**
     * get names of all kitties currently in the database
     */
    public ArrayList<String> listKittyNames() throws IOException {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<CatData> data = readKitties();
        for (CatData cat : data) {
            names.add(cat.getName());
        }
        return names;
    }

    /**
     * get description for kitty. if kitty does not exist in database, returns empty string ""
     */
    public String getKittyDescription(String kittyName) throws IOException {
        ArrayList<CatData> cats = readKitties();
        for (CatData cat : cats) {
            if (cat.getName().equals(kittyName)) {
                return cat.getDescription();
            }
        }
        // kitty was not found
        return "";
    }

    /**
     * get pronouns for kitty. if kitty does not exist in database, returns empty string ""
     */
    public String getKittyPronouns(String kittyName) throws IOException {
        ArrayList<CatData> cats = readKitties();
        for (CatData cat : cats) {
            if (cat.getName().equals(kittyName)) {
                return cat.getPronouns();
            }
        }
        // kitty was not found
        return "";
    }

    /**
     * save new kitty to database.
     * if a kitty with this name already exists, its description will be overwritten.
     */
    public void saveKitty(String name, String description, String pronouns) throws IOException {
        ArrayList<CatData> cats = readKitties();
        // convert to map to remove duplicates
        Map<String, CatData> catMap = cats.stream()
                .collect(Collectors.toMap(CatData::getName, Function.identity()));
        // create and add new kitty
        CatData newKitty = new CatData(name, description, pronouns);
        catMap.put(name, newKitty);
        // change map back to basic array list
        ArrayList<CatData> updatedCats = new ArrayList<>(catMap.values());
        saveKitties(updatedCats);
    }

    /**
     * remove a kitty from the database.
     * just provide the kitty's name.
     * if the kitty does not exist, then nothing will happen.
     */
    public void removeKitty(String name) throws IOException {
        ArrayList<CatData> cats = readKitties();
        cats.removeIf(x -> x.getName().equals(name));
        saveKitties(cats);
    }

    /**
     * reads list of cats from data.json. returns as easily usable ArrayList
     */
    private ArrayList<CatData> readKitties() throws IOException {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/data.json"));
        Map<String, CatData[]> allData = objectMapper.readValue(jsonData, new TypeReference<Map<String, CatData[]>>(){});
        CatData[] catData = allData.get("cats");
        return new ArrayList<>(Arrays.asList(catData));
    }

    /**
     * saves list of cats to data.json file
     */
    private void saveKitties(ArrayList<CatData> cats) throws IOException {
        // sort cats alphabetically by name
        ArrayList<CatData> sortedCats = (ArrayList<CatData>) cats.stream()
                .sorted((x1, x2) ->  x1.getName().compareTo(x2.getName()))
                .collect(Collectors.toList());
        Map<String, ArrayList<CatData>> jsonData = new HashMap<>();
        jsonData.put("cats", sortedCats);
        File file = new File("src/data.json");
        objectMapper.writer(printer).writeValue(file, jsonData);
    }
}
