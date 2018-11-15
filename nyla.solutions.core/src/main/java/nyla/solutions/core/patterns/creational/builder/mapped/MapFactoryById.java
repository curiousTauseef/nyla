package nyla.solutions.core.patterns.creational.builder.mapped;

import java.util.Map;

import nyla.solutions.core.Identifiable;
import nyla.solutions.core.data.Identifier;

public interface MapFactoryById<K,V> 
extends Identifier, Identifiable
{
	public Map<K,V> createMap();
}
