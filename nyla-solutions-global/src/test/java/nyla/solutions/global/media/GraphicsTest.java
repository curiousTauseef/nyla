/**
 * 
 */
package nyla.solutions.global.media;

import java.io.File;

import nyla.solutions.global.media.Graphics;
import junit.framework.TestCase;

/**
 * @author greengr
 *
 */
public class GraphicsTest extends TestCase
{

   /**
    * @param name
    */
   public GraphicsTest(String name)
   {
	super(name);
   }

   /* (non-Javadoc)
    * @see junit.framework.TestCase#setUp()
    */
   protected void setUp() throws Exception
   {
	super.setUp();
   }

   /**
    * Test method for {@link nyla.solutions.global.media.Graphics#printScreen(int, int, int, int, java.lang.String, java.io.File)}.
    */
   public void testPrintScreenIntIntIntIntStringFile()
   {
	Graphics.printScreen(0, 0, 1000, 800, "png", new File("runtime/tmp/output/screenshot.png"));
   }

}
