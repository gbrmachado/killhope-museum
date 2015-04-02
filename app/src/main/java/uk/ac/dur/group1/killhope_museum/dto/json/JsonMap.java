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


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Title",
        "Image",
        "Exhibitions",
        "Rooms"
})
public class JsonMap {

    public static JsonMap fromJson(String json)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, JsonMap.class);
        }
        catch (IOException e)
        {
            throw new IllegalStateException("invalid JSON", e);
        }

    }

    @JsonProperty("Height")
    private int Height;
    @JsonProperty("Title")
    private String Title;
    @JsonProperty("Image")
    private String Image;
    @JsonProperty("Exhibitions")
    private List<Object> Exhibitions = new ArrayList<Object>();
    @JsonProperty("Rooms")
    private List<JsonRoom> Rooms = new ArrayList<JsonRoom>();
    @JsonIgnore
    private java.util.Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Height")
    public int getHeight() {
        return Height;
    }
    @JsonProperty("Height")
    public void setHeight(int Height) {
        this.Height = Height;
    }

    /**
     *
     * @return
     * The Title
     */
    @JsonProperty("Title")
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @param Title
     * The Title
     */
    @JsonProperty("Title")
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     *
     * @return
     * The Image
     */
    @JsonProperty("Image")
    public String getImage() {
        return Image;
    }

    /**
     *
     * @param Image
     * The Image
     */
    @JsonProperty("Image")
    public void setImage(String Image) {
        this.Image = Image;
    }

    /**
     *
     * @return
     * The Exhibitions
     */
    @JsonProperty("Exhibitions")
    public List<Object> getExhibitions() {
        return Exhibitions;
    }

    /**
     *
     * @param Exhibitions
     * The Exhibitions
     */
    @JsonProperty("Exhibitions")
    public void setExhibitions(List<Object> Exhibitions) {
        this.Exhibitions = Exhibitions;
    }

    /**
     *
     * @return
     * The Rooms
     */
    @JsonProperty("Rooms")
    public List<JsonRoom> getRooms() {
        return Rooms;
    }

    /**
     *
     * @param Rooms
     * The Rooms
     */
    @JsonProperty("Rooms")
    public void setRooms(List<JsonRoom> Rooms) {
        this.Rooms = Rooms;
    }

    @JsonAnyGetter
    public java.util.Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}