<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<c:if test="${!(empty sessionScope.form) && !sessionScope.form.readOnly}">
<script language="JavaScript">
	if (document.aForm) {
		window.onbeforeunload = promptToLeave;
		var submitFunction = document.aForm.submit;
		document.aForm.submit = submitForm;
	}
	function promptToLeave() {
		// prevent duplicate prompt when typing into browser location bar
		// sometimes onbeforeunload event gets fired twice
		if (!window.prompted) {
			window.prompted = true;
			setTimeout("window.prompted = false;", 1);
			return "Changes may be lost if you do not save the form.";
		}
	}
	function submitForm() {
		window.onbeforeunload = "";
		submitFunction();
	}
</script>
</c:if>