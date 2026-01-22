package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import service.interf.UpdateReflect;


public class FileFetcher {
	
	
	public static SearchDTO fetchMatches(String sourceFolder, String targetFolder, String regEx,
			boolean recursive, UpdateReflect updater) {
		if(regEx == null) {
			return nativeAttribFetch(sourceFolder, targetFolder, recursive, updater);
		}
		SearchDTO dto = new SearchDTO();
		File sourceDir = new File(sourceFolder);
		File[] sourceFiles = sourceDir.listFiles();
		Pattern pattern = Pattern.compile(regEx);
		for(File file : sourceFiles) {
			if(file.isDirectory()) {
				if(recursive) {
					recursiveFetchMatches(file.getPath(), targetFolder, pattern, dto, updater);					
				}
			} else { //not a directory
				updater.updateStatus(file);
				notADirectory(pattern, targetFolder, file, dto);
			}
		}
		return dto;
	}
	
	private static void recursiveFetchMatches(String sourceFolder, String targetFolder, Pattern pattern,
			SearchDTO dto, UpdateReflect updater) {
		File sourceDir = new File(sourceFolder);
		File[] sourceFiles = sourceDir.listFiles();
		for (File file : sourceFiles) {
			if(file.isDirectory()) {
				recursiveFetchMatches(file.getPath(), targetFolder, pattern, dto, updater);
			} else {
				updater.updateStatus(file);
				notADirectory(pattern, targetFolder, file, dto);
			}
		}
	}
	
	private static void notADirectory(Pattern pattern, String targetFolder, File file, SearchDTO dto) {
		Matcher matcher = pattern.matcher(file.getName());
		if(matcher.matches()) {
			String year = matcher.group(1);
			String month = matcher.group(2);
			String day = matcher.group(3);
			
			try {
				copyFile(file, targetFolder, year, month);
				dto.addMatch(file, year,month,day);
			}catch(Exception e) {
				dto.addError(file, year, month, day, e.getMessage());
			}
		} else {
			dto.addLeftOut(file);
		}
	}
	
	private static void copyFile(File sourceFile, String targetFolder, String year, String month) throws IOException {
		Path targetPath = Paths.get(targetFolder, year,month);
		if(!Files.exists(targetPath)) {
			Files.createDirectories(targetPath);
		}
		targetPath = targetPath.resolve(sourceFile.getName());
		if(Files.exists(targetPath)) {
			throw new RuntimeException("File already exists");
		}
		Path sourcePath = Paths.get(sourceFile.getPath());
		Files.copy(sourcePath, targetPath);
	}
	
	private static SearchDTO nativeAttribFetch(String sourceFolder, String targetFolder,
			boolean recursive, UpdateReflect updater) {
		File[] sourceFiles = new File(sourceFolder).listFiles();
		SearchDTO dto = new SearchDTO();
		for(File file : sourceFiles) {
			if(file.isDirectory()) {
				if(recursive) {
					recursiveNative(file.getPath(), targetFolder, dto, updater);
				}
			} else {
				//not a directory
				updater.updateStatus(file);
				nativeNotADirectory(file, targetFolder, dto);
			}
		}
		return dto;		
	}
	
	private static void recursiveNative(String sourceFolder, String targetFolder,
			SearchDTO dto, UpdateReflect updater) {
		File[] sourceFiles = new File(sourceFolder).listFiles();
		for(File file : sourceFiles) {
			if(file.isDirectory()) {
				recursiveNative(file.getPath(), targetFolder, dto, updater);
			} else {
				//not a directory
				updater.updateStatus(file);
				nativeNotADirectory(file, targetFolder, dto);
			}
		}
	}
	
	private static void nativeNotADirectory(File file, String targetFolder, SearchDTO dto ) {
		try {					
			BasicFileAttributes attrs = Files.readAttributes(Paths.get(file.getPath()), BasicFileAttributes.class);
			LocalDateTime date = LocalDateTime.ofInstant(attrs.creationTime().toInstant(), ZoneId.systemDefault());
			String year = Integer.toString(date.getYear());
			String month = Integer.toString(date.getMonthValue());
			String day = Integer.toString(date.getDayOfMonth());
			try {
				copyFile(file, targetFolder, year, month);
				dto.addMatch(file, year, month, day);
			}catch(Exception e) {
				dto.addError(file, year, month, day, "Copying error: " + e.getMessage());
			}
		} catch(Exception e) {
			dto.addError(file, "X", "X", "X", "Could not fetch date: " + e.getMessage());
		}
	}
	
}
