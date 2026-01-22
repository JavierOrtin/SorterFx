package model;

import java.io.File;

public class Error {
	
	private File file;
	private String year;
	private String month;
	private String day;
	private String cause;
	
	public Error(File file, String year, String month, String day, String cause) {
		this.file = file;
		this.year = year;
		this.month = month;
		this.day = day;
		this.cause = cause;
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
	public String getCause() {
		return this.cause;
	}
	
	/** Shows error as HTML table */
	@Override
	public String toString() {
		 return String.format("""
			        <html>
			          <body>
			            <table width='100%%'>
			              <tr>
			                <td align='left' width='50%%'>%s</td>
			                <td align='left' width='50%%'>%s-%s-%s</td>
			              </tr>
			              <tr>
			                <td colspan='2' align='left'>%s</td>
			              </tr>
			            </table>
			          </body>
			        </html>
			        """,
			        this.file.getPath(),
			        this.year, this.month, this.day,
			        this.cause);
	}

}
