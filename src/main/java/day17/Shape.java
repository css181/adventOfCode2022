package day17;

public abstract class Shape {

	protected char[][] peice;
	
	
	protected char[][] getPeice() {
		return peice;
	}

	protected int getHeight() {
		return peice.length;
	}

	protected int getWidth() {
		return peice[0].length;
	}

	@Override
    public String toString() {
		String print = "";
		for(int row=0; row<peice.length; row++) {
			for(int col=0; col<peice[row].length; col++) {
				print+=peice[row][col];
			}
			print+="\n";
		}
		print=print.substring(0, print.length()-1);//remove last crlf
		return print;
    } 
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Shape)) { return false; }
        Shape other = (Shape) obj;

        if(!this.getClass().getName().equals(other.getClass().getName())) { return false; }
        
        return true;
    }

	public Chamber addShapeToGrid(Chamber chamber) {
		Chamber newChamber = copyExistingGridShiftingDownForNewShape(this, chamber);
		addShapeToNewChamber(this, newChamber);
		return newChamber;
	}
	

	private void addShapeToNewChamber(Shape shape, Chamber newChamber) {
		for(int row=0; row<shape.getHeight(); row++) {
			for(int col=0; col<9; col++) {
				if(col==0 || col==8) {
					newChamber.grid[row][col] = new ChamberTile('|');
				}
				else if(col==1 || col==2) {
					newChamber.grid[row][col] = new ChamberTile('.');
				}
				else if(col>(shape.getWidth()+2)) {
					newChamber.grid[row][col] = new ChamberTile('.');
				} else {
					if(shape.getPeice()[row][col-3]=='#') {
						if(col<newChamber.leftMostShapeIndex) {
							newChamber.leftMostShapeIndex=col;
						}else if(col>newChamber.rightMostShapeIndex) {
							newChamber.rightMostShapeIndex=col;
						}
						newChamber.grid[row][col] = new ChamberTile('#', true);
					}else {
						newChamber.grid[row][col] = new ChamberTile('.');
					}
				}
			}
		}
	}

	private Chamber copyExistingGridShiftingDownForNewShape(Shape shape, Chamber chamber) {
		getChamberToHaveHighestRockIndex3(chamber);
		Chamber newChamber = new Chamber();
		int newHeight = chamber.grid.length+shape.getHeight();
		newChamber.lowestRockIndex+=shape.getHeight();
		newChamber.grid = new ChamberTile[newHeight][9];
		int oldRow=0;
		for(int row=shape.getHeight(); row<newHeight; row++) {
			for(int col=0; col<9; col++) {
				newChamber.grid[row][col] = chamber.grid[oldRow][col];
			}
			oldRow++;
		}
		return newChamber;
	}

	private void getChamberToHaveHighestRockIndex3(Chamber chamber) {
		Chamber temp = new Chamber();
		while (chamber.lowestRockIndex<3) {
			temp.grid = new ChamberTile[chamber.grid.length+1][chamber.grid[0].length];
			for(int col=0; col<9; col++) {
				temp.grid[0][col] = new ChamberTile();
			}
			temp.grid[0][0].value='|';
			temp.grid[0][8].value='|';
			for(int row=0; row<chamber.grid.length; row++) {
				for(int col=0; col<9; col++) {
					temp.grid[row+1][col] = chamber.grid[row][col];
				}
			}
			chamber.lowestRockIndex++;
			chamber.grid=temp.grid;
		}
		while (chamber.lowestRockIndex>3) {
			temp.grid = new ChamberTile[chamber.grid.length-1][chamber.grid[0].length];
			for(int row=1; row<chamber.grid.length; row++) {
				for(int col=0; col<9; col++) {
					temp.grid[row-1][col] = chamber.grid[row][col];
				}
			}
			chamber.lowestRockIndex--;
			chamber.grid=temp.grid;
		}
	}

	//returns true we completed a move down, return false if we are resting on something.
	public boolean processOneMove(Character character, Chamber chamber) {
		switch (character) {
		case '<': 
			//Try to move LEFT
			if(areAllSpotsLeftOfShapeEmpty(chamber)) {
				if(chamber.leftMostShapeIndex>1) {
					moveShapeLeft(chamber);
				}
			}
			break;
		case '>':
			//Try to move RIGHT
			if(areAllSpotsRightOfShapeEmpty(chamber)) {
				if(chamber.rightMostShapeIndex<7) {
					moveShapeRight(chamber);
				}
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected move direction value: " + character);
		}
		
		//Try to move DOWN
		if(areAllSpotsBelowShapeEmpty(chamber)) {
			moveShapeDown(chamber);
			return true;
		}else {
			resetAllShapeTiles(chamber);
			return false;
		}
	}
	

	private void resetAllShapeTiles(Chamber chamber) {
		//TODO optimize how deep to look better later
		for(int row=chamber.grid.length-1; row>=0; row--) {
			for(int col=1; col<=7; col++) {
				ChamberTile curTile = chamber.grid[row][col];
				if(curTile.isCurrentShape && row<chamber.lowestRockIndex) {
					chamber.lowestRockIndex=row;
				}
				curTile.isCurrentShape=false;
			}
		}
		chamber.leftMostShapeIndex=Chamber.DEFAULT_LEFT;
		chamber.rightMostShapeIndex=Chamber.DEFAULT_RIGHT;
	}

	private void moveShapeLeft(Chamber chamber) {
		//TODO optimize how deep to look better later
		int endHeightSearch = Math.max(chamber.grid.length-10, chamber.grid.length-1);
		for(int row=0; row<endHeightSearch; row++) {
			for(int col=1; col<=6; col++) {
				if(chamber.grid[row][col+1].isCurrentShape) {
					chamber.grid[row][col] = new ChamberTile(chamber.grid[row][col+1].value, true);
					chamber.grid[row][col+1] = new ChamberTile();
					if(chamber.leftMostShapeIndex>(col)) {
						chamber.leftMostShapeIndex=col;
					}
					if(!isAnyShapeRightOfThis(col, endHeightSearch, chamber)) {
						chamber.rightMostShapeIndex=col;
					}
				}
			}
		}
	}
	private boolean isAnyShapeRightOfThis(int col, int endHeightSearch, Chamber chamber) {
		//TODO optimize how deep to look better later
		for(int row=0; row<endHeightSearch; row++) {
			if(chamber.grid[row][col+1].isCurrentShape) {
				return true;
			}
		}
		return false;
	}

	private void moveShapeRight(Chamber chamber) {
		//TODO optimize how deep to look better later
		int endHeightSearch = Math.max(chamber.grid.length-10, chamber.grid.length-1);
		for(int row=0; row<endHeightSearch; row++) {
			for(int col=7; col>=2; col--) {
				if(chamber.grid[row][col-1].isCurrentShape) {
					chamber.grid[row][col] = new ChamberTile(chamber.grid[row][col-1].value, true);
					chamber.grid[row][col-1] = new ChamberTile();
					if(chamber.rightMostShapeIndex<(col)) {
						chamber.rightMostShapeIndex=col;
					}
					if(!isAnyShapeLeftOfThis(col, endHeightSearch, chamber)) {
						chamber.leftMostShapeIndex=col;
					}
				}
			}
		}
	}
	private boolean isAnyShapeLeftOfThis(int col, int endHeightSearch, Chamber chamber) {
		//TODO optimize how deep to look better later
		for(int row=0; row<endHeightSearch; row++) {
			if(chamber.grid[row][col-1].isCurrentShape) {
				return true;
			}
		}
		return false;
	}
	
	private void moveShapeDown(Chamber chamber) {
		for(int row=chamber.grid.length-1; row>=1; row--) {
			for(int col=1; col<=7; col++) {
				if(chamber.grid[row-1][col].isCurrentShape) {
					chamber.grid[row][col] = new ChamberTile(chamber.grid[row-1][col].value, true);
					chamber.grid[row-1][col] = new ChamberTile();
				}
			}
		}
	}

	private boolean areAllSpotsBelowShapeEmpty(Chamber chamber) {
		//TODO optimize what to look through later
		for(int row=chamber.grid.length-1; row>0; row--) {
			for(int col=1; col<=7; col++) {
				ChamberTile curTile = chamber.grid[row][col];
				if(curTile.isCurrentShape) {
					ChamberTile belowTile = chamber.grid[row+1][col];
					if((belowTile.value=='#') && !belowTile.isCurrentShape) {
						return false;
					}
				}
			}
		}
		return true;
	}
	private boolean areAllSpotsRightOfShapeEmpty(Chamber chamber) {
		//TODO optimize what to look through later
		for(int row=chamber.grid.length-1; row>0; row--) {
			for(int col=1; col<=7; col++) {
				ChamberTile curTile = chamber.grid[row][col];
				if(curTile.isCurrentShape) {
					ChamberTile rightTile = chamber.grid[row][col+1];
					if((rightTile.value=='#') && !rightTile.isCurrentShape) {
						return false;
					}
				}
			}
		}
		return true;
	}
	private boolean areAllSpotsLeftOfShapeEmpty(Chamber chamber) {
		//TODO optimize what to look through later
		for(int row=chamber.grid.length-1; row>0; row--) {
			for(int col=1; col<=7; col++) {
				ChamberTile curTile = chamber.grid[row][col];
				if(curTile.isCurrentShape) {
					ChamberTile leftTile = chamber.grid[row][col-1];
					if((leftTile.value=='#') && !leftTile.isCurrentShape) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
