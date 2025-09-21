package strategy.searchStrategy;

import entities.Question;
import entities.User;

import java.util.List;

public class UserSearchStrategy implements SearchStrategy {
    private User user;
    public UserSearchStrategy(User user) {
        this.user = user;
    }

    @Override
    public List<Question> filter(List<Question> questions) {
        return questions.stream().filter(
                question -> question.getAuthor().getUserId().equals(user.getUserId())
        ).collect(java.util.stream.Collectors.toList());
    }
}
