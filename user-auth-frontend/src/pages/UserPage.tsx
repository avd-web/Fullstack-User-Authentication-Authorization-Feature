import React, { useEffect, useState } from "react";
import Login from "../components/general/Login";
import GetUser from "../components/general/GetUser"; // Adjust the import path as necessary

export default function UserPage() {
  const [authenticatedUser, setAuthenticatedUser] = useState<string | null>(
    null
  );

  useEffect(() => {
    const token =
      sessionStorage.getItem("access_token") ||
      localStorage.getItem("access_token");
    setAuthenticatedUser(token);
  }, []);

  if (!authenticatedUser) {
    return (
      <>
        <Login />
      </>
    );
  } else {
    return (
      <>
        <GetUser authenticatedUser={authenticatedUser} />
      </>
    );
  }
}
