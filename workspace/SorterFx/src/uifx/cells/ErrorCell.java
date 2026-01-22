package uifx.cells;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Error;

public class ErrorCell extends ListCell<Error> {

    private final Label pathLabel = new Label();
    private final Label dateLabel = new Label();
    private final Label causeLabel = new Label();

    private final HBox header = new HBox(10);
    private final VBox container = new VBox(4);

    public ErrorCell() {
        // Header: path (izq) + date (der)
        HBox.setHgrow(pathLabel, Priority.ALWAYS);
        pathLabel.setMaxWidth(Double.MAX_VALUE);

        dateLabel.getStyleClass().add("error-date");
        causeLabel.getStyleClass().add("error-cause");

        causeLabel.setWrapText(true);

        header.getChildren().addAll(pathLabel, dateLabel);
        container.getChildren().addAll(header, causeLabel);
    }

    @Override
    protected void updateItem(Error item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            pathLabel.setText(item.getFile().getPath());
            dateLabel.setText(
                String.format("%s-%s-%s",
                    item.getYear(),
                    item.getMonth(),
                    item.getDay())
            );
            causeLabel.setText(item.getCause());

            setGraphic(container);
        }
    }
}
