package entities;

import enums.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Question extends Post{
    private String title;
    private Set<Tag> tags;
    private final List<Answer> answers = new ArrayList<>();
    private Answer acceptedAnswer;

    public Question(String title, String body, User author, Set<Tag> tags) {
        super(body, author);
        this.title = title;
        this.tags = tags;
        System.out.println("Question Posted Successfully with title: " + title);
    }

    public String getTitle() {
        return title;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setAcceptAnswer(Answer answer) {
        this.acceptedAnswer = answer;
        answer.setAcceptAnswer(true);
        notifyObservers(new Event(EventType.ACCEPT_ANSWER, answer.getAuthor(), this));
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }


}
