import Form from "react-bootstrap/Form";
import { useRef, useState, useEffect } from "react";
import instance from "../../api/instance";

const SignUpInput = ({
  nickname,
  email,
  password,
  passwordConfirm,
  setEmail,
  setNickName,
  setPassword,
  setPasswordConfirm,
}) => {
  const btnRef = useRef(true);
  const [canSignupEmail, setCanSignupEmail] = useState(null);
  const [canSignupPassword, setCanSignupPassword] = useState(false);
  const [btnDisable, setBtnDisable] = useState(true);
  const emailPattern =
    /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.[A-Za-z]{3}$/;
  const passwordPattern = /^(?=.*[A-Z])(?=.*[!@#$%^&*()_+])(?=.{8,})$/;
  const isValidEmail = emailPattern.test(email);

  useEffect(() => {
    if (isValidEmail) {
      setCanSignupEmail(true);
    } else {
      setCanSignupEmail(false);
    }
  }, [email, isValidEmail]);

  useEffect(() => {
    if (canSignupEmail === true) {
      btnRef.current.disabled = false;
    } else {
      btnRef.current.disabled = true;
    }
  }, [canSignupEmail]);

  const checkDuplicateEmail = (e) => {
    e.preventDefault();
    instance.post("/users/checkemail", { email }).then((res) => {
      if (res.data.message === "해당 이메일 사용 가능") {
        setCanSignupEmail(true);
        alert("사용 가능한 이메일입니다.");
      } else {
        setCanSignupEmail(false);
        alert("중복된 이메일입니다.");
      }
    });
  };

  return (
    <div>
      <Form.Group className="mb-3" controlId="formBasicNickname">
        <Form.Label>Nickname</Form.Label>
        <Form.Control
          type="text"
          placeholder="Nickname"
          value={nickname}
          onChange={(e) => setNickName(e.target.value)}
        />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label>Email Address</Form.Label>
        <button
          className="duplicate-check"
          type="button"
          onClick={checkDuplicateEmail}
          ref={btnRef}
        >
          중복 확인
        </button>
        <Form.Control
          type="text"
          placeholder="Enter Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Password</Form.Label>
        <Form.Control
          type="password"
          placeholder="Enter Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Confirm Password</Form.Label>
        <Form.Control
          type="password"
          placeholder="Confirm Password"
          value={passwordConfirm}
          onChange={(e) => setPasswordConfirm(e.target.value)}
        />
      </Form.Group>
    </div>
  );
};

export default SignUpInput;
