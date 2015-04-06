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
        "Rocks",
        "Count"
})
public class JsonRockData {

    @JsonProperty("Rocks")
    private List<JsonRock> Rocks = new ArrayList<JsonRock>();
    @JsonProperty("Count")
    private Integer Count;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Rocks
     */
    @JsonProperty("Rocks")
    public List<JsonRock> getRocks() {
        return Rocks;
    }

    /**
     *
     * @param Rocks
     * The Rocks
     */
    @JsonProperty("Rocks")
    public void setRocks(List<JsonRock> Rocks) {
        this.Rocks = Rocks;
    }

    /**
     *
     * @return
     * The Count
     */
    @JsonProperty("Count")
    public Integer getCount() {
        return Count;
    }

    /**
     *
     * @param Count
     * The Count
     */
    @JsonProperty("Count")
    public void setCount(Integer Count) {
        this.Count = Count;
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