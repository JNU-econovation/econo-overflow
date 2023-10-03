import React from 'react';
import MDEditor from '@uiw/react-md-editor';

const BoardCreate = () => {
  const [value, setValue] = React.useState('**Hello world!!!**');

  return (
    <div className='container'>
      <div>
        <MDEditor height={700} value={value} onChange={setValue} />
      </div>
      <div className='grid place-items-center'>
        <button
          className='w-[5rem] h-10 rounded-lg text-base cursor-pointer mt-8 bg-blue text-white'
          type='submit'
        >
          글쓰기
        </button>
      </div>
    </div>
  );
};

export default BoardCreate;
