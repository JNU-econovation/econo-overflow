import React from "react";
import props from "CommentForm.jsx";
const CommentList = () => {
  const { commentArray } = props.commentArray;
  return (
    <div>
      <ul className="commentList border">
        {commentArray.map((comment) => (
          <li className="comment border" key={comment.id}>
            <p>{comment.nickname}</p>
            <p>{comment.content}</p>
            <p>{comment.createdAt}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CommentList;
