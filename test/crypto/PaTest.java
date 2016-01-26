package net.oschina.crypto;

import org.apache.commons.lang3.RandomStringUtils;

public class PaTest {
	public static void main(String[] args) throws Exception {
		// String string = "ZDC524";

		String s;
		int i2 = 0;
		int j = 0;
		int z1 = 0;
		int i3 = 0;
		int i4 = 0;
		int i5 = 0;
		long l;
		for (int i = 0; i < 320; i++) {

			s = RandomStringUtils.randomNumeric(18);
			l = PartitionUtil.partitioning(s, 4);// 生成分库ID
			if (l == 0L) {
				i2++;
			}
			if (l == 1L) {
				j++;
			}
			if (l == 2L) {
				z1++;
			}
			if (l == 3L) {
				i3++;
			}
			if (l == 4L) {
				i4++;
			}
			if (l == 5L) {
				i5++;
			}
		}
		System.out.println("0号库" + i2);
		System.out.println("1号库" + j);
		System.out.println("2号库" + z1);
		System.out.println("3号库" + i3);
		System.out.println("4号库" + i4);
		System.out.println("5号库" + i5);
	}
}
