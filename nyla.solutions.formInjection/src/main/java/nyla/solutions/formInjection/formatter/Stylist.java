package nyla.solutions.formInjection.formatter;

import nyla.solutions.global.util.Text;


/**
 * @author green_gregory
 * @version 1.0
 *
 * <b>WordUtil</b>  
 * 
 */
public class Stylist
{
   public static final String ESCAPE = "\\";
   
   public static final String BACK_SLASH = "\\";
   public static final String START_BRACES = "{";
   public static final String END_BRACES = "}";
   
   /**
    * the backslash ( \) and braces ({ }) have special 
    * meaning in RTF. To use these characters as text, 
    * precede them with a backslash, as in \\, \{, and \}.
    * @param aText
    * @return
    */
   public static String fix(String aText)
   {
      aText = Text.replace(BACK_SLASH,ESCAPE+BACK_SLASH,aText);
      aText = Text.replace(START_BRACES,ESCAPE+START_BRACES,aText);
      return Text.replace(END_BRACES,ESCAPE+END_BRACES,aText);
   }
}
