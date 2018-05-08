package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class JavaFX extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("primary_view/canvas.fxml"));
		stage.setTitle("Sensum Udred");
		stage.setScene(new Scene(root, 870, 616));
		stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("img/logo.png")));
		stage.show();
	}
}