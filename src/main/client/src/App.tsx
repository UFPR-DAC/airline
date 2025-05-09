import { BrowserRouter, Routes, Route } from 'react-router'
import './App.css'
import { lazy, useEffect } from 'react'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'
import { AuthProvider } from './contexts/AuthContext'
import { FlightSearchProvider } from './contexts/FlightSearchContext'
import { mockFlights } from './mocks/flights'
const Header = lazy(() => import('../src/components/Header/Header'))
const Signup = lazy(() => import('../src/pages/Signup/Signup'))
const ClientLogin = lazy(() => import('./pages/ClientLogin'))
const ClientHome = lazy(() => import('../src/pages/ClientHome'))
const ClientDashboard = lazy(() => import('../src/pages/ClientDashboard'))
const Reservations = lazy(() => import('../src/pages/Reservations'))
const SearchResults = lazy(() => import('../src/pages/SearchResults'))
const ComprarMilhas = lazy(() => import('./pages/Milhas/Milhas'))
const Funcionario = lazy(() => import('../src/pages/Funcionario'))
const FuncionarioCadastrar = lazy(
	() => import('../src/components/Funcionario/CadastrarFuncionario/CadastrarFuncionario')
)
const EditarFuncionario = lazy(() => import('../src/components/Funcionario/EditarFuncionario/EditarFuncionario'))
const queryClient = new QueryClient()

function App() {
	useEffect(() => {
		if (!localStorage.getItem('mockFlights')) {
			localStorage.setItem('mockFlights', JSON.stringify(mockFlights))
		}
	}, [])

	return (
		<AuthProvider>
			<FlightSearchProvider>
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
									<Route path="/cliente/:clientId/reservas" element={<Reservations />} />
									<Route path="/cliente/:clientId/milhas/comprar" element={<ComprarMilhas />} />
									<Route path="/funcionario" element={<Funcionario />} />
									<Route path="/funcionario/inserir" element={<FuncionarioCadastrar />} />
									<Route path="/funcionario/editar" element={<EditarFuncionario />} />
								</Routes>
							</main>
						</div>
					</QueryClientProvider>
				</BrowserRouter>
			</FlightSearchProvider>
		</AuthProvider>
	)
}

export default App
