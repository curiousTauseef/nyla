package nyla.solutions.global.patterns.creational;

import nyla.solutions.global.exception.DuplicateRowException;

public interface KeyValueCRUD<Key,Value>
{
	
	Value create(Key key, Value value)
	throws DuplicateRowException;
	
	Value read(Key key);
	
	Value update(Key key,Value value);
	
	Value save(Key key, Value value);
	
	Value delete(Key key);
	
	void deleteAll();
	
}
