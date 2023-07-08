package tn.esprit.spring.Service;

import tn.esprit.spring.entity.ForumComment;

import java.util.List;

public interface IForumCmntService {

    public ForumComment addNewComment(ForumComment comment);
//    public void affectCommentToPost (int idComment, int idPost);

    public ForumComment editComment(ForumComment comment);

    public void deleteComment(int idComment);

    public List<ForumComment> retrieveAll();

    public ForumComment assignPostToComment(ForumComment comment, int idPost);

    public List<ForumComment> getPostComments(int postId);
}
