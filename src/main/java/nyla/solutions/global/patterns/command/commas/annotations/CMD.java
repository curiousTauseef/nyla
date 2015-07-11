package nyla.solutions.global.patterns.command.commas.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nyla.solutions.global.patterns.transaction.Transactional.TransactionType;


/**
 * Indicates that the execute method for this function
 * is onRegion and it's region name.
 * 
 * @author Gregory Green
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CMD
{
	/** The unique function name .  Please note that function names can be overwritten by 
	 * other modules with the same name.
	 * @return the module name
	 */
	public String name() default "";
	
	/**
	 * The name of the grid function set in the server.xml.
	 * 
	 * Ex: bridgeFunction, commasBridgeFunction,transactionNameBridgeFunction, etc.
	 * 
	 * 
	 * Default "controller"
	 * @return @see controller
	 */
	public String controller() default "controller";
	
	/**
	 * The container name to read from if any.
	 * @return
	 */
	public String inputName() default "";
	
	/**
	 * 
	 * @return the name of the target
	 */
	public String targetName() default "";
	
	/**
	 * 
	 * @return alias names for the function
	 */
	public String[] aliases() default {};
	
	/**
	 * Description of function
	 */
	public String notes() default "";

	/**
	 * Transaction Type
	 * NONE - no transaction support
	 * READONLY- read (not write) transaction
	 * WRITE - read/write or read transaction data
	 * @return default WRITE
	 */
	public TransactionType transactionType() default TransactionType.WRITE;
	
	/**
	 * Aspect advice name
	 */
	public String advice()  default "";
	
	/**
	 * Function attributes
	 * @return
	 */
	public Attribute[] attributes() default {};
	
	public Class<?> inputClass() default Object.class;
	
	public Class<?> returnClass() default Object.class;
}