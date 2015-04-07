package uk.ac.dur.group1.killhope_museum.dto.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Term",
        "Aliases"
})
public class JsonGlossaryAlias {

    @JsonProperty("Term")
    private String Term;
    @JsonProperty("Aliases")
    private List<String> Aliases = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Term
     */
    @JsonProperty("Term")
    public String getTerm() {
        return Term;
    }

    /**
     *
     * @param Term
     * The Term
     */
    @JsonProperty("Term")
    public void setTerm(String Term) {
        this.Term = Term;
    }

    /**
     *
     * @return
     * The Aliases
     */
    @JsonProperty("Aliases")
    public List<String> getAliases() {
        return Aliases;
    }

    /**
     *
     * @param Aliases
     * The Aliases
     */
    @JsonProperty("Aliases")
    public void setAliases(List<String> Aliases) {
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