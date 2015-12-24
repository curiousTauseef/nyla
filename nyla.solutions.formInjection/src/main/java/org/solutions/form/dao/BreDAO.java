package org.solutions.form.dao;

import java.sql.SQLException;
import java.util.Collection;

import nyla.solutions.global.exception.NoDataFoundException;
import nyla.solutions.global.patterns.Disposable;

import org.solutions.form.BRE;
import org.solutions.form.bre.ExpressionBluePrint;
import org.solutions.form.bre.OperationBluePrint;
import org.solutions.form.data.Form;
import org.solutions.form.data.Questionaire;


public interface BreDAO extends Disposable, QuestionDAO
{

   /**
    * Select the expression blue print
    * @param aExpressionPK the expression primary key
    * @return the expression blue print
    * @throws NoDataFoundException
    * @throws SQLException
    */
   public ExpressionBluePrint selectExpressionByPK(
                                                            Integer aExpressionPK) throws NoDataFoundException, SQLException;//--------------------------------------------

   /**
    * Select the rules linked to the form type
    * @param aFormTypeID the form type ID
    * @return the collection
    * @throws NoDataFoundException
    * @throws SQLException
    */
   public Collection selectRulesByFormTypeCode(String aFormTypeID) throws NoDataFoundException, SQLException;//-------------------------------------------

   /**
    * 
    * @param aOperationPK the operation primary key
    * @return OperationBluePrint information
    */
   public OperationBluePrint selectOperationBluePrintByPK(
                                                                   Integer aOperationPK) throws NoDataFoundException, SQLException;//--------------------------------------------

   /**
    * Construct the BRE populated with rules form the database (if they exist).
    * @param aFormTypeCode
    * @return
    * @throws NoDataFoundException
    * @throws SQLException
    */
   public BRE constructBRE(String aFormTypeCode, Questionaire aQuestionaire) throws NoDataFoundException, SQLException;//--------------------------------------------

   /**
    * 
    * @param aForm the form to construct the BRE for
    * @return BRE instance
    * @throws NoDataFoundException
    * @throws SQLException
    */
   public BRE constructBRE(Form aForm) throws NoDataFoundException, SQLException;//--------------------------------------------

   /**
    * 
    * @return latest version of form types
    * @throws NoDataFoundException when no form types found
    * @throws SQLException
    */
   public Collection selectFormTypes() throws NoDataFoundException, SQLException;//--------------------------------------------
   
   
   /***
    * Commit a transaction
    *
    */
   public void commit();
   
   /***
    * Rollback a transaction
    *
    */
   public void rollback();
   

}