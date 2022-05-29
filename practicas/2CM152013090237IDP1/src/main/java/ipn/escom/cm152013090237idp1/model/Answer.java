package ipn.escom.cm152013090237idp1.model;

public class Answer{
    private String answer;
    private boolean isRightAnswer;

    public Answer(String answer, boolean isRightAnswer) {
        this.answer = answer;
        this.isRightAnswer = isRightAnswer;
    }
    
    public Answer(String answer){
        this(answer,false);
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getIsRightAnswer() {
        return isRightAnswer;
    }

    public void setIsRightAnswer(boolean isRightAnswer) {
        this.isRightAnswer = isRightAnswer;
    }
}
