package nyla.solutions.formInjection.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import nyla.solutions.global.data.Auditable;
import nyla.solutions.global.data.Copier;
import nyla.solutions.global.data.Createable;
import nyla.solutions.global.data.Data;
import nyla.solutions.global.data.Identifier;
import nyla.solutions.global.data.PrimaryKey;
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
public interface  FormComponent extends Serializable, Copier, Cloneable, 
Createable, Textable, Auditable, Identifier, PrimaryKey
{
    

	
    default void copy(Copier aFrom) {
        try {
            PropertyUtils.copyProperties(this, aFrom);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("Can't copy from " + aFrom, e);
        }
    }//--------------------------------------------
    
    
    default void updateAudit(Object userId) {
        Timestamp now = new Timestamp(new Date().getTime());
        if (getCreateDate() == null)
            setCreateDate(now);
        if (getCreateUserID() == null)
            setCreateUserID(userId);
        setUpdateDate(now);
        setUpdateUserID(userId);
    }//--------------------------------------------
    
    default Integer replaceWithNull(int id)
    {
    	if(id > 0)
    		return id;
    	else
    		return Data.NULL;
    	
    }//--------------------------------------------
    default Object getKey()
    {
    	return getId();
    }//--------------------------------------------
    default boolean isNew()
    {
    	int primaryKey = this.getPrimaryKey();
    	return primaryKey < 0;
    }
    

    default boolean isDeleted()
    {
    	return Data.YES.equals(this.getDeletedCode());
    }
    
    @Override
    default void delete()
    {
    	this.setDeletedCode(Data.YES);
    }
    
}
