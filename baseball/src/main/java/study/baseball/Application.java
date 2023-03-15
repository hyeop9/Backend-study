package study.baseball;

import java.util.Arrays;
import java.util.List;
import study.baseball.domain.Judgment;
import study.baseball.domain.NumberGenerator;

/**
 * 객체 지향 프로그래밍
 * 1. 각 기능을 가지고 있는 클래스를 인스턴스화(=객체) 한다.
 * 2. 필요한 기능을 (역할에 맞는) 인스턴스가 수행하게 한다.(의인화)
 * 3. 각 결과를 종합한다.
 */
public class Application {
	public static void main(String[] args) {
//		NumberGenerator generator = new NumberGenerator();
//		List<Integer> numbers = generator.createRandomNumbers();
//		System.out.println(numbers);

		Judgment judgment = new Judgment();
//		final int count = judgment.correctCount(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3));
//		System.out.println(count);

		boolean place = judgment.hasPlace(Arrays.asList(7, 8, 9), 1, 7);
		System.out.println(place);
	}
}
