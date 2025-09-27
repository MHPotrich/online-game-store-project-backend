
public class CreditCard extends Payment {
	private int number;
	private int numberLength = 0;
	private int cvv;
	private int cvvLength = 0;
	private String brand;
	
	CreditCard(String p_brand) {
		switch(p_brand) {
		case "visa":
			this.numberLength = 8;
			this.cvvLength = 3;
			break;
		case "master":
			this.numberLength = 8;
			this.cvvLength = 3;
			break;
		default:
			this.numberLength = 0;
			this.cvvLength = 0;
		}
		
		this.brand = p_brand;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public int getCvv() {
		return this.cvv;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public boolean setNumber(int p_number) {
		int length = String.valueOf(p_number).length();
		
		if(length == numberLength) {
			this.number = p_number;
			return true;
		}
		
		return false;
	}
	
	public boolean setCvv(int p_cvv) {
		int length = String.valueOf(p_cvv).length();
		
		if(length == cvvLength) {
			this.cvv = p_cvv;
			return true;
		}
		
		return false;
	}
}
