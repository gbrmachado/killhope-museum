package uk.ac.dur.group1.killhope_museum.dto.json;

import java.io.IOException;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.ac.dur.group1.killhope_museum.dto.QuizDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Quiz"
})
public class JsonQuizWrapper {

    private static final String EASY = "Easy";
    private static final String MEDIUM = "Medium";
    private static final String HARD = "Hard";


    public static QuizDTO dtoFromJSON(String json)
    {
        JsonQuizWrapper wrapper = fromJson(json);

        List<QuizDTO.QuizQuestion> easyQuestions = null;
        List<QuizDTO.QuizQuestion> mediumQuestions = null;
        List<QuizDTO.QuizQuestion> hardQuestions = null;

        for(JsonQuiz q : wrapper.getQuiz())
        {
            List<QuizDTO.QuizQuestion> questions =  getQuestionsFromQuiz(q);

            switch (q.getSectionName())
            {
                case EASY:
                    easyQuestions = questions; break;
                case MEDIUM:
                    mediumQuestions = questions; break;
                case HARD:
                    hardQuestions = questions; break;
                default:
                    throw new IllegalStateException(String.format("unexpected section name: %s",q.getSectionName()));
            }
        }

        return new QuizDTO(easyQuestions, mediumQuestions, hardQuestions);


    }

    private static List<QuizDTO.QuizQuestion> getQuestionsFromQuiz(JsonQuiz q)
    {
        ArrayList<QuizDTO.QuizQuestion> questions = new ArrayList<>();
        for(JsonQuizQuestion question : q.getQuestions())
        {
            String questionText = question.getQuestion();
            List<String> answers = question.getAnswers();
            Integer correct = question.getCorrect();

            questions.add(new QuizDTO.QuizQuestion(questionText, answers, correct));
        }
        return questions;
    }

    public static JsonQuizWrapper fromJson(String json)
    {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, JsonQuizWrapper.class);
        }
        catch (IOException e)
        {
            throw new IllegalStateException("invalid JSON", e);
        }
    }

    @JsonProperty("Quiz")
    private List<JsonQuiz> Quiz = new ArrayList<>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    /**
     *
     * @return
     * The Quiz
     */
    @JsonProperty("Quiz")
    public List<JsonQuiz> getQuiz() {
        return Quiz;
    }

    /**
     *
     * @param Quiz
     * The Quiz
     */
    @JsonProperty("Quiz")
    public void setQuiz(List<JsonQuiz> Quiz) {
        this.Quiz = Quiz;
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

