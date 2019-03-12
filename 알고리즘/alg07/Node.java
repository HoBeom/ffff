
public class Node {
	Node P, L, R;
	Person data;
	boolean color;

	Node(String n, String c, String a, String z, String p, String e) {
		data = new Person(n, c, a, z, p, e);
		P = L = R = null;
	}

	public String toString() {
		return data.toString() + (color ? "\nBLACK" : "\nRED");
	}
}
