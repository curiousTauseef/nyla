/**
 * 
 */
package nyla.solutions.global.io;

import java.io.File;




import nyla.solutions.global.data.Textable;
import nyla.solutions.global.exception.ConfigException;
import nyla.solutions.global.io.Fileable;


/**
 * Wrapper to return the absolute path of a Fileable object's file representation
 * @author Gregory Green
 *
 */
public class AbsolutePathFileableText implements Textable
{

	/**
	 * Return the file able.file.getAbsolutePath()
	 */
	public String getText()
	{
		if(this.fileable == null)
			throw new ConfigException("Fileable not set");
		File file = this.fileable.getFile();
		
		return file.getAbsolutePath();
	}//---------------------------------------------
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fileable == null) ? 0 : fileable.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbsolutePathFileableText other = (AbsolutePathFileableText) obj;
		if (fileable == null)
		{
			if (other.fileable != null)
				return false;
		}
		else if (!fileable.equals(other.fileable))
			return false;
		return true;
	}

	/**
	 * @return the fileable
	 */
	public Fileable getFileable()
	{
		return fileable;
	}//---------------------------------------------
	/**
	 * @param fileable the fileable to set
	 */
	public void setFileable(Fileable fileable)
	{
		this.fileable = fileable;
	}//---------------------------------------------
	
	private Fileable fileable = null;

}
