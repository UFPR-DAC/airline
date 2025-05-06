import { BrowserRouter, Routes, Route } from 'react-router'
import './App.css'
import { lazy } from 'react'
import ComprarMilhasComponent from './pages/Milhas/Comprar/ComprarMilhasComponent'
import CadastroComponent from './pages/cadastro/CadastroComponent'
const Header = lazy(() => import('../src/components/Header/Header'))
const Signup = lazy(() => import('../src/pages/Signup/Signup'))
const ClientLogin = lazy(() => import('./pages/ClientLogin'))
const ClientHome = lazy(() => import('../src/pages/ClientHome'))
const ClientDashboard = lazy(() => import('../src/pages/ClientDashboard'))
const SearchResults = lazy(() => import('../src/pages/SearchResults'))

function App() {
	return (
		<BrowserRouter>
			<div className="flex flex-col h-screen">
				<Header />
				<main className="flex flex-col flex-grow overflow-auto items-center justify-center">
					<Routes>
						<Route path="/" element={<ClientHome />} />
						<Route path="/cadastro" element={<Signup />} />
						<Route path="/login" element={<ClientLogin />} />
						<Route path="/busca" element={<SearchResults />} />
						<Route path="/cliente/:clientId" element={<ClientDashboard />} />
						<Route path="/milhas/comprar" element={<ComprarMilhasComponent />} />
						<Route path="/cad" element={<CadastroComponent />} />
					</Routes>
				</main>
			</div>
		</BrowserRouter>
	)
}

export default App
