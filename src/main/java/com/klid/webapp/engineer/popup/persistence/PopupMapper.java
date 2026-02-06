package com.klid.webapp.engineer.popup.persistence;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface PopupMapper {

	/** page 추가 */
	int addPage(Map<String, Object> paramMap);
	
	/** page 수정 */
	void savePage(Map<String, Object> paramMap);

	/** page 삭제 */
	void delPage(Map<String, Object> paramMap);
	
	/** pageGroup 추가 */
	int addPageGroup(Map<String, Object> paramMap);
	
	/** pageGroup 수정 */
	void savePageGroup(Map<String, Object> paramMap);
	
	/** pageGroup 삭제 */
	void delPageGroup(Map<String, Object> paramMap);
	
	/** Menu 추가 */
	void addMenu(Map<String, Object> paramMap);
	
	/** Menu 수정 */
	void saveMenu(Map<String, Object> paramMap);
	
	/** Menu 삭제 */
	void delMenu(Map<String, Object> paramMap);
	
}
