import { createContext, ReactNode, useContext, useEffect, useState } from 'react'
import { FlightSearchParams } from '../types/flightSearch'
import { FlightResults } from '../types/flight'

type SearchContextType = {
	searchPayload: FlightSearchParams | null
	searchResponse: FlightResults[] | null
	runSearch: (search: FlightSearchParams) => void
}

const FlightSearchContext = createContext<SearchContextType | undefined>(undefined)

type SearchProviderProps = {
	children: ReactNode
}

export const FlightSearchProvider = ({ children }: SearchProviderProps) => {
	const [searchPayload, setSearchPayload] = useState<FlightSearchParams | null>(null)
	const [searchResponse, setSearchResponse] = useState<FlightResults[] | null>(null)

	useEffect(() => {
		const storedPayload = localStorage.getItem('searchPayload')
		const storedResponse = localStorage.getItem('searchResponse')
		if (storedPayload) {
			setSearchPayload(JSON.parse(storedPayload))
		}
		if (storedResponse) {
			setSearchResponse(JSON.parse(storedResponse))
		}
	}, [])

	const runSearch = (params: FlightSearchParams) => {
		setSearchPayload(params)
		localStorage.setItem('searchPayload', JSON.stringify(params))
		const mockFlights = localStorage.getItem('mockFlights')
		if (!mockFlights) return
		const allFlights: FlightResults[] = JSON.parse(mockFlights)

		const matchingFlights = allFlights.filter(
			(flight) => flight.origem === params.origin && flight.destino === params.destination
		)

		if (matchingFlights.length > 0) {
			setSearchResponse(matchingFlights)
			localStorage.setItem('searchResponse', JSON.stringify(matchingFlights))
		} else {
			setSearchResponse(null)
			localStorage.removeItem('searchResponse')
		}
	}

	return (
		<FlightSearchContext.Provider value={{ searchPayload, searchResponse, runSearch }}>
			{children}
		</FlightSearchContext.Provider>
	)
}

export const useSearch = (): SearchContextType => {
	const context = useContext(FlightSearchContext)
	if (!context) {
		throw new Error('useSearch must be used within a SearchProvider')
	}
	return context
}
