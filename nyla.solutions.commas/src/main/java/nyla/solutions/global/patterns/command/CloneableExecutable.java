package nyla.solutions.global.patterns.command;

public interface CloneableExecutable extends Cloneable, Executable
{
   /**
    * 
    * @return the clone
    */
   public Object clone()
   throws CloneNotSupportedException;
}
