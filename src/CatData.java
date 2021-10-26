import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class CatData {
    private String name;
    private String description;
    private String pronouns;

    @JsonCreator
    public CatData(@JsonProperty("name") String name,
                   @JsonProperty("description") String description,
                   @JsonProperty("pronouns")String pronouns) {
        this.name = name;
        this.description = description;
        this.pronouns = pronouns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPronouns() {
        return pronouns;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }
}
