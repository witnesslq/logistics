package businesslogicservice.logisticsBLservice;

import java.util.ArrayList;

import vo.LogisticsVO;

public interface LogisticsBLService {

	/**
	 * 前置：输入物流单号查询物流信息且该单号存在
	 * 后置：显示物流信息
	 * @param id
	 * @return
	 */
	public LogisticsVO showState(String id);
	/**
	 * 前置：输入物流单号并修改物流信息
	 * @param id
	 * @param state
	 * @return
	 */
	public boolean setState(String id,String state);
	public boolean setState(LogisticsVO o);
	public boolean setState(ArrayList<LogisticsVO> al);
}
