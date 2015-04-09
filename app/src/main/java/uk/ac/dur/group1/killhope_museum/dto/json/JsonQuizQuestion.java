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
        "Question",
        "Answers",
        "Correct"
})
public class JsonQuizQuestion {

    @JsonProperty("Question")
    private String Question;
    @JsonProperty("Answers")
    private List<String> Answers = new ArrayList<String>();
    @JsonProperty("Correct")
    private Integer Correct;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The Question
     */
    @JsonProperty("Question")
    public String getQuestion() {
        return Question;
    }

    /**
     *
     * @param Question
     * The Question
     */
    @JsonProperty("Question")
    public void setQuestion(String Question) {
        this.Question = Question;
    }

    /**
     *
     * @return
     * The Answers
     */
    @JsonProperty("Answers")
    public List<String> getAnswers() {
        return Answers;
    }

    /**
     *
     * @param Answers
     * The Answers
     */
    @JsonProperty("Answers")
    public void setAnswers(List<String> Answers) {
        this.Answers = Answers;
    }

    /**
     *
     * @return
     * The Correct
     */
    @JsonProperty("Correct")
    public Integer getCorrect() {
        return Correct;
    }

    /**
     *
     * @param Correct
     * The Correct
     */
    @JsonProperty("Correct")
    public void setCorrect(Integer Correct) {
        this.Correct = Correct;
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