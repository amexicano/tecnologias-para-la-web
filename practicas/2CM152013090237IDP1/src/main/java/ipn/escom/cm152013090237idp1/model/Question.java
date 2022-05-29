package ipn.escom.cm152013090237idp1.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import org.jdom2.Element;

public class Question {
    private String question;
    private final List<Answer> options;
    
    public Question(String question, Answer ...options){
        this.question = question;
        this.options = new ArrayList<>();
        if(options.length != 0) this.options.addAll(Arrays.asList(options));
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getQuestion() {
        return this.question;
    }

    public List<Answer> getAnswerOptions() {
        return this.options;
    }
    
    public void addAnswerOption(Answer option){
        this.options.add(option);
    }
    
    public void updateAnswerOption(Answer option, int id){
        this.options.remove(id);
        this.options.add(id, option);
    }
    
    public static Question parseChildrenElements(Element e){
        List<Element> list = e.getChildren("OPCION");
        Question question  = new Question(e.getAttributeValue("TEXTO"));
        for(Element answer : list){
            question.addAnswerOption(new Answer(
                answer.getText(),
                Boolean.parseBoolean(answer.getAttributeValue("ISRIGHTANSWER"))
                )
            );
        }
        return question;
    }
}
