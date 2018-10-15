package baekjoon_samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Queue;
import java.util.LinkedList;

public class b_14502_연구소 {

	static int n, m;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int[][] arr, arrCopy;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] nums = br.readLine().split(" ");

		n = Integer.parseInt(nums[0]);
		m = Integer.parseInt(nums[1]);

		arr = new int[n][m];
		arrCopy = new int[n][m];

		for (int i = 0; i < n; i++) {
			String[] row = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(row[j]);
				arrCopy[i][j] = Integer.parseInt(row[j]);
			}
		 } // input

		int max = Integer.MIN_VALUE;

		for (int x1 = 0; x1 < n; x1++) {
			for (int y1 = 0; y1 < m; y1++) {
				if (arrCopy[x1][y1] != 0) {
					continue; // 벽세울 위치가 0이 아니라면 Pass
				}

				for (int x2 = 0; x2 < n; x2++) {
					for (int y2 = 0; y2 < m; y2++) {
						if (x1 == x2 && y1 == y2)
							continue; // 같은거 또 해줄 필요없잖아.
						if (arrCopy[x2][y2] != 0) {
							continue; // 벽세울 위치가 0이 아니라면 Pass
						}

						for (int x3 = 0; x3 < n; x3++) {
							for (int y3 = 0; y3 < m; y3++) {
								if (x1 == x3 && y1 == y3)
									continue; // 같은거 또 해줄 필요없잖아.
								if (x2 == x3 && y2 == y3)
									continue;
								if (arrCopy[x3][y3] != 0) {
									continue; // 벽세울 위치가 0이 아니라면 Pass
								}

								for (int i = 0; i < n; i++) {
									for (int j = 0; j < m; j++) {
										// 만약 바이러스가 있다면
										if (arrCopy[i][j] == 2) {
											arrCopy[x1][y1] = 1;
											arrCopy[x2][y2] = 1;
											arrCopy[x3][y3] = 1; // 벽 세우고
											BFS(i, j); // BFS돌리자
										}
									}
								}

								max = Math.max(max, Check());
								reset();
							}
						}

					}
				}

			}
		}
		System.out.println(max);
	}

	private static void reset() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arrCopy[i][j] = arr[i][j];
			}
		}
	}

	public static boolean isOut(int x, int y) { // 범위초과하는지 확인
		if (x >= n  || y >= m || x < 0 || y < 0) {
			return true;
		} else
			return false;
	}
	
	
	public static void BFS(int x, int y) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(x, y));

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = p.x + dx[i];
				int nextY = p.y + dy[i];

				// 이동할 부분이 바깥이라면? 패스
				if (isOut(nextX, nextY)) {
					continue;
				}

				// 이동할 부분이 벽이거나, 바이러스라면 패스
				if (arrCopy[nextX][nextY] == 1 || arrCopy[nextX][nextY] == 2) {
					continue;
				}

				q.add(new Point(nextX, nextY));
				arrCopy[nextX][nextY] = 2;
			}
		}
	} // BFS END

	public static int Check() {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arrCopy[i][j] == 0)
					cnt++;
			}
		}

		return cnt;
	}

}

class Point { // 좌표 입력 가능하게 만드는 Class	
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
