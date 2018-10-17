package baekjoon_samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.io.IOException;

public class b_14501_퇴사 {

	static int N, sum, day;
	static int[] time; // 상담하는데 걸리는 시간
	static int[] money;// 일하고 받을 수 있는 금액
	static int[] dp; // 모든 합의 값들이 들어갈 배열

	static void dfs(int s) {
		if (s + time[s] <= N + 1) {
			sum += money[s];
			day += time[s];
			if (s + time[s] >= N + 1)
				return;
			else
				dfs(s);
		} else
			return;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		time = new int[N + 1];
		money = new int[N + 1];
		dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			String[] work = br.readLine().split(" ");
			time[i] = Integer.parseInt(work[0]);
			money[i] = Integer.parseInt(work[1]);
			dp[i] = money[i];
		} // Input

		// dp[i] = i일때까지 얻은 수익

		for (int i = 2; i <= N; i++) {
			for (int j = 1; j < i; j++) {
				if (i - j >= time[j]) {
					dp[i] = Math.max(money[i] + dp[j], dp[i]);
				}
			}
		}

		int max = 0;

		for (int i = 1; i <= N; i++) {
			if (i + time[i] <= N + 1 && max < dp[i]) {
				max = dp[i];
			}
		}
		System.out.println(max);
	}
}