package baekjoon_samsung;

import java.util.Scanner;

public class b_6603_Lotto_DFS_BackTracking {

	// m개 중에서 n개를 골라야 하는 경우는 DFS+백트래킹을 떠올려야 한다!

	static int[] array;
	static StringBuilder sb = new StringBuilder();
	static int k;
	static int cnt;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while ((k = sc.nextInt()) != 0) { // 입력받은 k가 0이 아니라면 수행한다
			array = new int[13]; // 어차피 최대 크기는 12이기에, 13크기로 정해준다.

			for (int i = 0; i < k; i++) {
				array[i] = sc.nextInt();
			}

			for (int i = 0; i < k - 5; i++) { // 입력받은 숫자의 개수에서 6개만큼 뽑아 내려면 앞에서부터 k-5번 만큼 돌릴 수 있기 때문에 
											  // 그만큼만 앞자리수를 돌려줍니다.
											  // 어차피 오름차순으로 입력해주기 때문에 괜찮.
				cnt = 1;
				dfs(i, array[i] + " "); // dfs 시작
			}

			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void dfs(int v, String str) {
		if (cnt == 6) { // 종료 조건 : cnt가 6이면 그대로 StringBuilder에 str을 더해준다.
			sb.append(str + "\n");
		} else {
			for (int i = v + 1; i < k; i++) {
				// 오름 차순이 되어있는 번호를 탐색할 때마다, 보다 더 큰 수를 탐색하기 때문.
				++cnt; // 카운트를 올려주고,
				dfs(i, str + array[i] + " "); // str
			}
		}
		--cnt; // 다 나온다면, 카운트를 - 1 해줘서 돌아가자
	}
}
