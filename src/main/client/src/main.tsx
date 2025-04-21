import React from 'react'
import { StrictMode } from "react";
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import {BrowserRouter, Routes, Route} from "react-router";
import LoginComponent from "./pages/login/LoginComponent.tsx";
import ClientHome from "./pages/ClientHome";
import CadastroComponent from './pages/cadastro/CadastroComponent.tsx';
import HeaderComponent from './pages/header/HeaderComponent.tsx';
import ComprarMilhas from './components/ComprarMilhas/ComprarMilhas.tsx';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <BrowserRouter>
      <HeaderComponent />
          <Routes>
              <Route path="/" element={<ClientHome />}/>
              <Route path="login"  element={<LoginComponent />} />
              <Route path="cadastro" element={<CadastroComponent/>} />
              <Route path="comprar/milhas" element={<ComprarMilhas/>} />
          </Routes>
      </BrowserRouter>
  </StrictMode>,
)
