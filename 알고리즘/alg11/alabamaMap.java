import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class alabamaMap {
	List<City> citys;
	List<Edge> edges;
	PriorityQueue<node> PQ;
	int[] previous;
	boolean[] S;
	double[] d;

	public static void main(String[] args) {
		new alabamaMap();
	}

	alabamaMap() {
		long start, end;
		try {
			start = System.currentTimeMillis();
			readcity();
			readedge();
			end = System.currentTimeMillis() - start;
			System.out.println(end / 1000.0);
		} catch (IOException e) {
			System.out.println("file read error");
		}
		//init data
		int s = citys.size();
		previous = new int[s];
		d = new double[s];
		S = new boolean[s];
		
		Scanner kb = new Scanner(System.in);
		while (kb.hasNext()) {
			try {
				switch (kb.nextLine()) {
				case "src":
					System.out.print("Start city is : ");
					if (setSrc(kb.nextLine()))
						System.out.println("Complete");
					break;
				case "dst":
					System.out.print("End city is : ");
					getDst(kb.nextLine());
					break;
				case "printd":
					for (int i = 0; i < d.length; i++) {
						City x = citys.get(i);
						System.out.print(i+":"+x.name);
						System.out.println("\t" + i + " " + d[i]);
					}
					break;
				case "exit":
					System.exit(0);
					break;
				default:
					System.out.println("input error");
					break;
				}
			} catch (NoSuchElementException e) {
				System.out.println("input error");
			}
		}
	}

	private boolean getDst(String dst) {
		int x = citys.indexOf(new City(dst));
		if(x < 0)
			return false;
		System.out.println("===========Path============");
		printPath(x);
		System.out.println("Distance :" + d[x]);
		return true;
	}

	private void printPath(int x) {
		if (previous[x] == -1) {
			System.out.println(citys.get(x).name + " " + d[x]);
			return;
		}
		printPath(previous[x]);
		System.out.println(citys.get(x).name + " " + d[x]);
	}

	private boolean setSrc(String src) {
		// Dijkstra
		int s = citys.indexOf(new City(src));
		if (s < 0) {
			System.out.println("Src error");
			return false;
		}
		// init
		for (int i = 0; i < previous.length; i++) {
			previous[i] = -1;
			d[i] = Double.MAX_VALUE;
			S[i] = false;
		}
		d[s] = 0;
		PQ = new PriorityQueue<node>();
		node Q = new node(s, 0);
		Edge e;
		int v, w;
		City u;
		// 목적지 까지만 구할려면 while문 조건에 dst index가 아니다 조건을 추가
		while (Q != null) {
			//System.out.println(Q.cityindex + " " + d[Q.cityindex]);
			if (S[Q.cityindex]) {
				Q = PQ.poll();
				continue;
			}
			S[Q.cityindex] = true;
			u = citys.get(Q.cityindex);
			e = u.nextedge;
			while (e != null) {
				//System.out.println(e);
				v = e.x;
				w = e.y;
				if (Q.cityindex == v) {
					v = e.y;
					w = e.x;
				}
				if (d[v] > d[w] + e.cost) {
					d[v] = d[w] + e.cost;
					previous[v] = w;
				}
			//	System.out.println("alabamaMap.setSrc() " + v + " " + d[v]);
				PQ.offer(new node(v, d[v]));
				e = e.nextedge;
			}
			Q = PQ.poll();
		}
		return true;
	}

	private void readedge() throws IOException {
		edges = new ArrayList<Edge>();
		for (String line : Files.readAllLines(Paths.get("./roadList2.txt")))
			addedge(line.split("\t"));
		/*
		 * edges.sort(new Comparator<Edge>() {
		 * 
		 * @Override public int compare(Edge o1, Edge o2) { // TODO Auto-generated
		 * method stub return (int) (o2.cost -  o1.cost);// 내림차순 정렬 } }); // 인접리스트로 저장
		 * System.out.println(edges.size()); for (Edge e : edges) {
		 * citys.get(e.x).addEdge(e); citys.get(e.y).addEdge(e); }
		 */}

	private void addedge(String[] line) {
		int xi = citys.indexOf(new City(line[0]));
		int yi = citys.indexOf(new City(line[1]));
		if (xi != -1 && yi != -1) {
			City x = citys.get(xi);
			City y = citys.get(yi);
			double dist = calDistance(x.lat, x.lon, y.lat, y.lon);
			// edges.add(new Edge(xi, yi, dist));
			x.addEdge(new Edge(xi, yi, dist));
			y.addEdge(new Edge(xi, yi, dist));
		} else
			System.out.println("ADDROAD ERROR\t" + line[0] + "\tto\t" + "" + line[1]);
	}

	private double calDistance(double lat1, double lon1, double lat2, double lon2) {
		double theta, dist;
		theta = lon1 - lon2;
		dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;
		dist = dist * 1000.0;
		return dist;
	}

	private double deg2rad(double deg) {
		return (double) (deg * Math.PI / (double) 180);
	}

	private double rad2deg(double rad) {
		return (double) (rad * (double) 180 / Math.PI);
	}

	private void readcity() throws IOException {
		citys = new ArrayList<City>();// 저장공간
		// 파일읽기
		for (String line : Files.readAllLines(Paths.get("./alabama.txt")))
			citys.add(new City(line.split("\t")));
		/*
		 * // 정렬 citys.sort(new Comparator<city>() {
		 * 
		 * @Override public int compare(city c1, city c2) { return
		 * c1.name.compareTo(c2.name); } });
		 */
		// 도시출력
		// for (City city : citys)
		// System.out.println(city);
	}

}

class node implements Comparable<node> {
	int cityindex;
	double d;

	public node(int cityindex, double d) {
		this.cityindex = cityindex;
		this.d = d;
	}

	@Override
	public int compareTo(node o) {
		if (this.d < o.d)
			return -1;
		else if (this.d > o.d)
			return 1;
		return 0;
	}
}

class Edge {
	int x, y;
	double cost;
	Edge nextedge;

	public Edge(int x, int y, double cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
		nextedge = null;
	}

	@Override
	public String toString() {
		return x + " " + y + " " + cost;
	}

}

class City {
	String name;
	double lon, lat;
	Edge nextedge;

	City(String[] line) {
		name = line[0];
		lon = Double.valueOf(line[1]);
		lat = Double.valueOf(line[2]);
		nextedge = null;
	}

	City(String x) {
		name = x;
	}

	public void addEdge(Edge x) {
		x.nextedge = this.nextedge;
		nextedge = x;
	}

	@Override
	public String toString() {
		return name + "\t" + String.valueOf(lon) + "\t" + String.valueOf(lat);
	}

	@Override
	public boolean equals(Object obj) {
		City c = (City) obj;
		return this.name.equals(c.name);
	}
}
