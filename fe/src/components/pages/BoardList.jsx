import React, { useState } from 'react';
import mockPosts from '../../mockData/mockPosts';

const BoardList = () => {
  const [post, setPosts] = useState(mockPosts);
  console.log(post[0].title);

  return (
    <div>
      {post.map((a, i) => {
        console.log(a);
        return (
          <div>
            <div className='m-10 card block rounded-lg bg-white p-6 shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] dark:bg-neutral-700'>
              <h5 className='mb-2 text-xl font-medium leading-tight text-neutral-800 dark:text-neutral-50'>
                {post[i].title}
              </h5>
              <p className='mb-4 text-base text-neutral-600 dark:text-neutral-200'>
                {post[i].content}
              </p>
              <button
                className='w-[5rem] h-10 rounded-lg text-base cursor-pointer mt-8 bg-blue text-white'
                type='submit'
              >
                보러가기
              </button>
            </div>
          </div>
        );
      })}
    </div>
  );
};

export default BoardList;
