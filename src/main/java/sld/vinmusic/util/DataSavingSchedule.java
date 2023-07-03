package sld.vinmusic.util;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sld.vinmusic.dto.VinRequest;
import sld.vinmusic.entity.Vinlike;
import sld.vinmusic.service.MoodService;
import sld.vinmusic.service.VinService;

import java.util.Set;

@Component
@Configurable
@EnableScheduling
public class DataSavingSchedule {
    @Resource
    private RedisTemplate<String, Long> rtKeyMood;
    @Resource
    private RedisTemplate<Long, VinRequest> rtMoodVinReq;

    @Qualifier("vinServiceRedisImpl")
    private VinService redisService;
    @Qualifier("vinServiceDBImpl")
    private VinService dbService;
    private MoodService moodService;

    @Scheduled(cron = "*/10 * * * * *")
    public void saveToDataBase() {
        Set<Long> moods = rtKeyMood.opsForSet()
                .members(Global.VINLST_HASH_KEY);

        if (moods == null) return;

        moods.forEach(moodId->{
            Set<VinRequest> reqList = rtMoodVinReq.opsForSet()
                    .members(moodId);
            if(reqList == null) return;

            reqList.forEach(item->{
                item.isLike() ? dbService.addVin(item) : dbService.withdrawVin(item)
            });

            rtMoodVinReq.delete(moodId);
        });

        rtKeyMood.delete(Global.VINLST_HASH_KEY);
    }
}
