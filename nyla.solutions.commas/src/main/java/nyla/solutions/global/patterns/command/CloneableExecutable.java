package nyla.solutions.global.patterns.command;

import nyla.solutions.global.patterns.command.Executable;

public interface CloneableExecutable extends Cloneable, Executable
{
   /**
    * 
    * @return the clone
    */
   public Object clone()
   throws CloneNotSupportedException;
}
