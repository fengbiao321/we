package myJunit.service;

import myJunit.dao.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    public Integer userTotalCount(){
        return userInfoMapper.countUser();
    }

    public int batchUpdateUserInfo(List<Map<String,Object>> userinfo){
        return userInfoMapper.batchUpdateUserInfo(userinfo);
    }

    @Transactional(rollbackFor = Exception.class)
    public int batchUpdateUserInfoException(List<Map<String,Object>> userinfo) throws Exception {
        Integer i = userInfoMapper.batchUpdateUserInfo(userinfo);
//        try{
            if (i > 0){
                throw new Exception();
            }
//        }catch (Exception e){
//            throw new RuntimeException();
//        }
        return i;
    }

}
