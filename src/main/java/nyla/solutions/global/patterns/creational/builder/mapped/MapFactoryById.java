package nyla.solutions.global.patterns.creational.builder.mapped;

import java.util.Map;

import nyla.solutions.global.data.Identifier;

public interface MapFactoryById<K,V> extends Identifier
{
	public Map<K,V> createMap();
}
