package nyla.solutions.global.patterns.creational;

public interface KeyValueSaver<Key,Value>
{
	
	
	Value save(Key key, Value value);
	
		
}
