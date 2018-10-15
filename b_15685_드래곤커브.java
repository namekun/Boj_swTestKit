package baekjoon_samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class b_15685_드래곤커브 {

	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[][] board = new int[101][101];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		int[][] curve = new int[T][4]; // 입력 받을 2차원 배열

		// 커브 조건 입력
		for (int i = 0; i < T; i++) {
			String[] row = br.readLine().split(" ");
			for (int j = 0; j < 4; j++) {
				curve[i][j] = Integer.parseInt(row[j]);
			}
		} // for end

		Dragon(curve);
	}

	public static void Dragon(int[][] curve) {

		for (int i = 0; i < curve.length; i++) {

			ArrayList<Integer> direction = new ArrayList<>();
			direction.add(curve[i][2]); // 방향을 더해준다.

			for (int j = 0; j < curve[i][3]; j++) {
				int size = direction.size();
				for (int k = size - 1; k >= 0; k--) { // pop
					int p = direction.get(k); // 마지막 주소 가져오고
					direction.add((p + 1) % 4); // 다시 넣어준다.
				}
			}
			Drawing(curve[i][0], curve[i][1], direction); // 시작 지점부터 방향에 따른 선을 그려준다.
		}
		Count();

	}

	public static void Count() {
		int cnt = 0;
		// board.length-1인 이유? 마지막 행과 열은 애초에 사각형을 이룰 수 없기 때문에 검사ㄴㄴ
		for (int i = 0; i < board.length - 1; i++) {
			for (int j = 0; j < board.length - 1; j++) {
				if (board[i][j] == 1 && board[i][j + 1] == 1 && board[i + 1][j] == 1 && board[i + 1][j + 1] == 1) {
					cnt++;
				}
			}
		}

		System.out.println(cnt);
	}

	public static void Drawing(int x, int y, ArrayList<Integer> arr) {
		board[y][x] = 1; // 입력받는건 x, y지만 실제 배열에서는 y, x가 된다.
		for (int i = 0; i < arr.size(); i++) {
			y = y + dx[arr.get(i)];
			x = x + dy[arr.get(i)];
			board[y][x] = 1;
		}

	} // drawing end

}
