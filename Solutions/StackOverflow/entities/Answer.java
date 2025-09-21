package entities;

import java.util.UUID;

public class Answer extends Post {
    private final Question question;
    private boolean acceptAnswer = false;

    public Answer(String body, User author, Question question) {
        super(body, author);
        this.question = question;
        System.out.println("\n" + "Answer Posted Successfully with body: " + body);
    }

    public Question getQuestion() {
        return question;
    }

    public void setAcceptAnswer(boolean acceptAnswer) {
        this.acceptAnswer = acceptAnswer;
    }





}
