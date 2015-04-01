
package uk.ac.dur.group1.killhope_museum.dto;

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
public class Room {

    @JsonProperty("id")
    private Object id;
    @JsonProperty("rocks")
    private Object rocks;
    @JsonProperty("points")
    private List<Point> points = new ArrayList<Point>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public Object getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(Object id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The rocks
     */
    @JsonProperty("rocks")
    public Object getRocks() {
        return rocks;
    }

    /**
     *
     * @param rocks
     * The rocks
     */
    @JsonProperty("rocks")
    public void setRocks(Object rocks) {
        this.rocks = rocks;
    }

    /**
     *
     * @return
     * The points
     */
    @JsonProperty("points")
    public List<Point> getPoints() {
        return points;
    }

    /**
     *
     * @param points
     * The points
     */
    @JsonProperty("points")
    public void setPoints(List<Point> points) {
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

