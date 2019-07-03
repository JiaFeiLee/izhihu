package cn.edu.xmut.izhihu.service.impl;

import cn.edu.xmut.izhihu.dao.AgreeOpposeMapper;
import cn.edu.xmut.izhihu.dao.AttentionMapper;
import cn.edu.xmut.izhihu.pojo.common.ResultVO;
import cn.edu.xmut.izhihu.pojo.common.SuccessVO;
import cn.edu.xmut.izhihu.pojo.entity.AgreeOppose;
import cn.edu.xmut.izhihu.pojo.entity.Attention;
import cn.edu.xmut.izhihu.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Description:
 * @Author: qiuguanlin
 * @Date: 2019-06-30 12:40
 * @Version: 1.0
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private AgreeOpposeMapper agreeOpposeMapper;

    @Autowired
    private AttentionMapper attentionMapper;

    /**
     * 关注某事
     *
     * @param userId    用户id
     * @param contentId 内容id
     * @param type      类别（1回答，2文章，3想法，4问题，5话题，6用户，7收藏夹，8专栏）
     * @return
     */
    @Override
    public ResultVO infollow(String userId, String contentId, int type) {
        if (this.checkFollow(userId, contentId)) {
            return new SuccessVO("已关注");
        }
        Attention record = new Attention();
        record.setUserId(userId);
        record.setAttId(contentId);
        record.setType(type);
        attentionMapper.insertSelective(record);
        return new SuccessVO("已关注");
    }

    /**
     * 取消关注某事
     *
     * @param userId    用户id
     * @param contentId 内容id
     * @return
     */
    @Override
    public ResultVO unfollow(String userId, String contentId) {
        if (!this.checkFollow(userId, contentId)) {
            return new SuccessVO("尚未关注");
        }
        Attention att = new Attention();
        att.setUserId(userId);
        att.setAttId(contentId);
        attentionMapper.delete(att);

        return new SuccessVO("已取消关注");
    }

    /**
     * 是否已关注
     *
     * @param userId    用户id
     * @param contentId 内容id
     * @return 已关注返回True 未关注返回False
     */
    @Override
    public Boolean checkFollow(String userId, String contentId) {
        Example example = new Example(Attention.class);
        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("attId", contentId);

        List<Attention> record = attentionMapper.selectByExample(example);
        if (record.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 点赞某事
     *
     * @param userId    用户id
     * @param contentId 内容id
     * @return
     */
    @Override
    public ResultVO like(String userId, String contentId) {
        if (this.checkLike(userId, contentId)) {
            return new SuccessVO("已点赞");
        }
        AgreeOppose record = new AgreeOppose();
        record.setUserId(userId);
        record.setArticleId(contentId);
        record.setAgrOpp(1);
        agreeOpposeMapper.insertSelective(record);
        return new SuccessVO("点赞成功");
    }

    /**
     * 不赞成某事
     *
     * @param userId    用户id
     * @param contentId 内容id
     * @return
     */
    @Override
    public ResultVO unlike(String userId, String contentId) {
        if (this.checkLike(userId, contentId)) {
            return new SuccessVO("已不赞成");
        }
        AgreeOppose record = new AgreeOppose();
        record.setUserId(userId);
        record.setArticleId(contentId);
        record.setAgrOpp(0);
        agreeOpposeMapper.insertSelective(record);
        return new SuccessVO("不赞成成功");
    }

    /**
     * 取消点赞和不赞成，删除自己的态度
     *
     * @param userId    用户id
     * @param contentId 内容id
     * @return
     */
    @Override
    public ResultVO cancelLike(String userId, String contentId) {
        Example example = new Example(AgreeOppose.class);
        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("articleId", contentId);
        agreeOpposeMapper.deleteByExample(example);
        return new SuccessVO("已取消态度");
    }

    /**
     * 是否已点赞某事
     *
     * @param userId    用户id
     * @param contentId 内容id
     * @return 已点赞返回True 未点赞返回False
     */
    @Override
    public Boolean checkLike(String userId, String contentId) {
        Example example = new Example(AgreeOppose.class);
        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("articleId", contentId)
                .andEqualTo("agrOpp", 1);
        AgreeOppose record = agreeOpposeMapper.selectOneByExample(example);
        if (record != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否已不赞成某事
     *
     * @param userId    用户id
     * @param contentId 内容id
     * @return 已不赞成返回True 未不赞成返回False
     */
    @Override
    public Boolean checkUnLike(String userId, String contentId) {
        Example example = new Example(AgreeOppose.class);
        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("articleId", contentId)
                .andEqualTo("agrOpp", 0);
        AgreeOppose record = agreeOpposeMapper.selectOneByExample(example);
        if (record != null) {
            return true;
        } else {
            return false;
        }
    }
}
