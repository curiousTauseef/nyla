package nyla.solutions.global.data;

import java.util.Map;

/**
 * Represents an object that returns an Map representation of its data
 * @author Gregory Green
 *
 */
public interface Mapped<KeyType, ValueType>
{
   public Map<KeyType, ValueType> getMap();
   
   public void setMap(Map<KeyType, ValueType> map);
}

