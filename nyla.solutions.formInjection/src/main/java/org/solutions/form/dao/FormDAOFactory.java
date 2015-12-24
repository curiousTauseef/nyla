package org.solutions.form.dao;

import org.solutions.patterns.servicefactory.ServiceFactory;



public abstract class FormDAOFactory
{

   private FormDAOFactory()
   {
      super();
      
   }// --------------------------------------------

   /**
    * 
    * @return Create instance of FormDAO
    */
   public static final FormDAO createFormDAO()
   {
      return (FormDAO)ServiceFactory.getInstance().create(FormDAO.class);
   }// --------------------------------------------

   /**
    * 
    * @return Create instance of BreDAO
    */
   public static final BreDAO createBreDAO()
   {
      return (BreDAO) ServiceFactory.getInstance().create(BreDAO.class);
   }// --------------------------------------------
   /**
    * 
    * @return Create instance of QuestionDAO
    */
   public static final QuestionDAO createQuestionDAO()
   {
      return (QuestionDAO) ServiceFactory.getInstance().create(QuestionDAO.class);
   }// --------------------------------------------

}
