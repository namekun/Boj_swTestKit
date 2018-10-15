package baekjoon_samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b_14889_스타트와링크 {
	static int n;
	static int[][] inputArr;
	static boolean[] check;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 게임에 참가하는 팀의 수

		inputArr = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				inputArr[i][j] = Integer.parseInt(st.nextToken());
			}
		} // input

		check = new boolean[n + 1];

		dfs(0, 0);
		System.out.println(ans);
	}

	public static void dfs(int v, int depth) {
		if (depth == n / 2) {
			divideTeam();
			return;
		}

		for (int i = v + 1; i <= n; i++) { // back Tracking
			if (check[i])
				continue;
			check[i] = true; // check[i]번째 수를 true로 바꿔주고
			dfs(i, depth + 1); // 이 위치에서 dfs시작
			check[i] = false;// 탐색이 끝나면 false로 돌려준다.
		}
	}

	public static void divideTeam() { // 팀을 나눠주는 메소드
		int[] aTeam = new int[n / 2 + 1];
		int[] bTeam = new int[n / 2 + 1];
		int aidx = 1;
		int bidx = 1;
		for (int i = 1; i <= n; i++) {
			if (check[i]) { // check 배열을 통해 팀을 나누자
				aTeam[aidx++] = i;
			} else {
				bTeam[bidx++] = i;
			}
		}
		int aState = getState(aTeam);
		int bState = getState(bTeam);
		int diffState = Math.abs(aState - bState);
		ans = Math.min(ans, diffState);
	}

	public static int getState(int[] arr) { // 값을 다 더해준 result를 구해줍니다.
		int result = 0;
		for (int i = 1; i <= n / 2; i++) {
			for (int j = i + 1; j <= n / 2; j++) {
				result += inputArr[arr[i]][arr[j]];
				result += inputArr[arr[j]][arr[i]];
			}
		}
		return result;
	}

}
