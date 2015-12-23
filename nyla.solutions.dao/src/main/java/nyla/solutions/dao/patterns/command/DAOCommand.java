package nyla.solutions.dao.patterns.command;

import nyla.solutions.dao.Connectable;
import nyla.solutions.dao.DataSourceable;
import nyla.solutions.global.patterns.Disposable;
import nyla.solutions.global.patterns.command.Command;

public interface DAOCommand<ReturnType,InputType> extends Command<ReturnType,InputType>, DataSourceable, Connectable,
		Disposable
{

}
