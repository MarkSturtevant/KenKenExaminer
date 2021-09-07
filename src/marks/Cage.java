package marks;

import java.util.ArrayList;
import java.util.List;

public class Cage {

	List<Cell> cells;
	int cageID;
	int possibilities;
	boolean isFree;
	
	private static List<int[]> temp;
	
	public Cage(int id) {
		cells = new ArrayList<>();
		cageID = id;
	}
	
	public void addCell(Cell c) {
		cells.add(c);
		c.setColor(cageID);
	}
	
	public void setLabel(String number, String operand) {
		isFree = false;
		int value = Integer.valueOf(number);
		int size = GUIHandler.gameSize;
		Cell labelCell = cells.get(0);
		for (Cell c : cells)
			if (c.id < labelCell.id)
				labelCell = c;
		labelCell.setText(number + operand);
		// do not copy this code as it is imperfect.
		int reps = 0;
		if (cells.size() > 2) {
			List<Integer> paired = new ArrayList<>();
			for (Cell c1 : cells)
				if (!paired.contains(c1.id))
					for (Cell c2 : cells)
						if (!(paired.contains(c2.id) || c1.id % size == c2.id % size || c1.id / size == c2.id / size)) {
							reps++;
							paired.add(c1.id);
							paired.add(c2.id);
						}
		}
		possibilities = 0;
		temp = new ArrayList<>();
		switch(operand) {
		case "+":
			makeCombos(1, size + 1, 1, cells.size(), new int[0]);
			int sum, rep;
			for (int k = temp.size() - 1; k > -1; k--) {
				int[] numList = temp.get(k);
				sum = rep = 0;
				for (int i : numList)
					sum += i;
				if (sum == value) {
					for (int j = 1; j < numList.length; j++)
						if (numList[j] == numList[j - 1])
							rep++;
					if (rep <= reps) {
						System.out.print("[");
				    	for (int i : numList)
				    		System.out.print(i + " ");
				    	System.out.println("]");
				    	continue;
					}
				}
				temp.remove(k);
			}
			possibilities = temp.size();
			break;
		case "-":
			possibilities = size - value;
			break;
		case "*":
			makeCombos(1, size + 1, 1, cells.size(), new int[0]);
			int prod, rep2;
			for (int k = temp.size() - 1; k > -1; k--) {
				int[] numList = temp.get(k);
				prod = 1; rep2 = 0;
				for (int i : numList)
					prod *= i;
				if (prod == value) {
					for (int j = 1; j < numList.length; j++)
						if (numList[j] == numList[j - 1])
							rep2++;
					if (rep2 <= reps)
						continue;
				}
				temp.remove(k);
			}
			possibilities = temp.size();
			break;
		case "/":
			for (int i = size; i > 1; i--)
				for (int j = i - 1; j > 0; j--)
					if (i % j == 0 && i / j == value)
						possibilities++;
			break;
		}
		if (cells.size() == 3 && possibilities == 1 && (temp.get(0)[0] == temp.get(0)[1] || temp.get(0)[1] == temp.get(0)[2]))
			isFree = true;
		System.out.println("Cage: " + number + operand + ": " + possibilities + " possibilities");
	}
	
	private static void makeCombos(int min, int max, int sz, int depth, int[] vals){
	    //Guard against bad depth
	    if (depth < 0) return;
	    if (depth == 0)
	    	temp.add(vals);
	    else{
	        for(int i = min; i < max; i += sz){
	            int[] newVals = new int[vals.length + 1];
	            for(int z = 0; z < vals.length; z++) newVals[z] = vals[z];
	            newVals[vals.length] = i;
	            makeCombos(i, max, sz, depth - 1, newVals);
	        }
	    }
	}
	
}
