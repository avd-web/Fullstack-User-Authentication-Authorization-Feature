import { useState } from "react";

export default function Login() {
  const [auth, setAuth] = useState(sessionStorage.getItem("access_token"));

  if (!auth) {
    return (
      <>
        <div>
          <p>Login</p>
        </div>
      </>
    );
  }
}
