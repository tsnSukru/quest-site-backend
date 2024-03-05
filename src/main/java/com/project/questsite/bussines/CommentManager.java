package com.project.questsite.bussines;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.questsite.dataAccess.ICommentDal;
import com.project.questsite.dataAccess.IPostDal;
import com.project.questsite.entities.Comment;
import com.project.questsite.entities.Post;
import com.project.questsite.entities.User;
import com.project.questsite.requests.CommentCreateRequest;
import com.project.questsite.requests.CommentDeleteRequest;
import com.project.questsite.requests.CommentUpdateRequest;
import com.project.questsite.responses.CommentResponse;

@Service
public class CommentManager implements ICommentService {

	ICommentDal commentDal;
	IUserService userManager;
	IPostDal postDal;

	@Autowired
	public CommentManager(ICommentDal commentDal, IUserService userManager, IPostDal postDal) {
		// TODO Auto-generated constructor stub
		this.commentDal = commentDal;
		this.userManager = userManager;
		this.postDal = postDal;
	}

	@Override
	@Transactional
	public List<CommentResponse> getAll(Long postId) {
		// TODO Auto-generated method stub
		List<Comment> comments = commentDal.getAll(postId);
	    List<CommentResponse> commentResponses = comments.stream().map(comment -> new CommentResponse(comment.getId(), comment.getText(),
	    		comment.getCreateDate(),comment.getPost().getId(),comment.getUser().getId(),comment.getUser().getUserName(),comment.getUser().getAvatar()
	    		)).collect(Collectors.toList());
		return commentResponses;
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
		Post post = postDal.getById(commentCreateRequest.postId);
		if (user == null || post == null) {
			System.out.println("Post ya da kullanici yok");
		} else {
			Comment comment = new Comment();
			comment.setUser(user);
			comment.setPost(post);
			comment.setText(commentCreateRequest.text);
			comment.setCreateDate(new Date());
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
