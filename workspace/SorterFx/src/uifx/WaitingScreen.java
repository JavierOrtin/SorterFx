package uifx;


import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class WaitingScreen extends BorderPane {

	private Label fileLabel = new Label();

	
	public WaitingScreen() {
		Label titleLabel = new Label("Fetching matching pictures...");
		titleLabel.getStyleClass().add("label-title");
		VBox box = new VBox(10);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(titleLabel, fileLabel);
		setCenter(box);
	}
	
	void bindTask(Task<?> task) {
	    fileLabel.textProperty().bind(task.messageProperty());
	}
}
