import { useState } from "react";
import { getYearFromNowMonths, getDaysForMonth } from "../../utils/date/calendar.js";

export default function FlightParams() {
  const [isDatepickerOpen, setIsDatepickerOpen] = useState(false);
  const [selectedDate, setSelectedDate] = useState<Date | null>(null);
  const months = getYearFromNowMonths();

  const handleDatepicker = () => {
    setIsDatepickerOpen(true);
  }
  
    return (
      <div className="flex flex-col gap-2">
      <div className="flex gap-4 bg-amber-200 p-8 rounded-2xl rounded-tl-none">
        <div className="flex">
          <div className="flex items-center rounded-l-md outline-1 outline-gray-300 bg-white pl-3 has-[input:focus-within]:outline-2 has-[input:focus-within]:-outline-offset-2 has-[input:focus-within]:outline-green-600">
            <label
              htmlFor="origin"
              className="block text-sm/6 font-medium text-gray-900"
            >
              Origem
            </label>
            <div className="m-2">
              <div className="flex items-center">
                <div className="shrink-0 text-base text-gray-500 select-none sm:text-sm/6" />
                <input
                  id="origin"
                  name="origin"
                  type="text"
                  value="Curitiba (CWB)"
                  className="block min-w-0 grow py-1.5 pr-3 pl-1 text-base text-gray-900 placeholder:text-gray-400 focus:outline-none sm:text-sm/6"
                />
              </div>
            </div>
          </div>
          <div className="flex items-center rounded-r-md outline-1 outline-gray-300 bg-white pl-3 has-[input:focus-within]:outline-2 has-[input:focus-within]:-outline-offset-2 has-[input:focus-within]:outline-green-600">
            <label
              htmlFor="origin"
              className="block text-sm/6 font-medium text-gray-900"
            >
              Destino
            </label>
            <div className="m-2">
              <div className="flex items-center">
                <div className="shrink-0 text-base text-gray-500 select-none sm:text-sm/6" />
                <input
                  id="destination"
                  name="destination"
                  type="text"
                  value="São Paulo (GRU)"
                  className="block min-w-0 grow py-1.5 pr-3 pl-1 text-base text-gray-900 placeholder:text-gray-400 focus:outline-none sm:text-sm/6"
                />
              </div>
            </div>
          </div>
        </div>
        <div className="flex items-center rounded-md outline-1 outline-gray-300 bg-white pl-3 has-[input:focus-within]:outline-2 has-[input:focus-within]:-outline-offset-2 has-[input:focus-within]:outline-green-600">
          <label
            htmlFor="origin"
            className="block text-sm/6 font-medium text-gray-900"
          >
            Datas
          </label>
          <div className="m-2">
            <div className="flex items-center">
              <div className="shrink-0 text-base text-gray-500 select-none sm:text-sm/6" />
                <input
                id="date"
                name="date"
                type="text"
                value="10/04/2025 - 17/04/2025"
                onClick={handleDatepicker}
                className="block min-w-0 grow py-1.5 pr-3 pl-1 text-base text-gray-900 placeholder:text-gray-400 focus:outline-none sm:text-sm/6"
              />
            </div>
          </div>
        </div>
        <button className="cursor-pointer text-white bg-green-600 p-4 rounded-full">Pesquisar</button>
      </div>
      {isDatepickerOpen && <div className="max-h-[80vh] overflow-y-auto space-y-8 bg-amber-200 p-8 rounded-2xl">
      {months.map((monthDate, index) => {
        const year = monthDate.getFullYear();
        const month = monthDate.getMonth();
        const days = getDaysForMonth(year, month);

        return (
          <div key={index}>
            <h3 className="text-lg font-semibold text-gray-700 mb-2">
              {monthDate.toLocaleString('default', { month: 'long' })} {year}
            </h3>

            <div className="grid grid-cols-7 text-xs text-gray-400 mb-1">
              {['S', 'T', 'Q', 'Q', 'S', 'S', 'D'].map((d, i) => (
                <div key={i} className="text-center">{d}</div>
              ))}
            </div>

            <div className="grid grid-cols-7 gap-y-1">
              {days.map((day, i) => (
                <div key={i} className="text-center h-8">
                  {day ? (
                    <button
                      className=
                        {'w-8 h-8 rounded-full flex items-center justify-center text-sm' +
                        (selectedDate?.toDateString() === day.toDateString()
                          ? 'bg-blue-600 text-white'
                          : 'text-gray-700 hover:bg-blue-100'
                      )}
                      onClick={() => setSelectedDate(day)}
                    >
                      {day.getDate()}
                    </button>
                  ) : (
                    <div className="w-8 h-8" />
                  )}
                </div>
              ))}
            </div>
          </div>
        );
      })}
    </div>}
      </div>
    );
  }
  