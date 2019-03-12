package alg08;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class markovChain {
	Node[] htable;
	final int NHASH = 4093;
	int MULTIPLIER = 31;
	final int MAXLENGTH = 10000;
	int WORDLENGTH = 0;

	public static void main(String[] args) {
		new markovChain();
	}

	markovChain() {
		htable = new Node[NHASH];
		readData();
		// testmode();
		// AZStart();
		startRandomIndex();
	}

	private void startRandomIndex() {
		int rand;
		do
			rand = (int) (Math.random() * (NHASH - 1));
		while (htable[rand] == null);
		makeline(htable[rand]);
	}

	void testmode() {
		Scanner kb = new Scanner(System.in);
		while (true)
			print(kb.nextInt(), kb.nextInt());
	}

	private void print(int i, int j) {
		while (i < j) {
			if (htable[i] != null) {
				System.out.println(i);
				print(htable[i]);
				System.out.println();
			}
			i++;
		}
	}

	private void printall() {
		for (int i = 0; i < NHASH; i++) {
			if (htable[i] != null) {
				System.out.println(i);
				print(htable[i]);
				System.out.println();
			}
		}
	}

	private void print(Node node) {
		if (node != null) {
			print(node.data);
			print(node.next);
		}
	}

	private void print(prefix pfix) {
		System.out.print(pfix.pword1);
		System.out.print(" " + pfix.pword2);
		System.out.println(" sumfreq:" + pfix.sumfreq);
		print(pfix.suf);
	}

	private void print(suffix sfix) {
		if (sfix != null) {
			System.out.print(sfix.sword + ":" + sfix.freq + "\t");
			print(sfix.next);
		}
	}

	void AZStart() {
		int rand;
		Node node = null;
		while (node == null && WORDLENGTH < MAXLENGTH) {
			rand = (int) (Math.random() * (NHASH - 1));
			node = htable[rand];
			if (node != null) {
				char x = node.data.pword1.charAt(0);
				if (x >= 'A' && x <= 'Z') {
					makeline(node);
				} else
					node = null;
			}
		}
	}

	private void makeline(Node node) {
		WORDLENGTH += 2;
		String word1 = node.data.pword1;
		String word2 = node.data.pword2;
		String word3 = nextword(node.data.suf);
		System.out.print(word1 + " " + word2);
		while (word3 != null && WORDLENGTH < MAXLENGTH) {
			System.out.print(" " + word3);
			char nextline = word3.charAt(word3.length() - 1);
			if (nextline == '.' || nextline == '?' || nextline == '!')
				System.out.println();
			WORDLENGTH++;
			word1 = word2;
			word2 = word3;
			word3 = searchword(word1, word2);
		}
	}

	private String searchword(String word1, String word2) {
		int slot = hash(word1, word2);
		Node node = htable[slot];
		prefix pfix;
		while (node != null) {
			pfix = node.data;
			if (pfix.pword1.equals(word1) && pfix.pword2.equals(word2))
				return nextword(pfix.suf);
			node = node.next;
		}
		return null;
	}

	private String nextword(suffix suf) {
		String word = suf.sword;
		int rand = suf.freq;
		while (suf.next != null) {
			suf = suf.next;
			rand += suf.freq;
			if (Math.random() * (rand - 1) < suf.freq)
				word = suf.sword;
		}
		return word;
	}

	private void readData() {
		try {
			// File file = new File("./src/test.txt");
			File file = new File("./HarryPotter.txt");
			Scanner read = new Scanner(file);
			StringTokenizer token;
			String word1 = null, word2 = null, word3 = null;
			while (read.hasNext()) {
				token = new StringTokenizer(read.nextLine(), " ");
				while (token.hasMoreTokens()) {
					word1 = word2;
					word2 = word3;
					word3 = token.nextToken().trim();
					if (word1 != null && word2 != null) {
						int slot = hash(word1, word2);
						if (htable[slot] == null) {
							suffix sfix = new suffix(word3);
							prefix pfix = new prefix(sfix, word1, word2);
							htable[slot] = new Node(pfix);
						}
						// 충돌시
						else {
							Node node = htable[slot];
							while (node != null) {
								prefix pfix = node.data;
								if (pfix.pword1.equals(word1) && pfix.pword2.equals(word2)) {
									suffix x = pfix.suf;
									while (x != null) {
										if (x.sword.equals(word3)) {
											x.freq++;
											break;
										} else
											x = x.next;
									}
									if (x == null) {
										suffix newsfix = new suffix(word3);
										newsfix.next = pfix.suf;
										pfix.suf = newsfix;
									}
									pfix.sumfreq++;
									break;
								} else
									node = node.next;
							}
							if (node == null) {
								Node newnode = new Node(new prefix(new suffix(word3), word1, word2));
								newnode.next = htable[slot];
								htable[slot] = newnode;
							}
						}
					}
				}
			}
			read.close();
			System.out.println("File read complete");
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound");
		}
	}

	// 해슁
	int hash(String key1, String key2) {
		int h = 0;
		for (int i = 0; i < key1.length(); i++)
			h = MULTIPLIER * h + key1.charAt(i);
		for (int i = 0; i < key2.length(); i++)
			h = MULTIPLIER * h + key2.charAt(i);
		return Math.abs(h % NHASH);
	}
}

// 노드들
class Node {
	prefix data;
	Node next;

	Node(prefix data) {
		this.data = data;
		next = null;
	}
}

class suffix {
	String sword;
	int freq;
	suffix next;

	suffix(String word) {
		sword = word;
		next = null;
		freq = 1;
	}
}

class prefix {
	String pword1, pword2;
	suffix suf;
	int sumfreq;

	prefix(suffix suf, String word1, String word2) {
		pword1 = word1;
		pword2 = word2;
		this.suf = suf;
		sumfreq = 1;
	}
}