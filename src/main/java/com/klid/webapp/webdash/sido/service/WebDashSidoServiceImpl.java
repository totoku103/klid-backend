package com.klid.webapp.webdash.sido.service;

import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.MsgService;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.webdash.sido.persistence.WebDashSidoMapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service("webDashSidoService")
public class WebDashSidoServiceImpl extends MsgService implements WebDashSidoService {

	private final WebDashSidoMapper mapper;

	public WebDashSidoServiceImpl(MessageSource messageSource, WebDashSidoMapper mapper) {
		super(messageSource);
		this.mapper = mapper;
	}

	/** 공지사항리스트 */
	@Override
	public ReturnData getNoticeList(Criterion criterion) {
		return new ReturnData(mapper.getNoticeList(criterion.getCondition()));
	}

	/** 보안리스트 */
	@Override
	public ReturnData getSecuList(Criterion criterion) {
		return new ReturnData(mapper.getSecuList(criterion.getCondition()));
	}

	/**수동차단 */
	@Override
	public ReturnData getRegionStatusManual(Criterion criterion) {
		return new ReturnData(mapper.getRegionStatusManual(criterion.getCondition()));
	}

	/**위변조 */
	@Override
	public ReturnData getForgeryCheck(Criterion criterion) {
		return new ReturnData(mapper.getForgeryCheck(criterion.getCondition()));
	}

	/**헬스체크 */
	@Override
	public ReturnData getHcCheck(Criterion criterion) {
		return new ReturnData(mapper.getHcCheck(criterion.getCondition()));
	}

	/**처리현황 */
	@Override
	public ReturnData getProcess(Criterion criterion) {
		return new ReturnData(mapper.getProcess(criterion.getCondition()));
	}

	/**시도리스트 */
	@Override
	public ReturnData getSidoList(Criterion criterion) {
		return new ReturnData(mapper.getSidoList(criterion.getCondition()));
	}
}
