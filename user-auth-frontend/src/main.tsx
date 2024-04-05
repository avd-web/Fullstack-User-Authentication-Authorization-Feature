import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import HomePage from "./pages/HomePage";
import UserPage from "./pages/UserPage";
import Navigation from "./components/general/Navigation";
import Footer from "./components/footer/Footer";

//Styles:
import "./styles/Page-layout.css";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <BrowserRouter>
      <Navigation />

      <Routes>
        <Route index element={<HomePage />} />
        <Route path="/userpage" element={<UserPage />} />
      </Routes>

      <Footer />
    </BrowserRouter>
  </React.StrictMode>
);
