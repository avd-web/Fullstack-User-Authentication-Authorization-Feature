import Login from "./Login";
import { Link } from "react-router-dom";

import { LuMenuSquare } from "react-icons/lu";
import { useState } from "react";

export default function NavBar() {
  const [selected, setSelected] = useState(false);

  const toggle = () => {
    // if (selected) {
    //   return setSelected(null);
    // }

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
          <Link to="/" className="nav__brand">
            <img src="src/assets/images/logo.svg" alt="" />
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
              <a href="#">Contact</a>
            </li>
          </ul>
          <div className="nav__list collapsible__content">
            <Login />
          </div>
        </nav>
      </div>

      {/* <div className="accordion">
          {data.map((item, i) => (
            <div className="item">
              <div className="title" onClick={() => toggle(i)}>
                <h2>{item.question}</h2>
                <span>{selected === i ? "-" : "+"}</span>
              </div>
              <div className={selected === i ? "content show" : "content"}>
                {item.answer}
              </div>
            </div>
          ))}
        </div> */}

      {/* <nav className="nav collapsible">
        <div className="accordion">
          <div className="item">
            <div className="title" onClick={() => toggle()}>
              <Link to="/" className="nav__brand">
                <img src="src/assets/images/logo.svg" alt="" />
              </Link>
              <LuMenuSquare className="icon icon--white nav__toggler" />
            </div>
            <div className={selected ? "content show" : "content"}>
              <ul className="list nav__list collapsible__content">
                <li className="nav__item">
                  <a href="#">Hosting</a>
                </li>
                <li className="nav__item">
                  <a href="#">VPS</a>
                </li>
                <li className="nav__item">
                  <a href="#">Domain</a>
                </li>
              </ul>
            </div>
          </div>
        </div> */}
      {/* </nav> */}
    </>
  );
}

// const data = [
//   {
//     question: "question 1",
//     answer: "home",
//   },
// ];
