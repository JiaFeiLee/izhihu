package cn.edu.xmut.izhihu.pojo.request;

import lombok.Data;

/**
 * @Description:
 * @Author: qiuguanlin
 * @Date: 2019-07-06 18:46
 * @Version: 1.0
 */
@Data
public class UpdateFavoriteRequest {
    private String favoriteId;
    private String name;
    private String describe="暂无描述";
    private int isPublic;
}
