package src;

import java.util.ArrayList;
import java.util.Hashtable;

public class BoardRows {
	//creates rows for the board -> one in every direction
	//NEEDS to be created after the board otherwise it won't work
	
	ArrayList<ArrayList<String>> horizontal = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> topLeft = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<String>> topRight = new ArrayList<ArrayList<String>>();
	
	public BoardRows() {
		//create the rows
		
		//horizontal rows
		for (char ch='A'; ch <= 'I'; ch++) {
			String letterCode = Character.toString(ch);
			ArrayList<String> row = new ArrayList<String>();
			for (int j = 0; j < 10; j++) {
				if (Board.hashBoard.containsKey((letterCode+j))) {
					row.add(letterCode+j);
				}
			}
			horizontal.add(row);
		}
		
		//starting from top left (going to bottom right)
		for (int j = 0; j < 10; j++) {
			ArrayList<String> row = new ArrayList<String>();
			for (char ch='A'; ch <= 'I'; ch++) {
				String letterCode = Character.toString(ch);
				if (Board.hashBoard.containsKey((letterCode+j))) {
					row.add(letterCode+j);
				}
			}
			topLeft.add(row);
		}
		
		//starting from top right (going to bottom left)
		
		char start = 'I';
		for (int i = 13; i >= 5; i--) {
			ArrayList<String> row = new ArrayList<String>();
			for (int j = 0; j < 9; j++) {
				String lettercode = Character.toString(start);
				int temp = i-j;
				if (Board.hashBoard.containsKey((lettercode+temp))) {
					row.add(lettercode+temp);
				}
				start = (char) (start -1);
			}
			start = 'I';
			topRight.add(row);
		}
		
		
		
	}
	
	public boolean sameRowThree(Hexagon one, Hexagon two, Hexagon three) {
		boolean sameRow = false;
		
		for (int i = 0; i < horizontal.size(); i++) {
			if (sameRow) {
				break;
			}
			if (horizontal.get(i).contains(one.code) && horizontal.get(i).contains(two.code) && horizontal.get(i).contains(three.code)) {
				sameRow = true;
				
			}
		}
		
		for (int i = 0; i < topLeft.size(); i++) {
			if (sameRow) {
				break;
			}
			if (topLeft.get(i).contains(one.code) && topLeft.get(i).contains(two.code) && topLeft.get(i).contains(three.code)) {
				sameRow = true;
				
			}
		}
		
		for(int i = 0; i < topRight.size(); i++) {
			if (sameRow) {
				break;
			}
			if (topRight.get(i).contains(one.code) && topRight.get(i).contains(two.code) && topRight.get(i).contains(three.code)) {
				sameRow = true;
			}
			System.out.println(sameRow);
			
		}
		return sameRow;
	}
	
	public int direction(Hexagon first, Hexagon moveTo) {
		boolean sameRow = false;
		int direction = 0;
		
		for (int i = 0; i < horizontal.size(); i++) {
			if (sameRow) {
				break;
			}
			if (horizontal.get(i).contains(first.code) && horizontal.get(i).contains(moveTo.code)) {
				String numberFirst = first.code.substring(1);
				String numberMoveTo = moveTo.code.substring(1);
				
				int numberOne = Integer.parseInt(numberFirst);
				int numberTwo = Integer.parseInt(numberMoveTo);
				
				if (numberOne>numberTwo) {
					direction = 1;
				}
				else {
					direction = 4;
				}
				
				sameRow = true;
			}
		}
		
		for (int i = 0; i < topLeft.size(); i++) {
			if (sameRow) {
				break;
			}
			if (topLeft.get(i).contains(first.code) && topLeft.get(i).contains(moveTo.code)) {
				
					char letterFirst = first.code.charAt(0);
					char letterMoveTo = moveTo.code.charAt(0);
					
					if (letterFirst>letterMoveTo) {
						direction = 5;
					}
					else {
						direction = 2;
					}
				
			}
		}
		
		for(int i = 0; i < topRight.size(); i++) {
			if (sameRow) {
				break;
			}
			if (topRight.get(i).contains(first.code) && topRight.get(i).contains(moveTo.code)) {
				String numberFirst = first.code.substring(1);
				String numberMoveTo = moveTo.code.substring(1);
				
				int numberOne = Integer.parseInt(numberFirst);
				int numberTwo = Integer.parseInt(numberMoveTo);
				
				if (numberOne>numberTwo) {
					direction = 6;
				}
				else {
					direction = 3;
				}
				
				sameRow = true;
			}
			
		}
		
		return direction;
	}
	
	public boolean sideways(Hexagon first, Hexagon second, Hexagon moveTo) {
		if (this.direction(first, second) == 1 & this.direction(first, moveTo) ==4 || this.direction(first, second) == 4 & this.direction(first, moveTo) ==1) {
			return false;
		}
		else if (this.direction(first, second) == 2 & this.direction(first, moveTo) ==5 || this.direction(first, second) == 5 & this.direction(first, moveTo) ==2) {
			return false;
		}
		else if(this.direction(first, second) == 3 & this.direction(first, moveTo) ==6 || this.direction(first, second) == 6 & this.direction(first, moveTo) ==3) {
			return false;
		}
		else if (this.direction(first, second) != this.direction(first, moveTo)){
			return true;
		}
		else
		return false;
	}
	
	//if it's sideways with two!!
	public boolean twoFree(Hexagon first, Hexagon second, Hexagon moveTo, Hashtable<String, Hexagon> board) {
		int direction = direction(first, moveTo);
		//System.out.println("direction is " + direction);
		char letterFirst = first.code.charAt(0);
		char letterSecond = second.code.charAt(0);
		
		String letterFirstSt = first.code.substring(0,1);
		String letterSecondSt = second.code.substring(0,1);
		
		String numberFirst = first.code.substring(1);
		String numberSecond = second.code.substring(1);
		int numberOne = Integer.parseInt(numberFirst);
		int numberTwo = Integer.parseInt(numberSecond);
		
		//when the value is one bigger
		int numberOnePlus = numberOne + 1;
		int numberTwoPlus = numberTwo + 1;
		char letterOnePlus = (char) (letterFirst +1);
		char letterTwoPlus = (char) (letterSecond +1);
		String letterOnePlusSt = Character.toString(letterOnePlus);
		String letterTwoPlusSt = Character.toString(letterTwoPlus);
		
		
		//when the value is one smaller
		int numberOneMinus = numberOne - 1;
		int numberTwoMinus = numberTwo - 1;
		char letterOneMinus = (char) (letterFirst -1);
		char letterTwoMinus = (char) (letterSecond -1);
		String letterOneMinusSt = Character.toString(letterOneMinus);
		String letterTwoMinusSt = Character.toString(letterTwoMinus);
		
		
		if (direction ==1) {
			if(board.get(letterFirstSt+numberOneMinus).empty && board.get(letterSecondSt+numberTwoMinus).empty) {
				System.out.println("ONE TRUE");
				return true;
			}	
		}
		
		else if (direction ==2) {
			if(board.get(letterOnePlusSt + numberOne).empty && board.get(letterTwoPlusSt+ numberTwo).empty) {
				System.out.println("TWO TRUE");
				return true;
			}	
		}
		
		else if(direction ==3) {
			if(board.get(letterOnePlusSt + numberOnePlus).empty && board.get(letterTwoPlusSt+numberTwoPlus).empty) {
				System.out.println("THREE TRUE");
				return true;
			}	
			
		}
		else if (direction ==4) {
			if(board.get(letterFirstSt+numberOnePlus).empty && board.get(letterSecondSt+numberTwoPlus).empty) {
				System.out.println("FOUR TRUE");
				return true;
			}
		}
		else if (direction ==5) {
			if(board.get(letterOneMinusSt + numberOne).empty && board.get(letterTwoMinusSt + numberTwo).empty) {
				System.out.println("FIVE TRUE");
				return true;
			}
		}
		else if (direction ==6) {
			if(board.get(letterOneMinusSt + numberOneMinus).empty && board.get(letterTwoMinusSt + numberTwoMinus).empty) {
				System.out.println("SIX TRUE");
				return true;
			}
			
		}
		
		System.out.println("FALSE SIDE");
		return false;
	}
	
	//if it's sideways with three
	public boolean threeFree(Hexagon first, Hexagon second, Hexagon third, Hexagon moveTo, Hashtable<String, Hexagon> board) {
		int direction = direction(first, moveTo);
		//System.out.println("direction is " + direction);
		char letterFirst = first.code.charAt(0);
		char letterSecond = second.code.charAt(0);
		char letterThird = third.code.charAt(0);
		
		String letterFirstSt = first.code.substring(0,1);
		String letterSecondSt = second.code.substring(0,1);
		String letterThirdSt = third.code.substring(0,1);
		
		String numberFirst = first.code.substring(1);
		String numberSecond = second.code.substring(1);
		String numberThird = third.code.substring(1);
		int numberOne = Integer.parseInt(numberFirst);
		int numberTwo = Integer.parseInt(numberSecond);
		int numberThree = Integer.parseInt(numberThird);
		
		//when the value is one bigger
		int numberOnePlus = numberOne + 1;
		int numberTwoPlus = numberTwo + 1;
		int numberThreePlus = numberThree + 1;
		char letterOnePlus = (char) (letterFirst +1);
		char letterTwoPlus = (char) (letterSecond +1);
		char letterThreePlus = (char) (letterThird + 1);
		String letterOnePlusSt = Character.toString(letterOnePlus);
		String letterTwoPlusSt = Character.toString(letterTwoPlus);
		String letterThreePlusSt = Character.toString(letterThreePlus);
		
		
		//when the value is one smaller
		int numberOneMinus = numberOne - 1;
		int numberTwoMinus = numberTwo - 1;
		int numberThreeMinus = numberThree -1;
		char letterOneMinus = (char) (letterFirst -1);
		char letterTwoMinus = (char) (letterSecond -1);
		char letterThreeMinus = (char) (letterThird -1);
		String letterOneMinusSt = Character.toString(letterOneMinus);
		String letterTwoMinusSt = Character.toString(letterTwoMinus);
		String letterThreeMinusSt = Character.toString(letterThreeMinus);
		
		
		if (direction ==1) {
			if(board.get(letterFirstSt+numberOneMinus).empty && board.get(letterSecondSt+numberTwoMinus).empty && board.get(letterThirdSt + numberThreeMinus).empty) {
				System.out.println("ONE TRUE");
				return true;
			}	
		}
		
		else if (direction ==2) {
			if(board.get(letterOnePlusSt + numberOne).empty && board.get(letterTwoPlusSt+ numberTwo).empty && board.get(letterThreePlusSt + numberThree).empty) {
				System.out.println("TWO TRUE");
				return true;
			}	
		}
		
		else if(direction ==3) {
			if(board.get(letterOnePlusSt + numberOnePlus).empty && board.get(letterTwoPlusSt+numberTwoPlus).empty && board.get(letterThreePlusSt + numberThreePlus).empty) {
				System.out.println("THREE TRUE");
				return true;
			}	
			
		}
		else if (direction ==4) {
			if(board.get(letterFirstSt+numberOnePlus).empty && board.get(letterSecondSt+numberTwoPlus).empty && board.get(letterThirdSt + numberThreePlus).empty) {
				System.out.println("FOUR TRUE");
				return true;
			}
		}
		else if (direction ==5) {
			if(board.get(letterOneMinusSt + numberOne).empty && board.get(letterTwoMinusSt + numberTwo).empty && board.get(letterThreeMinusSt + numberThree).empty) {
				System.out.println("FIVE TRUE");
				return true;
			}
		}
		else if (direction ==6) {
			if(board.get(letterOneMinusSt + numberOneMinus).empty && board.get(letterTwoMinusSt + numberTwoMinus).empty && board.get(letterThreeMinusSt + numberThreeMinus).empty) {
				System.out.println("SIX TRUE");
				return true;
			}
			
		}
		
		System.out.println("FALSE SIDE");
		return false;
	}
	
	public String adjacentDirection(Hexagon moveTo, int direction ) {
		char letterMoveTo = moveTo.code.charAt(0);
		String letterMoveToSt = moveTo.code.substring(0,1);
		String numberMoveToSt = moveTo.code.substring(1);
		int numberMoveTo = Integer.parseInt(numberMoveToSt);
		
		//when the value is one bigger
		int numberMoveToPlus = numberMoveTo + 1;
		char letterMoveToPlus = (char) (letterMoveTo +1);
		String letterMoveToPlusSt = Character.toString(letterMoveToPlus);
		
		
		//when the value is one smaller
		int numberMoveToMinus = numberMoveTo - 1;
		char letterMoveToMinus = (char) (letterMoveTo -1);
		String letterMoveToMinusSt = Character.toString(letterMoveToMinus);
		
		if (direction ==1) {
			return letterMoveToSt+numberMoveToMinus;
		}
		if (direction ==2) {
			return letterMoveToPlusSt + numberMoveTo;
		}
		if (direction ==3) {
			return letterMoveToPlusSt + numberMoveToPlus;
		}
		if (direction ==4) {
			return letterMoveToSt+numberMoveToPlus;
		}
		if (direction ==5) {
			return letterMoveToMinusSt + numberMoveTo;
		}
		if (direction ==6) {
			return letterMoveToMinusSt + numberMoveToMinus;
		}
		return null;
	}
}
