import { useState } from "react";
import FlightSearch from "../FlightSearch/FlightSearch";

export default function TabGroup() {
  const tabs = [{ label: "Buscar voo", component: FlightSearch }];
  const [activeTab, setActiveTab] = useState(0);
  const TabContent = tabs[activeTab].component;

  return (
    <div className="mt-10">
      <div className="flex">
        {tabs.map((tab, index) => (
          <button
            key={tab.label}
            onClick={() => setActiveTab(index)}
            className={`flex py-2 px-4 text-center font-medium rounded-t-2xl bg-amber-200 cursor-pointer ${
              activeTab === index
                ? "text-black"
                : "text-gray-500"
            }`}
          >
            {tab.label}
          </button>
        ))}
      </div>
      <div className="flex">
        <TabContent />
      </div>
    </div>
  );
}
