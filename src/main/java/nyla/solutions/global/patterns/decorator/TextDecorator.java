package nyla.solutions.global.patterns.decorator;

import nyla.solutions.global.data.Textable;

/**
 * TextDecorator interface to wrap a target object with additional text 
 * @author Gregory Green
 */
public interface TextDecorator<DecoratorType> extends Textable
{   
	/**
	 * Set the target object to wrap
	 * @param target the target
	 */
   public void setTarget(DecoratorType target);
   
   /**
    * 
    * @return the targeted object
    */
   public DecoratorType getTarget();
   

}
