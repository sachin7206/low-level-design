package strategy.searchStrategy;

import entities.Question;

import java.util.List;

public class KeywordSearchStrategy implements SearchStrategy {
    String keyWord;
    public KeywordSearchStrategy(String keyWord) {
        this.keyWord = keyWord;
    }
    @Override
    public List<Question> filter(List<Question> questions) {
        return questions.stream().filter(
                question -> question.getTitle().toLowerCase().contains(keyWord.toLowerCase()) || question.getBody().toLowerCase().contains(keyWord.toLowerCase())
        ).collect(java.util.stream.Collectors.toList());
    }
}
