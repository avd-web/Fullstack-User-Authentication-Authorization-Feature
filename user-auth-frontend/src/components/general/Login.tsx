import React, { useState, useEffect } from "react";
import axios from "axios"; // Assuming you use axios for API calls

import "../../styles/Login.css";

// Interface for User object (optional, but improves type safety)
interface User {
  email: string;
  password: string;
}

export default function Login(): JSX.Element | null {
  const [auth, setAuth] = useState<string | null>(
    sessionStorage.getItem("access_token")
  );
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

      sessionStorage.setItem("access_token", authResp.data.access_token);
      sessionStorage.setItem("user_email", email);
      setAuth(sessionStorage.getItem("access_token"));
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
    setAuth(sessionStorage.getItem("access_token"));
  }, []); // Empty dependency array for one-time check

  return !auth ? ( // Render login form only if not authenticated
    <form className="login" onSubmit={handleSubmit}>
      <div>
        <label htmlFor="email" id="email-label">
          E-mail:
        </label>
        <input
          type="text"
          id="email"
          required
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>
      <div>
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
      <button className="btn btn--stretched" type="submit">
        login
      </button>
    </form>
  ) : null;
}
