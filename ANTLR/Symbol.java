package ANTLR;
/*
 * Every Symbol should be unique; otherwise, the code cannot be compiled
 */
public class Symbol {
	String text, PrimitiveType, className;
	public Symbol(String text, String PrimitiveType, String className) {
		this.text=text;this.PrimitiveType=PrimitiveType;this.className=className;
	}
	@Override
	public String toString() {
		return text + " "+PrimitiveType+" "+className;
	}
}
