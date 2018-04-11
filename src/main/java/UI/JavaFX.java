package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("primary_view/sample.fxml"));
		stage.setTitle("Hello World");
		stage.setScene(new Scene(root, 300, 275));
		stage.show();
	}
}