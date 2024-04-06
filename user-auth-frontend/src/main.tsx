import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Hero from "./blocks/Hero";
import UserPage from "./pages/UserPage";
import NavBar from "./components/general/NavBar";
import Footer from "./components/footer/Footer";

//Styles:
import "./styles/PageLayout.css";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <BrowserRouter>
      <NavBar />

      <Routes>
        <Route index element={<Hero />} />
        <Route path="/userpage" element={<UserPage />} />
      </Routes>

      <Footer />
    </BrowserRouter>
  </React.StrictMode>
);
