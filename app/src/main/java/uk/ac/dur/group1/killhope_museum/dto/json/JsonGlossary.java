package uk.ac.dur.group1.killhope_museum.dto.json;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Glossary"
})
public class JsonGlossary {

    public static JsonGlossary fromJson(String json)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, JsonGlossary.class);
        }
        catch (IOException e)
        {
            throw new IllegalStateException("invalid JSON", e);
        }

    }

    @JsonProperty("Glossary")
    private List<JsonGlossaryItem> Glossary = new ArrayList<JsonGlossaryItem>();
    @JsonProperty("Aliases")
    private List<JsonGlossaryAlias> Aliases = new ArrayList<JsonGlossaryAlias>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The Glossary
     */
    @JsonProperty("Glossary")
    public List<JsonGlossaryItem> getGlossary() {
        return Glossary;
    }

    /**
     * @param Glossary The Glossary
     */
    @JsonProperty("Glossary")
    public void setGlossary(List<JsonGlossaryItem> Glossary) {
        this.Glossary = Glossary;
    }

    /**
     *
     * @return
     * The Aliases
     */
    @JsonProperty("Aliases")
    public List<JsonGlossaryAlias> getAliases() {
        return Aliases;
    }

    /**
     *
     * @param Aliases
     * The Aliases
     */
    @JsonProperty("Aliases")
    public void setAliases(List<JsonGlossaryAlias> Aliases) {
        this.Aliases = Aliases;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}