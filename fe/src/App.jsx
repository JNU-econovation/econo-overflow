import './App.css';
import React from 'react';
import TitleLogo from './components/common/TitleLogo';
import LoginForm from './components/login/LoginForm';
import Nav from './components/common/Nav';

function App() {
  return (

      <div className="App">
        <header>
          <TitleLogo />
          <Nav />
        </header>
        <LoginForm />
      </div>

  );
}

export default App;
