import { lazy, StrictMode } from "react";
import { createRoot } from 'react-dom/client'
import './index.css'
import {BrowserRouter, Routes, Route} from "react-router";
const Login = lazy(() => import('../src/pages/Login'));
const ClientHome = lazy(() => import('../src/pages/ClientHome'));
const SearchResults = lazy(() => import('../src/pages/SearchResults'));
import CadastroComponent from './pages/cadastro/CadastroComponent.tsx';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<ClientHome />}/>
              <Route path="login"  element={<Login />} />
              <Route path="cadastro" element={<CadastroComponent/>} />
              <Route path="search" element={<SearchResults />}/>
          </Routes>
      </BrowserRouter>
  </StrictMode>,
)
