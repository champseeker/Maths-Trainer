package sg.edu.rp.c346.mathsgames;

public class Question {

    public String question;
    public Double answer;
    public Integer questionNumber;

    public Question(Integer quesNum, String ques, Double ans) {
        this.questionNumber = quesNum;
        this.question = ques;
        this.answer = ans;
    }

    public Double getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }

}