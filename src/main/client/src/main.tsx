import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App'

createRoot(document.getElementById('root')!).render(
	<StrictMode>
		<App />
	</StrictMode>
)

                <Routes>
                    <Route path="/" element={<ClientHome />} />
                    <Route path="login" element={<Login />} />
                    <Route path="cadastro" element={<CadastroComponent />} />
                    <Route path="search" element={<SearchResults />} />
                    <Route path="milhas/comprar" element={<ComprarMilhasComponent />} />
                </Routes>
            </div>
        </BrowserRouter>
    </StrictMode>
);