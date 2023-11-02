import React, { useState } from "react";
import mockPosts from "../../mockData/mockPosts";
import "./BoardRead.css";
import CommentComponent from "./CommentComponent";

const BoardRead = () => {
  const [post, setPosts] = useState(mockPosts);

  return (
    <div className="container">
      <div className="title">
        <p>{post[0].title}</p>
      </div>
      <div className="content">
        <p>{post[0].content}</p>
      </div>
      <div className="comment">
        <CommentComponent />
      </div>
    </div>
  );
};

export default BoardRead;
