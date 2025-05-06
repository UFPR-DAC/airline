// import { useState } from "react";

export default function Funcionario() {

    // const [buttonColor, setButtonColor] = useState("#FEE685");

    function GetEmail()
    {
        var email = document.getElementById("email") as HTMLInputElement;
        alert("Email: " + email.value);
    }

    return (
        <div className="flex justify-center items-center min-h-screen p-8">
            <form style={{backgroundColor: '#FEE685'}} className="bg-white p-8 rounded-2xl shadow-md w-full max-w-3xl">
                <h1 className="text-2xl font-bold mb-6 text-center">Cadastro</h1>

                <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
                    <div>
                        <label htmlFor="nome" className="block text-sm font-medium">Nome Completo</label>
                        <input
                            type="text"
                            id="nome"
                            placeholder="Nome Completo"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>

                    <div>
                        <label htmlFor="email" className="block text-sm font-medium">E-mail</label>
                        <input
                            type="email"
                            id="email"
                            placeholder="seuemail@email.com"
                            className="bg-white mt-1 w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-red-300"
                        />
                    </div>
                </div>

                <div className="flex justify-between mt-6">
                    {/* <button type="button" className="px-4 py-2 rounded-md" onClick={() => GetEmail() }  style={{ backgroundColor: buttonColor }} onMouseEnter={() => setButtonColor("#FF9632")} onMouseLeave={() => setButtonColor("#FEE685")}>Login</button> */}
                    <button type="submit" style={{ backgroundColor: '#dc4435' }} onClick={() => GetEmail() } className="text-white px-4 py-2 rounded-md">Cadastrar</button>
                </div>
            </form>
        </div>
    );
}

