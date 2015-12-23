package nyla.solutions.global.web.controller;

public interface ControllerConstants
{
   /**
    * Log out URL property
    */
   public static final String LOGOUT_URL_PROP = "logout.url";

   /**
    * Basic command constants
    */
   public static final String COMMAND      = "cmd";
   public static final String NEXT_COMMAND = "next_cmd";

   public static final String MSG_TAG      = "MSG_TAG";
   
   public static final String POPUP_MSG_TAG      = "popUpMsg";
   
   public static final String VALIDATOR_LIST   = "validator_list";

   public static final String VALIDED_PROP_SEPARATOR = "_";
   public static final String VALIDATION_REQUIRED_PROP = "req";
   public static final String VALIDATION_PARM_PREFIX = "v"+VALIDED_PROP_SEPARATOR;
   public static final int    VALIDATION_PROP_LEN    = 7;
   
   public static final String NEXT_VIEW    = "nv";
   public static final String CURRENT_VIEW = "cv";

   //System Constants   
   public static final String ERROR_PAGE      = "/themes/default/errors/internal_server_error.jsp";
   public static final String HOME_PAGE       = "/themes/default/welcome.jsp";
   public static final String APP_HOME_PAGE       = "application_home.jsp";
   
   /**
    * Download Constants
    */
   public static final String FILE_NAME = "filename";
   public static final String CONTENT_TYPE = "contentType";
   public static final String TEMPLATE_NAME_PARAM = "template";   
   
   
   /**
    * Web Session Constants
    */
   public static final String USER = "user";
   public static final String VIEW = "view";
   

   /**
    * Sort command constants
    */
   public static final String SORT_PROP = "sortProp";
   
   public static final String SORT_LIST  = "sortList";
   
   
   /**
    * Application Constants
    */
   public static final String CURRENT_SECTION = "CurrentSection";
   public static final String NEXT_ACTION = "APPLICTION_NEXT_ACTION";
   public static final String PREV_ACTION = "APPLICATION_PREVIOUS_ACTION";
   public static final String NAV_DIRECTION = "NAV_DIRECTION";
   
   /***
    * constants used in the Marketing Materials Form
    */
   public static final String MARKETING_FORM_ACTION = "MF_ACTION";
   public static final String MARKETING_FORM_ACTION_ADD = "ADD";
   public static final String MARKETING_FORM_ACTION_REMOVE = "REMOVE";
   public static final String MARKETING_FORM_ACTION_SUBMIT = "SUBMIT";
   /**
    * the download parameter name used for a request
    */
   public static final String DOWNLOADER_CLASS = "dl";
   
   public static final String APPLICATION_LIST = "applications";   
   
   public static final String APP  = "app";
   
   public static final String ACTION_SEND_LABEL = "ACTION_SEND";
   public static final String ACTION_SEND_IND = "SUBMIT";  
   
   /**
    * Core Configuration properties
    */
   public static final String FILE_MAX_BYTE_SIZE_PROP = "file.max.byte.size";
   public static final String FILE_SAVE_DIR_PROP = "file.save.dir";
   public static final String TEMPLATE_DIR_PROP = "template.dir";
   public static final String DS_NAME_PROP = "ds.name";
   public static final String DS_NAME_2_PROP = "ds.2.name";
   public static final String ALWAY_RELOAD_PROP = "always.reload.properties";   
   public static final String JNDI_INTIAL_ContextFactory = "jndi.initial.context.factory";
   
   public static final String TEMPORARY_FILE_DIR_PROP = "file.directory.temporary";
         
   public static final String DOC_TYPE_MARKETING_MATERIALS = "MARKETING_MATERIALS";
   public static final String DOC_TYPE_TEMPORARY = "TEMPORARY_DOC";
   
   // List buffer Sizes Properties
   public static final String APP_LIST_BUFFER_PROP = "app.list.buffer";
   public static final String APP_UPDATE_HISTORY_BUFFER_PROP = "app.update.history.list.buffer";
    
   //MAIL SERVER PROPERTIES
   public static final String MAIL_SERVER_PROP = "mail.smtp.server";   
   public static final String MAIL_ACCOUNT_NAME = "mail.account.name";
   public static final String MAIL_ACCOUNT_ADDRESS = "mail.account.address";

   public static final String EMAIL_CLIENT_SIGNOFF_MSG_TEXT = "email.static.msg.clientsignoff";
   public static final String EMAIL_PRODUCER_SUBMIT_CHUBB_MSG_TEXT = "email.static.msg.producersubmittochubb";
   public static final String UW_MAIN_EMAIL = "email.uw.main.address";

   //Purge task property names
   public static final String PURGE_DRAFT_DAYS_PROP = "purge.draft.days";
   public static final String PURGE_ACK_DAYS_PROP = "purge.ack.days";
   public static final String PURGE_UW_REVIEW_DOCS_DAYS_PROP = "purge.uw.review.docs.days";
   public static final String PURGE_UW_REVIEW_DOCS_REMINDER_DAYS_PROP = "purge.uw.review.docs.reminder.days";
   
   public static final String PURGE_EMAIL_REMINDER_TEMPLATE = "EMAIL_PURGE_REMINDER_TEMPLATE";
   
   public static final String TASK_DB_JDBC_DRIVER_PROP = "task.db.jdbc.driver";   
   public static final String TASK_DB_JDBC_URL_PROP    = "task.db.jdbc.url";
   
   
   public static final String APP_LOCK_TIMEOUT_ENFORCE_TRUE_FALSE ="applock.timeout.enforce";
   public static final String APP_LOCK_TIMEOUT_DAYS = "applock.timeout.days";
   public static final int MAX_APP_COMMENT_LENGTH = 50;
   public static final int MAX_QUESTION_COMMENT_LENGTH = 50;
}
