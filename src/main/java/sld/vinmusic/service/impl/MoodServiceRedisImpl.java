package sld.vinmusic.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sld.vinmusic.dao.MoodDetailMapper;
import sld.vinmusic.dao.MoodMapper;
import sld.vinmusic.dto.MoodResponse;
import sld.vinmusic.dto.MoodsResponse;
import sld.vinmusic.dto.VinRequest;
import sld.vinmusic.entity.Account;
import sld.vinmusic.entity.Mood;
import sld.vinmusic.service.AccountService;
import sld.vinmusic.service.MoodService;
import sld.vinmusic.util.Global;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoodServiceRedisImpl implements MoodService {
	@Resource
	private RedisTemplate<Long, VinRequest> rtMoodVinReq;

	@Autowired
	private MoodMapper moodMapper;
	@Autowired
	private MoodDetailMapper moodDetailMapper;

	@Override
	public MoodsResponse findAll() {
		var moodList = moodMapper.selectAll();
		if (moodList == null)
			return MoodsResponse.builder()
				.moodList(new ArrayList<>())
				.build();

		return wrap(moodList);
	}

	@Override
	public MoodsResponse findByAccount(Account account) {
		return null;
	}

	@Override
	public MoodsResponse wrap(List<Mood> list) {
		var dtoList = new ArrayList<MoodResponse>();

		list.forEach(item -> {
			var dto = MoodResponse.builder()
				.mood(item)
				.vinCount(getVinCount(item.getId()))
				.build();

			dtoList.add(dto);
		});

		return MoodsResponse.builder()
			.ok(true)
			.moodList(dtoList)
			.build();
	}

	private long getVinCount(long mood) {
		long countRedisVin = rtMoodVinReq.opsForSet().members(mood) == null ? 0 :
			rtMoodVinReq.opsForSet().members(mood).size();
		return moodDetailMapper
			.selectByMood(mood)
			.getVinCount()
			+ countRedisVin;
	}
}
