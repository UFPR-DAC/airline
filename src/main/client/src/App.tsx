import { BrowserRouter, Routes, Route } from 'react-router'
import './App.css'
import { lazy } from 'react'
import { QueryClient, QueryClientProvider, useQueryClient } from '@tanstack/react-query'
const Header = lazy(() => import('../src/components/Header/Header'))
const Signup = lazy(() => import('../src/pages/Signup/Signup'))
const ClientLogin = lazy(() => import('./pages/ClientLogin'))
const ClientHome = lazy(() => import('../src/pages/ClientHome'))
const ClientDashboard = lazy(() => import('../src/pages/ClientDashboard'))
const SearchResults = lazy(() => import('../src/pages/SearchResults'))
const ComprarMilhas = lazy(() => import('./pages/Milhas/Milhas'))
const Funcionario = lazy(() => import('../src/pages/Funcionario'))	
const FuncionarioCadastrar = lazy(() => import('../src/components/Funcionario/CadastrarFuncionario/CadastrarFuncionario'))
const EditarFuncionario = lazy(() => import('../src/components/Funcionario/EditarFuncionario/EditarFuncionario'))
const queryClient = new QueryClient()

function App() {
	return (
		<BrowserRouter>
			<QueryClientProvider client={queryClient}>
				<div className="flex flex-col h-screen">
					<Header />
					<main className="flex flex-col flex-grow overflow-auto items-center justify-center">
						<Routes>
							<Route path="/" element={<ClientHome />} />
							<Route path="/cadastro" element={<Signup />} />
							<Route path="/login" element={<ClientLogin />} />
							<Route path="/busca" element={<SearchResults />} />
							<Route path="/cliente/:clientId" element={<ClientDashboard />} />
							<Route path="/milhas/comprar" element={<ComprarMilhas />} />
							<Route path="/funcionario" element={<Funcionario/>} />
						<Route path="/funcionario/inserir" element={<FuncionarioCadastrar/>} />
						<Route path="/funcionario/editar" element={<EditarFuncionario/>} />
						</Routes>
					</main>
				</div>
			</QueryClientProvider>
		</BrowserRouter>
	)
}

export default App
