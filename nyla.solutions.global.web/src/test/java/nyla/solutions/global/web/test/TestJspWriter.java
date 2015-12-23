package nyla.solutions.global.web.test;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

/**
 * <pre>
 * TestJspWriter provides a set of functions to
 * </pre> 
 * @author Gregory Green
 * @version 1.0
 */
public class TestJspWriter extends JspWriter
{
   public TestJspWriter()
   {
      super(10,false);
   }//--------------------------------------------
   /**
    * Constructor for TestJspWriter initalizes internal 
    * data settings.
    * @param arg0
    * @param arg1
    */
   public TestJspWriter(int arg0, boolean arg1)
   {
      super(arg0, arg1);

   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#newLine()
    */
   public void newLine() throws IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#print(boolean)
    */
   public void print(boolean arg0) throws IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#print(char)
    */
   public void print(char arg0) throws IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#print(int)
    */
   public void print(int arg0) throws IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#print(long)
    */
   public void print(long arg0) throws IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#print(float)
    */
   public void print(float arg0) throws IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#print(double)
    */
   public void print(double aText) throws IOException
   {
      outText.append(aText);
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#print(char[])
    */
   public void print(char[] aText) throws IOException
   {
      outText.append(aText);
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#print(java.lang.String)
    */
   public void print(String aText) throws IOException
   {
      outText.append(aText);
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#print(java.lang.Object)
    */
   public void print(Object aText) throws IOException
   {
      outText.append(aText);
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#println()
    */
   public void println() throws IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#println(boolean)
    */
   public void println(boolean arg0) throws IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#println(char)
    */
   public void println(char arg0) throws IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#println(int)
    */
   public void println(int aText) throws IOException
   {
      outText.append(aText);
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#println(long)
    */
   public void println(long aText) throws IOException
   {
      outText.append(aText);
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#println(float)
    */
   public void println(float aText) throws IOException
   {
      outText.append(aText);
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#println(double)
    */
   public void println(double aText) throws IOException
   {
      outText.append(aText);
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#println(char[])
    */
   public void println(char[] aText) throws IOException
   {
      outText.append(aText);
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#println(java.lang.String)
    */
   public void println(String aText) throws IOException
   {
      outText.append(aText);
   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#println(java.lang.Object)
    */
   public void println(Object aText) throws IOException
   {
      outText.append(aText);
   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#clear()
    */
   public void clear() throws IOException
   {
      outText.setLength(0);
   }//--------------------------------------------

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#clearBuffer()
    */
   public void clearBuffer() throws IOException
   {
      outText.setLength(0);
   }//--------------------------------------------

   /**
    * 
    * @see java.io.Writer#flush()
    */
   public void flush() throws IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see java.io.Writer#close()
    */
   public void close() throws IOException
   {
      // TODO Auto-generated method stub

      //----------------------------------------
   }

   /**
    * 
    * @see javax.servlet.jsp.JspWriter#getRemaining()
    */
   public int getRemaining()
   {
      // TODO Auto-generated method stub
      return 0;
      //----------------------------------------
   }

   /**
    * 
    * @see java.io.Writer#write(char[], int, int)
    */
   public void write(char[] arg0, int arg1, int arg2) throws IOException
   {
   }//--------------------------------------------
   /**
    * @return Returns the outText.
    */
   public StringBuffer getOutText()
   {
      return outText;
   }//--------------------------------------------
   /**
    * 
    * @see java.io.Writer#write(java.lang.String)
    */
   public void write(String aText) throws IOException
   {
      outText.append(aText);
   }
   private StringBuffer outText = new StringBuffer();

}
