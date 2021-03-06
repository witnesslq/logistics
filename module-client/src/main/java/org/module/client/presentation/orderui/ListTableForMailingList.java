package org.module.client.presentation.orderui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import org.module.client.businesslogic.orderbl.MailingControl;
import org.module.client.businesslogicservice.orderBLservice.MailingBLService;
import org.module.client.presentation.ResultFrame;
import org.module.client.vo.MailingListVO;

public class ListTableForMailingList extends ListTableForAll {

	private static final long serialVersionUID = 83627053089339309L;
	protected ArrayList<MailingListVO> listCell;
	protected MailingBLService controller ;
	
	@Override
	protected void initData() {
		controller = new MailingControl();
		this.listCell = this.controller.getAll();
		this.typeArray = new String[]{
				"快递编号","类型","寄件人","出发城市","收件人","目的城市","状态"
		};
	}

	@Override
	protected void refresh() {
		this.listCell = this.controller.getAll();
		this.table.setList(listCell);
		this.table.fireTableDataChanged();
	}

	@Override
	protected void modify() {
		int[] indexes = this.table.getCheckedIndexes();
		if(indexes.length!=1){
			return;
		}
		final NewMailingListInputFrame frame = new NewMailingListInputFrame(
				this.listCell.get(indexes[0]),this.controller
				);
		frame.setVisible(true);
		frame.getComfirm().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(frame.isDataUsable()){
					if(controller.update(frame.getVO())){
						frame.dispose();
						new ResultFrame(true);
					}else{
						new ResultFrame(false);
					}
					table.fireTableDataChanged();
					
				}
			}
		});
	}

	@Override
	protected void add() {
		final NewMailingListInputFrame frame = new NewMailingListInputFrame(
				this.controller
				);
		frame.setVisible(true);
		frame.getComfirm().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(frame.isDataUsable()){
					if(controller.creat(frame.getVO())){
						frame.dispose();
						new ResultFrame(true);
					}else{
						new ResultFrame(false);
					}
					table.fireTableDataChanged();
					
				}
			}
		});
	}

}
