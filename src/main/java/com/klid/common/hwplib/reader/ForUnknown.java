package com.klid.common.hwplib.reader;

import com.klid.common.hwplib.object.etc.UnknownRecord;
import com.klid.common.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 알수 없는 레코드를 읽기 위한 객체
 * 
 */
public class ForUnknown {
	/**
	 * 알수 없는 레코드를 읽는다.
	 * 
	 * @param unknown
	 *            알 수 없는 레코드
	 * @param sr
	 *            스트림 리더
	 * @throws IOException
	 */
	public static void read(UnknownRecord unknown, StreamReader sr)
			throws IOException {
		byte[] body = new byte[(int)unknown.getHeader().getSize()];
		sr.readBytes(body);
		unknown.setBody(body);
	}
}
