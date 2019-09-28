import java.util.Scanner;
//201430189 전호범  
public class p13 {
	// 크기가 K개인 단위 사이클의 개수
	public static void main(String[] args) {
		new p13();
	}

	public p13() {
		Scanner kb = new Scanner(p13.class.getResourceAsStream("input13.txt"));
		int T = kb.nextInt();
		while (0 < T--) {

			new planeGraph(kb);
		}
	}
}

class planeGraph {
	int N, K;
	Point[] V;
	Node[] nodes;

	public planeGraph(Scanner kb) {
		N = kb.nextInt();
		K = kb.nextInt();
		V = new Point[N + 1];
		for (int i = 1; i < V.length; i++) {
			int index = kb.nextInt();
			V[index] = new Point(kb.nextInt(), kb.nextInt());
			V[index].index = index;
			V[index].edgelength = kb.nextInt();
			V[index].head = new Edge(kb.nextInt());
			Edge temp = V[index].head;
			for (int j = 1; j < V[index].edgelength; j++) {
				temp.next = new Edge(kb.nextInt());
				temp = temp.next;
			}
		}
		initGraph();
		visitOuter();
		System.out.println(innerface());
	}
	private void visitOuter() {
		int minX = 501,indexminX = 0;
		for(int i=1; i < V.length;i++) {
			if(V[i].x<minX) {
				minX = V[i].x;
				indexminX = i;
			}
		}
		Node start = nodes[indexminX].next;
		countface(start,indexminX);
	}

	private int countface(Node start, int startindex) {
		int count = 1;
		start.visited= true;
		int preindex = startindex;
		int forwordindex = start.index;
		while(startindex!=forwordindex) {
			count++;
			Node visiter = findNextNode(forwordindex,preindex);
			visiter.visited = true;
			preindex = forwordindex;
			forwordindex = visiter.index;
		}		
		return count;
	}
	
	private Node findNextNode(int forwordindex, int preindex) {
		Node finder = nodes[forwordindex].next;
		while(finder.next!=null && finder.index!=preindex)
			finder= finder.next;
		if(finder.next==null)
			return nodes[forwordindex].next;
		return finder.next;
	}
	
	private int innerface() {
		int count = 0;
		for (int i = 1; i < N; i++) {
			Node next = nodes[i].next;
			while (next.next != null) {
				if (next.visited) 
					next = next.next;
				else
					if(K==countface(next,i))
						count++;
			}
		}
		return count;
	}

	private void printGraph() {
		for (int i = 1; i < V.length; i++) {
			System.out.print(i + " ");
			Edge temp = V[i].head;
			for (int j = 0; j < V[i].edgelength; j++) {
				System.out.print(temp.index + " ");
				temp = temp.next;
			}
			System.out.println();
		}
	}

	private void printNode() {
		for (int i = 1; i < nodes.length; i++) {
			Node temp = nodes[i];
			while (temp != null) {
				System.out.print(temp.index + " " + temp.visited + " ");
				temp = temp.next;
			}
			System.out.println();
		}
	}

	private void initGraph() {
		// sort by Angular
		nodes = new Node[N + 1];
		for (int i = 1; i < V.length; i++) {
			Edge pre = V[i].head;
			Node temp = new Node(i);
			nodes[i] = temp;
			temp.next = new Node(pre.index);
			for (int j = 1; j < V[i].edgelength; j++) {
				pre = pre.next;
				while (temp.next != null && compare(V[i], V[temp.next.index], V[pre.index]) < 0) {
					temp = temp.next;
				}
				Node nextnode = temp.next;
				temp.next = new Node(pre.index);
				temp.next.next = nextnode;
				temp = nodes[i];
			}
		}
	}

	private double compare(Point p, Point q, Point r) {
		// System.out.println(p.index + " " + q.index + " " + r.index);
		Point q2 = new Point(q.x - p.x, q.y - p.y);
		Point r2 = new Point(r.x - p.x, r.y - p.y);
		if (q2.x >= 0 && r2.x < 0)
			return -1;
		if (q2.x <= 0 && r2.x > 0)
			return 1;
		if (q2.x == 0 && r2.x == 0)
			return q2.y > r2.y ? -1 : 1;
		return crossProduct(q2, r2);
	}

	private double crossProduct(Point q2, Point r2) {
		return (q2.x * (double) r2.y) - (q2.y * (double) r2.x);
	}
}

class Node {
	int index;
	Node next;
	boolean visited;

	public Node(int index) {
		this.index = index;
		this.next = null;
		visited = false;
	}
}

class Point {
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	int index;
	int x, y;
	int edgelength;
	Edge head;
}

class Edge {
	public Edge(int index) {
		this.index = index;
		next = null;
	}

	int index;
	Edge next;
}