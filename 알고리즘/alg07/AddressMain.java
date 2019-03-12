

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AddressMain {
	BST tree;

	public static void main(String[] args) {
		new AddressMain();
	}

	AddressMain() {
		tree = new BST();
		Scanner kb = new Scanner(System.in);
		StringTokenizer token;
		while (kb.hasNext()) {
			token = new StringTokenizer(kb.nextLine(), " ");
			try {
				switch (token.nextToken()) {
				case "read":
					readData(token.nextToken());
					break;
				case "list":
					tree.list();
					break;
				case "find":
					tree.searchByName(token.nextToken());
					break;
				case "trace":
					tree.traceByName(token.nextToken());
					break;
				case "insert":
					tree.insert(token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken(),
							token.nextToken(), token.nextToken());
					break;
				case "delete":
					tree.deleteByName(token.nextToken());
					break;
				case "save":
					saveData(token.nextToken());
					break;
				case "test":
					tree.mylist();
					break;
				case "exit":
					System.exit(0);
				default:
					System.out.println("input error");
					break;
				}
			} catch (NoSuchElementException e) {
				System.out.println("input error");
			}
		}
	}

	private void saveData(String nextToken) {
		if(tree.savedata(nextToken))
			System.out.println("save complete");
		else
			System.out.println("save error");
	}

	private void readData(String filename) {
		try {
			File file = new File(filename);
			Scanner read = new Scanner(file);
			while (read.hasNext()) {
				StringTokenizer token = new StringTokenizer(read.nextLine(), "|");
				tree.insert(token.nextToken(), token.nextToken(), token.nextToken(), token.nextToken(),
						token.nextToken(), token.nextToken());
			}
			read.close();
			System.out.println("File read complete");
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
		}
	}

}
