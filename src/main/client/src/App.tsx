import { BrowserRouter, Routes, Route } from 'react-router'
import './App.css'
import { lazy } from 'react'
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
						<Route path="/pesquisa" element={<SearchResults />} />
						<Route path="/cliente/:clientId" element={<ClientDashboard />} />
					</Routes>
				</main>
			</div>
		</BrowserRouter>
	)
}

export default App
