import Login from "./Login";
import { Link } from "react-router-dom";

import { LuMenuSquare } from "react-icons/lu";
import { useState } from "react";

export default function NavBar() {
  const [selected, setSelected] = useState(null);

  const toggle = (i: any) => {
    if (selected === i) {
      return setSelected(null);
    }

    setSelected(i);
  };

  return (
    <>
      <nav className="nav collapsible">
        <Link to="/" className="nav__brand">
          <img src="src/assets/images/logo.svg" alt="" />
        </Link>
        <LuMenuSquare className="icon icon--white nav__toggler" />

        {/* <ul className="list nav__list collapsible__content">
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
        </div> */}

        <div className="accordion">
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
        </div>
      </nav>
    </>
  );
}

const data = [
  {
    question: "question 1",
    answer: "test-answer1",
  },
];
