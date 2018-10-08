package baekjoon_samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class b_14890_경사로 {

	static int[][] mapTopDown, mapLeftRight;
	static int n, l, cnt = 0;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());

		mapTopDown = new int[n][n];
		mapLeftRight = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				mapTopDown[j][i] = mapLeftRight[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			search(i, mapTopDown); // 위에서 아래로 탐색
			search(i, mapLeftRight); // 왼쪽에서 오른쪽으로 탐색
		}
		System.out.println(cnt);
	}

	public static void search(int idx, int[][] map) {

		arr = new int[n]; // 방문했는지 확인하기 위해서 사용한 배열

		// search
		for (int j = 0; j < n - 1; j++) {

			// 앞의 것과 뒤의 것과 다르다면
			if (map[idx][j] != map[idx][j + 1]) {
				int diff = map[idx][j] - map[idx][j + 1];
				if (diff != -1 && diff != 1) {
					return;
				}
				// 차이가 -1 이면 왼쪽으로 이동
				if (diff == -1) {
					for (int k = 0; k < l; k++) {
						// 만약 j-k 가 0보다 작거나(범위 초과), 이미 1이라면(경사로 있음)
						if (j - k < 0 || arr[j - k] == 1)
							return;
						if (map[idx][j] == map[idx][j - k]) {
							arr[j - k] = 1;
						} else
							return;
					}
				} else {
					// 차이가 1이면 오른쪽으로 이동
					for (int k = 1; k <= l; k++) {
						// 만약 j+k 가 n보다 크거나(범위 초과), 이미 1이라면(경사로 있음)
						if (j + k >= n || arr[j + k] == 1)
							return;
						if (map[idx][j] - 1 == map[idx][j + k]) {
							arr[j + k] = 1;
						} else
							return;
					}
				}
			}
		}
		cnt++;
	}

}
