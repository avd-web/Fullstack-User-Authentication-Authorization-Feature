import Login from "../general/Login";
import Navigation from "../general/Navigation";
import HeaderLogo from "./HeaderLogo";
import "../../styles/Header.css";

export default function Header() {
  return (
    <header className="header">
      <HeaderLogo />
      <Navigation />
      <Login />
    </header>
  );
}
