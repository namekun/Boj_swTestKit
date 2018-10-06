package baekjoon_samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

import java.util.Queue;
import java.util.LinkedList;

public class b_14503_BFS {

	static int N, M, r, c, d, cnt = 0;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visit = new boolean[N][M];

		st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		// map 입력 받는다.
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visit[r][c] = true;
		bfs(r, c, d);

		System.out.println(cnt);

	}

	public static boolean isOut(int x, int y) { // 범위초과하는지 확인
		if (x > M - 1 || y > N - 1 || x < 0 || y < 0) {
			return true;
		} else
			return false;
	}

	public static void bfs(int x, int y, int d) {

		if (map[x][y] == 0) {
			cnt++;
		} // 청소한 부분 추가

		Queue<Point3> q = new LinkedList<Point3>();
		q.offer(new Point3(x, y, d));

		while (!q.isEmpty()) {
			Point3 p = q.poll();
			x = p.r;
			y = p.c;
			d = p.d;

			int nextX = 0, nextY = 0, nextD = d;
			boolean flag = false;

			// 전진
			for (int i = 0; i < 4; i++) {

				nextD = (nextD + 3) % 4; // 방향 왼쪽으로 틀기

				nextX = dx[nextD] + x; // 다음으로 갈 좌표
				nextY = dy[nextD] + y; // 상동

				if (!isOut(nextX, nextY)) {
					if (map[nextX][nextY] == 0 && !visit[nextX][nextY]) {
						visit[nextX][nextY] = true;
						++cnt;
						q.add(new Point3(nextX, nextY, nextD));
						flag = true;
						break;
					}
				}
			}

			// 후진
			if (!flag) {
				nextD = (d + 2) % 4;

				nextX = x + dx[nextD];
				nextY = y + dy[nextD];

				if (!isOut(nextX, nextY)) {
					if (map[nextX][nextY] == 0) {
						q.add(new Point3(nextX, nextY, d));
					}
				}
			}
		} // bfs end
	}
}

class Point3 { // 좌표 입력 가능하게 만드는 Class
	int r;
	int c;
	int d;

	Point3(int r, int c, int d) {
		this.r = r;
		this.c = c;
		this.d = d;
	}
}
