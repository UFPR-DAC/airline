import { useState, useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowDown, faArrowUp } from "@fortawesome/free-solid-svg-icons";

type Coluna = {
  campo: string;
  titulo: string;
};

type Botao = {
  body: string;
  class: string;
  action: (item: any) => void;
};

type Props = {
  colunas: Coluna[];
  dados: any[];
  buttons: Botao[];
};

export default function Tabela({ colunas, dados, buttons }: Props) {
  const [filter, setFilter] = useState("");
  const [itensPorPagina, setItensPorPagina] = useState(5);
  const [paginaAtual, setPaginaAtual] = useState(1);
  const [sortedColumn, setSortedColumn] = useState<string | null>(null);
  const [sortDirection, setSortDirection] = useState<"asc" | "desc">("asc");

  const filtrarDados = () =>
    dados.filter((item) =>
      colunas.some((col) =>
        String(item[col.campo]).toLowerCase().includes(filter.toLowerCase())
      )
    );

  const ordenar = (dados: any[]) => {
    if (!sortedColumn) return dados;
    return [...dados].sort((a, b) => {
      const valA = a[sortedColumn];
      const valB = b[sortedColumn];
      if (valA < valB) return sortDirection === "asc" ? -1 : 1;
      if (valA > valB) return sortDirection === "asc" ? 1 : -1;
      return 0;
    });
  };

  const dadosFiltrados = ordenar(filtrarDados());
  const totalPaginas = Math.ceil(dadosFiltrados.length / itensPorPagina);
  const dadosPaginados = dadosFiltrados.slice(
    (paginaAtual - 1) * itensPorPagina,
    paginaAtual * itensPorPagina
  );

  const sortByColumn = (campo: string) => {
    if (sortedColumn === campo) {
      setSortDirection(sortDirection === "asc" ? "desc" : "asc");
    } else {
      setSortedColumn(campo);
      setSortDirection("asc");
    }
  };

  useEffect(() => {
    setPaginaAtual(1);
  }, [filter, itensPorPagina]);

  return (
    <div className="sm:px-6 w-full">
      <div className="bg-white dark:bg-gray-900 py-4 md:py-7 px-4 md:px-8 xl:px-10">
        <div className="sm:flex items-center justify-between">
          <div className="relative mb-4 sm:mb-0 w-full sm:max-w-xs">
            <input
              type="text"
              placeholder="Pesquisar..."
              value={filter}
              onChange={(e) => setFilter(e.target.value)}
              className="pl-10 pr-4 py-2 w-full border rounded-md focus:outline-none focus:ring-2 focus:ring-indigo-600 border-gray-100"
            />
            <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
              <svg className="h-5 w-5 text-gray-500" viewBox="0 0 20 20" fill="currentColor">
                <path fillRule="evenodd" d="M12.9 14.32a8 8 0 111.41-1.41l4.39 4.39a1 1 0 01-1.42 1.42l-4.39-4.39zM8 14a6 6 0 100-12 6 6 0 000 12z" clipRule="evenodd" />
              </svg>
            </div>
          </div>

          <div className="flex items-center">
            <select
              value={itensPorPagina}
              onChange={(e) => setItensPorPagina(Number(e.target.value))}
              className="ml-2 border bg-transparent text-sm text-gray-700 dark:text-gray-200 rounded"
            >
              <option value={5}>5</option>
              <option value={10}>10</option>
              <option value={25}>25</option>
            </select>
            <p className="ml-2 text-gray-600 dark:text-gray-200 text-sm">Registros por página</p>
          </div>
        </div>

        <div className="mt-7 overflow-x-auto">
          <table className="w-full whitespace-nowrap text-gray-900 dark:text-white">
            <thead>
              <tr className="h-16 border border-gray-100 rounded">
                {colunas.map((coluna) => (
                  <th
                    key={coluna.campo}
                    className="cursor-pointer max-w-48"
                    onClick={() => sortByColumn(coluna.campo)}
                  >
                    {coluna.titulo}
                    <FontAwesomeIcon
                      icon={faArrowDown}
                      className={`ml-2 opacity-45 ${sortedColumn === coluna.campo && sortDirection === "asc" ? "text-indigo-600" : ""}`}
                    />
                    <FontAwesomeIcon
                      icon={faArrowUp}
                      className={`ml-1 opacity-45 ${sortedColumn === coluna.campo && sortDirection === "desc" ? "text-indigo-600" : ""}`}
                    />
                  </th>
                ))}
                {buttons.length > 0 && <th>AÇÃO</th>}
              </tr>
            </thead>
            <tbody>
              {dadosPaginados.length > 0 ? (
                dadosPaginados.map((item, idx) => (
                  <tr key={idx} className="h-16 border border-gray-100 rounded text-center">
                    {colunas.map((coluna) => (
                      <td key={coluna.campo}>{item[coluna.campo]}</td>
                    ))}
                    {buttons.length > 0 && (
                      <td>
                        {buttons.map((btn, i) => (
                          <button
                            key={i}
                            className={`mx-2 ${btn.class}`}
                            onClick={() => btn.action(item)}
                            dangerouslySetInnerHTML={{ __html: btn.body }}
                          />
                        ))}
                      </td>
                    )}
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan={colunas.length + (buttons.length > 0 ? 1 : 0)} className="text-center py-4">
                    Nenhum registro encontrado
                  </td>
                </tr>
              )}
            </tbody>
          </table>

          <div className="flex justify-between items-center mt-4">
            <button
              onClick={() => setPaginaAtual((p) => Math.max(1, p - 1))}
              disabled={paginaAtual === 1}
              className="px-4 py-2 bg-gray-300 rounded-md disabled:opacity-50"
            >
              Anterior
            </button>
            <span className="dark:text-white">
              Página {paginaAtual} de {totalPaginas}
            </span>
            <button
              onClick={() => setPaginaAtual((p) => Math.min(totalPaginas, p + 1))}
              disabled={paginaAtual === totalPaginas}
              className="px-4 py-2 bg-gray-300 rounded-md disabled:opacity-50"
            >
              Próximo
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
