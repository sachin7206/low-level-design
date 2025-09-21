import entities.Answer;
import entities.Question;
import entities.Tag;
import entities.User;
import enums.VoteType;
import observer.ReputationManager;
import strategy.searchStrategy.SearchStrategy;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StackOverflow {
    private Map<String, Question> questions;
    private Map<String, Answer> answers;
    private Map<String, User> users;
    private StackOverflow(){
        questions = new ConcurrentHashMap<>();
        answers = new ConcurrentHashMap<>();
        users = new ConcurrentHashMap<>();
    }

    private static StackOverflow instance = null;
    public static synchronized StackOverflow getInstance() {
        if(instance == null) {
            return new StackOverflow();
        }
        return instance;
    }

    public User createUser(String name, String userName, String gmail) {
        User user = new User(name, userName, gmail);
        users.put(user.getUserId(), user);
        return user;
    }

    public Question postQuestion(String title, String description, String userId, Set<Tag> tags) {
        User user = users.get(userId);
        Question question = new Question(title, description, user, tags);
        questions.put(question.getId(), question);
        question.addObserver(new ReputationManager());
        return question;
    }

    public List<Question> searchQuestionByQuery(List<SearchStrategy> strategies) {
        List<Question> foundQuestions = new ArrayList<>(questions.values());
        for (SearchStrategy strategy : strategies) {
            foundQuestions = strategy.filter(foundQuestions);
        }
        if(foundQuestions.isEmpty()) {
            System.out.println("No Questions Found. ");
        }
        for(Question foundQuestion : foundQuestions) {
            System.out.println("Found Question: " + foundQuestion.getTitle() + " - " + foundQuestion.getBody());
        }
        return foundQuestions;
    }

    public Answer addAnswer(String body, String userId, String questionId) {
        User user = users.get(userId);
        Question question = questions.get(questionId);
        Answer answer = new Answer(body, user, question);
        answers.put(answer.getId(), answer);
        question.addAnswer(answer);
        answer.addObserver(new ReputationManager());
        return answer;
    }

    public void voteOnPost(String userId, String postId, VoteType voteType) {
        User user = users.get(userId);
        if(questions.containsKey(postId)) {
            questions.get(postId).vote(user, voteType);
        } else if(answers.containsKey(postId)) {
            answers.get(postId).vote(user, voteType);
        } else {
            System.out.println("Post Not Found");
        }
    }

    public void acceptAnswer(String questionId, String AnswerId) {
        Question question = questions.get(questionId);
        Answer answer = answers.get(AnswerId);
        question.setAcceptAnswer(answer);
    }
}
