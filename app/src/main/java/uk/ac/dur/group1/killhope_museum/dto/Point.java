
package uk.ac.dur.group1.killhope_museum.dto;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "x",
        "y"
})
public class Point {

    @JsonProperty("x")
    private int x;
    @JsonProperty("y")
    private int y;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The x
     */
    @JsonProperty("x")
    public int getX() {
        return x;
    }

    /**
     *
     * @param x
     * The x
     */
    @JsonProperty("x")
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return
     * The y
     */
    @JsonProperty("y")
    public int getY() {
        return y;
    }

    /**
     *
     * @param y
     * The y
     */
    @JsonProperty("y")
    public void setY(int y) {
        this.y = y;
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