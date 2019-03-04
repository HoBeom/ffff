import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
	
public class alabamaMap {
	List<City> citys;
	List<Edge> edges;
	final int hopsize = 10;
	boolean[] Pcheck;
	int[] Hcheck;
	Queue<City> cityQ;
	int n = 0;

	public static void main(String[] args) {
		new alabamaMap();
	}

	alabamaMap() {
		long start, end;
		try {
			start = System.currentTimeMillis();
			readcity();
			//readroad();
			readedge();
			makeMst();
			end = System.currentTimeMillis() - start;
			System.out.println(end / 1000.0);
		} catch (IOException e) {
			System.out.println("file read error");
		}
		Scanner kb = new Scanner(System.in);
		printhop(kb.nextLine());
		print(kb.nextLine());
		kb.close();
	}

	private void makeMst() throws IOException {
		// kruskal
		UnionFind uf = new UnionFind(citys.size());
		for (Edge e : edges) {
			if (uf.union(e.x, e.y)) {
				citys.get(e.x).addMstNode(e.y);
				citys.get(e.y).addMstNode(e.x);
			}
		}
		FileOutputStream output = new FileOutputStream("./mst.txt");
		for (int i = 0; i < citys.size(); i++) {
			City c = citys.get(i);
			String data = i + " " + c.lon + " " + c.lat+" "+c.nodecount;
			MstNode nextnode = c.nextnode;
			while (nextnode != null) {
				data += " " + nextnode.index;
				nextnode = nextnode.nextnode;
			}
			data += "\r\n";
			// System.out.print(data);
			output.write(data.getBytes());
		}
		output.close();
	}

	private void print(String cityname) {
		int index = citys.indexOf(new City(cityname));
		if (index >= 0 && index < citys.size()) {
			Pcheck = new boolean[citys.size()];
			City x = citys.get(index);
			print(x);
		} else
			System.out.println("unknown city");
	}

	private void print(City x) {
		if (x == null)
			return;
		int index = citys.indexOf(x);
		if (Pcheck[index])
			return;
		Pcheck[index] = true;
		System.out.println(x);
		road nextroad = x.nextroad;
		while (nextroad != null) {
			print(nextroad.nearCity);
			nextroad = nextroad.nextroad;
		}
	}

	private void printhop(String cityname) {
		int index = citys.indexOf(new City(cityname));
		if (index >= 0 && index < citys.size()) {
			Hcheck = new int[citys.size()];
			cityQ = new LinkedList<City>();
			Hcheck[index] = 1;
			City x = citys.get(index);
			cityQ.add(x);
			while (!cityQ.isEmpty()) {
				City u = cityQ.poll();
				System.out.println(u);
				int pre = citys.indexOf(u);
				if (Hcheck[pre] > hopsize)
					continue;
				road nextroad = u.nextroad;
				while (nextroad != null) {
					int vis = citys.indexOf(nextroad.nearCity);
					if (Hcheck[vis] == 0) {
						Hcheck[vis] = Hcheck[pre] + 1;
						cityQ.add(nextroad.nearCity);
					}
					nextroad = nextroad.nextroad;
				}
			}
		} else
			System.out.println("unknown city");
	}

	private void readroad() throws IOException {
		for (String line : Files.readAllLines(Paths.get("./roadList2.txt")))
			addroad(line.split("\t"));
	}

	private void addroad(String[] line) {
		int xi = citys.indexOf(new City(line[0]));
		int yi = citys.indexOf(new City(line[1]));
		if (xi != -1 && yi != -1) {
			City x = citys.get(xi);
			City y = citys.get(yi);
			double dist = calDistance(x.lat, x.lon, y.lat, y.lon);
			x.nextroad = new road(y, dist, x.nextroad);
			y.nextroad = new road(x, dist, y.nextroad);
		} else
			System.out.println("ADDROAD ERROR\t" + line[0] + "\tto\t" + "" + line[1]);
	}

	private void readedge() throws IOException {
		edges = new ArrayList<Edge>();
		for (String line : Files.readAllLines(Paths.get("./roadList2.txt")))
			addedge(line.split("\t"));
		edges.sort(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				return (int) (o1.cost - o2.cost);
			}
		});
		//for (Edge e : edges)
			//System.out.println(e.x + "\t" + e.y + "\t" + e.cost);
	}

	private void addedge(String[] line) {
		int xi = citys.indexOf(new City(line[0]));
		int yi = citys.indexOf(new City(line[1]));
		if (xi != -1 && yi != -1) {
			City x = citys.get(xi);
			City y = citys.get(yi);
			double dist = calDistance(x.lat, x.lon, y.lat, y.lon);
			edges.add(new Edge(xi, yi, dist));
		} else
			System.out.println("ADDROAD ERROR\t" + line[0] + "\tto\t" + "" + line[1]);
	}

	private double calDistance(double lat1, double lon1, double lat2, double lon2) {
		double theta, dist;
		theta = lon1 - lon2;
		dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) 
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
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
		//for (City city : citys)
			//System.out.println(city);
	}

}

class Edge {
	int x, y;
	double cost;

	public Edge(int x, int y, double cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
}

class UnionFind {
	int[] root;
	int[] size;

	public UnionFind(int size) {
		root = new int[size];
		this.size = new int[size];
		initialize();
	}

	private void initialize() {
		for (int i = 0; i < root.length; i++) {
			root[i] = i;
			size[i] = 1;
		}
	}
	
	//x의 루트를 리턴
	public int find(int x) {
		if (root[x] ==x)
			return x;
		return find(root[x]);
	}
	//세상에 증명에는 2가지 방법이있다_ 모순에의한증명, 수학적귀납법
	//이름을 붙이고 정복해라
	//브루드 포스! -> 이미 알고있는 알고리즘 -> Greedy method -> 분할정복,동적계획법 -> 피해갈수없는 막다른길;;
	//x가 속한 트리와 y가 속한 트리를 합친다.
	public boolean union(int x, int y) {
		int root1 = find(x);
		int root2 = find(y);
		//같은 사이클 내에 있을 경우 false
		if (root1 == root2)
			return false;
		//size 가 root2가 더 크다면 swap
		if (size[root1] < size[root2]) {
			root1 ^= root2;
			root2 ^= root1;
			root1 ^= root2;
		}
		//root1이 큰 루트이므로 root1으로 union
		size[root1] += size[root2];
		root[root2] = root1;
		return true;
	}

	//x가 포함된 트리의 size를 리턴
	public int size(int x) {
		return size[find(x)];
	}
}

class MstNode {
	MstNode nextnode;
	int index;

	MstNode(int index, MstNode nextnode) {
		this.index = index;
		this.nextnode = nextnode;
	}
}

class City {
	String name;
	double lon, lat;
	road nextroad;
	MstNode nextnode;
	int nodecount;

	City(String[] line) {
		name = line[0];
		lon = Double.valueOf(line[1]);
		lat = Double.valueOf(line[2]);
		nextnode = null;
		nextroad = null;
		nodecount = 0;
	}

	City(String x) {
		name = x;
	}

	public void addMstNode(int x) {
		nodecount++;
		nextnode = new MstNode(x, nextnode);
	}

	@Override
	public String toString() {
		return name + "\t" + String.valueOf(lon) + "\t" + String.valueOf(lat);
	}

	@Override
	public boolean equals(Object obj) {
		City c = (City) obj; // 여기서 강제 타입캐스팅으로 city class외의 객체가 들어오면 오류가 날수도?
		return this.name.equals(c.name);
	}
}

class road {
	City nearCity;
	double dist;
	road nextroad;

	road(City nC, double dist, road nR) {
		nearCity = nC;
		this.dist = dist;
		nextroad = nR;
	}
}