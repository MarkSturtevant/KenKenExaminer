package marks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;

public class Cell {

	public Rectangle rect;
	Label label;
	int id;
	
	public Cell(int id, double posX, double posY, double width) {
		this.id = id;
		rect = new Rectangle();
		rect.setLayoutX(posX);
		rect.setLayoutY(posY);
		rect.setWidth(width);
		rect.setHeight(width);
		rect.setFill(Color.WHITE);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(4);
		label = new Label();
		label.setFont(Font.font("Cambria", 18));
		label.setTextFill(Color.BLACK);
		label.setLayoutX(width / 4 + posX);
		label.setLayoutY(width / 4 + posY);
		label.setTextAlignment(TextAlignment.CENTER);
		label.setText("");
	}
	
	public void setText(String text) {
		label.setText(text);
	}
	
	public void setColor(int cageID) {
		rect.setFill(getColor(cageID));
	}
	
	private Color getColor(int cageID) {
		switch(cageID % 13) {
		case 0:
			return Color.rgb(255, 204, 204);
		case 1:
			return Color.rgb(255, 229, 204);
		case 2:
			return Color.rgb(255, 255, 204);
		case 3:
			return Color.rgb(229, 255, 204);
		case 4:
			return Color.rgb(204, 255, 204);
		case 5:
			return Color.rgb(204, 255, 229);
		case 6:
			return Color.rgb(204, 255, 255);
		case 7:
			return Color.rgb(204, 229, 255);
		case 8:
			return Color.rgb(204, 204, 255);
		case 9:
			return Color.rgb(229, 204, 255);
		case 10:
			return Color.rgb(255, 204, 255);
		case 11:
			return Color.rgb(255, 204, 229);
		case 12:
			return Color.rgb(224, 224, 224);
		}
		return null;
	}
	
}
