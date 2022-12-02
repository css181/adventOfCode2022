package day2;

public class Game {

	private Moves opponents;
	private Moves yours;
	
	public Game(Moves opponents, Moves yours) {
		this.opponents = opponents;
		this.yours = yours;
	}

	public Game(Moves opponent, Results result) {
		this.opponents = opponent;
		this.yours = GameJudger.getYourMove(opponent, result);
	}

	public Moves getOpponents() {
		return opponents;
	}

	public void setOpponents(Moves opponents) {
		this.opponents = opponents;
	}

	public Moves getYours() {
		return yours;
	}

	public void setYours(Moves yours) {
		this.yours = yours;
	}

	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        if(!(obj instanceof Game)) { return false; }
        Game other = (Game) obj;
        
        if(!this.opponents.equals(other.opponents)) { return false; }
        if(!this.yours.equals(other.yours)) { return false; }
        
        return true;
    }

	public Integer getScore() {
		return GameJudger.calculatePoints(this);
	}
}
