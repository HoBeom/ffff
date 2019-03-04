package alg13;

import java.util.Scanner;

public class DAG {
	int link[][];
	int path[][];
	int dist[][];
	int N, M;

	public static void main(String[] args) {
		new DAG();
	}

	DAG() {
		System.out.println("DAG");
		Scanner kb = new Scanner(System.in);
		N = kb.nextInt();
		link = new int[N][N];
		dist = new int[N][N];
		path = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				link[i][j] = 99999;
		}
		M = kb.nextInt();
		for (int i = 0; i < M; i++) {
			link[kb.nextInt()][kb.nextInt()] = kb.nextInt();
		}
		int s = kb.nextInt();
		int t = kb.nextInt();
		floydWarshall();
		System.out.println(dist[s][t]);
		printpath(s,t);
	}

	private void printpath(int s, int t) {
		// TODO Auto-generated method stub
		if(s==t) {
			System.out.print(t+"->");
		}else {
			printpath(s,path[s][t]);
			System.out.print(t+"->");
		}
			
	}

	void floydWarshall() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dist[i][j] = link[i][j];
				if (dist[i][j] != 99999)
					path[i][j] = i;
				else
					path[i][j] = -1;
			}
		}
		for (int k = 0; k < N; k++) {
			// Pick all vertices as source one by one
			for (int i = 0; i < N; i++) {
				// Pick all vertices as destination for the
				// above picked source
				for (int j = 0; j < N; j++) {
					// If vertex k is on the shortest path from
					// i to j, then update the value of dist[i][j]
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						path[i][j] = path[k][j];
					}
				}
			}
		}
	}
}
