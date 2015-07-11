package nyla.solutions.global.json.operations;

import nyla.solutions.global.patterns.command.commas.json.JsonExampleVistor;
import nyla.solutions.global.patterns.reflection.ClassSchema;
import nyla.solutions.global.patterns.reflection.Mirror;

public class JsonPrinter
{
	public static void main(String[] args) 
			
			throws ClassNotFoundException
	{
		if(args.length != 1) {
			throw new RuntimeException("Usage: java "+JsonPrinter.class.getName()+" className");
		}
		String className = args[0];
		
		JsonExampleVistor argVisitor = new JsonExampleVistor();
		
		ClassSchema schema = Mirror.toClassSchema(className);
		
		schema.accept(argVisitor);
	
		System.out.println(argVisitor.toString());
	}

}
