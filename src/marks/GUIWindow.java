package marks;

import javafx.application.Application;
import javafx.stage.Stage;

public class GUIWindow extends Application {
	
	public static Stage stage;
	
	public static void lauchGui() {
		launch();
	}
	
	@Override
	public void start(Stage s) throws Exception {
		stage = s;
		stage.setTitle("KenKen Examiner");
		GUIHandler.init();
		stage.show();
	}
	
	public void init() {}
	public void stop() {}
}
