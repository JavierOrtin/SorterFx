package uifx.util;

import javafx.scene.control.Alert;

public class PromptUtil {
	
	public static void exceptionPrompt(Throwable e) {
	    Alert alert = new Alert(Alert.AlertType.ERROR);
	    alert.setTitle("Error");
	    alert.setHeaderText(null);
	    alert.setContentText("Could not copy files:\n" + e.getMessage());
	    alert.showAndWait();
	}
}
