import React from "react";
import mockComment from "../../mockData/mockComment";

const MockCommentList = () => {
  return (
    <div>
      <ul className="commentList border">
        {mockComment.map((comment) => (
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

export default MockCommentList;
