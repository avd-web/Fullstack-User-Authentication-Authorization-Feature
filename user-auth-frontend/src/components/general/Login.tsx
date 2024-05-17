import React, { useState, useEffect } from "react";
import axios from "axios";

import "../../styles/Login.css";

import { IoMdLogIn } from "react-icons/io";

// Interface for User object (optional, but improves type safety)
interface User {
  email: string;
  password: string;
}

export default function Login(): JSX.Element | null {
  let authenticatedUser = null;
  if (sessionStorage.getItem("access_token")) {
    authenticatedUser = sessionStorage.getItem("access_token");
  } else if (localStorage.getItem("access_token")) {
    authenticatedUser = localStorage.getItem("access_token");
  }
  const [auth, setAuth] = useState<string | null>(authenticatedUser);

  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const authBody: User = { email, password }; // Use the User interface

    try {
      console.log(authBody);
      const authResp = await axios.post(
        "http://localhost:8080/api/v1/auth/authenticate",
        authBody
      );

      localStorage.setItem("access_token", authResp.data.access_token);
      localStorage.setItem("user_email", email);

      sessionStorage.setItem("access_token", authResp.data.access_token);
      sessionStorage.setItem("user_email", email);
      setAuth(sessionStorage.getItem("access_token"));

      window.location.reload();
    } catch (error) {
      console.error("Login error:", error);
      // Handle login errors (optional)
    } finally {
      emptyForm();
    }
  };

  const emptyForm = () => {
    setEmail(""); // Clear form fields instead of reload
    setPassword("");
  };

  useEffect(() => {
    // Check auth on component mount
    if (sessionStorage.getItem("access_token")) {
      setAuth(sessionStorage.getItem("access_token"));
    } else if (localStorage.getItem("access_token")) {
      setAuth(localStorage.getItem("access_token"));
    }
  }, []); // Empty dependency array for one-time check

  const logout = () => {
    localStorage.removeItem("access_token");
    localStorage.removeItem("user_email");
    sessionStorage.removeItem("access_token");
    sessionStorage.removeItem("user_email");
    setAuth(null);
    window.location.reload();
  };

  return !auth ? ( // Render login form only if not authenticated
    <form className="login--form nav__item" onSubmit={handleSubmit}>
      <div className="login">
        <div className="login__field">
          <label htmlFor="email" id="email-label">
            Email:
          </label>
          <input
            type="text"
            id="email"
            required
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div className="login__field">
          <label htmlFor="password" id="password-label">
            Password:
          </label>
          <input
            type="password"
            id="password"
            required
            value={password}
            onChange={(ev) => setPassword(ev.target.value)}
          />
        </div>
      </div>
      <button className="btn--small" type="submit">
        <IoMdLogIn className="icon--small" />
      </button>
    </form>
  ) : (
    // add getUser()
    <button className="btn--small" onClick={logout}>
      <IoMdLogIn className="icon--small" />
    </button>
  );
}
