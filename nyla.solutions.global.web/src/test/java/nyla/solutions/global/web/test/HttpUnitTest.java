package nyla.solutions.global.web.test;


import com.meterware.httpunit.*;

public class HttpUnitTest 
//extends TestCase 
{
	private WebConversation http;
	private WebResponse response;
	private WebForm form;
	
	/**
	 * step 1. call setUp("your URL goes here") in your test method with a target URL
	 * step 2. response = getResponse(); 
	 * step 3. WebForm yourForm = response.getFormWithName("yourFormName");
	 * step 4. use yourForm object to test the HTML page
	 * 
	 **@see com.bms.informatics.gcsm.schedule.web.NewVisitActionTest 
	 *  for code example
	 * 
	 */
	public void setUp(String url, String username, String password) throws Exception {
		http = new WebConversation();

		response = http.getResponse(new GetMethodWebRequest(url));
		WebForm []forms = response.getForms();
		forms[0].setParameter("j_username",username);  
		forms[0].setParameter("j_password",password);
		SubmitButton[] submit = forms[0].getSubmitButtons();
		response = forms[0].submit(submit[0]);
	}
	
	public WebForm getForm() {
		return form;
	}
	

	public void setForm(WebForm form) {
		this.form = form;
	}

	public WebResponse getResponse() {
		return response;
	}
	

	public void setResponse(WebResponse response) {
		this.response = response;
	}
	
	

}
