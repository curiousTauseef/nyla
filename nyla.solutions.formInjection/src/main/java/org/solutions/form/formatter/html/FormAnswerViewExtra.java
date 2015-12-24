/*
 * Created on Nov 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.solutions.form.formatter.html;

import org.solutions.form.data.FormAnswer;

/**
 * @author JIsrael
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FormAnswerViewExtra extends FormAnswerView {
    public String text;
    
    public FormAnswerViewExtra(FormAnswer answer) {
        super(answer);
    }
    public String toString() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
