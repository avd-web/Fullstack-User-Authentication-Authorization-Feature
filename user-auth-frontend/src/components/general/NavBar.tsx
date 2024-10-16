import Login from "./Login";
import { Link } from "react-router-dom";

import { LuMenuSquare } from "react-icons/lu";
import { useState } from "react";

export default function NavBar() {
  const [selected, setSelected] = useState(false);

  const toggle = () => {
    setSelected(!selected);
  };

  return (
    <>
      <div onClick={() => toggle()}>
        <nav
          className={
            selected
              ? "nav collapsible collapsible--expanded"
              : "nav collapsible"
          }
        >
          <div className="nav__brand">
            <Link to="/">
              <span style={{ fontSize: "100px" }}>&#9708;</span>
              {/* <img src="src/assets/images/logo.svg" alt="" /> */}
            </Link>
          </div>

          <LuMenuSquare className="icon icon--white nav__toggler" />

          <ul className="nav list nav__list collapsible__content">
            <li className="nav__item">
              <a href="#">Home</a>
            </li>
            <li className="nav__item">
              <a href="#">Products</a>
            </li>
            <li className="nav__item">
              <a href="#">Contact</a>
            </li>
          </ul>

          <div className="nav nav__list collapsible__content">
            <Login />
          </div>
        </nav>
      </div>
    </>
  );
}
