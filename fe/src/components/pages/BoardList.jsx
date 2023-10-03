import React from 'react';
import MDEditor from '@uiw/react-md-editor';

const BoardList = () => {
  return (
    <div className='grid place-items-center'>
      <div className='flex flex-col rounded-lg bg-white shadow-[0_2px_15px_-3px_rgba(0,0,0,0.07),0_10px_20px_-2px_rgba(0,0,0,0.04)] md:max-w-2xl md:flex-row'>
        <img
          className='h-96 w-full rounded-t-lg object-cover md:h-auto md:w-48 md:rounded-none md:rounded-l-lg'
          src='https://tecdn.b-cdn.net/wp-content/uploads/2020/06/vertical.jpg'
          alt=''
        />
        <div className='flex flex-col justify-start p-6'>
          <h5 className='mb-2 text-xl font-medium text-neutral-800 dark:text-neutral-50'>
            Card title
          </h5>
          <p className='mb-4 text-base text-neutral-600 dark:text-neutral-200'>
            This is a wider card with supporting text below as a natural lead-in
            to additional content. This content is a little bit longer.
          </p>
          <p className='text-xs text-neutral-500 dark:text-neutral-300'>
            Last updated 3 mins ago
          </p>
        </div>
      </div>
    </div>
  );
};

export default BoardList;
