package uk.ac.dur.group1.killhope_museum.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * A Quiz consists of three levels: Easy, Medium and Hard.
 */
public class QuizDTO
{
    private final List<QuizQuestion> easyQuestions;
    private final List<QuizQuestion> mediumQuestions;
    private final List<QuizQuestion> hardQuestions;

    private static <T> void verifyList(List<T> list, String name)
    {
        if(list == null)
            throw new IllegalArgumentException(String.format("%s is null", name));
        if(list.size() == 0)
            throw new IllegalArgumentException(String.format("%s contains no elements", name));
    }

    public QuizDTO(List<QuizQuestion> easyQuestions, List<QuizQuestion> mediumQuestions, List<QuizQuestion> hardQuestions)
    {
        verifyList(easyQuestions, "easyQuestions");
        verifyList(mediumQuestions, "mediumQuestions");
        verifyList(hardQuestions, "hardQuestions");

        this.easyQuestions = easyQuestions;
        this.mediumQuestions = mediumQuestions;
        this.hardQuestions = hardQuestions;
    }

    /**
     * @return The sum of the total number of questions from each section
     */
    public int getQuestionCount()
    {
        return easyQuestions.size() + mediumQuestions.size() + hardQuestions.size();
    }

    public List<QuizQuestion> getEasyQuestions() {
        return easyQuestions;
    }

    public List<QuizQuestion> getMediumQuestions() {
        return mediumQuestions;
    }

    public List<QuizQuestion> getHardQuestions() {
        return hardQuestions;
    }

    public static class QuizQuestion
    {
        private final ArrayList<String> answers;
        private final String question;
        private final int correctAnswerIndex;

        public QuizQuestion(String question, List<String> answers, int correctAnswer) {
            if(question == null)
                throw new IllegalArgumentException("question is null");
            if(answers == null)
                throw new IllegalArgumentException("answers is null");
            if(answers.size() == 0)
                throw new IllegalArgumentException("answers has no values");

            if(correctAnswer >= answers.size())
                throw new IllegalArgumentException("The correct answer supplied does not match with the answers provided");

            ArrayList<String> actualAnswers = new ArrayList<>(answers);


            this.question = question;
            this.answers = actualAnswers;
            this.correctAnswerIndex = correctAnswer;
        }


        /**
         *
         * @param index
         * @return Whether the supplied 0-based index is the correct answer.
         */
        public boolean isCorrectAnswerIndex(int index)
        {
            return index == this.correctAnswerIndex;
        }

        public int numberOfPossibleAnswers()
        {
            return answers.size();
        }

        public List<String> getAnswers()
        {
            return (ArrayList<String>) answers.clone();
        }

        public String getQuestion()
        {
            return question;
        }
    }
}
