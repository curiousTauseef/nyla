package org.solutions.form.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import nyla.solutions.global.data.AbstractAuditable;
import nyla.solutions.global.data.Copier;
import nyla.solutions.global.data.Createable;
import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.SystemException;

import org.apache.commons.beanutils.PropertyUtils;


/**
 * 
 * <pre>
 * FormComponent provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public abstract class FormComponent extends AbstractAuditable implements Serializable, Copier, Cloneable, Createable, Textable 
{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5559396701920011248L;
	
	
	public String toString() {    
        return "["+this.getClass().getName()+"] key="+String.valueOf(this.getKey());
    }//--------------------------------------------
    public int hashCode() {
        return getPrimaryKey();
    }
    public void copy(Copier aFrom) {
        try {
            PropertyUtils.copyProperties(this, aFrom);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("Can't copy from " + aFrom, e);
        }
    }//--------------------------------------------
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public int compareTo(Object o) {
        FormComponent that = (FormComponent) o;
        if (this.getKey() == that.getKey())
            return 0;
        if (this.getKey() == null)
            return -1;
        if (that.getKey() == null)
            return 1;
        return ((Comparable) this.getKey()).compareTo(that.getKey());
    }
    public boolean equals(Object o) {
        return compareTo(o) == 0;
    }
    public void updateAudit(Object userId) {
        Timestamp now = new Timestamp(new Date().getTime());
        if (getCreateDate() == null)
            setCreateDate(now);
        if (getCreateUserID() == null)
            setCreateUserID(userId);
        setUpdateDate(now);
        setUpdateUserID(userId);
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    protected Integer replaceWithNull(Integer i) {
        return i == null || i.intValue() < 1 ? null : i;
    }
}
