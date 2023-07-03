package sld.vinmusic.service.impl;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sld.vinmusic.dao.VinlikeMapper;
import sld.vinmusic.dto.VinRequest;
import sld.vinmusic.entity.Vinlike;
import sld.vinmusic.service.VinService;
import sld.vinmusic.util.Global;

@Service
public class VinServiceRedisImpl implements VinService {
	@Autowired
	private VinlikeMapper dao;
	@Resource
	private RedisTemplate<String, Long> rtKeyMood;
	@Resource
	private RedisTemplate<Long, VinRequest> rtMoodVinReq;

	public boolean isLiked(Vinlike vinlike) {
		return getHistoryVin(vinlike) != null;
	}

	public Vinlike getHistoryVin(Vinlike vinlike) {
		var vin = dao.selectByMoodAndAccount(vinlike);
		if (vin == null) {
			var setVin = rtMoodVinReq.opsForSet()
				.members(vinlike.getMoodId());

			if (setVin == null)
				return null;

			var first = setVin
				.stream()
				.filter(
					e -> e.getMoodId().equals(vinlike.getMoodId()) &&
						e.getAvId().equals(vinlike.getAvId())
				).findFirst();

			if (first.isPresent())
				vin = first.get();
		}
		return vin;
	}

	public int addVin(Vinlike vinlike) {
		var opsVin = rtMoodVinReq.opsForSet();
		var opsMood = rtKeyMood.opsForSet();

		if (opsVin.members(vinlike.getMoodId()) == null)
			opsMood.add(Global.VINLST_HASH_KEY,
				vinlike.getMoodId());

		var vinReq = new VinRequest();
		vinReq.setLike(true);
		vinReq.setAvId(vinlike.getAvId());
		vinReq.setMoodId(vinlike.getMoodId());

		return Math.toIntExact(
			opsVin.add(vinlike.getMoodId(),
				vinReq
			)
		);
	}

	public int withdrawVin(Vinlike vinlike) {
		var ops = rtMoodVinReq.opsForSet();
		if (ops.members(vinlike.getMoodId()) == null)
			return 0;

		var vinReq = new VinRequest();
		vinReq.setLike(false);
		vinReq.setAvId(vinlike.getAvId());
		vinReq.setMoodId(vinlike.getMoodId());

		return Math.toIntExact(
			ops.remove(vinlike.getMoodId(),
				vinReq)
		);
	}
}
