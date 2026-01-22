package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchDTO {
	
	private List<Match> matches;
	private List<Error> errors;	
	private List<File> leftOut;
	
	public SearchDTO() {
		this.matches = new ArrayList<Match>();
		this.errors = new ArrayList<Error>();		
		this.leftOut = new ArrayList<File>();
	}
	
	public void addMatch(File file, String year, String month, String day) {
		matches.add(new Match(file, year, month, day));
	}
	
	public void addError(File file, String year, String month, String day, String cause) {
		errors.add(new Error(file, year, month, day, cause));
	}
	
	public void addLeftOut(File file) {
		this.leftOut.add(file);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("---MATCHES---\n");
		for(Match match : matches) {
			sb.append(String.format("%s  %s-%s-%s\n",
					match.getFile().getName(),
					match.getYear(),
					match.getMonth(),
					match.getDay()));
		}
		sb.append("----ERRORS----\n");
		for(Error error : errors) {
			sb.append(String.format("%s  %s-%s-%s \t %s\n",
					error.getFile().getName(),
					error.getYear(),
					error.getMonth(),
					error.getDay(),
					error.getCause()));
		}
		sb.append("---LEFT OUT---\n");
		for (File file : leftOut) {
			sb.append(file.getName() + "\n");
		}
		return sb.toString();
	}
	
	public List<Match> getMatches() {
		return new ArrayList<Match>(this.matches);
	}
	
	public List<Error> getErrors() {
		return new ArrayList<Error>(this.errors);
	}
	
	public List<File> getLeftOut() {
		return new ArrayList<File>(this.leftOut);
	}
	
	public int getNumMatches() {
		return this.matches.size();
	}
	
	public int getNumErrors() {
		return this.errors.size();
	}
	
	public int getNumLeftOut() {
		return this.leftOut.size();
	}
	
}
