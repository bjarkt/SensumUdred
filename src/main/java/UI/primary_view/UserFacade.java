package UI.primary_view;

import BLL.IBusiness;
import UI.IUserInterface;
import UI.JavaFX;
import UI.components.header.HeaderController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class UserFacade implements IUserInterface, Initializable {
	private static IBusiness business;

	private HeaderController headerController;

	@FXML
	private BorderPane canvas;

	public UserFacade(){
		headerController = new HeaderController();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		canvas.setTop(headerController.getView());


		JFXButton button = new JFXButton("Log in");
		button.getStyleClass().add("button-raised");

		canvas.setCenter(button);

	}

	@Override
	public void injectBusiness(IBusiness business) {
		UserFacade.business = business;
	}

	@Override
	public void startApplication(String[] args) {
		Application.launch(JavaFX.class, args);
	}
}