import Login from "../general/Login";
import Navigation from "../general/Navigation";
import HeaderLogo from "./HeaderLogo";

export default function Header() {
  return (
    <header>
      <HeaderLogo />
      <Navigation />
      <Login />
    </header>
  );
}
