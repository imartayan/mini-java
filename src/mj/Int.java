package mj;

public class Int implements Value {
	public final int val;

	public Int(int val) {
		this.val = val;
	}
	
	@Override
	public String toString() {
		return Integer.toString(val);
	}
}