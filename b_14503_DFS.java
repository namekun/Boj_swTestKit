package baekjoon_samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class b_14503_DFS {

	static int N, M, cnt = 1;
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 }, dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(x, y, d);
		System.out.println(cnt);
	}

	/**
	 * 규칙
	 * 
	 * 1. 4방향을 돌면서 청소 가능한 곳을 탐색, 다시 dfs() 호출 
	 * 2. 4방향 청소가 끝나면 후진 
	 * 3. 후진이 불가하면 return;
	 * 
	 */

	private static void dfs(int x, int y, int d) {
		map[x][y] = 2;
		int nx, ny;
		
		// 되는 경우의 수를 따진다.
		for (int i = 0; i < 4; i++) {
			d = (d + 3) % 4; // 바라보고 있는 방향의 왼쪽을 가리키는 방향
			nx = x + dx[d];
			ny = y + dy[d];

			if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
				// 왼쪽이 청소가 가능한 경우
				cnt ++ ;
				dfs(nx, ny, d);
				return; // 탈출!
			}
		}
		

		// 후진
		int back = (d + 2) % 4; // 후진할 방향, 즉 바라보고 있는 방향의 반대방향
		nx = x + dx[back];
		ny = y + dy[back];
		if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] != -1) {
			dfs(x + dx[back], y + dy[back], d);
		}
	}

}
