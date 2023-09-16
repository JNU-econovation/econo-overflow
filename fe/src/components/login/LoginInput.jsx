import React from "react";
import "./login.css";
import LoginRegEmail from "./LoginRegEmail";
import LobinRegPw from "./LoginRegPw";

const LoginInput = ({
  email,
  password,
  setEmail,
  setPassword,
  isValidEmail,
  isValidPassWord,
}) => {
  return (
    <div>
      <div className="login-input">
        <input
          type="text"
          className="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          placeholder="이메일"
        />
        <LoginRegEmail isValidEmail={isValidEmail} />
      </div>
      <div className="login-input">
        <input
          type="password"
          className="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="비밀번호"
        />
      </div>
      <LobinRegPw isValidPassWord={isValidPassWord} />
    </div>
  );
};

export default LoginInput;
