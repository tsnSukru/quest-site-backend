package com.project.questsite.bussines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.questsite.dataAccess.ICommentDal;
import com.project.questsite.entities.Comment;
import com.project.questsite.entities.Post;
import com.project.questsite.entities.User;
import com.project.questsite.request.CommentCreateRequest;
import com.project.questsite.request.CommentDeleteRequest;
import com.project.questsite.request.CommentUpdateRequest;

@Service
public class CommentManager implements ICommentService {

	ICommentDal commentDal;
	IUserService userManager;
	IPostService postManager;

	@Autowired
	public CommentManager(ICommentDal commentDal, IUserService userManager, IPostService postManager) {
		// TODO Auto-generated constructor stub
		this.commentDal = commentDal;
		this.userManager = userManager;
		this.postManager = postManager;
	}

	@Override
	@Transactional
	public List<Comment> getAll(Long postId) {
		// TODO Auto-generated method stub
		return commentDal.getAll(postId);
	}

	@Override
	@Transactional
	public Comment getById(Long id) {
		// TODO Auto-generated method stub
		return commentDal.getById(id);
	}

	@Override
	@Transactional
	public void add(CommentCreateRequest commentCreateRequest) {
		// TODO Auto-generated method stub
		User user = userManager.getById(commentCreateRequest.userId);
		Post post = postManager.getById(commentCreateRequest.postId);
		if (user == null || post == null) {
			System.out.println("Post ya da kullanici yok");
		} else {
			Comment comment = new Comment();
			comment.setId(commentCreateRequest.id);
			comment.setUser(user);
			comment.setPost(post);
			comment.setText(commentCreateRequest.text);
			commentDal.add(comment);
		}

	}

	@Override
	@Transactional
	public void update(CommentUpdateRequest commentUpdateRequest) {
		// TODO Auto-generated method stub
		Comment comment = commentDal.getById(commentUpdateRequest.id);
		if (comment == null) {
			System.out.println("yorum yok");
		} else {
			comment.setText(commentUpdateRequest.text);
			commentDal.update(comment);
		}
	}

	@Override
	@Transactional
	public void delete(CommentDeleteRequest commentDeleteRequest) {
		// TODO Auto-generated method stub
		Comment comment = commentDal.getById(commentDeleteRequest.id);
		if (comment == null) {
			System.out.println("yorum yok");
		} else {
			commentDal.delete(comment);
		}
	}
}
