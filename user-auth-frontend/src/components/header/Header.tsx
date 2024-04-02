import Login from "../general/Login";
import Navigation from "../general/Navigation";
import "../../styles/Header.css";

export default function Header() {
  return (
    <header className="header">
      <Navigation />
      {/* <Login /> */}
    </header>
  );
}
