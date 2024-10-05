
import javafx.stage.*;
import controller.ViewLoader;
import javafx.application.Application;
import javafx.scene.image.Image;
import model.*;

public class InventoryManagementSystem extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage stage = new Stage();
        
        stage.setX(ViewLoader.X + 601);
        stage.setY(ViewLoader.Y);
        stage.getIcons().add(new Image("/image/inventory.jpeg"));
        ViewLoader.showStage(new Inventory(), "/view/MainView.fxml", "Inventory System", stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
