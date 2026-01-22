package model;

import java.io.File;

public class Match {
	
	private File file;
	private String year;
	private String month;
	private String day;
	
	public Match(File file, String year, String month, String day) {
		this.file = file;
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public File getFile() {
		return file;
	}
	public String getYear() {
		return year;
	}
	public String getMonth() {
		return month;
	}
	public String getDay() {
		return day;
	}
	
	@Override
	public String toString() {
		return this.getFile().getPath();
		
		/*
		return String.format("""
				<html>
				  <body>
				    <table width='100%%'>
				      <tr>
				        <td align='left' width='90%%'>%s</td>
				        <td align='right' width='10%%'>%s-%s-%s</td>
				      </tr>
				    </table>
				  </body>
				</html>
				""",
				this.file.getPath(),
				this.day,
				this.month,
				this.year); */
	}

}
