package nyla.solutions.global.web;

import java.util.*;
import java.io.*;

import nyla.solutions.global.data.Mappable;
import nyla.solutions.global.data.Named;
import nyla.solutions.global.data.PrimaryKey;
import nyla.solutions.global.util.Presenter;

/**
 * 
 * <pre>

 *  AbstractAction abstract for common web action functions
 * </pre>
 * 
 * @author Gregory Green
 * 
 * @version 1.0
 *  
 */

public abstract class AbstractAction implements Serializable
{

   /**
	 * 
	 */
	private static final long serialVersionUID = 5789747463604969968L;

/**
    * 
    * 
    * 
    * @param aCollection
    *           map.put(new Integer(element.hashCode()),element);
    * 
    * @return  
    */

   public Map<?,?> toMap(Collection<?> aCollection)
   {

      Hashtable<Object,Object> map = new Hashtable<Object,Object>(aCollection.size());

      buildMap(aCollection, map);

      return map;

   }//--------------------------------------------

   /**
    * 
    * 
    * 
    * @param aCollection
    *           map.put(new Integer(element.hashCode()),element);
    * 
    * @return  
    */

   public SortedMap<Object,Object> toSortedMap(Collection<?> aCollection)

   {

      TreeMap<Object,Object> map = new TreeMap<Object,Object>();

      buildMap(aCollection, map);

      return map;

   }//--------------------------------------------

   /**
    * 
    * @param aCollection
    *           the collection to build from
    * 
    * @param aMap
    *           the map
    *  
    */

   @SuppressWarnings("unchecked")
protected void buildMap(Collection<?> aCollection, Map<Object,Object> aMap)

   {

      Object element = null;

      Mappable<Object,Object> mappable = null;

      PrimaryKey primaryKey = null;

      Named named = null;

      for (Iterator<?> i = aCollection.iterator(); i.hasNext();)
      {

         element = i.next();

         if (element instanceof Mappable)

         {

            mappable = (Mappable<Object,Object>) element;

            aMap.put(mappable.getKey(), mappable.getValue());

         }

         else if (element instanceof PrimaryKey)

         {

            primaryKey = (PrimaryKey) element;

            aMap.put(new Integer(primaryKey.getPrimaryKey()), primaryKey);

         }

         else if (element instanceof Named)

         {

            named = (Named) element;

            aMap.put(named.getName(), named.getText());

         }

         else

         {

            aMap.put(new Integer(element.hashCode()), element);

         }

      }

   }//--------------------------------------------

   /**
    * 
    * ExternalContext Application Map
    * 
    * @return  
    */

   protected abstract Map<?,?> getApplicationMap();

   /**
    * 
    * ExternalContext Session Map
    * 
    * @return  
    */

   protected abstract Map<?,?> getSessionMap();

   /**
    * 
    * ExternalContext Request Map
    * 
    * @return  
    */

   protected abstract Map<?,?> getRequestMap();
   

   /**
    * 
    * Get Remote USer
    * 
    * @return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()
    *  
    */

   protected abstract String getRemoteUser();


   /**
    * 
    * Get Request cookie map
    * 
    * @return FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap()
    *  
    */

   protected abstract Map<?,?> getRequestCookieMap();

   /**
    * 
    * Get Request header map
    * 
    * @return FacesContext.getCurrentInstance().getExternalContext().getRequestHeaderMap()
    *  
    */

   protected abstract Map<?,?> getRequestHeaderMap();

   /**
    * 
    * Get View root Locale
    * 
    * @return FacesContext.getCurrentInstance().getApplication().getDefaultLocale()
    *  
    */

   protected abstract Locale getApplicationDefaultLocale();

   /**
    * 
    * Get View root Locale
    * 
    * @return FacesContext.getCurrentInstance().getViewRoot().getLocale()
    *  
    */

   protected abstract Locale getViewRootLocale();

   /**
    * 
    * Get Locale
    * 
    * @return FacesContext.getCurrentInstance().getExternalContext().getRequestLocale()
    *  
    */

   protected abstract Locale getRequestLocale();

   /**
    * 
    * Get request parameter map
    * 
    * @return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
    *  
    */

   protected abstract Map<?,?> getRequestParameterMap();

   
   /**
    * 
    * Get Resource As input Stream
    * 
    * @param aURI
    *           the uri
    * 
    * @return FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream()
    *  
    */

   protected abstract InputStream getResourceAsStream(String aURI);


   /**
    * 
    * 
    * 
    * @return instance of presenter for the root view locale
    *  
    */

   protected Presenter getPresenter()
   {

      return Presenter.getPresenter();

   }//--------------------------------------------

   /**
    * 
    * 
    * 
    * @return instance of presenter for the root view locale
    *  
    */

   protected Presenter getPresenter(Locale aLocale)

   {

      return Presenter.getPresenter(aLocale);

   }//--------------------------------------------
   /**
    * Add message to current faces context
    * @param aMessage the message
    */
   protected abstract void setMessage(Object aMessage);
   
   /**
    * Add message to current faces context
    * @param aKey the key for message
    * @param aMessage the message
    */
   protected abstract void setMessage(String aKey, Object aMessage);
   

}