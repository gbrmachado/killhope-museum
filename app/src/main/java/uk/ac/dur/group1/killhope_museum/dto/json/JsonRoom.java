
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
        "id",
        "rocks",
        "points"
})
public class JsonRoom {

    @JsonProperty("id")
    private String id;
    @JsonProperty("rocks")
    private List<String> rocks;
    @JsonProperty("points")
    private List<JsonPoint> points = new ArrayList<JsonPoint>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The rocks
     */
    @JsonProperty("rocks")
    public List<String> getRocks() {
        return rocks;
    }

    /**
     *
     * @param rocks
     * The rocks
     */
    @JsonProperty("rocks")
    public void setRocks(List<String> rocks) {
        this.rocks = rocks;
    }

    /**
     *
     * @return
     * The points
     */
    @JsonProperty("points")
    public List<JsonPoint> getPoints() {
        return points;
    }

    /**
     *
     * @param points
     * The points
     */
    @JsonProperty("points")
    public void setPoints(List<JsonPoint> points) {
        this.points = points;
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

