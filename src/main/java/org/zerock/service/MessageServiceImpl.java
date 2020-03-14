package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dao.MessageDAO;
import org.zerock.dao.PointDAO;
import org.zerock.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDAO messageDao;
	
	@Autowired
	private PointDAO pointDao;

	
	//aop를 통한 트랜잭션 적용대상
	@Transactional
	@Override
	public void addMessage(MessageVO vo) {

		/*
		 * 이클립스 개발툴에서 왼쪽에 보이는 위아래 화살표는 스프링 aop의 어드바이스의 적용 범위를 나타내는 Around타입을 뜻한다. - 안보일수도 있고...
		 * 1. Advice(어드바이스) : 실제 기능을 구현한 객체 // 지금은 이 클래스 전부가 advice
		 * 2. Around타입 : target 메서드를 호출전 이후 모두 적용(가장 광범위하게 사용됨)
		 * 3. target: 대상 메서드를 가지는 객체
		 * 
		 */
		
		this.messageDao.create(vo);//메시지 추가
		this.pointDao.updatePoint(vo.getSender(),10);//메시지 보낸 사람 10점 up
		// 트랜잭션 적용 해야하는 부분 : create가 수행되고 updatePoint가 에러일때 create에서 접근하는 db는 변경 됨    , 자동 커밋이 문제인가..?
		//@Transactional : 해결 끝
		
		
		
	}
	
	
	
	
}
