package myJunit.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserInfoMapper {

    public Integer countUser();

    public Integer batchUpdateUserInfo(@Param("userInfo") List<Map<String,Object>> userInfo);

}
