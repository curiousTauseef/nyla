/*
 * Created on Nov 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.solutions.form.data;


/**
 * 
 * <pre>
 * FormComment provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormComment extends FormComponent {
    private Integer commentId;
    private Integer formId;
    private Integer userId;
    private String commentText;
    
    
    public Integer getCommentId() {
        return commentId;
    }
    protected void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
    public Object getKey() {
        return getCommentId();
    }
    public String getCommentText() {
        return commentText;
    }
    protected void setCommentText(String commentText) {
        this.commentText = commentText;
    }
    public Integer getFormId() {
        return formId;
    }
    protected void setFormId(Integer formId) {
        this.formId = formId;
    }
    public Integer getUserId() {
        return userId;
    }
    protected void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getText() {
        return getCommentText();
    }
    static final long serialVersionUID = FormComment.class.getName().hashCode();
}
