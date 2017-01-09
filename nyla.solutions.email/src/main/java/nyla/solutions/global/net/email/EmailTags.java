package nyla.solutions.global.net.email;





public interface EmailTags

{

   public static final String MAIL_SESSION_JNDI_USED = "mail.smtp.jndi.session.used";

   public static final String MAIL_SESSION_JNDI_NAME = "mail.smtp.jndi.session.name";

   
   /**
    * MAIL_FROM_PASSWORD_PROP = "mail.smtp.from.password"
    */
   public static final String MAIL_FROM_PASSWORD_PROP = "mail.from.password";

   /**
    * MAIL_SUBJECT_PROP = "mail.subject"
    */
   public static final String MAIL_SUBJECT_PROP = "mail.subject";

   /**

    * true then mail user/password required

    */

   public static final String MAIL_AUTHENICATION_REQUIRED_PROP = "mail.auth.required";

   public static final String EMAIL_DELIMITER_IND = ";";

   public static final String MAIL_SERVER_PROP = "mail.host";

   /**
    * MAIL_FROM_ADDRESS_PROP = "mail.from"
    */
   public static final String MAIL_FROM_ADDRESS_PROP = "mail.from";
  
   

   public static final String  TEMPLATE_DIR_PROP = "template.dir";

    public static final String TEMPLATE_NAME = "tmp_nm";

    public static final String EMAIL_US_TEMPLATE = "email_us";

    

    

    public static final String TO = "to";

    public static final String CATEGORY = "CATEGORY";

    public static final String CONTACT_ID = "CONTACT_ID";

    public static final String SUBJECT = "SUBJECT";

    public static final String BODY = "BODY";

    public static final String FROM_EMAIL = "mail.from";

    public static final String FROM_NAME = "mail.from";

    public static final String FILE_LIST = "FILE_LIST";

    public static final String FILE_TYPE = "FILE_TYPE";

    public static final String INCLUDE_FILES = "INCLUDE_FILES";

    public static final String CC = "CC";

}

