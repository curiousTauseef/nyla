package nyla.solutions.core.media;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import nyla.solutions.core.exception.SystemException;
import nyla.solutions.core.util.Debugger;



/**
 * Used to modify video and photo media files
 * @author Gregory Green
 */
public class Graphics
{
	/**
	 * Rotate a file a given number of degrees
	 * @param input input image file
	 * @param output the output image fiel
	 * @param format the format (png, jpg, gif, etc.)
	 * @param degrees angle to rotote
	 * @throws IOException
	 */
	  public static void rotateImage(File input, File output, String format, int degrees) 
	  throws IOException
	  {
	   	   
		  BufferedImage inputImage = ImageIO.read(input);	   

		    Graphics2D g = (Graphics2D) inputImage.getGraphics();
		    g.drawImage(inputImage, 0, 0, null);
	
		    AffineTransform at = new AffineTransform();
	
		    // scale image
		    //at.scale(2.0, 2.0);
	
		    // rotate 45 degrees around image center
		    at.rotate(degrees * Math.PI / 180.0, inputImage.getWidth() / 2.0, inputImage.getHeight() / 2.0);
		    //at.rotate(Math.toRadians(degrees), inputImage.getWidth() / 2.0, inputImage.getHeight() / 2.0);
	
		   
		    /*
		     * translate to make sure the rotation doesn't cut off any image data
		     */
		    AffineTransform translationTransform;
		    translationTransform = findTranslation(at, inputImage);
		    at.preConcatenate(translationTransform);
		   
		    
		    // instantiate and apply transformation filter
		    BufferedImageOp bio = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		    
		    BufferedImage destinationBI = bio.filter(inputImage, null);
	
		    ImageIO.write(destinationBI, format, output);
		    
	  }//---------------------------------------------
	  /**
	   * 
	   * find proper translations to keep rotated image correctly displayed
	   */
	  private static AffineTransform findTranslation(AffineTransform at, BufferedImage bi) 
	  {
	    Point2D p2din, p2dout;

	    p2din = new Point2D.Double(0.0, 0.0);
	    p2dout = at.transform(p2din, null);
	    double ytrans = p2dout.getY();

	    p2din = new Point2D.Double(0, bi.getHeight());
	    p2dout = at.transform(p2din, null);
	    double xtrans = p2dout.getX();

	    AffineTransform tat = new AffineTransform();
	    tat.translate(-xtrans, -ytrans);
	    
	    return tat;
	  }
	  //---------------------------------------------------------------
	  public static void printScreen(int x, int y, int width, int height, String format,File file)
	  {
	     try
	     {
		  if(robot == null)
			  robot = new Robot();
		     
		  synchronized (robot)
		  {
		     Rectangle rect = new Rectangle(x,y,width,height);
		     
		     BufferedImage image = robot.createScreenCapture(rect);
		     ImageIO.write(image, format, file);
		  }
	     } 
	     catch (Exception e)
	     {
		  throw new SystemException(Debugger.stackTrace(e));
		}
	  }// ----------------------------------------------
	  public static void printScreen(int x, int y, int width, int height, String format,OutputStream outputStream)
	  {
	     try
	     {
		  if(robot == null)
			  robot = new Robot();
		     
		  synchronized (robot)
		  {
		     Rectangle rect = new Rectangle(x,y,width,height);
		     
		     BufferedImage image = robot.createScreenCapture(rect);
		     ImageIO.write(image, format, outputStream);
		  }
	     } 
	     catch (Exception e)
	     {
		  throw new SystemException(Debugger.stackTrace(e));
		}
	  }// ----------------------------------------------
	  private static Robot robot = null;

}
