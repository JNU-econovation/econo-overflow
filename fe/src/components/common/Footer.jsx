import React from "react";
import FooterIntro from "./FooterIntro";
import FooterContact from "./FooterContact";
import FooterLink from "./FooterLink";
import FooterSite from "./FooterSite";

const Footer = () => {
  return (
    <div>
      <div className="flex justify-center flex-row items-start fixed bottom-0 w-[100%] bg-[#5F5F5F] text-white">
        <FooterIntro />
        <FooterContact />
        <FooterLink />
        <FooterSite />
      </div>
    </div>
  );
};

export default Footer;
