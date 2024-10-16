import React, { useState, useEffect } from "react";
import axios from "axios";
import "../../styles/Login.css";
import { IoMdLogIn } from "react-icons/io";
import Logout from "./Logout";
import GetUser from "./GetUser";

// Interface for User object (optional, but improves type safety)
interface User {
  email: string;
  password: string;
}

export default function Login(): JSX.Element | null {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [authenticatedUser, setAuthenticatedUser] = useState<string | null>(
    null
  );

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

      window.location.reload(); // Reload the page to update authentication status
    } catch (error) {
      console.error("Login error:", error);
      // Handle login errors (optional)
    } finally {
      emptyForm();
    }
  };

  const emptyForm = () => {
    setEmail("");
    setPassword("");
  };

  useEffect(() => {
    // Check auth on component mount
    const token =
      sessionStorage.getItem("access_token") ||
      localStorage.getItem("access_token");
    setAuthenticatedUser(token);
    if (token) {
      // Perform any necessary actions if authenticated
      console.log("User is authenticated");
    }
  }, []); // Empty dependency array for one-time check

  return !authenticatedUser ? (
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
    <>
      <span>Logged in as:</span>
      {/* <GetUser authenticatedUser={authenticatedUser} /> */}
      <Logout />
    </>
  );
}
