package it.papersoft.viewmodels;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;

import it.papersoft.utils.VerifyRecaptcha;

public class IndexViewModel {

	private String recaptchaResponse;
	private String submitError = "";
	
	@Command
    @NotifyChange("submitError")
    public void submit() throws Exception {
		if (recaptchaResponse != null) {
			boolean verified = VerifyRecaptcha.verify(recaptchaResponse);
			if (!verified) {
				submitError = "ko";
				// Reset Recaptcha
				Clients.evalJavaScript("grecaptcha.reset();");
			}
			else {
				submitError = "ok";
				// DO YOUR STUFF HERE
			}
		}
		else
			submitError = "ko";
	}
	

	public String getSubmitError() {
		return submitError;
	}

	public void setSubmitError(String submitError) {
		this.submitError = submitError;
	}

	public String getRecaptchaResponse() {
		return recaptchaResponse;
	}
	public void setRecaptchaResponse(String recaptchaResponse) {
		this.recaptchaResponse = recaptchaResponse;
	}
}
