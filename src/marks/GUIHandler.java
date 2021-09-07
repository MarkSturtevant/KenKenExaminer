package marks;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;

public class GUIHandler {
	
	static Cell[] cells;
	static List<Cage> cages;
	static Pane p;
	static double cellWidth;
	static int gameSize;
	static Cage currentCage;
	static int curCageId;
	static String valueInput;
	
	static Label infoLabel;

	public static void init() {
		gameSize = 6;
		
		p = new Pane();
		Scene sc = new Scene(p, 900, 600);
		currentCage = null;
		sc.setOnKeyPressed(e -> {
			onKeyPress(e.getText());
		});
		sc.setOnMouseClicked(e -> {
			onMouseClick(e.getSceneX(), e.getSceneY());
		});
		infoLabel = new Label();
		infoLabel.setFont(Font.font("Cambria", 22));
		infoLabel.setTextFill(Color.BLACK);
		infoLabel.setLayoutX(260);
		infoLabel.setLayoutY(560);
		infoLabel.setTextAlignment(TextAlignment.CENTER);
		reset();
		GUIWindow.stage.setScene(sc);
	}
	
	public static void reset() {
		cellWidth = 500 / gameSize;
		cells = new Cell[gameSize * gameSize];
		cages = new ArrayList<>();
		curCageId = 0;
		valueInput = "";
		infoLabel.setText("");
		currentCage = null;
		p.getChildren().clear();
		p.getChildren().add(infoLabel);
		for (int i = 0; i < gameSize * gameSize; i++) {
			cells[i] = new Cell(i, (i % gameSize) * cellWidth + 200, (i / gameSize) * cellWidth + 50, cellWidth);
			p.getChildren().addAll(cells[i].rect, cells[i].label);
		}
	}
	
	public static void onMouseClick(double x, double y) {
		if (x < 200 || x > 700 || y < 50 || y > 550)
			return;
		int id = ((int) ((x - 200) / cellWidth)) + ((int) ((y - 50) / cellWidth) * gameSize);
		if (!cells[id].rect.getFill().equals(Color.WHITE))
			return;
		if (currentCage == null) {
			currentCage = new Cage(curCageId++);
			cages.add(currentCage);
			valueInput = "";
		}
		currentCage.addCell(cells[id]);
	}
	
	public static void onKeyPress(String key) {
		switch(key) {
		case "6": case "7": case "8": case "9": 
			if (cages.size() == 0) {
				gameSize = Integer.valueOf(key);
				reset();
				break;
			}
		case "0": case "1": case "2": 
		case "3": case "4": case "5": 
			valueInput += key;
			break;
		case "a":
			currentCage.setLabel(valueInput, "+");
			currentCage = null;
			break;
		case "s":
			currentCage.setLabel(valueInput, "-");
			currentCage = null;
			break;
		case "d":
			currentCage.setLabel(valueInput, "*");
			currentCage = null;
			break;
		case "f":
			currentCage.setLabel(valueInput, "/");
			currentCage = null;
			break;
		case "l":
			int cageCount = 0, free = gameSize * gameSize;
			int[] size = new int[4], pos = new int[6];
			size[0] = size[1] = size[2] = size[3] = pos[0] = pos[1] = pos[2] = pos[3] = pos[4] = pos[5] = 0;
			for (Cage cg : cages) {
				if (cg.isFree)
					continue;
				cageCount++;
				int cellSize = cg.cells.size();
				size[cellSize > 5 ? 3 : cellSize - 2]++;
				free -= cellSize;
				pos[cg.possibilities > 6 ? 5 : cg.possibilities - 1] += cellSize;
			}
			infoLabel.setText(cageCount + " | " + free + " | " + size[0] + " | " + size[1] + " | " + size[2] + " | " + size[3]
					 + " | " + pos[0] + " | " + pos[1] + " | " + pos[2] + " | " + pos[3] + " | " + pos[4] + " | " + pos[5]);
			break;
		case "p":
			reset();
			break;
		}
	}
	
}
