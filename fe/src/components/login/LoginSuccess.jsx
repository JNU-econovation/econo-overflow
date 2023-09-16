import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { clearUser } from "../../store/userSlice.js";
import "./login.css";

const LoginSuccess = () => {
  const user = useSelector((state) => state.user);
  const dispatch = useDispatch();

  const logoutFunction = (e) => {
    e.preventDefault();
    localStorage.removeItem("accessToken");
    dispatch(clearUser(user));
    console.log(user);
  };
  return (
    <div className="menu-container">
      <p className="menu-content-user">님 </p>
      <button className="menu-content-button" onClick={logoutFunction}>
        로그아웃
      </button>
    </div>
  );
};

export default LoginSuccess;
