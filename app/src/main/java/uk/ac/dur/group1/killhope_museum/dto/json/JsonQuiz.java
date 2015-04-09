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
        "SectionName",
        "Questions"
})
public class JsonQuiz {

    @JsonProperty("SectionName")
    private String SectionName;
    @JsonProperty("Questions")
    private List<JsonQuizQuestion> Questions = new ArrayList<>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     *
     * @return
     * The SectionName
     */
    @JsonProperty("SectionName")
    public String getSectionName() {
        return SectionName;
    }

    /**
     *
     * @param SectionName
     * The SectionName
     */
    @JsonProperty("SectionName")
    public void setSectionName(String SectionName) {
        this.SectionName = SectionName;
    }

    /**
     *
     * @return
     * The Questions
     */
    @JsonProperty("Questions")
    public List<JsonQuizQuestion> getQuestions() {
        return Questions;
    }

    /**
     *
     * @param Questions
     * The Questions
     */
    @JsonProperty("Questions")
    public void setQuestions(List<JsonQuizQuestion> Questions) {
        this.Questions = Questions;
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