package strategy.searchStrategy;

import entities.Question;
import entities.Tag;

import java.util.List;
import java.util.Set;

public class TagSearchStrategy implements SearchStrategy {
    private Tag tag;
    public TagSearchStrategy(Tag tag) {
        this.tag = tag;

    }
    @Override
    public List<Question> filter(List<Question> questions) {
        return questions.stream().filter(
                question -> question.getTags().stream().anyMatch(t -> t.getTagId().equals(tag.getTagId()))
        ).collect(java.util.stream.Collectors.toList());
    }
}
