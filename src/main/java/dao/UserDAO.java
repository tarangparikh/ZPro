package dao;

import vo.UserVO;

import java.util.List;

public class UserDAO {
    private final DBOperationDAO dbOperationDAO = initialize();
    private DBOperationDAO initialize(){return new DBOperationDAO();}
    public void insert(UserVO userVO){
            dbOperationDAO.openCurrentSessionWithTransaction();
            dbOperationDAO.insert(userVO);
            dbOperationDAO.closeCurrentSessionWithTransaction();
    }
    public List<UserVO> getList(){
        dbOperationDAO.openCurrentSessionWithTransaction();
        List<UserVO> list = dbOperationDAO.getList(UserVO.class,"from vo.UserVO");
        dbOperationDAO.closeCurrentSessionWithTransaction();
        return list;
    }
}
