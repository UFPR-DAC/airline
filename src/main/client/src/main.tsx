import React from 'react'
import { StrictMode } from "react";
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import {BrowserRouter, Routes, Route} from "react-router";
import LoginComponent from "./pages/login/LoginComponent.tsx";
import ClientHome from "./pages/ClientHome";
import CadastroComponent from './pages/cadastro/CadastroComponent.tsx';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<ClientHome />}/>
              <Route path="login"  element={<LoginComponent />} />
              <Route path="cadastro" element={<CadastroComponent/>} />
          </Routes>
      </BrowserRouter>
  </StrictMode>,
)
