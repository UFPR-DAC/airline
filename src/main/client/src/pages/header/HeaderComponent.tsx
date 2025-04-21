import { useNavigate } from "react-router-dom";

export default function HeaderComponent() {
    const navigate = useNavigate();

    return (
        //fixed top-0 z-50 
        <nav className="fixed top-0 z-50 flex items-center h-16 w-full bg-gradient-to-r from-purple-300 via-yellow-300 to-red-500 text-white font-semibold">
            <div className="max-w-screen-xl flex items-center p-3 h-16">

                <div className="flex items-center space-x-3 mr-10">
                    <img className="w-15" src="images/solis192.png" alt="" />
                    <label className="" htmlFor="">Companhia Aérea SOLIS</label>
                </div>

                <div className="flex items-center space-x-10 mr-10">
                    <a href="/" className="hover:underline">Home</a>
                    <a href="#about" className="hover:underline">Sobre</a>
                    <a href="#contact" className="hover:underline">Contato</a>
                </div>

                <div className="absolute right-0 flex items-center space-x-3 mr-10">
                    <button
                        className="bg-white px-4 py-2 rounded hover:bg-purple-100"
                        onClick={() => navigate("/login")}
                    >
                        <label htmlFor="" className="text-purple-500">Login</label>
                    </button>
                </div>
            </div>
        </nav>
    );
}