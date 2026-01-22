package model;

public enum SortingMode {
	YYYYMMDD(".*?(\\d{4})(\\d{2})(\\d{2}).*", "YYYYMMDD"),
	NATIVE_DATE(null, "File date attribute");	
	
	private String regexString;
	private String description;
	
	private SortingMode(String regex, String desc) {
		this.regexString = regex;
		this.description = desc;
	}
	
	@Override
	public String toString() {
		return this.description;
	}
	
	public String getRegExString() {	
		return this.regexString;
	}
}
