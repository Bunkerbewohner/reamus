package reamus;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Control sidebar1;
    Control sidebar2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Reamus");
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.setHeight(600);

        Scene scene = new Scene(createLayout());
        scene.getStylesheets().add("styles/default.css");
        primaryStage.setScene(scene);

        center(primaryStage);
        primaryStage.show();
    }

    Pane createLayout() {
        BorderPane root = new BorderPane();

        root.setLeft(createSidebars());
        root.setCenter(createContent());

        return root;
    }

    Control createContent() {
        BorderPane contentPane = new BorderPane();
        contentPane.getStyleClass().add("content");
        ScrollPane contentContainer = new ScrollPane(contentPane);
        contentContainer.getStyleClass().add("content-frame");
        contentContainer.setFitToHeight(true);
        contentContainer.setFitToWidth(true);

        Button button = new Button("Collapse");
        button.setOnAction(e -> collapse(sidebar1));

        Button button2 = new Button("Expand");
        button2.setOnAction(e -> expand(sidebar1, 180));

        HBox hbox = new HBox();
        hbox.getChildren().add(button);
        hbox.getChildren().add(button2);

        contentPane.setCenter(hbox);

        return contentContainer;
    }

    Pane createSidebars() {
        BorderPane sidebarContainer = new BorderPane();

        StackPane sidebar1 = new StackPane();
        sidebar1.setMinWidth(180);
        sidebar1.getStyleClass().add("folders-sidebar");

        ScrollPane sidebar1Container = new ScrollPane(new BorderPane(sidebar1));
        sidebar1Container.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sidebar1Container.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sidebar1Container.setPrefWidth(180);
        sidebar1Container.setFitToHeight(true);
        sidebar1Container.setFitToWidth(true);
        sidebar1Container.setMinWidth(0);
        sidebar1Container.getStyleClass().add("folders-sidebar-frame");

        StackPane sidebar2 = new StackPane();
        sidebar2.setMinWidth(240);
        sidebar2.getStyleClass().add("docs-sidebar");

        ScrollPane sidebar2Container = new ScrollPane(new BorderPane(sidebar2));
        sidebar2Container.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sidebar2Container.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sidebar2Container.setPrefWidth(240);
        sidebar2Container.setFitToHeight(true);
        sidebar2Container.setFitToWidth(true);
        sidebar2Container.setMinWidth(0);
        sidebar2Container.getStyleClass().add("docs-sidebar-frame");

        sidebarContainer.setLeft(sidebar1Container);
        sidebarContainer.setCenter(sidebar2Container);

        this.sidebar1 = sidebar1Container;
        this.sidebar2 = sidebar2Container;

        return sidebarContainer;
    }

    void collapse(Control pane) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(400), new KeyValue(pane.prefWidthProperty(), 0, Interpolator.EASE_OUT))
        );
        timeline.play();
    }

    void expand(Control pane, Number width) {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(400), new KeyValue(pane.prefWidthProperty(), width, Interpolator.EASE_IN))
        );
        timeline.play();
    }

    void center(Stage stage) {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        double x = (bounds.getWidth() - stage.getMinWidth()) / 2;
        double y = (bounds.getHeight() - stage.getMinHeight()) / 2;
        stage.setX(x);
        stage.setY(y);
    }
}
