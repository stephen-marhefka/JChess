public class Piece {
	
	private String color;
	private String type;
	
	public Piece(String color, String type) {
		this.color = color;
		this.type = type;
	}
	
	public Piece() {
		this.color = "";
		this.type = " ";
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getColor() {
		return this.color;
	}
}