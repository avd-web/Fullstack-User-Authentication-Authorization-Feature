import { IoMdLogIn } from "react-icons/io";

export default function Logout(): JSX.Element {
  const logout = () => {
    localStorage.removeItem("access_token");
    localStorage.removeItem("user_email");
    sessionStorage.removeItem("access_token");
    sessionStorage.removeItem("user_email");
    window.location.reload();
  };

  return (
    <button className="btn--small" onClick={logout}>
      <IoMdLogIn className="icon--small" />
    </button>
  );
}
