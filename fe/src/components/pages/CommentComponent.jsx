import React, { useState } from "react";
import CommentForm from "./CommentForm";
import MockCommentList from "./MockCommentList";

const CommentComponent = () => {
  const [comment, setComment] = useState("");
  const [commentArray, setCommentArray] = useState([]);
  const commentHandler = (e) => {
    setComment(e.target.value);
  };

  const commentSubmitHandler = (e) => {
    e.preventDefault();
    if (comment === "") {
      alert("댓글을 입력해주세요.");
      return;
    }
    setCommentArray((commentValueList) => [comment, ...commentValueList]);
    setComment("");
  };

  return (
    <div>
      <MockCommentList />
      <CommentForm
        comment={comment}
        setComment={setComment}
        commentArray={commentArray}
        setCommentArray={setCommentArray}
      />
    </div>
  );
};

export default CommentComponent;
