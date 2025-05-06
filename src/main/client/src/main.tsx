import { lazy, StrictMode } from "react";
import { createRoot } from 'react-dom/client';
import './index.css';
import { BrowserRouter, Routes, Route } from "react-router";
const Login = lazy(() => import('../src/pages/Login'));
const ClientHome = lazy(() => import('../src/pages/ClientHome'));
const SearchResults = lazy(() => import('../src/pages/SearchResults'));
import CadastroComponent from './pages/cadastro/CadastroComponent.tsx';
import HeaderComponent from './pages/header/HeaderComponent.tsx';
import ComprarMilhasComponent from './pages/Milhas/Comprar/ComprarMilhasComponent.tsx';
import FuncionarioComponent from "./pages/Funcionario/index.tsx";

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <BrowserRouter>
            <div className="flex flex-col h-screen">
                <div className="pt-16">
                    <HeaderComponent />
                </div>

                <Routes>
                    <Route path="/" element={<ClientHome />} />
                    <Route path="login" element={<Login />} />
                    <Route path="cadastro" element={<CadastroComponent />} />
                    <Route path="search" element={<SearchResults />} />
                    <Route path="milhas/comprar" element={<ComprarMilhasComponent />} />
                    <Route path="funcionarios" element={<FuncionarioComponent />} /> 
                </Routes>
            </div>
        </BrowserRouter>
    </StrictMode>
);