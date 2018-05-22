package UI;

import BLL.IBusiness;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class JavaFX extends Application {

	FXMLLoader loader;

	@Override
	public void start(Stage stage) throws Exception {
		loader = new FXMLLoader(getClass().getResource("primary_view/canvas.fxml"));
		Parent root = loader.load();
		stage.setTitle("Sensum Udred");
		stage.setScene(new Scene(root, 870, 616));
		stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("img/logo.png")));
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		((IUserInterface)loader.getController()).shutdown();
	}
}