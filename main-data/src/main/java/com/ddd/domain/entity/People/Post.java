package com.ddd.domain.entity.People;

import com.questInvest.vo.TreeNode;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class Post {

    private Org org;

    private String postName;

    private int postId;

    private int postType;

    private TreeNode<PostInfo> postNode;


    public void test() {
        System.out.println("post");
    }

    public void postConifg() {
        System.out.println("postConifg");
    }

//    public Post lookUpSubPost(int postID)
//    {
//        Post tn = (Post)super.lookUpSubNode(postID);
//        tn.postConifg();
//        return tn;
//    }

//    @Override
//    public TreeNode lookUpSubNode(int nodeId) {
//        return super.lookUpSubNode(nodeId);
//    }
}
