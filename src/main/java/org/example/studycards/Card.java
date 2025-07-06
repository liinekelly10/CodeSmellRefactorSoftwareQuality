package org.example.studycards;

public class Card {
    private String question;
    private String answer;

    private Card(String question, String answer) {
        validate(question, answer);
        this.question = question;
        this.answer = answer;
    }

    public static Card create(String question, String answer) {
        return new Card(question, answer);
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setQuestion(String question) {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }
        this.question = question;
    }

    public void setAnswer(String answer) {
        if (answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be null or empty");
        }
        this.answer = answer;
    }

    public void edit(String question, String answer) {
        setQuestion(question);
        setAnswer(answer);
    }

    private void validate(String question, String answer) {
        if (question == null || question.isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }
        if (answer == null || answer.isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be null or empty");
        }
    }
}
