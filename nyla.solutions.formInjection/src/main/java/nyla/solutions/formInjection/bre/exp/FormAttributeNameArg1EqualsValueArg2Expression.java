package nyla.solutions.formInjection.bre.exp;
import nyla.solutions.formInjection.bre.LogicalExpression;
import nyla.solutions.formInjection.data.Form;
import nyla.solutions.formInjection.data.FormComponent;
import nyla.solutions.global.data.Attribute;

/**
 * <pre>
 * Test if Form has an attribute specified by input argument 1
 * and matches the value specified by input argument 2.
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class FormAttributeNameArg1EqualsValueArg2Expression 
extends LogicalExpression
{  
   static final long serialVersionUID = FormAttributeNameArg1EqualsValueArg2Expression.class
   .getName().hashCode();
   
   /**
    * @return attribute.getValue().toString().equals(this.getExpressionBluePrint().getInputArgument2())
    * @see nyla.solutions.formInjection.bre.LogicalExpression#interpret(org.solutions.form.bre.FormComponent)
    */
   public boolean interpret(FormComponent aBusinessComponent)
   {
     Form form = this.retrieveForm(aBusinessComponent);
     
     Attribute attribute = form.retrieveAttribute(this.getExpressionBluePrint().getInputArgument1());
     
     String arg2 = this.getExpressionBluePrint().getInputArgument2();
     
     return attribute != null &&
            String.valueOf(attribute.getValue()).equalsIgnoreCase(arg2);
   }//--------------------------------------------

}
