package com.klid.common.hwplib.reader.bodytext.paragraph.memo;

import com.klid.common.hwplib.object.bodytext.paragraph.memo.MemoList;
import com.klid.common.hwplib.util.compoundFile.reader.StreamReader;

import java.io.IOException;

/**
 * 메모 리스트 레코드를 읽기 위한 객체
 * 
 */
public class ForMemoList {
	/**
	 * 메모 리스트 레코드를 읽는다.
	 * 
	 * @param ml
	 *            메모 리스트 레코드 객체
	 * @param sr
	 *            스트림 리더
	 * @throws IOException
	 */
	public static void read(MemoList ml, StreamReader sr) throws IOException {
		ml.setMemoIndex(sr.readUInt4());
	}
}
