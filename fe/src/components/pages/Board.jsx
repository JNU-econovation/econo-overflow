import React from 'react';
import MDEditor from '@uiw/react-md-editor';

const Board = () => {
  const [value, setValue] = React.useState('**Hello world!!!**');

  return (
    <div className='container'>
      <MDEditor height={700} value={value} onChange={setValue} />
    </div>
  );
};

export default Board;
