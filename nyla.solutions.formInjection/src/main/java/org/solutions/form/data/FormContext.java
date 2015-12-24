package org.solutions.form.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import nyla.solutions.global.data.Attribute;
import nyla.solutions.global.data.Property;
import nyla.solutions.global.security.data.SecurityCredential;


/**
 * 
 * <pre>
 * FormContext provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormContext implements Serializable 
{
    private SecurityCredential accessUser;
    private Map attributeMap = new HashMap();
    private Map dynamicChoices = new HashMap();
    
    FormContext() {
    }
    
    public FormContext(SecurityCredential accessUser) {
        this.accessUser = accessUser;
    }
    
    public Attribute getAttribute(Comparable aKey )
    {
        return (Attribute)this.attributeMap.get(aKey);
    }
    public Attribute removeAttribute(Comparable key) {
        return (Attribute) this.attributeMap.remove(key);
    }
    public void addAttribute(Comparable key, Serializable value) {
        Property p = new Property();
        p.setKey(key);
        p.setValue(value);
        attributeMap.put(key, p);
    }
    public SecurityCredential getAccessUser() {
        return accessUser;
    }
    /**
     * 
     * @param user
     */
    public void setAccessUser(SecurityCredential user) 
    {
       //TODO:|| !user.getId().equals(getAccessUser().getId()
        if (user == null || getAccessUser() == null ) {
            this.accessUser = user;
            attributeMap.clear();
         dynamicChoices.clear();
        }
    }//--------------------------------------------
    public Map getDynamicChoices(Integer questionId) {
        String key = String.valueOf(questionId);
        Map m = (Map) dynamicChoices.get(key);
        if (m == null)
            return Collections.EMPTY_MAP;
        return m;
    }
    public Map getDynamicChoices(Integer questionId, Integer colNumber) {
        String key = String.valueOf(questionId + "-" + colNumber);
        Map m = (Map) dynamicChoices.get(key);
        if (m == null)
            return Collections.EMPTY_MAP;
        return m;
    }
    public void addDynamicChoice(Integer questionId, QuestionChoice choice) {
        String key = String.valueOf(questionId);
        Map choices = (Map) dynamicChoices.get(key);
        if (choices == null) {
            choices = new LinkedHashMap();
            dynamicChoices.put(key, choices);
        }
        choices.put(choice.getKey(), choice);
    }
    public void addDynamicChoice(Integer questionId, Integer colNumber, QuestionChoice choice) {
        String key = String.valueOf(questionId + "-" + colNumber);
        Map choices = (Map) dynamicChoices.get(key);
        if (choices == null) {
            choices = new LinkedHashMap();
            dynamicChoices.put(key, choices);
        }
        choices.put(choice.getKey(), choice);
    }
    public void addDynamicChoices(Integer questionId, Collection choices) {
        String key = String.valueOf(questionId);
        Map m = (Map) dynamicChoices.get(key);
        if (m == null) {
            m = new LinkedHashMap();
            dynamicChoices.put(key, m);
        }
        for (Iterator i = choices.iterator(); i.hasNext(); ) {
            QuestionChoice choice = (QuestionChoice) i.next();
            m.put(choice.getKey(), choice);
        }
    }
    public void addDynamicChoices(Integer questionId, Integer colNumber, Collection choices) {
        String key = String.valueOf(questionId + "-" + colNumber);
        Map m = (Map) dynamicChoices.get(key);
        if (m == null) {
            m = new LinkedHashMap();
            dynamicChoices.put(key, m);
        }
        for (Iterator i = choices.iterator(); i.hasNext(); ) {
            QuestionChoice choice = (QuestionChoice) i.next();
            m.put(choice.getKey(), choice);
        }
    }
    static final long serialVersionUID = FormContext.class.getName().hashCode();
}
