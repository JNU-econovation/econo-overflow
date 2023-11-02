import React, { useState, useEffect } from "react";

const LoginReg = ({ isValidEmail }) => {
  const [emailReg, setEmailReg] = useState("");

  useEffect(() => {
    if (isValidEmail) {
      setEmailReg("");
    }
  }, [isValidEmail]);

  return <div>{!isValidEmail && <p className="invalid-email-message"></p>}</div>;
};

export default LoginReg;
