export default function FlightSearch() {
    return (
      <>
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
                  placeholder="Curitiba (CWB)"
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
                  placeholder="São Paulo (GRU)"
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
                placeholder="10/04/2025 - 17/04/2025"
                className="block min-w-0 grow py-1.5 pr-3 pl-1 text-base text-gray-900 placeholder:text-gray-400 focus:outline-none sm:text-sm/6"
              />
            </div>
          </div>
        </div>
        <button className="text-white bg-green-600 p-4 rounded-full">Pesquisar</button>
      </div>
      </>
    );
  }
  