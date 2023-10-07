import React, { useState } from 'react';
import mockPosts from '../../mockData/mockPosts';
import './BoardRead.css';

const BoardRead = () => {
  const [post, setPosts] = useState(mockPosts);

  return (
    <div className='container'>
      <div className='title'>
        <p>{post[0].title}</p>
      </div>
      <div className='content'>
        <p>{post[0].content}</p>
      </div>
      <div className='comment'>
        <p>댓글창임여</p>
      </div>
    </div>
  );
};

export default BoardRead;
