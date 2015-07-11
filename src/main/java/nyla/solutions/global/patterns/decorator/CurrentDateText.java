package nyla.solutions.global.patterns.decorator;

import java.util.Calendar;



import nyla.solutions.global.data.Textable;
import nyla.solutions.global.util.Text;


/**
 * Default dateFormat = "yyyy-MM-dd-hh:mm";
 * @author Gregory Green
 *
 */
public class CurrentDateText implements Textable
{

   public String getText()
   {	
	return Text.formatDate(dateFormat, Calendar.getInstance().getTime());
   }

   private String dateFormat = "yyyy-MM-dd hh:mm:ss";

}
