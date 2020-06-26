package ink.zerohua.bookofgeek.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer uId;
    private String username;
    private String phoneNumbers;
    private String email;
    //阻止网络传输
    private transient String password;
    private transient String salt;
    //1是男，2是女
    private int sex;
    //所属公司
    private String company;
    //座右铭
    private String motto;
    //注册时间
    private Date startDate;
}
