package alg06;

import java.io.FileWriter;
import java.io.IOException;

public class BST {
	Node root;

	BST() {
		root = null;
	}

	void searchByName(String name) {
		Node node = search(root, name);
		if (node == null) {
			System.out.println("Not exist");
			System.out.println("floor is "+floor(root, name));
			System.out.println("ceiling is "+ceiling(root, name));
		}
		else
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
	
	Node floor(Node node,String name) {
		Node tmp = null;
		while(node != null) {
			int direc = node.data.name.compareTo(name);
			if(direc == 0)
				return node;
			else if(direc > 0)
				node = node.L;
			else {
				tmp = node;
				node = node.R;
			}
		}
		return tmp;
	}
	
	Node ceiling(Node node,String name) {
		Node tmp = null;
		while(node != null) {
			int direc = node.data.name.compareTo(name);
			if(direc == 0)
				return node;
			else if(direc < 0)
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

	void inorder(Node node) {
		if (node.L != null)
			inorder(node.L);
		System.out.println(node);
		if (node.R != null)
			inorder(node.R);
	}

	void insert(Node node) {
		Node Y = null;
		Node X = root;
		while (X != null) {
			Y = X;
			if (X.data.name.compareTo(node.data.name) > 0) // -> (X>node)
				X = X.L;
			else
				X = X.R;
		}
		if (Y != null) {
			node.P = Y;
			if (Y.data.name.compareTo(node.data.name) > 0) // -> (Y>node)
				Y.L = node;
			else
				Y.R = node;
		} else
			root = node;
	}

	public void deleteByName(String name) {
		Node node = search(root, name);
		if (node == null)
			System.out.println("unfind Node");
		else
			System.out.println("delete complete\n" + deleteByNode(node));
	}
/*
	private void delLink(Node delnode, Node sun, Node P) {
		if (P == null)
			root = sun;
		else {
			if (P.R == delnode)
				P.R = sun;
			else
				P.L = sun;
			delnode.P = null;
		}
	}
*/
	private Node deleteByNode(Node node) {
		// TODO Auto-generated method stub
/*
		if (node.R == null && node.L == null)
			delLink(node, null, node.P);
		else if (node.L == null)
			delLink(node, node.R, node.P);
		else if (node.R == null)
			delLink(node, node.L, node.P);
		else {
			Node succ = successor(node);
			node.data = succ.data;
			delLink(succ, succ.R, succ.P);
		}
		return node;
*/
		Node x, y;
		if (node.R == null || node.L == null)
			y = node;
		else
			y = successor(node);
		if (y.L == null)
			x = y.R;
		else
			x = y.L;
		if (x != null)
			x.P = y.P;
		if (y.P == null)
			root = x;
		else if (y == y.P.L)
			y.P.L = x;
		else
			y.P.R = x;
		if (y != node)
			node.data = y.data;
		return y;
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
