
import java.io.FileWriter;
import java.io.IOException;

public class BST {
	static final boolean RED = false;
	static final boolean BLACK = true;;
	Node root;

	BST() {
		root = null;
	}

	void searchByName(String name) {
		Node node = search(root, name);
		if (node == null) {
			System.out.println("Not exist");
			System.out.println("floor is " + floor(root, name));
			System.out.println("ceiling is " + ceiling(root, name));
		} else
			System.out.println(node);
	}

	void traceByName(String name) {
		Node node = trace(root, name);
		if (node == null)
			System.out.println("Not exist");
		else
			System.out.println(node);
	}

	Node trace(Node node, String name) {
		if (node == null)
			return null;
		else if (node.data.name.equals(name))
			return node;
		else {
			System.out.println(node.data.name);
			if (node.data.name.compareTo(name) > 0) // 1 -> (X>node)
				return trace(node.L, name);
			else
				return trace(node.R, name);
		}
	}

	Node search(Node node, String name) {
		if (node == null)
			return null;
		else if (node.data.name.equals(name))
			return node;
		else {
			if (node.data.name.compareTo(name) > 0) // 1 -> (X>node)
				return search(node.L, name);
			else
				return search(node.R, name);
		}
	}

	Node floor(Node node, String name) {
		Node tmp = null;
		while (node != null) {
			int direc = node.data.name.compareTo(name);
			if (direc == 0)
				return node;
			else if (direc > 0)
				node = node.L;
			else {
				tmp = node;
				node = node.R;
			}
		}
		return tmp;
	}

	Node ceiling(Node node, String name) {
		Node tmp = null;
		while (node != null) {
			int direc = node.data.name.compareTo(name);
			if (direc == 0)
				return node;
			else if (direc < 0)
				node = node.R;
			else {
				tmp = node;
				node = node.L;
			}
		}
		return tmp;
	}

	void list() {
		if (root == null) {
			System.out.println("Empty");
			return;
		} else
			inorder(root);
	}

	void mylist() {
		if (root == null) {
			System.out.println("Empty");
			return;
		} else
			aaaa(root);
	}

	void aaaa(Node node) {
		if (node == null)
			return;
		if (node.L != null) {
			System.out.println("L  " + node.L.data.name);
		}
		System.out.println("me  " + node.data.name + (node.color ? ":BLACK" : ":RED"));
		if (node.P != null)
			System.out.println("P  " + node.P.data.name);
		if (node.R != null) {
			System.out.println("R  " + node.R.data.name);
		}
		System.out.println();
		aaaa(node.L);
		aaaa(node.R);
	}

	void inorder(Node node) {
		if (node.L != null)
			inorder(node.L);
		System.out.println(node);
		if (node.R != null)
			inorder(node.R);
	}

	void insert(Node node) {
		System.out.println(node.data.name);
		Node Y = null;
		Node X = root;
		while (X != null) {
			Y = X;
			if (X.data.name.compareTo(node.data.name) > 0) // -> (X>node)
				X = X.L;
			else
				X = X.R;
		}
		if (Y == null) {
			root = node;
			root.color = BLACK;
		} else {
			node.P = Y;
			if (Y.data.name.compareTo(node.data.name) > 0) // -> (Y>node)
				Y.L = node;
			else
				Y.R = node;
			node.color = RED;
			insertFix(node);
		}
	}

	private void insertFix(Node node) {
		Node me = node, ancle;
		while (me != root && me.color != BLACK && me.P.color == RED) {
			Node father = me.P;
			Node Gfather = me.P.P;
			if (father == Gfather.L) {
				ancle = Gfather.R;
				if (ancle != null && ancle.color == RED) {
					Gfather.color = RED;
					ancle.color = BLACK;
					father.color = BLACK;
					me = Gfather;
				} else {
					if (me == father.R) {
						leftRotate(father);
						me = father;
						father = me.P;
					}
					rightRotate(Gfather);
					boolean temp = father.color;
					father.color = Gfather.color;
					Gfather.color = temp;
					me = father;
				}
			} else {
				ancle = me.P.P.L;
				if (ancle != null && ancle.color == RED) {
					father.color = BLACK;
					ancle.color = BLACK;
					ancle.P.color = RED;
					me = ancle.P;
				} else {
					if (me == father.L) {
						rightRotate(father);
						me = father;
						father = me.P;
					}
					leftRotate(Gfather);
					boolean temp = father.color;
					father.color = Gfather.color;
					Gfather.color = temp;
					me = father;
				}
			}
		}
		root.color = BLACK;
	}

	private void rightRotate(Node node) {
		Node y = node.L;
		node.L = y.R;
		if (node.L != null)
			node.L.P = node;
		y.P = node.P;
		if (y == root)
			root = y;
		else if (node == node.P.R)
			node.P.R = y;
		else
			node.P.L = y;
		y.R = node;
		node.P = y;
	}

	private void leftRotate(Node node) {
		Node y = node.R;
		node.R = y.L;
		if (node.R != null)
			node.R.P = node;
		y.P = node.P;
		if (node == root)
			root = y;
		else if (node == node.P.L)
			node.P.L = y;
		else
			node.P.R = y;
		y.L = node;
		node.P = y;
	}

	public void deleteByName(String name) {
		Node node = search(root, name);
		if (node == null)
			System.out.println("unfind Node");
		else
			System.out.println("delete complete\n" + deleteByNode(node));
	}

	/*
	 * private void delLink(Node delnode, Node sun, Node P) { if (P == null) root =
	 * sun; else { if (P.R == delnode) P.R = sun; else P.L = sun; delnode.P = null;
	 * } }
	 */
	private Node deleteByNode(Node node) {
		/*
		 * if (node.R == null && node.L == null) delLink(node, null, node.P); else if
		 * (node.L == null) delLink(node, node.R, node.P); else if (node.R == null)
		 * delLink(node, node.L, node.P); else { Node succ = successor(node); node.data
		 * = succ.data; delLink(succ, succ.R, succ.P); } return node;
		 */
		Node x = node, y = null;
		int sun = 0;
		boolean color = RED;
		if (x.R == null || x.L == null)
			y = node;
		else {
			y = successor(x);
			sun = 1;
			color = x.color;
		}
		if (y.L != null)
			x = y.L;
		else {
			if (y.R != null)
				x = y.R;
			else
				x = null;
		}
		if (x != null)
			x.P = y.P;
		if (y.P == null)
			root = x;
		else {
			if (y == y.P.L)
				y.P.L = x;
			else
				y.P.R = x;
		}
		if (y != node)
			node.data = y.data;
		if (x != null && y.color)
			deleteFix(x);
		else if (x == null) {
			if (sun == 0 && y.color) {
				Node father = y.P;
				father.L = y;
				y.color = BLACK;
				deleteFix(y);
				y = father.L;
				father.L = null;
			} else if (sun == 1 && color) {
				if (y.L == null)
					deleteFix(y.P);
				else
					deleteFix(y.P.L);
			}
		}
		return y;
	}

	private void deleteFix(Node x) {
		Node bro;
		while (x != root && x.color) {
			if (x == x.P.L) {
				bro = x.P.R;
				if (bro != null && !bro.color) {
					bro.color = BLACK;
					x.P.color = RED;
					leftRotate(x.P);
					bro = x.P.R;
				}
				if (bro == null) {
					x = x.P;
					continue;
				}
				if (bro.L == null && bro.R == null || bro.L.color && bro.R.color) {
					bro.color = RED;
					x = x.P;
				} else {
					if (bro.R == null || bro.R.color) {
						bro.L.color = BLACK;
						bro.color = RED;
						rightRotate(bro);
						bro = x.P.R;
					}
					bro.color = x.P.color;
					x.P.color = BLACK;
					bro.R.color = BLACK;
					leftRotate(x.P);
					x = root;
				}
			} else {
				bro = x.P.L;
				if (bro != null && !bro.color) {
					bro.color = BLACK;
					x.P.color = RED;
					rightRotate(x.P);
					bro = x.P.L;
				}
				if (bro == null) {
					x = x.P;
					continue;
				}
				if (bro.L == null && bro.R == null || bro.L.color && bro.R.color) {
					bro.color = RED;
					x = x.P;
				} else {
					if (bro.L == null || bro.L.color) {
						bro.R.color = BLACK;
						bro.color = RED;
						leftRotate(bro);
						bro = x.P.L;
					}
					bro.color = x.P.color;
					x.P.color = BLACK;
					bro.L.color = BLACK;
					rightRotate(x.P);
					x = root;
				}
			}
		}
		x.color = BLACK;
	}

	private Node successor(Node node) {
		if (node.R != null) {
			return minimum(node.R);
		}
		Node y = node.P, x = node;
		while (y != null && y.R == x) {
			x = y;
			y = y.P;
		}
		return y;
	}

	private Node minimum(Node node) {
		Node x = node.L, y = node;
		while (x != null) {
			y = x;
			x = x.L;
		}
		return y;
	}

	public void insert(String n, String c, String a, String z, String p, String e) {
		insert(new Node(n, c, a, z, p, e));
	}

	public boolean savedata(String filename) {
		try {
			FileWriter writer = new FileWriter(filename);
			savenode(root, writer);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	private void savenode(Node node, FileWriter writer) throws IOException {
		if (node == null)
			return;

		savenode(node.L, writer);
		writer.write(node.data.name);
		for (int i = 0; i < 15 - node.data.name.length(); i++)
			writer.write(" ");
		writer.write("| ");
		writer.write(node.data.company);
		for (int i = 0; i < 35 - node.data.company.length(); i++)
			writer.write(" ");
		writer.write("| ");
		writer.write(node.data.address);
		for (int i = 0; i < 55 - node.data.address.length(); i++)
			writer.write(" ");
		writer.write("| ");
		writer.write(node.data.zipcode + " | ");
		writer.write(node.data.phone + " | ");
		writer.write(node.data.email + System.lineSeparator());
		savenode(node.R, writer);
	}
}
