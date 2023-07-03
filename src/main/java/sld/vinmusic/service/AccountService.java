package sld.vinmusic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sld.vinmusic.dao.AccountMapper;
import sld.vinmusic.entity.Account;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accountMapper;
    public Account getById(long id){
        var result = accountMapper.selectById(id);
        return result;
    }
}
