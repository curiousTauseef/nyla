package nyla.solutions.formInjection.dao.file.xml;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.formInjection.dao.QuestionDAO;
import nyla.solutions.formInjection.data.AttributeFacts;
import nyla.solutions.formInjection.data.FormType;
import nyla.solutions.formInjection.data.Question;
import nyla.solutions.formInjection.data.QuestionAttribute;
import nyla.solutions.formInjection.data.Questionaire;
import nyla.solutions.formInjection.data.ResponseTable;
import nyla.solutions.formInjection.data.ResponseType;
import nyla.solutions.global.exception.DuplicateRowException;
import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.exception.SystemException;
import nyla.solutions.global.operations.logging.Log;
import nyla.solutions.global.util.Config;
import nyla.solutions.global.util.Debugger;
import nyla.solutions.global.xml.XML;
import nyla.solutions.global.xml.XMLInterpreter;

import org.apache.log4j.Category;

/**
 * 
 * <b>QuestionXmlDAO</b> is a
 * 
 *  [Sample Config properties]
    org.solutions.form.dao.file.xml.QuestionXmlDAO.questionaireLocation=/Projects/solutions/FormInjection/runtime/fileDB/questionaire/
    org.solutions.form.dao.file.xml.QuestionXmlDAO.formTypeLocation=/Projects/solutions/FormInjection/runtime/fileDB/formType/

 * @author Gregory Green
 *
 */
public class QuestionXmlDAO implements QuestionDAO
{

   public void commit()
   {
      // TODO Auto-generated method stub

   }

   public final AttributeFacts insert(AttributeFacts attributeFacts) throws SQLException, DuplicateRowException
   {
      // TODO Auto-generated method stub
      return null;
   }

   public void rollback()
   {
      // TODO Auto-generated method stub

   }

   public final Question saveQuestion(Question question)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public final QuestionAttribute saveQuestionAttribute(QuestionAttribute attribute)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public final ResponseTable saveResponseTable(ResponseTable responseTable)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public final ResponseType saveResponseType(ResponseType responseType)
   {
      // TODO Auto-generated method stub
      return null;
   }

   public final QuestionAttribute selectQuestionAttributeByPK(Integer formTypeId,
                                                        Integer questionId,
                                                        String attrName) throws SQLException, NoDataFoundException
   {
      // TODO Auto-generated method stub
      return null;
   }

   public final Collection selectQuestionsByFormTypeCode(String formTypeName) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }

   public final Collection selectSectionsByFormTypeCode(String formTypeCode) throws NoDataFoundException, SQLException
   {
      // TODO Auto-generated method stub
      return null;
   }//--------------------------------------------
   /**
    * Read questionaire where location is (this.questionaireLocation+"/"+formTypeName)
    *
    * @see nyla.solutions.formInjection.dao.QuestionDAO#constructQuestioniareByFormTypeCode(java.lang.String)
    */
   public final Questionaire constructQuestioniareByFormTypeCode(String formTypeName) throws NoDataFoundException, SQLException
   {
      String questionaireLocation = this.questionaireLocation+"/"+formTypeName+extension;
      
      return (Questionaire)readObject(questionaireLocation);
   }//--------------------------------------------
   /**
    * 
    *location = this.formTypeLocation+"/"+formTypeName+extension;
    * @see nyla.solutions.formInjection.dao.QuestionDAO#selectFormTypeByCode(java.lang.String)
    */
   public final FormType selectFormTypeByCode(String formTypeName) throws SQLException, NoDataFoundException
   {
      String location = this.formTypeLocation+"/"+formTypeName+extension;
      
      return (FormType)this.readObject(location);
   }//--------------------------------------------



   public void dispose()
   {
      // TODO Auto-generated method stub

   }
   /**
    * Read the object from a given location
    * @param location the path location
    * @return the converted object
    * @throws NoDataFoundException
    */
   protected Object readObject(String location)
   throws NoDataFoundException
   {
      Debugger.println(this,"Reading object from location "+location);
      
      File file = new File(location);
      
      if(!file.exists())
      {
         throw  new NoDataFoundException(file.getAbsolutePath());
      }
      
      if(!file.isFile())
      {
         throw  new NoDataFoundException("Not a file "+file.getAbsolutePath());
      }
      
      try
      {
         return xmlInterpreter.toObject(file);
      }
      catch (IOException e)
      {
         throw new SystemException(Debugger.stackTrace(e));
      }      
   }//--------------------------------------------
   protected void writeObject(Object object, String location)
   throws NoDataFoundException
   {
      Debugger.println(this,"Writing object from location "+location);
      
      File file = new File(location);
      
      if(!file.getParentFile().exists())
      {
         throw  new NoDataFoundException(file.getAbsolutePath());
      }
            
      try
      {
         xmlInterpreter.toXMLFile(object, file);
      }
      catch (IOException e)
      {
         throw new SystemException(Debugger.stackTrace(e));
      }      
   }//--------------------------------------------
   

   /**
    * @return the extension
    */
   protected String getExtension()
   {
      return extension;
   }

   /**
    * @param extension the extension to set
    */
   protected void setExtension(String extension)
   {
      this.extension = extension;
   }

   protected Log logger = Debugger.getLog(getClass());
   private String extension = Config.getProperty(getClass().getPackage().getName()+".extension",".xml");
   private XMLInterpreter xmlInterpreter = XML.getInterpreter();
   private String questionaireLocation  =Config.getProperty(QuestionXmlDAO.class.getName()+".questionaireLocation");
   private String formTypeLocation  = Config.getProperty(QuestionXmlDAO.class.getName()+".formTypeLocation");
}
