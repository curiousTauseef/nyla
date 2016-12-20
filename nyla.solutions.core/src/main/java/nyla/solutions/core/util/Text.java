package nyla.solutions.core.util;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nyla.solutions.core.data.Textable;
import nyla.solutions.core.exception.FormatException;
import nyla.solutions.core.exception.RequiredException;
import nyla.solutions.core.exception.SetupException;
import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.io.IO;
import nyla.solutions.core.patterns.decorator.BasicTextStyles;
import nyla.solutions.core.patterns.decorator.TextStyles;

/**
 * <pre>
 * <b>Text</b> is geared toward string based processing. It includes template engine support 
 * like Free Marker that builds composite strings/values dynamically at runtime 
 *  (see http://freemarker.sourceforge.net/). There are also methods to support complex 
 *  regular expressions with Boolean AND, OR and NOT logic, numerous string conversions, 
 *  general text manipulation and parsing methods.
 * </pre>
 * @author Gregory Green
 *  
 */

public class Text
{
   /**
    * TEMPLATE_DIR_PROP_NM = Text.class.getName()+".template.dir"
    */
   public static final String TEMPLATE_DIR_PROP_NM = Text.class.getName()+".template.dir";
   
   /**
    * TEMPLATE_EXTENSION_PROP_NM = Text.class.getName()+".template.extension"
    */
   public static final String TEMPLATE_EXTENSION_PROP_NM = Text.class.getName()+".template.extension";
      
   private static final String[] fixedNumberPrefixLookup =
   {
	   "0","00","000","0000","00000","000000","0000000","00000000","000000000","0000000000"
   };
   
   /**
    * Complex matches ${NOT}
    */
   public static final String NOT = "${NOT}";
   
   /**
    * Complex matches ${OR}
    */
   public static final String OR = "${OR}";
   
   /**
    * Complex matches and
    */
   private static final String AND  = "${AND}";
   
   /**
    * TEMPLATE_LOCALE_COUNTRY = Text.class.getName()+".TEMPLATE_LOCALE_COUNTRY"
    */
   public static final String TEMPLATE_LOCALE_COUNTRY = Text.class.getName()+".TEMPLATE_LOCALE_COUNTRY";
   
   /**
    * TEMPLATE_LOCALE_LANGUAGE = Text.class.getName()+".TEMPLATE_LOCALE_LANGUAGE"
    */
   public static final String TEMPLATE_LOCALE_LANGUAGE = Text.class.getName()+".TEMPLATE_LOCALE_LANGUAGE";
   /**
    * SPECIAL_START = "${START}" used for the parse method (start tag)
    */
   public static final String SPECIAL_START = "${START}";
   
   /**
    * SPECIAL_END = "${END} used for the parse method (end tag)
    */
   public static final String SPECIAL_END = "${END}";
   
   /**
    * DATE_FORMAT = Config.getProperty(Text.class.getName()+".formatDate","MM/dd/yyyy hh:mm:ss a")
    */
   public static final String DATE_FORMAT = Config.getProperty(Text.class.getName()+".DATE_FORMAT","MM/dd/yyyy hh:mm:ss:SS a");

   /**
    * Append newline to text
    * 
    */
   public static String appendNewLine(String text)
   {
	  
	  String newline = IO.newline();
	   
	  if(text == null || text.length() == 0)
		  return newline;
	  
	  StringBuilder sb = new StringBuilder(text);
	  
	   if(!text.endsWith(newline))
		{
			 sb.append(newline);
		}
	   
	   return sb.toString();
   }// --------------------------------------------------------
   /**
    * Inject an with the property solutions.global.util.Text.textStyles=className
    *  
    * @return implementation of  text styles
    */
   public static TextStyles getTextStyles()
   {
	   if(textStyles == null)
	   {
		   String className = Config.getProperty(Text.class,"textStyles",BasicTextStyles.class.getName());
		  
		   try
			{
				textStyles = (TextStyles)Class.forName(className).newInstance(); 
			}
			catch (Exception e)
			{
				throw new SetupException("Unable to create TextSytles instance:"+className, e);
			}
	   }
	   
	   return textStyles;
	   
   }// --------------------------------------------------------
   public static String fixedLength(int number, int length)
   {
	   String numberText = String.valueOf(number);
	   int numberLength =  numberText.length();
	   
	   StringBuilder sb = new StringBuilder();
	   
	   if(length > 16)
	   {
		 sb.ensureCapacity(length);  
	   }
	   
	   sb.append(numberText);
	   
	   //prefix 0
	   if(length == numberLength)
		   return sb.toString();
	   
	   
	   if(numberLength > length)
	   {
		   sb.setLength(length);
		   return sb.toString();
	   }
	
	   int diff = length - numberLength;
	   
	   if(diff <  fixedNumberPrefixLookup.length)
	   {
		   sb.insert(0, fixedNumberPrefixLookup[diff-1]);
		   return sb.toString();
	   }
	   
	   //prefix 0(s) = 
	   //length = 2 and value = 1 = 01
	   
	   for(int i=0; i < length- numberLength;i++)
	   {
		   sb.insert(0, "0");
	   }
	   
	   return sb.toString();
	   
	   /*NumberFormat formatter = NumberFormat.getNumberInstance();
	   formatter.setGroupingUsed(false);
	   formatter.setMinimumIntegerDigits(length);
	   
	   return formatter.format(number);*/
   }// ------------------------------------------------
   public static String fixedLength(String text, int length)
   {
	   StringBuilder sb = new StringBuilder(length);
	   
	   sb.append(text);
	   /*Formatter formatter = new Formatter(sb);
	   Object [] input = {text};
	   formatter.format("%0$-"+length+"s", input);
	   */
	   sb.setLength(length);
	   return sb.toString();	
	   
   }// ------------------------------------------------
   
   public static String fixedLength(String text, int length, char filleChar)
   {

	   StringBuilder sb = new StringBuilder(length);
	   sb.append(text);
	   sb.setLength(length);
	   
	   /*
	   Formatter formatter = new Formatter(sb);
	   Object [] input = {text};
	   formatter.format("%0$-"+length+"s", input);
	   
	   if(text == null)
	   {
		 return sb.toString();
	   }*/
	   
	   //Take original 
	   if(filleChar != '\0')
	   {
		   //replace with prefix
		   int originalLength = text.length();
		   
		   if(originalLength < sb.length())
		   {
			   String newEnd = sb.substring(originalLength, sb.length()).replace('\0', filleChar);
			  sb = sb.replace(originalLength, sb.length(), newEnd);
		   }
		   
	   }
	   
	   return sb.toString();	
	   
   }// ------------------------------------------------
   
   /**
    * Loop thrus map to formats any needed value with data from other properties.
    * 
    * See Text.format(String,Map)
    * @param map the map to format
    * @throws FormatException
    */
   public static final void formatMap(Map<Object,Object> map)
   throws FormatException
   {
	if(map == null || map.isEmpty())
	   return;// nothing to process
	
	//format properties
      Object key = null;
      Object value =null;
      String text = null;
      
      for (Map.Entry<Object, Object> entry : map.entrySet())
      {
		key =  entry.getKey();
		value = entry.getValue();
		
		if(!(value instanceof String))
		   continue; //skip
		
		text = (String)value;
	
		//overwrite value with formatted version
		if(text.indexOf("${") > -1)
		   map.put(key, Text.format(text, map));
	   }
	
   }//----------------------------------------------
   /**
    * Generate a unique ID
    * @return the generated ID
    */
   public static final String generateId()
   {
	Date date = Calendar.getInstance().getTime();
	
	String dateId = formatDate("yyyyMMddHHmmssSS", date);
	
	return new StringBuffer(dateId).append(random.nextInt()).toString();
	
   }// ----------------------------------------------
   /**
    * End all line with \r\n
    * @param s string to replace
    * @return 
    */
   public static String newlineToCrLf(String s)
   {
       String s1 = s.replaceAll("\\r", "");
       String s2 = s1.replaceAll("\\n", "\r\n");
       return s2;
   }// ----------------------------------------------
   
   /**
    * dateFormat = Config.getProperty(Text.class,"dateFormat", "MM/dd/yyyy")
    */
   public static final String  dateFormat = Config.getProperty(Text.class,"dateFormat", "MM/dd/yyyy");
   
  /**
   * 
   * @param text tokens separated by \t\n\r
   * @return tokens separated by \n (newline)
   */
   public static Set<String> toSet(String[] lines)
   {
	   TreeSet<String> set = new TreeSet<String>(Arrays.asList(lines));
	  
	   return set;
   }// --------------------------------------------------------
   
   public static String removeNewLines(String text)
   {
      return replace("\r\n", " ", text);
   }//--------------------------------------------
   
   /**
    * 
    * @param aValue the value
    * @param aType the type of the value
    * @return value cast to the object type instance
    */
   public static Object toObject(String aValue, String aType)
   throws ParseException
   {
      if("Date".equalsIgnoreCase(aType))
      {
         return toDate(aValue);
      }
      else if("Boolean".equalsIgnoreCase(aType))
      {
         return Boolean.valueOf(aValue);
      }
      else if("Int".equalsIgnoreCase(aType) ||
              "Integer".equalsIgnoreCase(aType))
      {
         return Integer.valueOf(aValue);
      }
      else if("Double".equalsIgnoreCase(aType))
      {
         return Double.valueOf(aValue);
      }
      else
      {
         return aValue;
      }
      
   }// --------------------------------------------

   /**
    * Use a regular expression to update a given text
    * @param aText the text to update
    * @param aRE the regular expression
    * @param aReplaceText the replacement text
    * @return the formatted regular expression
    */
   public static String replaceForRegExprWith(final String text,final String re,final String replaceText)
   {
      if(text == null  || text.length() == 0)
         return "";
      
      Pattern pattern = Pattern.compile(re);
      
      return pattern.matcher(text).replaceAll(replaceText);
      
   }// --------------------------------------------
   /**
    * Parse text based on regular expressions 
    * TODO: add multiple results
    * @param startRE the start regular express
    * @param endRE the end regular expression
    * @param text the text to parse
    * @return the parsed output
    */
   public static String parseRE(String text, String startRE,String endRE)
   {
      try
      {
		if(text == null  || text.length() == 0)
		     return text;
		  
		  if(startRE == null || endRE == null)
			  return text;
		  
		  Pattern startPattern = Pattern.compile(startRE);
		  Pattern endPattern = Pattern.compile(endRE);
		  
		  Matcher startMatcher = startPattern.matcher(text);
		  
		  if(!startMatcher.find())
			  return text;
		  
		  int startIndex = startMatcher.end();
		   
		  String endText = text.substring(startIndex);
		  
		  Matcher endMatcher = endPattern.matcher(endText);
		  if(!endMatcher.find())
			  return text.substring(startIndex);
		  
		  int endIndex = endMatcher.start();
		  
		  if(endIndex < 0)
			  endIndex = endText.length() - 1;
		  
		  return text.substring(startIndex, startIndex+endIndex);
		} 
	    catch (IllegalStateException e)
		{
			throw new SystemException("startRE="+startRE+" endRE="+endRE+" text="+text+" "+Debugger.stackTrace(e));
		}
   }// --------------------------------------------
   /**
    * Replace the last instance of the regular expression with a given token
    * @param text the source text
    * @param re the regular expression
    * @param replacement the replacement text
    * @return the text with the last regExpr instance replaced 
    */
   public static String replaceFirstRegExpWith(String text, String re, String replacement )
   {
	   if(text == null || text.length() == 0)
	         return "";
	   
	   //compile pattern
	   Pattern pattern = Pattern.compile(re);
	      
	   return pattern.matcher(text).replaceFirst(replacement);
	   
   }//---------------------------------------------
   /**
    * 
    * @param collection convert collection of String to array
    * @return the array of strings
    */
   @SuppressWarnings("unchecked")
public static String[] toStrings(Object object)
   {
      if(object == null)
         return null;
      
      
      if(object instanceof Collection)
      {
         return toStrings((Collection<Object>)object);
      }
      else if(object instanceof Object[])
      {
         return toStrings(Arrays.asList((Object[])object));
      }
      else
      {
         //return single array value
         String[] strings = { object.toString()};
         
         return strings;
      }

      
   }// --------------------------------------------

   
   /**
    * 
    * @param collection convert collection of String to array
    * @return the array of strings
    */
   public static String[] toStrings(Collection<Object> collection)
   {
      if (collection == null)
         throw new RequiredException("collection in Text.toStrings");
      
      if(collection.isEmpty())
         return null;
      
      String[] texts = new String[collection.size()];
      
      collection.toArray(texts);
      
      
      return texts;
      
   }// --------------------------------------------
   public static String toText(Collection<Object> collection, String separator)
   {
      if(collection == null || collection.isEmpty())
         return "";
      
      StringBuffer sb =new StringBuffer();
      
      Object object = null;
      for (Iterator<Object> i = collection.iterator(); i.hasNext();)
      {
         object = i.next();
         
         if(object == null)
            continue;  //skip
         if(sb.length() > 0)
         {
            sb.append(separator);
         }
         
         sb.append(object);         
      }
      
      return sb.toString();
   }// --------------------------------------------
   /**
    * Split a single text into many determined by the separation character
    * @param aText the text to split
    * @param aSeparator the separation character
    * @return the array of the split strings
    */
   public static String[] splitRE(String aText, String re)
   {
	   if(aText == null || aText.length() == 0)
	         return null;
	   
	   if(re == null)
		throw new RequiredException("regular expression");
	
	   return aText.split(re);	   
   }//---------------------------------------------

   /**
    * Split a single text into many determined by the separation character
    * @param aText the text to split 
    * @return the array of the split strings
    */
   public static String[] split(String aText)
   {
      if(aText == null || aText.length() == 0)
         return null;
      
      StringTokenizer toks = new StringTokenizer(aText, " \t\n\r");
      
    
      return toStrings(toks);      
   }// --------------------------------------------
   /**
    * Split a single text into many determined by the separation character
    * @param aText the text to split
    * @param aSeparator the separation character
    * @return the array of the split strings
    */
   public static String[] split(String text, String token)
   {
      if(text == null)
         return null;
      
      if (token == null)
      {
         String[] results = {text};
         return results;
      }

      int i = text.indexOf(token);

      if (i < 0)
      {
         //nothing to replace

         String[] results = {text};
         return results;

      }

      ArrayList<String> resultList = new ArrayList<String>();

      int start = 0;


      do
      {

         //split all instances
         if (start != i)
         {
            resultList.add(text.substring(start, i));
         }

         start = i + token.length();
         i = text.indexOf(token, start);

      }

      while (i >= 0);

      //append rest of string

      if (start < text.length())

         resultList.add(text.substring(start, text.length()));

     if(resultList.isEmpty())
     {
        throw new IllegalArgumentException("Unexpected empty list");
     }
     
     String[] results = new String[resultList.size()];
     
     resultList.toArray(results);
     
     return results;
   }// --------------------------------------------

   /**
    * 
    * @param aTokenizer the string token object
    * @return the array of tokens
    */
   public static String[] toStrings(StringTokenizer aTokenizer)
   {
      String[] texts = new String[aTokenizer.countTokens()];
      
      for(int i=0;i < texts.length;i++)
      {
         texts[i] = aTokenizer.nextToken();
         if(texts[i] != null)
            texts[i] = texts[i].trim();
      }
      return texts;
   }// --------------------------------------------
   /**
    *  Convert a text string to a date instance
    * @param aText the text to convert
    * @return date version of text
    * @throws ParseException
    */
   public static Date toDate(String text)   
   {
          return toDate(text,dateFormat);
         
   }// --------------------------------------------
   
   /**
    *  Convert a text string to a date instance
    * @param aText the text to convert
    * @return date version of text
    * @throws ParseException
    */
   public static Date toDate(String aText, String dateFormat)   
   {
      try
      {
         if (aText == null || aText.length() == 0)
            throw new IllegalArgumentException("aText required in toDate");
         
         SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
         return simpleDateFormat.parse(aText);         
      }
      catch(ParseException e)
      {
         Debugger.printWarn(e);
         return null;
      }
   }// --------------------------------------------

   /**
    */
   private static final int indexOf(String aContent, String aSearchText, int aFromIndex)
   {
      if(SPECIAL_START.equals(aSearchText))
      {
            return aFromIndex;
      }
      else if(SPECIAL_END.equals(aSearchText))
      {
         return aContent.length();
      }
      
      
      return aContent.indexOf(aSearchText, aFromIndex);
   }// --------------------------------------------
   /**
    * 
    * @param aContent
    * @param aSearchText
    * @return indexOf(aContent,aSearchText,0)
    */
   private static final int indexOf(String aContent, String aSearchText)
   {
      return indexOf(aContent,aSearchText,0);
   }// --------------------------------------------
   /**
    * First occurrence of text
    * @param aContent
    * @param aStart
    * @param aEnd
    * @return
    */
   public static String parseText(String aContent, String aStart, String aEnd)
   {
      //TODO: parse single
      Collection<String> results = parse(aContent, aStart, aEnd);
      
      if(results == null || results.isEmpty())
      {
         return "";
      }
      
      return results.iterator().next().toString(); 
   }// --------------------------------------------
   public static String parseText(Reader aContent, String aStart, String aEnd)
   throws IOException
   {
      //TODO: parse single
      Collection<Object> results = parse(aContent, aStart, aEnd);
      
      if(results == null || results.isEmpty())
      {
         return "";
      }
      
      return results.iterator().next().toString(); 
   }// --------------------------------------------

   /**
    * Parse all string surrounded by the aStart and aEnd tag
    * aIgnoreCase = false
    * @param aContent the content to parse
    * @param aStart the content start tag
    * @param aEnd the content end tag
    * 
    * @return 
    */
   public static Collection<String> parse(String aContent, String aStart, String aEnd)
   {
      return parse(aContent,aStart,aEnd,false);
   }// --------------------------------------------
   /**
    * Parse all string surrounded by the aStart and aEnd tag
    * aIgnoreCase = false
    * @param aContent the content to parse
    * @param aStart the content start tag
    * @param aEnd the content end tag
    * 
    * @return 
    */
   public static Collection<Object> parse(Reader aContent, String aStart, String aEnd)
   throws IOException
   {
      return parse(aContent,aStart,aEnd,false);
   }// --------------------------------------------

   /**
    * Parse all string surrounded by the aStart and aEnd tag
    * @param aContent the content to parse
    * @param aStart the content start tag
    * @param aEnd the content end tag
    * @param aIgnoreCase
    * @return 
    */
   public static Collection<String> parse(String aContent, String aStart, String aEnd, boolean aIgnoreCase)
   {
       String compareContent = null;
       if (aIgnoreCase)
       {
           compareContent = aContent.toUpperCase();
           aStart = aStart.toUpperCase();
           aEnd = aEnd.toUpperCase();
       }
       else
       {
           compareContent = aContent;
       }

       ArrayList<String> results = new ArrayList<String>();
       int indexOfHref = indexOf(compareContent, aStart);
       
       int startText = indexOfHref + aStart.length();
       if(SPECIAL_START.equals(aStart))
       {
          startText = 0;
       }
       
       
       int endText = indexOf(aContent, aEnd, startText);

       if(endText == aContent.length() && startText == 0 )
       {
          results.add(aContent);
          return results;
       }
       
       String txt = null;
       while (startText > -1 && endText >= startText)
       {
          txt = aContent.substring(startText, endText);
          results.add(txt);

           indexOfHref = indexOf(compareContent, aStart, endText);

           if (indexOfHref < 0)
               break;

           startText = indexOfHref + aStart.length();
           endText = indexOf(aContent, aEnd, startText);
       }

       return results;
   }//-----------------------------------------------
   /**
    * Parse all string surrounded by the aStart and aEnd tag
    * @param aContent the content to parse
    * @param aStart the content start tag
    * @param aEnd the content end tag
    * @param aIgnoreCase
    * @return collection of parse strings 
    */
   public static Collection<Object> parse(Reader aReader, String aStart, String aEnd, boolean aIgnoreCase)
   throws IOException
   {
      BufferedReader reader = null;
      
      if(aReader instanceof BufferedReader)
      {
         reader = (BufferedReader)aReader;
      }
      else
      {
         reader = new BufferedReader(aReader);
      }
      
      String content = null;
      String compareContent = null;
      ArrayList<Object> results = new ArrayList<Object>();
      while((content = reader.readLine()) != null)
      {
         
         if (aIgnoreCase)
         {
             compareContent = content.toUpperCase();
             aStart = aStart.toUpperCase();
             aEnd = aEnd.toUpperCase();
         }
         else
         {
             compareContent = content;
         }

         int indexOfHref = indexOf(compareContent, aStart);
         
         int startText = indexOfHref + aStart.length();
         if(SPECIAL_START.equals(aStart))
         {
            startText = 0;
         }
         
         
         int endText = indexOf(content, aEnd, startText);

         if(endText == content.length() && startText == 0 )
         {
            results.add(content);
            return results;
         }
         
         String txt = null;
         while (startText > -1 && endText >= startText)
         {
            txt = content.substring(startText, endText);
            results.add(txt);

             indexOfHref = indexOf(compareContent, aStart, endText);

             if (indexOfHref < 0)
                 break;

             startText = indexOfHref + aStart.length();
             endText = indexOf(content, aEnd, startText);
         }

      }
      
      return results;
   }//-----------------------------------------------   
   /**
    * Not complex regular expression match
    * @param aRE the regular expression
    * @param aValue the value to test
    * @return true if the regular expression matches the given value
    */
   protected static boolean matchesRE(String aRE, Object aValue)
   {
      if(aValue == null )
         return false;
      
      if(aRE.indexOf("(") > -1)
      {
    	  if(aRE.indexOf(")") < 0)
    	  {
    		  //correct unbalanced
    		  aRE = new StringBuilder().append(aRE).append(")").toString();
    	  }
      }
      else
      {
    	  //no start
    	  if(aRE.indexOf(")") > -1)
    	  {
    		  //correct unbalanced
    		  aRE = new StringBuilder().append("(").append(aRE).toString();
    	  }
      }
      
      Pattern pattern = Pattern.compile(aRE, Pattern.DOTALL);
      java.util.regex.Matcher matcher = pattern.matcher(Text.toString(aValue));
      return matcher.matches();
      
   }// --------------------------------------------
   /**
    * IndexOf function based on regular expressions
    * @param text the Text to start
    * @param regExp the regular expression
    * @return the start index of the match regular expression
    */
   public static int indexOfRegExp(String text, String regExp)
   {
	   Pattern amountsPattern = Pattern.compile(regExp);
	   Matcher matcher = amountsPattern.matcher(text);
	   
	   if(matcher.find())
		   return matcher.start();
	   
	   /* while (matcher.find()) {
		   System.out.println("Starting & ending index of " + matcher.group()+ ":=" +
		  "start=" + matcher.start() + " end = " + matcher.end());
		   }
	   */
	   return -1;

   }// --------------------------------------------------------
   /**
    * 
    * Summary of regular-expression constructs 
      Construct Matches 
        
      Characters 
      x The character x 
      \\ The backslash character 
      \0n The character with octal value 0n (0 <= n <= 7) 
      \0nn The character with octal value 0nn (0 <= n <= 7) 
      \0mnn The character with octal value 0mnn (0 <= m <= 3, 0 <= n <= 7) 
      \xhh The character with hexadecimal value 0xhh 
     
      \t The tab character ('\u0009') 
      \n The newline (line feed) character ('\u000A') 
      \r The carriage-return character ('\u000D') 
      \f The form-feed character ('\u000C') 
      \a The alert (bell) character ('\u0007') 
      \e The escape character ('\u001B') 
      \cx The control character corresponding to x 
        
      Character classes 
      [abc] a, b, or c (simple class) 
      [^abc] Any character except a, b, or c (negation) 
      [a-zA-Z] a through z or A through Z, inclusive (range) 
      [a-d[m-p]] a through d, or m through p: [a-dm-p] (union) 
      [a-z&&[def]] d, e, or f (intersection) 
      [a-z&&[^bc]] a through z, except for b and c: [ad-z] (subtraction) 
      [a-z&&[^m-p]] a through z, and not m through p: [a-lq-z](subtraction) 
        
      Predefined character classes 
      . Any character (may or may not match line terminators) 
      \d A digit: [0-9] 
      \D A non-digit: [^0-9] 
      \s A whitespace character: [ \t\n\x0B\f\r] 
      \S A non-whitespace character: [^\s] 
      \w A word character: [a-zA-Z_0-9] 
      \W A non-word character: [^\w] 
        
      POSIX character classes (US-ASCII only) 
      \p{Lower} A lower-case alphabetic character: [a-z] 
      \p{Upper} An upper-case alphabetic character:[A-Z] 
      \p{ASCII} All ASCII:[\x00-\x7F] 
      \p{Alpha} An alphabetic character:[\p{Lower}\p{Upper}] 
      \p{Digit} A decimal digit: [0-9] 
      \p{Alnum} An alphanumeric character:[\p{Alpha}\p{Digit}] 
      \p{Punct} Punctuation: One of !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~ 
      \p{Graph} A visible character: [\p{Alnum}\p{Punct}] 
      \p{Print} A printable character: [\p{Graph}] 
      \p{Blank} A space or a tab: [ \t] 
      \p{Cntrl} A control character: [\x00-\x1F\x7F] 
      \p{XDigit} A hexadecimal digit: [0-9a-fA-F] 
      \p{Space} A whitespace character: [ \t\n\x0B\f\r] 
        
      Classes for Unicode blocks and categories 
      \p{InGreek} A character in the Greek block (simple block) 
      \p{Lu} An uppercase letter (simple category) 
      \p{Sc} A currency symbol 
      \P{InGreek} Any character except one in the Greek block (negation) 
      [\p{L}&&[^\p{Lu}]]  Any letter except an uppercase letter (subtraction) 
        
      Boundary matchers 
      ^ The beginning of a line 
      $ The end of a line 
      \b A word boundary 
      \B A non-word boundary 
      \A The beginning of the input 
      \G The end of the previous match 
      \Z The end of the input but for the final terminator, if any 
      \z The end of the input 
        
      Greedy quantifiers 
      X? X, once or not at all 
      X* X, zero or more times 
      X+ X, one or more times 
      X{n} X, exactly n times 
      X{n,} X, at least n times 
      X{n,m} X, at least n but not more than m times 
        
      Reluctant quantifiers 
      X?? X, once or not at all 
      X*? X, zero or more times 
      X+? X, one or more times 
      X{n}? X, exactly n times 
      X{n,}? X, at least n times 
      X{n,m}? X, at least n but not more than m times 
        
      Possessive quantifiers 
      X?+ X, once or not at all 
      X*+ X, zero or more times 
      X++ X, one or more times 
      X{n}+ X, exactly n times 
      X{n,}+ X, at least n times 
      X{n,m}+ X, at least n but not more than m times 
        
      Logical operators 
      XY X followed by Y 
      X|Y Either X or Y 
      (X) X, as a capturing group 
        
      Back references 
      \n Whatever the nth capturing group matched 
        
      Quotation 
      \ Nothing, but quotes the following character 
      \Q Nothing, but quotes all characters until \E 
      \E Nothing, but ends quoting started by \Q 
        
      Special constructs (non-capturing) 
      (?:X) X, as a non-capturing group 
      (?idmsux-idmsux)  Nothing, but turns match flags on - off 
      (?idmsux-idmsux:X)   X, as a non-capturing group with the given flags on - off 
      (?=X) X, via zero-width positive lookahead 
      (?!X) X, via zero-width negative lookahead 
      (?<=X) X, via zero-width positive lookbehind 
      (?<!X) X, via zero-width negative lookbehind 
      (?>X) X, as an independent, non-capturing group 

    * @param aMatchRegExp
    * @param aText
    * @return
    */
   public static final String grepText(String aMatchRegExp, String aText)
   {
      if(aText == null || aMatchRegExp == null)
         return "";
      
      StringTokenizer tok = new StringTokenizer(aText, "\r\n");
      
      java.util.regex.Pattern p = java.util.regex.Pattern.compile(aMatchRegExp);
      
      String line = null;
      while(tok.hasMoreTokens())
      {
         line = tok.nextToken();
         
         if(p.matcher(line).find())
         {
            return line;
         }
      }
      
      return "";
      
   }// --------------------------------------------


   /**
    * 
    * Determines whether the provided str is equal to null
    * 
    * or the length is equal to zero
    *  
    */

   public static boolean isNull(String str)

   {

      return str == null || str.trim().length() == 0 ||

      "null".equals(str.trim());

   } //---------------------------------------------
   /**
    * 
    * @param aText
    * @return 
    */
   public static String initCaps(String aText)
   {
      if(isNull(aText))
         return aText;
      
      //Character character = null;
      char textChar;
      
      boolean needInitUpper = true;
      boolean first = true;
      StringBuffer results = new StringBuffer();
      
      for (int i = 0; i < aText.length(); i++)
      {
         textChar = aText.charAt(i);
         if(!Character.isWhitespace(textChar) && needInitUpper)
         {
            needInitUpper = false;
            first = false;
            
            textChar = Character.toUpperCase(textChar);
         }
         else if(!Character.isWhitespace(textChar) && !needInitUpper)
         {
            textChar = Character.toLowerCase(textChar);
         }
         else if(!first && Character.isWhitespace(textChar))
         {
            needInitUpper = true;
         }
         
         results.append(textChar);
      }//end for
      
      return results.toString();
   }//--------------------------------------------
   public static String toByteText(byte[] aByte)

   {

      if (aByte == null)

         return "";

      StringBuffer text = new StringBuffer();

      for (int i = 0; i < aByte.length; i++)

      {

         text.append(Byte.toString(aByte[i]));

         if (i + 1 < aByte.length)

            text.append(" ");

      }

      return text.toString();

   }//--------------------------------------------

   public static byte[] toBytesFromByteText(String aByteText)

   {

      if (aByteText == null)

         return new byte[0];

      StringTokenizer tok = new StringTokenizer(aByteText);

      byte[] bytes = new byte[tok.countTokens()];

      int i = 0;

      while (tok.hasMoreTokens())

      {

         bytes[i] = Byte.valueOf(tok.nextToken()).byteValue();

         i++;

      }

      return bytes;

   }//--------------------------------------------
   /**
    * @param the double
    * @return formatted currency
    */  
   public static String formatCurrency(String doubleText)
   {
	   if(doubleText ==  null || doubleText.length() == 0)
	         return "";
	   
	   return formatCurrency(Double.valueOf(doubleText).doubleValue());
   }// --------------------------------------------------------
   /**
    * Format a number as a currency
    * @param number a money amount
    * @return formatted currency
    */  
   public static String formatCurrency(double number)
   {  
         NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
         return numberFormat.format(number);  
   }//---------------------------------------
   /**
    * Used the"#,##0.0###" formatting for a given number
    * @return formatted number
    */  
   public static String formatDouble(String aNumber)
   {
	   return formatDouble(aNumber,"#,##0.0###");
   }// --------------------------------------------------------
   /**
    * 
    * @return formatted number
    */  
   public static String formatDouble(String number, String format)
   {
	   if(number == null || number.length() == 0)
	         return "";
	   
	   return formatDouble(Double.valueOf(number).doubleValue(),format);
	   
   }// --------------------------------------------------------
   /**
    * 
    * @return formatted number
    */  
   public static String formatDouble(double number)
   {
	   return formatDouble(number,"#,##0.0###");
   }// --------------------------------------------------------
   /**
    * 
    * @return formatted number
    */  
   public static String formatDouble(double number, String format)
   {
   
         DecimalFormat decimalFormat = new DecimalFormat(format);
        // numberFormat.
        return decimalFormat.format(number);

   }//--------------------------------------------------------   

   /**
    * 
    * @return formatted number
    */  
   public static String formatNumber(String aNumber)
   {

      return formatDouble(aNumber);
   }//--------------------------------------------------------
   /**
    * 
    * @return formatted number
    */  
   public static String formatNumber(double number)
   {
	   
      return formatDouble(number);
   }//--------------------------------------------------------
   /**
    * 
    * @return formatted number
    */  
   public static String formatNumber(double number, String format)
   {
	   
      return formatDouble(number,format);
   }//--------------------------------------------------------   
   /**
    * 
    * @return formatted number
    */
   public static String formatPercent(String number)
   {
      if(number == null || number.length() == 0)
         return "";
      
      return formatPercent(Double.valueOf(number).doubleValue());
   }// --------------------------------------------------------
   
   /**
    * 
    * @param number
    * @return
    */
   public static String formatPercent(double number)
   {
       return formatNumber(number, "###.##'%'");
   }//---------------------------------------
 
   /**
    * <pre>
    * Regular expressions do not have an easy way to chain expressions together using AND/NOT/OR logic. 
    *The RE operation combines regular expressions 
    *with a special syntax to support AND/NOT/OR logic.
    *
    *<b>AND Operation</b>
The RE operation can support chaining expressions together with �AND� logic. This is accomplished by chaining 
expressions together with �${AND}�. The string �${AND}� can be used to separate two regular expressions. 
If any of the regular expressions return false then the entire regular expression is false. In the following 
example, the regular expression �.*USA.*${AND}.*Greece.*�, only returns true if the text contains both 
�USA� and �Greece�.

<b>NOT Operation</b>
The RE operation can supports negative logic (NOT) for expressions. This is accomplished by prefixing the 
expressions with �${NOT}�. In the following example, the regular expression �.*USA.*� only returns true 
if the text does not contain the word �USA�. Note that multiple �${NOT}�(s) can be chained together with 
�${AND}�(s) (see table below).

    *<b>OR Operation</b>
    *Regular express ${OR} returns true if the expressions to the right or left of the ${OR} are true
    *.*Chicken.*${OR}.*Egg.* return true for the string "Chickens run" and "Egg are layed"
    *
    *
    *Use complex boolean logic regular expression by adding ${AND}, ${OR} and ${NOT} tags
    * </pre>
    * @param sourceValue the source value to test
    * @param complexRegularExpression the complex regular expression (used ${AND} and ${NOT} to chain expressions together}
    * @return true if source value matches complex regular
    */
   public static boolean matches(String sourceValue, String complexRegularExpression)
   {
      
         try
         {
        	//test source contains AND
        	 int startOrIndex = complexRegularExpression.indexOf(OR);
       
            if(startOrIndex > -1)
            {
            	if(startOrIndex == 0)
            	{
            		if(!matches(sourceValue, complexRegularExpression.substring(OR.length(), complexRegularExpression.length())))
            			return false;
            	}
            	
               //split AND first only
               int endIndex = startOrIndex + OR.length();
               
              
               String leftExpr = complexRegularExpression.substring(0,startOrIndex);
               String rightExpr = complexRegularExpression.substring(endIndex,complexRegularExpression.length());
               
               return matches(sourceValue,leftExpr) ||  matches(sourceValue,rightExpr);
              
            }
            else
            {
	            //test source contains AND
	        	 int startAndIndex = complexRegularExpression.indexOf(AND);
	       
	            if(startAndIndex > -1)
	            {
	            	if(startAndIndex == 0)
	            	{
	            		if(!matches(sourceValue, complexRegularExpression.substring(AND.length(), complexRegularExpression.length())))
	            			return false;
	            	}
	            	
	               //split AND first only
	               int endIndex = startAndIndex + AND.length();
	               
	              
	               String leftExpr = complexRegularExpression.substring(0,startAndIndex);
	               String rightExpr = complexRegularExpression.substring(endIndex,complexRegularExpression.length());
	               
	               return matches(sourceValue,leftExpr) &&  matches(sourceValue,rightExpr);
	              
	            }
	            else 
	            {
	            	 int notIndex = complexRegularExpression.indexOf(NOT);
	            
	            	 if(notIndex > -1)
	                 {
	                    String notRegExp = complexRegularExpression.substring(notIndex+NOT.length(), complexRegularExpression.length());
	               
		               //return not match
		               return !matches(sourceValue,notRegExp);
	                 }
	            }//end else not
            }//end else and or not
         }
         catch (RuntimeException e)
         {
            throw new SystemException("complexRegularExpression="+complexRegularExpression+" sourceValue="+sourceValue+" "+Debugger.stackTrace(e));
         }
         
   
      return matchesRE(complexRegularExpression, sourceValue);   

      
   }// --------------------------------------------


   /**
    * 
    * 
    * 
    * @param aOld
    *           the string to be replaced
    * 
    * @param aNew
    *           the new string
    * 
    * @param aInput
    *           the input string
    * 
    * @return the replaced string
    *  
    */

   public static String replace(String aOld, String aNew, String aInput)
   {
	   
      if (aOld == null || aNew == null || aInput == null)
      {
         return aInput;

      }

      int i = aInput.indexOf(aOld);

      if (i < 0)

      {

         //nothing to replace

         return aInput;

      }

      StringBuilder sb = new StringBuilder();

      int start = 0;

      //String tmp = null;

      do
      {

         //replace all instances
         if (start != i)
         {

            sb.append(aInput.substring(start, i));

         }

         sb.append(aNew);

         start = i + aOld.length();

         i = aInput.indexOf(aOld, start);

      }

      while (i >= 0);

      //append rest of string

      if (start < aInput.length())

         sb.append(aInput.substring(start, aInput.length()));

      return sb.toString();

   }//----------------------------------------------

   /**
    * 
    * This method is used to convert an integer to roman numeral.
    * 
    * If the return value is " " then the integer was not valid. (1-4999)
    * 
    * @param input
    *           An integer to convert to Roman numeral.
    * 
    * @return String (Roman numeral)
    *  
    */

   public static String toRomanNumber(int input)
   {

      //Determines if the integer is in the range for 1-39999 to be able to
      // convert.

      if (input > 0 && input < 40000)
      {

         //An initialization of an empty string to store the roman numerals.

         StringBuilder strValue = new StringBuilder();

         //An array of strings stating the unique Roman sequences.

         //An array of integers corresponding to the Roman numeral sequences
         // respectively.

         String roNumStr[] = {
         "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M",
         "ME", "E", "MG", "G" };

         int roNumInt[] = {
         1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000, 4000, 5000,
         9000, 10000 };

         //A counted loop going through the arrays from the highest element to
         // the lowest.

         for (int i = roNumStr.length - 1; i >= 0; i--)
         {

            //This compares the contents in the element number of the loop to
            // the input value (or what is left of the input value)

            while (input >= roNumInt[i])
            {

               //The number that was compared is subtracted from the input
               // value and add the string that is respected to the compared
               // integer is added to the final string.

               input -= roNumInt[i];

               strValue.append(roNumStr[i]);

            }

         }

         return strValue.toString(); // Returns the roman numeral string value.

      }

      else
         return " "; // Returns " " because of the invalid input.

   }//-----------------------------------------------------------------

   /**
    * 
    * This method is used to convert Roman numerals to an integer.
    * 
    * @param input
    *           An integer to convert to Roman numerals.
    * 
    * @param valid
    *           A boolean to determine that states if the integer is valid or
    *           invalid.
    * 
    * @return Integer value of the Roman numeral.
    *  
    */

   public static int toIntegerFromRoman(String input, boolean valid)

   {

      if (input == null)

      {

         return 0;

      }

      //Checks to see if the input was valid.

      if (valid)
      {

         //Initializing the resultant integer.

         int intValue = 0;

         //An array of strings stating the roman numerals.

         //An array of integers corresponding to the roman numerals
         // respectively.

         char roNumStr[] = {
         'I', 'V', 'X', 'L', 'C', 'D', 'M', 'E', 'G' };

         int roNumInt[] = {
         1, 5, 10, 50, 100, 500, 1000, 5000, 10000 };

         //A nested counted loop comparing the character of the string from
         // right to

         //left to the roman numerals.

         for (int i = input.length() - 1; i >= 0; i--)
         {

            for (int j = roNumStr.length - 1; j >= 0; j--)
            {

               if (input.charAt(i) == roNumStr[j])
               {

                  //Adds the value to the total.

                  intValue += roNumInt[j];

                  /* 4 or 9 conditions */

                  //Checks if the character is I, X, C, or M because they are
                  // they only Roman numerals to determine if part of the value
                  // will be 4*10^x or 9*10^x.
                  //Checks to see if the character is not in the last position
                  // because the last position doesn't determine 4*10^x or
                  // 9*10^x.
                  //Checks the character to the right to see if that character
                  // is higher by 5*10^x or 1*10^(x+1) assuming x is the
                  // character's position in an integer number from right to
                  // left minus one.
                  //If it is, then it needs to be subtracted and since the
                  // value has been already added, the value needs to be
                  // subtracted twice.
                  if (i < input.length() - 1 && j < roNumStr.length - 1
                  && j % 2 == 0)
                  {

                     if (input.charAt(i + 1) == roNumStr[j + 1]
                     || input.charAt(i + 1) == roNumStr[j + 2])
                        intValue -= roNumInt[j] * 2;

                  }

                  break; //Breaks because the letter has been chosen.

               }

            }

         }

         return intValue; // Returns the roman numeral integer value if the
         // value is still valid.

      }

      else
         return 0; // Returns zero to indicate that the number is invalid.

   }//----------------------------------------------------------------------------

   /**
    * 
    * 
    * 
    * @param aInput
    *           the input string
    * 
    * @return
    */

   public static InputStream toInputStream(String aInput)

   {

      if (aInput == null)
      {

         return null;

      }

      return new BufferedInputStream(
      new ByteArrayInputStream(aInput.getBytes(IO.CHARSET)));

   }//------------------------------------------------------------

   /**
    * 
    * 
    * 
    * Inserts values from the object template into the Bind Str
    * 
    * For example if bindStr "Today is ${DAY}" and bindPara DAY="Monday"
    * 
    * results in a string "Today is Monday"
    * 
    * Default dateFormat MM/dd/yyyy
    * 
    * Functions
    * <#if x = 1>
        x is 1
      <#elseif x = 2>
        x is 2
      <#elseif x = 3>
      
        x is 3
      <#elseif x = 4>
        x is 4
      <#else>
        x is not 1 nor 2 nor 3 nor 4
      </#if>  
      
      <#if Tithes_1?exists>
     OK
    <#else>
       <#assign Tithes_1= 0>
 </#if>
 
   Date formating ${lastUpdated?string("yyyy-MM-dd HH:mm:ss zzzz")}
    * 
    * @param aBindText
    * 
    * @param aBindMap
    *           values to insert into the bind text
    * 
    * 
    *  
    */
   public static String format(String aBindText, Object aBindMap)
   throws FormatException
   {

      return Text.format(aBindText, aBindMap, DATE_FORMAT);

   }//------------------------------------------------------------------
   /**
    * Format a given array
    * 
    * @param bindText ex: Hello # ${0}
    * @param inputs {"Green", "Red"}
    * @return format response ex: Hello # Green
    */
   public static String formatArray(String bindText, Object[] inputs)
   throws FormatException
   {
	   if(inputs == null)
		   return bindText;
	   
	   
	   HashMap<String,Object> map = new HashMap<String,Object>();
	   
	   for (int i = 0; i < inputs.length; i++)
		{
		   map.put(String.valueOf(i),inputs[i]);
		}
	   
	   return format(bindText, map);
	   
   }// --------------------------------------------------------
   
   /**
    * 
    * Templates location is based on the configuration property "temp.dir"
    * 
    * 
    * 
    * Each template file as the following format;
    * 
    * 
    * 
    * <templateName>_ <countryLowerCase>_ <languageLowerCase>.txt
    * 
    * @param aTemplateName
    *           the template name
    * 
    * @param aBindMap
    *           the binding name/values pairs to insert into the template
    * 
    * @param aLocale
    *           the locale indicates the location and country
    * 
    * @return formated string
    * 
    * @throws Exception
    *  
    */
   
   /**
    * Formats a java.util.Date object in the standard format
    * for the application.
    * 
    */
   public static final String formatDate(Date date) {
  
      
      return formatDate(DATE_FORMAT, date);

   }//--------------------------------------------
    public static final String formatDate(Calendar date) 
    {
  
     if(date == null)
        return "";
     
      return formatDate(DATE_FORMAT, date.getTime());
   }//--------------------------------------------
   
   /**
    * Formats a java.util.Date object into the provided format
    * 
    * Letter  Date or Time Component  Presentation  Examples  
      G  Era designator  Text  AD  
      y  Year  Year  1996; 96  
      M  Month in year  Month  July; Jul; 07  
      w  Week in year  Number  27  
      W  Week in month  Number  2  
      D  Day in year  Number  189  
      d  Day in month  Number  10  
      F  Day of week in month  Number  2  
      E  Day in week  Text  Tuesday; Tue  
      a  Am/pm marker  Text  PM  
      H  Hour in day (0-23)  Number  0  
      k  Hour in day (1-24)  Number  24  
      K  Hour in am/pm (0-11)  Number  0  
      h  Hour in am/pm (1-12)  Number  12  
      m  Minute in hour  Number  30  
      s  Second in minute  Number  55  
      S  Millisecond  Number  978  
      z  Time zone  General time zone  Pacific Standard Time; PST; GMT-08:00  
      Z  Time zone  RFC 822 time zone  -0800  

    * @param format
    * @param aDate
    * @return
    */
   public static final String formatDate(String aFormat, Date aDate) 
   {
      if(aDate == null)
         return "";
      
      if (aFormat == null || aFormat.length() == 0)
      {
         return aDate.toString();
         
      }          
      
      SimpleDateFormat sdf = new SimpleDateFormat(aFormat);
          
      return sdf.format(aDate);
   }//--------------------------------------------
   public static final String formatDate(String text) 
   {
	  return formatDate(DATE_FORMAT, toDate(text));
	   
   }// --------------------------------------------------------

   public static final String formatFromTemplate(String aTemplateName,
                                                 Map<Object,Object> aBindMap, Locale aLocale)

   throws Exception

   {

      return format(Text.loadTemplate(aTemplateName, aLocale), aBindMap);

   }//--------------------------------------------
   

   /**
    * 
    * 
    * 
    * Inserts values from the object template into the Bind Str
    * 
    * For example if bindStr "Today is ${DAY}" and bindPara DAY="Monday"
    * 
    * results in a string "Today is Monday"
    * 
    * 
    * 
    * @param aBindText 
    * 
    * @param aBindMap values to insert into the bind text
    * 
    * @param aDateFormat
    *           i.e. MM/dd/yyyy
    *  
    */

   public static String format(String bindText, Object bindObj,
                               String dateFormat)

   throws FormatException

   {
	   return getTextStyles().format(bindText,bindObj,dateFormat);
   }//-----------------------------------------
   /**
    * Format text replacing place-holders prefixed with ${ and suffixed by } 
    * with the corresponding values in the map.
    * @param text the text to format
    * @param map the key/values
    * @return the formatted text
    */
   public static String formatText(String bindText, Map<String,String> map)
   throws FormatException
   {
	   return format(bindText,map);
   }// --------------------------------------------------------
   /**
    * write formatted output to the writer
    * @param aBindMap
    * @param text
    * @param writer
    * @throws IOException
    * @throws TemplateException
    */
   public static void formatWriter( String text, Object aBindMap, Writer writer) 
   throws IOException, FormatException
   {
      formatWriter(text, aBindMap, null, writer);  
   }//--------------------------------------------   
   /**
    * write formatted output to the writer
    * @param aBindMap
    * @param text
    * @param writer
    * @throws IOException
    * @throws TemplateException
    */
   public static void formatWriter( String text, Object aBindMap, String dateFormat, Writer writer) 
   throws IOException, FormatException
   {
     getTextStyles().formatWriter(text, aBindMap, dateFormat, writer);
   }//--------------------------------------------
   public static void formatWriterFromTemplateName( String templateName, Object aBindMap, Writer writer) 
   throws IOException, FormatException
   {
      
	   getTextStyles().formatWriter(new File(Config.getProperty(TEMPLATE_DIR_PROP_NM)), templateName,aBindMap, null, writer);
   }//--------------------------------------------
   public static void formatWriter( File templateDir, String templateName, Object aBindMap, String dateFormat, Writer writer) 
   throws IOException, FormatException
   {      
	   getTextStyles().formatWriter(templateDir, templateName, aBindMap, dateFormat, writer);
   }//--------------------------------------------

   /**
    * 
    * Compares two strings. Similar to using the String.equals()
    * 
    * method, but avoids NullPointerExceptions by having to check
    * 
    * for either String being null
    * 
    * 
    * 
    * @param str1
    *           One of 2 strings to be compared
    * 
    * @param str2
    *           Other of 2 strings to be compared
    * 
    * @return
    */

   public static boolean strCompare(String str1, String str2)
   {

      boolean isEqual = false;

      if (str1 == null && str2 == null)
      {

         isEqual = true;

      }
      else if (str1 != null && str2 == null)
      {

         isEqual = false;

      }
      else if (str1 == null && str2 != null)
      {

         isEqual = false;

      }
      else
      {

    	  if(str1 == null)
    		  return false;
    	  
         isEqual = str1.equals(str2);

      }

      return isEqual;

   }//--------------------------------------------

   /**
    * 
    * @param templateName
    * 
    * @return @throws
    * 
    * IOException
    *  
    */
   public static String loadTemplate(String templateName)
   throws  IOException
   {
	   return loadTemplate(templateName, null);
   }// --------------------------------------------------------
   /**
    * 
    * @param aTemplateNM
    * 
    * @return @throws
    * 
    * IOException
    *  
    */
   public static String loadTemplate(String aTemplateNM, Locale aLocale)
  throws IOException
   {

      if (aTemplateNM == null)

         throw new IllegalArgumentException(
         "aTemplateNM, aLocale required in Text");

      if (aLocale == null)

         aLocale = Locale.getDefault();

           String lang = aLocale.getLanguage();
           String country = aLocale.getCountry();

          if (Text.isNull(country)) 
             country=Config.getProperty(TEMPLATE_LOCALE_COUNTRY,Locale.getDefault().getCountry());
          if (Text.isNull(lang)) 
             lang= Config.getProperty(TEMPLATE_LOCALE_LANGUAGE,Locale.getDefault().getLanguage());

          StringBuffer templatePath = new StringBuffer(Config
         .getProperty(TEMPLATE_DIR_PROP_NM)).append("/").append(aTemplateNM);
          
          //Append locale information
          File file = new File(templatePath.toString());
          if(!file.exists())
          {
             templatePath.append("_").append(country.toLowerCase())
             .append("_").append(lang.toLowerCase())
             .append(Config.getProperty(TEMPLATE_EXTENSION_PROP_NM,".txt"));
          }
          
         return IO.readFile(templatePath.toString());

     
   } //------------------------------------------------------------

   /**
    * @return the number of digits in a string
    * @param val
    *           the input string
    */
   public static int digitCount(String val)
   {
      if (val == null)
         return 0;

      int c = 0;

      for (int i = 0; i < val.length(); i++)
      {
         if (Character.isDigit(val.charAt(i)))
         {
            c++;
         }
      }

      return c;
   }//----------------------------------------------
   /**
    * Get a count of a given character in a text
    * @param character the char to count
    * @param text the source text
    * @return the number of characters in the given text
    */
   public static int characterCount(char character, String text)
   {
      if (text == null || text.length() == 0)
         return 0;

      int c = 0;

      for (int i = 0; i < text.length(); i++)
      {
         if (text.charAt(i) == character)
         {
            c++;
         }
      }
      
      return c;
   }//----------------------------------------------

   /**
    * @return true the inputted string is an integer
    * @param val
    *           the input string
    */
   public static boolean isInteger(String val)
   {
      if (val == null || val.length() == 0)
         return false;

      val = val.trim();

      for (int i = 0; i < val.length(); i++)
      {
         if (!Character.isDigit(val.charAt(i)))
         {
            return false;
         }
      }

      return true;
   }//----------------------------------------------
   /**
    * 
    * @param aObject the object to convert
    * @return to string version of object
    */
   public static final String toString(Object aObject)
   {
     if(aObject == null)
        return "";
     
     if(aObject instanceof Date)
     {
        return Text.formatDate((Date)aObject);
     }
     else if (aObject instanceof Calendar)
     {
        return Text.formatDate((Calendar)aObject);
     }
     else if (aObject instanceof Object[])
     {
	  Object[] objs = (Object[])aObject;
	  
    	  StringBuffer text = new StringBuffer();
    	  for(int i=0; i < objs.length; i++)
    	  {
    	     if(i != 0)
    		  text.append(",");
    	     
    	     text.append(String.valueOf(objs[i]));
    	  }    	  
    	  return text.toString();
     }
     else if (aObject instanceof Textable)
     {
    	 return ((Textable)aObject).getText();
     }
     else if (aObject instanceof byte[])
     {
                
         return new String((byte[])aObject,IO.CHARSET);
    }
    else if (aObject instanceof InputStream)
    {
        try
        {
        	 return IO.readText((InputStream)aObject, true);
        }
        catch(IOException e)
        {
        	throw new SystemException(Debugger.stackTrace(e));
        }
            
    }
     
     
     return aObject.toString();
   }// --------------------------------------------
   /**
    * Determine whether the given object is an number (double, number) or can be 
    * converted to an number.
    * @param aObject the object to test
    * @return true is an instance of Number or a PARSE-ABLE number
    */
   public static boolean isNumber(Object aObject)
   {
      if(aObject instanceof Number)
      {
         return true;
      }
      
      if(aObject == null)
         return false;
      
      
      String text = aObject.toString().trim();
      
      if(text.length() == 0)
    	  return false;
      
      try
      {
         Double.parseDouble(text);
         return true;
      }
      catch(NumberFormatException e)
      {
         return false;
      }
   }// --------------------------------------------
   
   //private static final String encoding = Config.getProperty(Text.class,"encoding","ISO-8859-1");
   private static final Random random = new Random(Calendar.getInstance().getTime().getTime());
   private static TextStyles textStyles = null;    
   //private static final String TEMPLATE_PREFIX = "${";
   //private static final String TEMPLATE_SUFFIX = "}";  
}