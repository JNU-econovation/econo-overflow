import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { clearUser } from "../../store/userSlice.js";
import { Link } from "react-router-dom";

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
      <button className="mr-[4rem]" onClick={logoutFunction}>
        로그아웃
      </button>
      <button className="mr-[4rem]">
        <Link to="/boardwrite">글쓰기</Link>
      </button>
    </div>
  );
};

export default LoginSuccess;
