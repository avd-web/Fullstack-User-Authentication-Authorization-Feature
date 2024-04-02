import "../../styles/Navigation.css";
import Login from "./Login";
import { Link } from "react-router-dom";

import { LuMenuSquare } from "react-icons/lu";

export default function Navigation() {
  return (
    <>
      <nav className="nav collapsible">
        <Link to="/" className="nav__brand">
          <img src="http://localhost:5174/src/images/logo.svg" alt="" />
        </Link>
        <LuMenuSquare className="icon icon--white nav__toggler" />

        <ul className="list nav__list collapsible__content">
          <li className="nav__item">
            <a href="#">Home</a>
          </li>
          <li className="nav__item">
            <a href="#">Products</a>
          </li>
          <li className="nav__item">
            <a href="#">About Us</a>
          </li>
          <li className="nav__item">
            <a href="#">Contact</a>
          </li>
          <li className="nav__item">
            <Login />
          </li>
        </ul>
      </nav>
    </>
  );
}
