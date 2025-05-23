import { useState } from 'react'
import FlightSearch from '../FlightSearch/FlightSearch'
import Checkin from '../Checkin/Checkin'

export default function TabGroup() {
	const tabs = [
		{ label: 'Buscar voo', component: FlightSearch },
		{ label: 'Fazer check-in', component: Checkin },
	]
	const [activeTab, setActiveTab] = useState(0)
	const TabContent = tabs[activeTab].component

	return (
		<div className="mt-10">
			<div className="flex">
				{tabs.map((tab, index) => (
					<button
						key={tab.label}
						onClick={() => setActiveTab(index)}
						className={`flex w-60 py-4 px-6 text-center justify-center font-medium rounded-t-2xl bg-amber-200 cursor-pointer text-black 
              hover:underline
              ${activeTab === index ? 'bg-amber-200' : 'bg-white'}`}
					>
						{tab.label}
					</button>
				))}
			</div>
			<div className="flex">
				<TabContent />
			</div>
		</div>
	)
}
