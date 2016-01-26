package net.oschina.crypto;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.Conversion;

public class PartitionUtil {
	/**
	 * 
	 * @param id 需转换的主键
	 * @param i 需分布的规模 2的次方
	 * @return
	 */
	public static Long partitioning(String id, int i) {

		byte[] bs = DigestUtils.sha1(id);

		UUID long1 = Conversion.byteArrayToUuid(bs, 0);
		double d = Math.pow(2, i);

		Long long2 = Math.round(d);

		Long l = (long) (long1.getLeastSignificantBits() % long2);

		return Math.abs(l);
	}

}
