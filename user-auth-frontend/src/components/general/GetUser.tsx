import React, { useEffect, useState } from "react";
import axios from "axios";

const GetUser: React.FC<{ authenticatedUser: string | null }> = ({
  authenticatedUser,
}) => {
  const [userData, setUserData] = useState<any>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/demo-user/user",
          {
            headers: {
              Authorization: `Bearer ${authenticatedUser}`,
            },
          }
        );
        setUserData(response.data);
      } catch (error) {
        if (axios.isAxiosError(error) && error.response?.status === 401) {
          console.error("Unauthorized: Please login again.");
        } else {
          console.error("getUserError:", error);
        }
      } finally {
        setLoading(false);
      }
    };

    fetchUser();
  }, [authenticatedUser]);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (!userData) {
    return <div>No user data available</div>;
  }

  return (
    <div>
      <h1>User Data</h1>
      <pre>{JSON.stringify(userData, null, 2)}</pre>
    </div>
  );
};

export default GetUser;
