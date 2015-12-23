package nyla.solutions.global.web;
import java.io.Serializable;

/**
 * <pre>
 * 
 *  WebTags constants used to stored request, session, and application scope 
 *  attribute keys.
 *  
 * </pre>
 * 
 * @author Gregory Green
 * @version 1.0
 *  
 */

public interface WebTags extends Serializable
{
   /**
    * ERROR_MESSAGE = "errorMessage"
    */
   public static final String ERROR_MESSAGE = "errorMessage";
   
   /**
    * PARAMETER_VALUES_SEPARATOR = ","
    */
   public static final String PARAMETER_VALUES_SEPARATOR = ",";
   /**
    * VALIDED_PROP_SEPARATOR = "_" used by the ValidationFactory
    */
   public static final String VALIDED_PROP_SEPARATOR = "_";
   
   /**
    * VALIDATION_PROP_LEN = 7 used by the ValidationFactory
    */
   public static final int VALIDATION_PROP_LEN = 7;
   
   /**
    * VALIDATION_PARM_PREFIX = "v_" used by the ValidationFactory
    */
   public static final String VALIDATION_PARM_PREFIX = "v_";
   /**
    * 
    * session attribute name for accessRole
    * 
    * value ="accessRole"
    *  
    */    
   public static final String ACCESS_ROLE = "accessRole";
   
   /**
    * ACCESS_ROLE_CODE = "accessRoleCode"
    */
   public static final String ACCESS_ROLE_CODE = "accessRoleCode";

   /**
    * 
    * session attribute name for acl
    * 
    * value ="acl"
    *  
    */   
   public static final String ACL = "acl";
   
   /**
    * 
    * session attribute name for site ID
    * 
    * value ="siteID"
    *  
    */
   public static final String SITE_ID = "siteID";
   
   /**
    * 
    * session attribute name for the site
    * 
    * value ="site"
    *  
    */
   public static final String SITE = "site";   
   
   /**
    * 
    * session attribute name for users
    * 
    * value ="user"
    *  
    */
   public static final String USER = "user";
   public static final String SMM = "Site Monitor Manager";
   public static final String SMN = "Site Monitor";
   public static final String STM = "Site Manager";
   public static final String STB = "Site Manager back up";
   public static final String SMB = "Site Monitor Backup";
   public static final String PTM = "Protocol Manager";
   public static final String PTB = "Protocol Manager back up";
   public static final String USM = "Unblinded Site Monitor";
   public static final String MSM = "Manager Site Management";
   public static final String UM = "Unit Manager";
   public static final String LD = "Limited Database Administrator";
   public static final String DBA = "Database Administrator";   
   public static final String MPM= "Manager Protocol Management";
   public static final String SMM_CD = "SMM";
   public static final String SMN_CD = "SMN";
   public static final String SMB_CD = "SMB";   
   public static final String STM_CD = "STM";
   public static final String STB_CD = "STB";   
   public static final String LD_CD = "LD";
   public static final String DBA_CD = "DBA";
   public static final String MPM_CD = "MPM";
   public static final String MSM_CD = "MSM";
   public static final String USM_CD = "USM";
   public static final String PTB_CD = "PTB";
   public static final String PTM_CD = "PTM"; 
   
   public static final String INITPANELS = "INITPANELS";
    public static final String REFERESHPANELS = "REFERESHPANELS";
   public static final String REFERESHSITEPANEL = "REFERESHSITEPANEL";
    public static final String REFERESHVISITPANEL = "REFERESHVISITPANEL";
    public static final String REFERESHISSUEPANEL = "REFERESHISSUEPANEL";
    public static final String REFERESHALERTPANEL = "REFERESHALERTPANEL ";
    public static final String REFERESHSITEREPORTPANEL = "REFERESHSITEREPORTPANEL";
    public static final String REFERESHSTAFFPANEL = "REFERESHSTAFFPANEL";
   /**
    * 
    * front controller config property for session originator implement
    * 
    * 
    * 
    * value = "sessionOriginatorClass"
    *  
    */

   public static final String SESSION_ORIGINATOR_CLASS = "sessionOriginatorClass";
   
   /**
    * TRUE="T"
    */
   public static final String TRUE="T";
   
   /**
    * FALSE="F"
    */
   public static final String FALSE="F";
   
   /**
    * AUTHORIZED="AUTHORIZED"
    */
   public static final String AUTHORIZED="AUTHORIZED";
   
   /**
    * Roles tag name used by SecurityFilter
    * 
    * ROLES = "roles"
    */
   public static final String ROLES = "roles";
   
   /**
    * used by SecurityFilter
    * PERMISSIONS="permissions"
    * 
    */
   public static final String PERMISSIONS="permissions";

}

