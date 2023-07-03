package sld.vinmusic.service;

import org.springframework.stereotype.Service;
import sld.vinmusic.dto.MoodResponse;
import sld.vinmusic.dto.MoodsResponse;
import sld.vinmusic.entity.Account;
import sld.vinmusic.entity.Mood;

import java.util.List;

@Service
public interface MoodService {
    public MoodsResponse findAll();
    public MoodsResponse findByAccount(Account account);

    MoodsResponse wrap(List<Mood> list);
}