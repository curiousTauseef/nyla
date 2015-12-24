package org.solutions.form.formatter.html;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.solutions.form.FormGuide;
import org.solutions.form.data.Form;
import org.solutions.form.data.FormAnswer;
import org.solutions.form.data.FormComponent;
import org.solutions.form.data.QuestionAttribute;
import org.solutions.form.data.QuestionChoice;

/**
 * 
 * <pre>
 * FormAnswerView provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormAnswerView extends FormComponentView {

    
    public FormAnswerView(FormAnswer answer) {
        setAnswer(answer);
    }
    public String getStyleClass() {
        return fix(super.getStyleClass(), DEFAULT_STYLE_CLASS);
    }
    public String getWidth() {
        return fix(super.getWidth(), DEFAULT_WIDTH);
    }
    public boolean isAnswerable() {
        try {
            return answer.getResponseType().isAnswerable();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public FormAnswer getAnswer() {
        return answer;
    }
    public void setAnswer(FormAnswer answer) {
        this.answer = answer;
        super.setReadOnly(answer.isReadOnly());
        this.setValidateOnSubmit(answer.isRequired());
    }
    public String getReadOnlyValue() {
        String value = null;
        if (answer.hasChoices()) {
            StringBuffer buffer = new StringBuffer();
            Collection c = answer.retrievePickedChoices();
            for (Iterator i = c.iterator(); i.hasNext(); ) {
                QuestionChoice choice = (QuestionChoice) i.next();
                buffer.append(choice.getText());
                buffer.append(", ");
            }
            if (buffer.length() > 0)
                value = buffer.substring(0, buffer.length()-2);
        }
        else {
            value = (String)answer.getValue();
        }
        return fix(value);
    }
    public void setFormComponent(FormComponent component) {
        setAnswer((FormAnswer) answer);
    }
    public FormComponent getFormComponent() {
        return getAnswer();
    }
    public Form getForm() {
        return answer.getForm();
    }
    public String getType() {
        return fix(answer.getResponseType().getCode(), DEFAULT_RESPONSE_TYPE);
    }
    public String getInputName() {
        return HTMLDecorator.decorateInputName(answer);
    }
    public String getValue() {
        return fix(answer.getValue());
    }
    public String getJsOpts() {
        return getJsOpts(true);
    }
    public String getJsOpts(boolean includeOnChange) {
        String s = "";
        if (onBlurJS != null && !onBlurJS.equals(""))
            s = " onblur=\"" + onBlurJS + "\"";
        if (onClickJS != null && !onClickJS.equals(""))
            s = " onclick=\"" + onClickJS + "\"";
        if (onFocusJS != null && !onFocusJS.equals(""))
            s = " onfocus=\"" + onFocusJS + "\"";
        if (includeOnChange) {
	        if (onChangeJS != null && !onChangeJS.equals(""))
	            s = " onchange=\"" + onChangeJS + "\"";
        }
        return s;
    }
    public String getOnBlurJS() {
        return onBlurJS;
    }
    public void setOnBlurJS(String onBlurJS) {
        this.onBlurJS = onBlurJS;
    }
    public String getOnChangeJS() {
        return fix(onChangeJS);
    }
    public void setOnChangeJS(String onChangeJS) {
        this.onChangeJS = onChangeJS;
    }
    public String getOnClickJS() {
        return onClickJS;
    }
    public void setOnClickJS(String onClickJS) {
        this.onClickJS = onClickJS;
    }
    public String getOnFocusJS() {
        return onFocusJS;
    }
    public void setOnFocusJS(String onFocusJS) {
        this.onFocusJS = onFocusJS;
    }
    public Collection getOptions() {
        return answer.getQuestionChoices();
    }
    public String getSelected(Object key) {
        QuestionChoice choice = answer.getChoice(key);
        if (choice == null)
            return "";
        
        boolean hasPicked = false;
        Map choices = answer.getChoiceMap();
        for(Iterator i = choices.values().iterator(); i.hasNext();){
        	QuestionChoice iterChoice = (QuestionChoice) i.next();
        	hasPicked |= answer.isPicked(iterChoice);
        	if (answer.isPicked(iterChoice) && choice.equals(iterChoice)) {
                if ("listbox".equals(getType()))
                    return " selected";
                return " checked";
            }
        }
        
        if (!hasPicked && choice.getDefaultCode().equals("Y")) {
        	if ("listbox".equals(getType()))
                return " selected";
            return " checked";
        }
        	
        return "";
    }
    public String getAnswerId() {
        Object key = answer.getKey();
        if (key == null)
            answer.generateKey();
        return String.valueOf(answer.getKey());
    }
    public String getMultiSelectLabel() {
        String label = null;
        if (answer.isWithinTable())
            label = answer.getFormColumn().getText();
        else {
	        QuestionAttribute attribute = answer.findAttributeByName(FormGuide.MULTI_SELECT_LABEL_PROP_NM);
	        if(attribute != null && attribute.getValue() != null)
	        {
	           label = attribute.getValue().toString();
	        }
        }
        return fix(label);
    }
    public String getMultiSelectAction() {
        String action = null;
        QuestionAttribute actionAttribute = answer.findAttributeByName(FormGuide.MULTI_SELECT_ACTION_LABEL_PROP);
        
        if(actionAttribute != null && 
           actionAttribute.getValue() != null)
        {
           action = actionAttribute.getValue().toString();
        }
        return fix(action, FormGuide.DEFAULT_MULTI_SELECT_ACTION_LABEL);
    }
    public String getMaxLength() {
        return fix(maxLength, String.valueOf(FormGuide.retrieveMaxLength(answer)));
    }
    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }
    public String getName() {
        return "answer";
    }
    public void setReadOnly(boolean readOnly) {
        // override answer's read-only flag only if the answer
        // is not specified as read-only
//        if (!answer.isReadOnly())
//            super.setReadOnly(readOnly);
//        else
            super.setReadOnly(readOnly);
    }
    /**
     * currently only text, textarea and hidden fields are supported.
     * @return
     */
    public boolean isValidateOnSave() {
        
        return validateOnSave;
    }
    public void setValidateOnSave(boolean validateOnSave) {
        this.validateOnSave = validateOnSave;
    }
    public boolean isValidateOnSubmit() {
        return !validateOnSave ? validateOnSubmit : false;
    }
    public void setValidateOnSubmit(boolean validateOnSubmit) {
        this.validateOnSubmit = validateOnSubmit;
    }

    public final static String DEFAULT_STYLE_CLASS = "answer";   
    public static final String DEFAULT_WIDTH = "60%";
    public static final String DEFAULT_RESPONSE_TYPE = "text";

    private FormAnswer answer;
    private String onBlurJS;
    private String onChangeJS;
    private String onClickJS;
    private String onFocusJS;
    private String maxLength;
    private boolean validateOnSave;
    private boolean validateOnSubmit;
}
