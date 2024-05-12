import { useEffect, useState } from "react";
import Login from "../components/general/Login";
import axios, { AxiosError } from "axios";

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

  const getUser = async () => {
    console.log(authenticatedUser);
    try {
      if (!authenticatedUser) {
        throw new Error("No authenticated user found");
      }

      const response = await axios.get(
        "http://localhost:8080/api/v1/demo-user/user",
        {
          headers: {
            Authorization: `Bearer ${authenticatedUser}`,
          },
        }
      );
      console.log("User data:", response.data);
    } catch (error) {
      if (axios.isAxiosError(error)) {
        const axiosError = error as AxiosError;
        if (axiosError.response && axiosError.response.status === 401) {
          console.error("Unauthorized: Please login again.");
          // Handle unauthorized access, maybe redirect to login page
          return;
        }
      }
      console.error("getUserError:", error);
    }
  };

  if (!authenticatedUser) {
    return (
      <>
        <Login />
      </>
    );
  } else {
    return (
      <>
        <button onClick={getUser}>Get User Data</button>
      </>
    );
  }
}
