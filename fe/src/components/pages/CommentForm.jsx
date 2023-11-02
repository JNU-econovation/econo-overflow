import React from "react";

const CommentForm = (props) => {
  return (
    <div>
      <form className="border" onsubmit={props.commentSubmitHandler}>
        <input type="text" placeholder="댓글" value={props.comment} onChange={props.commentHandler} />
        <button type="submit">등록</button>
      </form>
    </div>
  );
};

export default CommentForm;
