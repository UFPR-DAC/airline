import FlightOption from '../../components/FlightOption/FlightOption'
import FlightParams from '../../components/FlightParams/FlightParams'
import { useSearch } from '../../contexts/FlightSearchContext'

export default function SearchResults() {
	const { searchResponse } = useSearch()
	if (!searchResponse || searchResponse.length === 0) {
		return <p>Nenhum voo corresponde à sua busca</p>
	}

	return (
		<>
			<FlightParams />
			<div className="flex flex-col">
				{searchResponse && searchResponse.map((flight) => <FlightOption key={flight.data} {...flight} />)}
			</div>
		</>
	)
}
