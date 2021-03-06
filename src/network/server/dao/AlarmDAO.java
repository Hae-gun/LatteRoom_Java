package network.server.dao;

import java.util.Iterator;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import network.server.mybatis.MyBatisFactory;
import network.server.vo.Alarm;
import network.server.vo.AlarmData;

public class AlarmDAO {
	
	public Alarm selectAlarm(String userNo) {
		try (SqlSession sqlSession = MyBatisFactory.getSqlSession()) {
			return sqlSession.selectOne("AlarmMapper.selectItem", userNo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int updateAlarm(Alarm data) {
		try (SqlSession sqlSession = MyBatisFactory.getSqlSession()) {
			data.nullCheck();
			data.setWeeks(data.getWeeks().toUpperCase());
			return sqlSession.update("AlarmMapper.updateItem", data);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<AlarmData> selectAlarmData(String userNo) {
		try (SqlSession sqlSession = MyBatisFactory.getSqlSession()) {
			return sqlSession.selectList("AlarmDataMapper.selectItem", userNo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int updateAlarmDataOne(AlarmData data) {
		try (SqlSession sqlSession = MyBatisFactory.getSqlSession()) {
			data.nullCheck();
			return sqlSession.update("AlarmDataMapper.updateItem", data);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int updateAlarmDataAll(List<AlarmData> list) {
		try (SqlSession sqlSession = MyBatisFactory.getSqlSession()) {
			
			Iterator<AlarmData> iterator = list.iterator();
			int size = list.size();
			int result = 0;
			
			while(iterator.hasNext()) {
				AlarmData data = iterator.next();
				data.nullCheck();
				data.setType(data.getType().toUpperCase());
				result += sqlSession.update("AlarmDataMapper.updateItem", data);
			}
			
			if(size==result) return 1;
			else return 0;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
