package nyla.solutions.global.patterns.conversion;

import nyla.solutions.global.data.Nameable;

/**
 * Used a generic version of Textable with a name.
 * @author Gregory Green
 *
 * @param <SourceType>
 * @param <TargetType>
 */
public interface NameableConverter<SourceType, TargetType> extends Nameable, Converter<SourceType, TargetType>
{

}
