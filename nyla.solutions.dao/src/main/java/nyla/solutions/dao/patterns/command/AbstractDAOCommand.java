package nyla.solutions.dao.patterns.command;

import nyla.solutions.dao.DAO;

/**
 * 
 * @author Gregory Green
 *
 * @param <T>
 */
public abstract class AbstractDAOCommand<I,O> extends DAO implements DAOCommand<I,O>
{

}
