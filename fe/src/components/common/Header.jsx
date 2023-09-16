import Nav from "./Nav";
import TitleLogo from "./TitleLogo";
import React from "react";
import "./common.css";

const Header = () => {
  return (
    <div>
      <header className="header-container">
        <TitleLogo />
        <Nav />
      </header>
    </div>
  );
};

export default Header;
