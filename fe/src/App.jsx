import './index.css';
import React from 'react';
import Header from './components/common/Header';
import LoginForm from './components/login/LoginForm';
import Main from './components/main/Main';
import SignUpForm from './components/signup/SignUpForm';
import Loading from './components/common/Loading';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Board from './components/pages/Board';

function App() {
  return (
    <div className='App'>
      <BrowserRouter>
        <header>
          <Header />
        </header>
        <Routes>
          <Route path='/' element={<Main />}></Route>
          <Route path='/login' element={<LoginForm />} />
          <Route path='/signup' element={<SignUpForm />} />
          <Route path='/loading' element={<Loading />} />
          <Route path='/board' element={<Board />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
