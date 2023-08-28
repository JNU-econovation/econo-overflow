import React from 'react'
import './login.css'

const LoginForm = () => {
  return (
    <div className='login-container'>
      <form className='login-form'>
        <div className="login-input">
            <input type="text" className="username" name="username" placeholder="아이디" unrequired/>
        </div>
        <div className="login-input">
            <input type="password" className="password" name="password" placeholder="비밀번호" unrequired />
        </div>
        <div class="login-input">
            <button className="login-btn" type="submit">
                로그인
            </button>
            <button class="login-slack">
                Slack으로 로그인
                <img class="slack-icon" src="/icon/slack-icon.png" alt="slack-icon"/>
            </button>
        </div>
      </form>
    </div>
  )
}

export default LoginForm;
