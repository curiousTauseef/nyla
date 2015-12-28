/*
 * Created on Nov 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package nyla.solutions.formInjection.formatter.html;

import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.formInjection.data.FormQuestion;
import nyla.solutions.formInjection.web.AutoCounter;


/**
 * 
 * <pre>
 * FormQuestionView is a value object representation of the FormQuestionView table
and associated entities.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormQuestionView extends FormComponentView {

    
    public FormQuestionView(FormQuestion question) {
        setQuestion(question);
    }

    public String getStyleClass() {
        return fix(super.getStyleClass(), DEFAULT_STYLE_CLASS);
    }
    public String getWidth() {
        return fix(super.getWidth(), DEFAULT_WIDTH);
    }
    public boolean hasTable() {
        return table != null;
    }
    public boolean hasAnswer() {
        return answer != null;
    }
    /*
    public boolean isRequired() {
        return question.isRequired();
    }
    */
    public String getNumberLabel() {
        return fix(numberLabel);
    }
    public void setNumberLabel(String numberLabel) {
        this.numberLabel = numberLabel;
    }
    public FormQuestion getFormQuestion() {
        return question;
    }
    public void setQuestion(FormQuestion question) {
        this.question = question;
        if (question.hasTable())
            table = new FormTableView(question.getFormTable());
        else {
	        this.answer = new FormAnswerView(question.getAnswer());
        }
    }
    public void setFormComponent(FormComponent component) {
        setQuestion((FormQuestion) question);
    }
    public FormComponent getFormComponent() {
        return this.getFormQuestion();
    }
    public Form getForm() {
        return getFormQuestion().getForm();
    }
    public FormTableView getTable() {
        return table;
    }
    public FormAnswerView getAnswer() {
        return answer;
    }
    public String getName() {
        if (table != null)
            return table.getName();
        return "question";
    }
    public FormQuestionView getQuestion() {
        return this;
    }
    public String getTextStyleClass() {
        String styleClass = fix(textStyleClass);
        if (table != null)
            styleClass = fix(table.getTextStyleClass(), styleClass);
        return styleClass;
    }
    public void setTextStyleClass(String styleClass) {
        this.textStyleClass = styleClass;
        if (table != null)
            table.setTextStyleClass(styleClass);
    }
    public void setAnswerStyleClass(String styleClass) {
        if (answer != null)
            answer.setStyleClass(styleClass);
        if (table != null)
            table.setAnswerStyleClass(styleClass);
    }
    public void setAutoNumber(boolean autoNumber) {
        super.setAutoNumber(autoNumber);
        if (answer != null)
            answer.setAutoNumber(autoNumber);
        if (table != null)
            table.setAutoNumber(autoNumber);
    }
    public void setImgPath(String imgPath) {
        super.setImgPath(imgPath);
        if (answer != null)
            answer.setImgPath(imgPath);
        if (table != null)
            table.setImgPath(imgPath);
        
    }
    public void setCounter(AutoCounter counter) {
        super.setCounter(counter);
        if (answer != null)
            answer.setCounter(counter);
        if (table != null)
            table.setCounter(counter);
    }
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        if (answer != null)
            answer.setReadOnly(readOnly);
        if (table != null)
            table.setReadOnly(readOnly);
    }
    public void setStyleClass(String styleClass) {
        super.setStyleClass(styleClass);
        if (table != null)
            table.setStyleClass(styleClass);
    }
    
    public void setWidth(String width) {
        super.setWidth(width);
        if (table != null)
            table.setQuestionWidth(width);
    }
    public void setAnswerWidth(String width) {
        if (answer != null)
            answer.setWidth(width);
        if (table != null)
            table.setAnswerWidth(width);
    }
    
    public void setMaxLength(String maxLength) {
        if (answer != null)
            answer.setMaxLength(maxLength);
        if (table != null)
            table.setMaxLength(maxLength);
    }
    public void setOnBlurJS(String onBlurJS) {
        if (answer != null)
            answer.setOnBlurJS(onBlurJS);
        if (table != null)
            table.setOnBlurJS(onBlurJS);
    }
    public void setOnChangeJS(String onChangeJS) {
        if (answer != null)
            answer.setOnChangeJS(onChangeJS);
        if (table != null)
            table.setOnChangeJS(onChangeJS);
    }
    public void setOnClickJS(String onClickJS) {
        if (answer != null)
            answer.setOnClickJS(onClickJS);
        if (table != null)
            table.setOnClickJS(onClickJS);
    }
    public void setOnFocusJS(String onFocusJS) {
        if (answer != null)
            answer.setOnFocusJS(onFocusJS);
        if (table != null)
            table.setOnFocusJS(onFocusJS);
    }//--------------------------------------------
    
    public boolean getHasHelpText()
    {
       return this.question.hasHelpText();
    }//--------------------------------------------
    /*
    public void setValidateOnSave(boolean validateOnSave) {
        if (answer != null)
            answer.setValidateOnSave(validateOnSave);
        if (table != null)
            table.setValidateOnSave(validateOnSave);
    }
    public void setValidateOnSubmit(boolean validateOnSubmit) {
        if (answer != null)
            answer.setValidateOnSubmit(validateOnSubmit);
        if (table != null)
            table.setValidateOnSubmit(validateOnSubmit);
    }
    */
    public static final String DEFAULT_STYLE_CLASS = "questionText";
    public static final String DEFAULT_WIDTH = "40%";

    protected FormQuestion question;
    protected FormTableView table;
    protected FormAnswerView answer;
    private String numberLabel;
    private String textStyleClass;
}
