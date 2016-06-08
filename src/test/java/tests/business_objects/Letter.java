package tests.business_objects;

public class Letter {

	private String letterAddress;
	private String letterSubject;
	private String letterText;

	public Letter(String address, String subject, String text) {
		this.letterAddress = address;
		this.letterSubject = subject;
		this.letterText = text;
	}

	public String getLetterAddress() {
		return letterAddress;
	}

	public void setLetterAddress(String letterAddress) {
		this.letterAddress = letterAddress;
	}

	public String getLetterSubject() {
		return letterSubject;
	}

	public void setLetterSubject(String letterSubject) {
		this.letterSubject = letterSubject;
	}

	public String getLetterText() {
		return letterText;
	}

	public void setLetterText(String letterText) {
		this.letterText = letterText;
	}

}
