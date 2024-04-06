import { FaSearch } from "react-icons/fa";

export default function Hero() {
  return (
    <>
      <section className="container block block--dark block--skewed-left hero">
        <div className="grid grid--1x2">
          <header className="block__header hero-content">
            <h1 className="block__heading" data-aos="fade-right">
              This is a Hero-Heading!
            </h1>
            <p className="hero__tagline">This is the message Hero-Tagline.</p>
            <a href="" className="btn btn--accent btn--stretched">
              Get Started
            </a>
          </header>
          <picture data-aos="zoom-in">
            <img
              className="hero__image"
              src="src/assets/images/banner.png"
              alt=""
            />
          </picture>
        </div>
      </section>

      <section data-aos="fade-up" className="block container block-domain">
        <header className="block__header">
          <h2>This is a Block-Heading!</h2>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti,
            exercitationem?
          </p>
        </header>
        <div className="input-group">
          <input
            type="text"
            className="input"
            placeholder="Enter domain name here..."
          />
          <button className="btn btn--accent">
            <FaSearch className="icon icon--white" />
            Search
          </button>
        </div>

        <ul className="list block-domain__prices">
          <li>
            <span className="badge badge--secondary">.com $9</span>
          </li>
          <li>.sg $10</li>
          <li>.space $11</li>
          <li>.info $14</li>
          <li>.net $10</li>
          <li>.xyz $10</li>
        </ul>
      </section>
    </>
  );
}
