
package nyla.solutions.formInjection.formatter.html;

import nyla.solutions.formInjection.data.FormAnswer;
import nyla.solutions.formInjection.data.FormQuestion;

/**
 * 
 * <pre>
 * FormQuestionViewExtra provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormQuestionViewExtra extends FormQuestionView {
    private String textStyleClass;
    
    public FormQuestionViewExtra(FormQuestion question) {
        super(question);
    }
    public String getAnswerStyleClass() {
        if (hasAnswer())
            return getAnswer().getStyleClass();
        if (hasTable())
            return getTable().getAnswerStyleClass();
        return "";
    }
    public void setAnswerStyleClass(String answerStyleClass) {
        super.setStyleClass(answerStyleClass);
    }
    public String getAnswerWidth() {
        if (hasAnswer())
            return getAnswer().getWidth();
        if (hasTable())
            return getTable().getAnswerWidth();
        return "";
    }
    public void setAnswerWidth(String answerWidth) {
        super.setAnswerWidth(answerWidth);
    }
    public String getQuestionText() {
        return getText();
    }
    public String getQuestionWidth() {
        return getWidth();
    }
    public void setQuestionWidth(String questionWidth) {
        setWidth(questionWidth);
    }
    public void setTextStyleClass(String styleClass) {
        super.setTextStyleClass(styleClass);
        this.textStyleClass = styleClass;
    }
    public String getTextStyleClass() {
        return textStyleClass;
    }
    public void setQuestion(FormQuestion question) {
        this.question = question;
        if (question.hasTable())
            table = new FormTableView(question.getFormTable());
        //else {
	        if (question.hasAnswer())
	            this.answer = new FormAnswerViewExtra(question.getAnswer());
	        else
	            this.answer = new FormAnswerViewExtra(new FormAnswer(question, 0, 0));
        //}
    }
    public void setAnswerText(String text) {
        FormAnswerViewExtra view = (FormAnswerViewExtra) getAnswer();
        view.setText(text);
    }
}
