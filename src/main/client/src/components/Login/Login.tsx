import {isLoading} from "./LoginTs";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

export default function Login() {
    
    const navigate = useNavigate();
    const [buttonColor, setButtonColor] = useState("#FEE685");

    return (
        <div className="flex justify-center items-center min-h-screen p-8">
            <form style={{backgroundColor: '#FEE685'}} className="p-8 rounded-2xl shadow-md w-full max-w-3xl">
                <h1 className="text-2xl font-bold mb-6 text-center">Login</h1>

                <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                    <div>
                        <label htmlFor="email" className="block text-sm font-medium">E-mail</label>
                        <input
                            type="text"
                            id="nome"
                            placeholder="example@gmail.com"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="senha" className="block text-sm font-medium">Senha</label>
                        <input
                            type="password"
                            id="cpf"
                            placeholder="**********"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                </div>

                <div className="flex justify-between mt-6">

                    <button type="button" className="px-4 py-2 rounded-md" style={{ backgroundColor: buttonColor }} onClick={() => navigate("/cadastro")} onMouseEnter={() => setButtonColor("#FF9632")} onMouseLeave={() => setButtonColor("#FEE685")}>
                        Cadastrar
                    </button>
                    <button type="submit" style={{ backgroundColor: '#dc4435' }} className="text-white px-4 py-2 rounded-md">
                        {isLoading() ? "Entrando ..." : "Entrar"}
                    </button>
                </div>
            </form>
        </div>
    );
}
