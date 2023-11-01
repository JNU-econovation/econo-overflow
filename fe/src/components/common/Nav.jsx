import React from "react";
import LoginSuccess from "../login/LoginSuccess";
import { useSelector } from "react-redux";

const Nav = () => {
  const selector = useSelector((state) => state.user);

  return (
    <nav className="flex justify-end">
      <div className="flex justify-end items-center">
        {selector.isLogin && <LoginSuccess />}
        {!selector.isLogin && <a className="navComponent">로그인</a>}
      </div>
    </nav>
  );
};

export default Nav;
