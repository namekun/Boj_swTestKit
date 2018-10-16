package baekjoon_samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class b_14888_연산자끼워넣기 {

	static ArrayList<Integer> arr; // 숫자배열
	static int[] operator; // 연산자횟수를 저장하기 위한 배열
	static ArrayList<Integer> list; // 최소 최대값을 찾기 위한 arrayList
	static int N;
	static int cnt = 0;

	static void dfs(int v, int sum) {
		for (int i = 0; i < 4; i++) {
			if (operator[i] > 0) {
				operator[i]--; // 연산자를 사용했으므로, -1해준다.
				switch (i) {
				case 0: // +
					dfs(v + 1, sum + arr.get(v));
					break;
				case 1: // -
					dfs(v + 1, sum - arr.get(v));
					break;
				case 2: // x
					dfs(v + 1, sum * arr.get(v));
					break;
				case 3: // /
					dfs(v + 1, sum / arr.get(v));
					break;
				}
				operator[i]++; // i 가 + 1될때마다 재사용해야하기 때문에, 다시 추가
			}
		}

		if (v == N) {
			list.add(sum);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		String[] nums = br.readLine().split(" ");
		arr = new ArrayList<>();
		list = new ArrayList<>();
		operator = new int[4];
		String[] op = br.readLine().split(" ");

		for (int i = 0; i < nums.length; i++) {
			arr.add(Integer.parseInt(nums[i]));
		}

		for (int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(op[i]);
		}

		dfs(1, arr.get(0)); // 연산 스타트

		Collections.sort(list);
		System.out.println(list.get(list.size() - 1));
		System.out.println(list.get(0));
	}

}
