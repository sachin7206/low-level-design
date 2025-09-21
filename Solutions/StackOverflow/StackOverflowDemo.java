import entities.Answer;
import entities.Question;
import entities.Tag;
import entities.User;
import enums.VoteType;
import strategy.searchStrategy.KeywordSearchStrategy;
import strategy.searchStrategy.SearchStrategy;
import strategy.searchStrategy.TagSearchStrategy;
import strategy.searchStrategy.UserSearchStrategy;

import java.util.List;
import java.util.Set;

public class StackOverflowDemo {
    public static void main(String[] args) {
        StackOverflow stackOverflow = StackOverflow.getInstance();

        Tag javaTag = new Tag("java");
        Tag designPatternsTag = new Tag("design-patterns");
        Tag singletonDesignPatternsTag = new Tag("singleton-design-pattern");
        Tag factoryDesignPatternsTag = new Tag("factory-design-pattern");


        // creating users - sachin, rithik, sowmya
        System.out.println("--- Creating Users ---");
        User sachin = stackOverflow.createUser("sachin", "sbisht", "sachin.bisht.7206352778@gmail.com");
        User rithik = stackOverflow.createUser("rithik", "rk123", "rithik123@gmail.com");
        User sowmya = stackOverflow.createUser("sowmya", "sw123", "sowmya123@gmail.com");
        User shivaya = stackOverflow.createUser("shivaya", "shivayaGeol", "shivayaGeol@gmail.com");


        // sachin and rithik posting questions
        System.out.println("\n" + "--- PostQuestions. --- ");
        Set<Tag> sachinQuestionTags = Set.of(javaTag, designPatternsTag, singletonDesignPatternsTag);
        Question sachinQuestion = stackOverflow.postQuestion("What is Singleton Design Pattern?", "I want to understand Singleton Design Pattern with example.", sachin.getUserId(), sachinQuestionTags);
        Set<Tag>rithikQuestionTags = Set.of(javaTag, designPatternsTag, factoryDesignPatternsTag);
        Question rithikQuestion = stackOverflow.postQuestion("What is Factory Design Pattern?", "I want to understand Factory Design Pattern with example.", rithik.getUserId(), rithikQuestionTags);
        printReputation(sachin, rithik, sowmya, shivaya);

        // sowmya and shivaya post answers
        System.out.println("--- PostAnswer --- ");
        Answer sowmyaAnswer = stackOverflow.addAnswer("Singleton pattern ensures a class has only one instance and provides a global point of access to it.", sowmya.getUserId(), sachinQuestion.getId());
        Answer shivayaAnswerOnSachinQuestion = stackOverflow.addAnswer("There is no singleton pattern in design patterns", shivaya.getUserId(), sachinQuestion.getId());
        Answer shivayaAnswerOnRithikQuestion = stackOverflow.addAnswer("Factory pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes.", shivaya.getUserId(), rithikQuestion.getId());
        printReputation(sachin, rithik, sowmya, shivaya);

        // Voting happens
        System.out.println("\n --- Voting on Questions and Answers --- ");
        stackOverflow.voteOnPost(sachin.getUserId(), sachinQuestion.getId(), VoteType.UPVOTE); // sachin upvotes her own question
        stackOverflow.voteOnPost(sachin.getUserId(), sowmyaAnswer.getId(), VoteType.UPVOTE); // sachin upvotes sowmya answer
        stackOverflow.voteOnPost(sachin.getUserId(), shivayaAnswerOnSachinQuestion.getId(), VoteType.DOWNVOTE); // sachin downvotes shivaya answer
        stackOverflow.voteOnPost(rithik.getUserId(), shivayaAnswerOnRithikQuestion.getId(), VoteType.UPVOTE); // rithik upvotes shivaya answer
        printReputation(sachin, rithik, sowmya, shivaya);

        // accept Answer
        System.out.println("\n--- Accept Answer --- ");
        System.out.println("\n sachin accepting sowmya's answer on his question");
        stackOverflow.acceptAnswer(sachinQuestion.getId(), sowmyaAnswer.getId()); // sachin accepts sowmya's answer
        printReputation(sachin, rithik, sowmya, shivaya);

        // searching questions
        System.out.println("\nsearching question with tag - design-patterns, keyword - Singleton Design Pattern and user - sachin");
        List<SearchStrategy> strategies = List.of(
                new TagSearchStrategy(designPatternsTag),
                new KeywordSearchStrategy("Singleton Design Pattern"),
                new UserSearchStrategy(sachin)

        );
        List<Question> foundQuestions = stackOverflow.searchQuestionByQuery(strategies);
    }

    private static void  printReputation(User... users) {
        System.out.println("\n--- Current Reputations ---");
        for(User user : users) {
            System.out.println(user.getName() + " reputation: "  + user.getReputation());
        }
    }

}
