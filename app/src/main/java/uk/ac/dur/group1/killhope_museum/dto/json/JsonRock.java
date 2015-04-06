
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
        "UniqueId",
        "Images",
        "GalleryImages",
        "VideoPath",
        "Title",
        "Formula",
        "Content"
})
public class JsonRock {

    @JsonProperty("UniqueId")
    private String UniqueId;
    @JsonProperty("Images")
    private List<Object> Images = new ArrayList<Object>();
    @JsonProperty("GalleryImages")
    private List<Object> GalleryImages = new ArrayList<Object>();
    @JsonProperty("VideoPath")
    private String VideoPath;
    @JsonProperty("Title")
    private String Title;
    @JsonProperty("Formula")
    private String Formula;
    @JsonProperty("Content")
    private List<JsonRock> Content = new ArrayList<JsonRock>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The UniqueId
     */
    @JsonProperty("UniqueId")
    public String getUniqueId() {
        return UniqueId;
    }

    /**
     *
     * @param UniqueId
     * The UniqueId
     */
    @JsonProperty("UniqueId")
    public void setUniqueId(String UniqueId) {
        this.UniqueId = UniqueId;
    }

    /**
     *
     * @return
     * The Images
     */
    @JsonProperty("Images")
    public List<Object> getImages() {
        return Images;
    }

    /**
     *
     * @param Images
     * The Images
     */
    @JsonProperty("Images")
    public void setImages(List<Object> Images) {
        this.Images = Images;
    }

    /**
     *
     * @return
     * The GalleryImages
     */
    @JsonProperty("GalleryImages")
    public List<Object> getGalleryImages() {
        return GalleryImages;
    }

    /**
     *
     * @param GalleryImages
     * The GalleryImages
     */
    @JsonProperty("GalleryImages")
    public void setGalleryImages(List<Object> GalleryImages) {
        this.GalleryImages = GalleryImages;
    }

    /**
     *
     * @return
     * The VideoPath
     */
    @JsonProperty("VideoPath")
    public String getVideoPath() {
        return VideoPath;
    }

    /**
     *
     * @param VideoPath
     * The VideoPath
     */
    @JsonProperty("VideoPath")
    public void setVideoPath(String VideoPath) {
        this.VideoPath = VideoPath;
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
     * The Formula
     */
    @JsonProperty("Formula")
    public String getFormula() {
        return Formula;
    }

    /**
     *
     * @param Formula
     * The Formula
     */
    @JsonProperty("Formula")
    public void setFormula(String Formula) {
        this.Formula = Formula;
    }

    /**
     *
     * @return
     * The Content
     */
    @JsonProperty("Content")
    public List<JsonRock> getContent() {
        return Content;
    }

    /**
     *
     * @param Content
     * The Content
     */
    @JsonProperty("Content")
    public void setContent(List<JsonRock> Content) {
        this.Content = Content;
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